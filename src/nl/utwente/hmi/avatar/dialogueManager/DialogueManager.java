package nl.utwente.hmi.avatar.dialogueManager;

import nl.utwente.hmi.avatar.InputListener;
import nl.utwente.hmi.avatar.SpeechAPI;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import pk.aamir.stompj.Connection;
import pk.aamir.stompj.StompJException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DialogueManager extends WebSocketServer implements InputListener {
    private static Connection con;
    static String apolloIP = "127.0.0.1";
    static int apolloPort = 61613;
    static String apolloTopic = "/topic/bmlRequests";
    public SpeechAPI speech;

    public static QAMatcher qa;
    public static boolean useQA = false;

    private static final Logger LOGGER = Logger.getLogger( DialogueManager.class.getName() );

    SimpleFormatter formatter = new SimpleFormatter();
    FileHandler fh;

    long bmlId = new Date().getTime();

    public DialogueManager( int port ) throws UnknownHostException {
        super( new InetSocketAddress( port ) );

        setupLogger(bmlId);
        connectActiveMQ();
        //connectSpeech();

    }

    public void setupLogger(long id) {
        try {
            fh = new FileHandler("logger" + id + ".log");
            fh.setFormatter(formatter);
            LOGGER.addHandler(fh);

            // the following statement is used to log any messages
            LOGGER.info("Start");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectActiveMQ() {
        String feedbackTopic = "/topic/bmlFeedback";

        try {
            con = new Connection(apolloIP, apolloPort, "admin", "password");
            con.connect();

            System.out.println("Hello World!");

        } catch (StompJException e) {
            System.out.println("Error while initialising STOMP connection: "+e.getMessage());
            e.printStackTrace();
            return;
        }

        con.subscribe(feedbackTopic, true);
    }

    public void connectSpeech() {
        speech = new SpeechAPI("nlspraak.ewi.utwente.nl:8891", null);
        speech.setListener(this);
    }

    public static void sendBml(String answer) {
        if (answer!=null){

            String prefix = "{ \"bml\": { \"content\": \"";
            String suffix = "\" } }";

            try {
                answer = prefix + URLEncoder.encode(answer, "UTF-8") + suffix;
                answer = answer.replace("  ", " ");//remove double spaces
                answer = answer.replace("", "").replace("\r", " ").replace("\n", " ");;//remove double spaces and linebreaks
            } catch (UnsupportedEncodingException e) {
                System.out.println("[sendBml] Encoding failed.");
            }

//            System.out.println("[sendAnswer] "+answer);
//            System.out.println("[sendAnswer] "+apolloTopic);
            try{
                con.send(answer, apolloTopic);
            } catch(Exception e) {
                System.out.println("[sendAnswer] "+e);
            }
        }
    }

    public static void sayThis(String answer, String behaviour, long bmlId) {
        String bmlt = BMLT.BMLBehaviour(behaviour, bmlId);

        sendBml(bmlt);

        LOGGER.info(bmlt);
    }

    public static void sayThis(String answer, long bmlId) {
        String prefix = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\" id=\"bml"+(++bmlId)+"\" composition=\"REPLACE\"><speech id=\"speech1\" start=\"0\"><text>";

        String suffix = "</text></speech></bml>";

        sendBml(prefix+answer+suffix);

        LOGGER.info(answer);
    }

    @Override
    public void onOpen( WebSocket conn, ClientHandshake handshake ) {
        this.sendToAll( "new connection: " + handshake.getResourceDescriptor() );
        System.out.println( conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered!" );
    }

    @Override
    public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
        this.sendToAll( conn + " has left!" );
        System.out.println( conn + " has left!" );
    }

    @Override
    public void onMessage( WebSocket conn, String message ) {
        this.sendToAll( message );

        String[] args = message.split(";", -1);

        if(useQA) {
            String answer = qa.getAnswer(args[0]);

            if(args.length > 1) {
                sayThis(answer, args[1], bmlId);
            }
            else {
                sayThis(answer, bmlId);
            }
        }
        else {
            if(args.length > 1) {
                sayThis(args[0], args[1], bmlId);
            }
            else {
                sayThis(args[0], bmlId);
            }
        }



        System.out.println( conn + ": " + message );
    }

    public static void main( String[] args ) throws InterruptedException , IOException {
        WebSocketImpl.DEBUG = true;
        int port = 8887; // 843 flash policy port
        try {
            port = Integer.parseInt( args[ 0 ] );
        } catch ( Exception ex ) {
        }
        DialogueManager s = new DialogueManager( port );
        s.start();
        System.out.println( "Server started on port: " + s.getPort() );

        BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
        while ( true ) {
            String in = sysin.readLine();
            s.sendToAll( in );
            if( in.equals( "exit" ) ) {
                s.stop();
                break;
            }
        }

        if(Objects.equals(args[0], "-qa")) {
            qa = new QAMatcher("127.0.0.1", 61613);
            useQA = true;
        }
    }
    @Override
    public void onError( WebSocket conn, Exception ex ) {
        ex.printStackTrace();
        if( conn != null ) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    /**
     * Sends <var>text</var> to all currently connected WebSocket clients.
     *
     * @param text
     *            The String to send across the network.
     * @throws InterruptedException
     *             When socket related I/O errors occur.
     */
    public void sendToAll( String text ) {
        Collection<WebSocket> con = connections();
        synchronized ( con ) {
            for( WebSocket c : con ) {
                c.send( text );
            }
        }
    }

    @Override
    public void onInput(String msg) {
        LOGGER.info(msg);
    }

}