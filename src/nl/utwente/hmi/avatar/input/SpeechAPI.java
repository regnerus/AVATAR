package nl.utwente.hmi.avatar.input;


import ee.ioc.phon.netspeechapi.duplex.DuplexRecognitionSession;
import ee.ioc.phon.netspeechapi.duplex.RecognitionEvent;
import ee.ioc.phon.netspeechapi.duplex.RecognitionEventListener;
import ee.ioc.phon.netspeechapi.duplex.WsDuplexRecognitionSession;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface WorkerCountInterface
{
    public abstract void notifyWorkerCount(int count);
}

class WorkerCountClient extends WebSocketClient
{

    private WorkerCountInterface handler;

    public WorkerCountClient(URI serverURI, WorkerCountInterface handler)
    {
        super(serverURI);
        this.handler = handler;
    }

    @Override
    public void onClose(int code, String reason, boolean remote)
    {
    }

    @Override
    public void onError(Exception arg0)
    {
        System.err.println("****** Exception: " + arg0);
    }

    @Override
    public void onMessage(String msg)
    {
        Object obj = JSONValue.parse(msg);

        if (obj != null)
        {
            JSONObject jsonObj = (JSONObject) obj;
            if (jsonObj.containsKey("num_workers_available"))
            {
                Object lo = jsonObj.get("num_workers_available");
                int n_workers = ((Long) lo).intValue();
                this.handler.notifyWorkerCount(n_workers);
            }
        }
    }

    @Override
    public void onOpen(ServerHandshake arg0)
    {
    }

}


public class SpeechAPI extends Input
{
    private static final long serialVersionUID = 1L;

    boolean stopCapture = false;
    static boolean sendPong = false;
    static boolean bufferFilled = false;
    static boolean workersAvailable = false;

    static ByteArrayOutputStream byteArrayOutputStream;
    AudioFormat audioFormat;
    TargetDataLine targetDataLine;
    static AudioInputStream audioInputStream;

    private static String DEFAULT_WS_URL;
    private static String DEFAULT_WS_STATUS_URL;

    static class RecognitionEventAccumulator implements RecognitionEventListener, WorkerCountInterface
    {

        private List<RecognitionEvent> events = new ArrayList<>();
        private boolean closed = false;

        public void notifyWorkerCount(int count)
        {
            if (count > 0)
            {
                workersAvailable = true;
            } else
            {
                workersAvailable = false;
            }
            System.out.println("****** N_WORKERS = " + count);
        }

        public void onClose()
        {
            closed = true;
            this.notifyAll();
        }

        public void onRecognitionEvent(RecognitionEvent event)
        {
            System.out.println("****** " + event);

            if (event.getStatus() == 10) {
                System.out.println("****** PINGED" + event);
                sendPong=true;
            } else {
                events.add(event);
            }
        }

        public List<RecognitionEvent> getEvents()
        {
            return events;
        }

        public boolean isClosed()
        {
            return closed;
        }
    }

    private static void sendStream(DuplexRecognitionSession session, ByteArrayOutputStream bAOS) throws IOException, InterruptedException
    {
        int chunksPerSecond = 4;

        byte audioData[] = bAOS.toByteArray();
        InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
        AudioFormat audioFormat = getAudioFormat();
        int bytesPerSecond = (int) (audioFormat.getSampleRate() * 2);

        byte buf[] = new byte[bytesPerSecond / chunksPerSecond];

        while (true)
        {
            long millisWithinChunkSecond = System.currentTimeMillis() % (1000 / chunksPerSecond);

            int size = byteArrayInputStream.read(buf, 0, buf.length);

            //System.err.println("Chunk size:" + size);
            if (size < 0)
            {
                byte buf2[] = new byte[0];
                session.sendChunk(buf2, true);
                break;
            }

            if (size == bytesPerSecond / chunksPerSecond)
            {
                session.sendChunk(buf, false);
            } else
            {
                byte buf2[] = Arrays.copyOf(buf, size);
                session.sendChunk(buf2, true);
                break;
            }
            Thread.sleep(1000 / chunksPerSecond - millisWithinChunkSecond);
        }
    }

    public SpeechAPI(String server, RecognitionEventListener rel) {
        if (server.matches("^.*:[0-9]+$")) {
            DEFAULT_WS_URL = "ws://"+server+"/client/ws/speech";
            DEFAULT_WS_STATUS_URL = "ws://"+server+"/client/ws/status";
        } else {
            System.out.println("Please specify server and port to use (this.is.my.server:0000)");
        }

        try {
            RecognitionEventAccumulator statusEventAccumulator = new RecognitionEventAccumulator();
            URI statusUri = new URI(DEFAULT_WS_STATUS_URL);
            WorkerCountClient status_session = new WorkerCountClient(statusUri,statusEventAccumulator);
            status_session.connect();
        } catch (Exception e3) {
            System.err.println("Caught Exception: " + e3.getMessage());
        }

        WsDuplexRecognitionSession session = null;
        try {
            RecognitionEventAccumulator eventAccumulator = new RecognitionEventAccumulator();
            session = new WsDuplexRecognitionSession(DEFAULT_WS_URL);
            session.addRecognitionEventListener(eventAccumulator);
            session.setUserId("laurensw");
            session.setContentId("SpeechAPIDemo");
            session.connect();
        } catch (Exception e2){
            System.err.println("Caught Exception: " + e2.getMessage());
        }
        captureAudio(session);
    }

    //This method captures audio input from a microphone and saves it in a ByteArrayOutputStream object.
    private void captureAudio(DuplexRecognitionSession session){
        try{
            //Get everything set up for capture
            audioFormat = getAudioFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();

            // Create a thread to capture the microphone data and start it running.
            // It will run until the Stop button is clicked.
            Thread captureThread = new Thread(new CaptureThread(session));
            captureThread.start();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    private static AudioFormat getAudioFormat()
    {
        float sampleRate = 16000.0F;  //8000,11025,16000,22050,44100
        int sampleSizeInBits = 16;    //8,16
        int channels = 1;        //1,2
        boolean signed = true;      //true,false
        boolean bigEndian = false;    //true,false
        return new AudioFormat(
                sampleRate,
                sampleSizeInBits,
                channels,
                signed,
                bigEndian);
    }

    //This thread puts the captured audio in the ByteArrayOutputStream object, and optionally sends it
    //to the speech server for live recognition.
    class CaptureThread extends Thread
    {
        private DuplexRecognitionSession session;

        //An arbitrary-size temporary holding buffer
        CaptureThread(DuplexRecognitionSession session)
        {
            this.session = session;
        }

        byte tempBuffer[] = new byte[8000];

        public void run()
        {
            byteArrayOutputStream = new ByteArrayOutputStream();
            stopCapture = false;
            try
            {
                //Loop until stopCapture is set by another thread that services the Stop button.
                while (!stopCapture)
                {
                    //Read data from the internal buffer of the data line.
                    int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
                    if (cnt > 0)
                    {
                        byteArrayOutputStream.write(tempBuffer, 0, cnt);
                        session.sendChunk(tempBuffer, false);
                    }
                    if (sendPong) {
                        sendPong = false;
                        session.sendChunk("PONG".getBytes(), false);
                    }
                }
                byteArrayOutputStream.close();
                byte tmp[] = new byte[0];
                session.sendChunk(tmp, true);
            }
            catch (Exception e)
            {
                System.out.println(e);
                System.exit(0);
            }
        }
    }
}
