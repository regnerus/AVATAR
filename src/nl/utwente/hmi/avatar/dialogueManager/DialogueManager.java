package nl.utwente.hmi.avatar.dialogueManager;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;
import org.json.JSONException;
import org.json.JSONObject;
import pk.aamir.stompj.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Bouke on 04/04/2017.
 */
public class DialogueManager implements ErrorHandler, MessageHandler {
    private static final Logger LOGGER = Logger.getLogger( DialogueManager.class.getName() );

    SimpleFormatter formatter = new SimpleFormatter();
    FileHandler fh;

    private static Connection con;
    static String apolloIP = "127.0.0.1";
    static int apolloPort = 61613;
    static String apolloTopic = "/topic/bmlRequests";

    public DialogueManager()  {
//        try {
//            con = new Connection(apolloIP, apolloPort, "admin", "password");
//            con.setErrorHandler(this);
//            con.connect();
//            System.out.println("Connection initialised.");
//        } catch (StompJException e) {
//            System.out.println("Error while initialising STOMP connection: "+e.getMessage());
//            e.printStackTrace();
//            return;
//        }
        String feedbackTopic = "/topic/bmlFeedback";

        try {
            con = new Connection(apolloIP, apolloPort, "admin", "password");
            con.setErrorHandler(this);
            con.connect();

            System.out.println(con);
            System.out.println("Hello World!");

        } catch (StompJException e) {
            System.out.println("Error while initialising STOMP connection: "+e.getMessage());
            e.printStackTrace();
            return;
        }
        //topiclistener bmlfeedback
        con.subscribe(feedbackTopic, true);
        con.addMessageHandler(feedbackTopic, this);

//        //topiclistener bmlfeedback
//        con.subscribe(apolloTopic, true);
//        con.addMessageHandler(apolloTopic, this);

        try {
            fh = new FileHandler("logger.log");
            fh.setFormatter(formatter);
            LOGGER.addHandler(fh);

            // the following statement is used to log any messages
            LOGGER.info("My first log");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String bmlBehaviour(String bmlBehaviourRequested, long bmlId){
        String bmlBehaviourOut ="";

        //String[] bmlOffer = {"<gesture id=\"","\" lexeme=\"offer\" start=\"speech","\" />"};
        //"<gaze id=\"","\" target=\"Car\"    start=\"bml",":speech",":sync","\" />"}

        //gesture bindings
        if (bmlBehaviourRequested.equals("offer")){
            bmlBehaviourOut = "<gesture id=\"offer"+bmlId+"\" lexeme=\"offer\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("SHAKE")){
            bmlBehaviourOut = "<head id=\"SHAKE"+bmlId+"\" lexeme=\"SHAKE\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("NOD")){
            bmlBehaviourOut = "<head id=\"NOD"+bmlId+"\" lexeme=\"NOD\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("BEAT_LOW")){
            bmlBehaviourOut = "<gesture id=\"BEAT_LOW"+bmlId+"\" lexeme=\"BEAT_LOW\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("BEAT")){
            bmlBehaviourOut = "<gesture id=\"BEAT"+bmlId+"\" lexeme=\"BEAT\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("BEAT")){
            bmlBehaviourOut = "<gesture id=\"BEAT"+bmlId+"\" lexeme=\"BEAT\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("BEAT_MID")){
            bmlBehaviourOut = "<gesture id=\"BEAT_MID"+bmlId+"\" lexeme=\"BEAT_MID\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("deictic_you")){
            bmlBehaviourOut = "<gesture id=\"deictic_you"+bmlId+"\" lexeme=\"deictic_you\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("deictic_self")){
            bmlBehaviourOut = "<gesture id=\"deictic_self"+bmlId+"\" lexeme=\"deictic_self\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("stop")){
            bmlBehaviourOut = "<gesture id=\"stop"+bmlId+"\" lexeme=\"stop\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("why")){
            bmlBehaviourOut = "<gesture id=\"why"+bmlId+"\" lexeme=\"why\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("indicateright")){
            bmlBehaviourOut = "<gesture id=\"indicateright"+bmlId+"\" lexeme=\"indicateright\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("indicateleft")){
            bmlBehaviourOut = "<gesture id=\"indicateleft"+bmlId+"\" lexeme=\"indicateleft\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("dismiss")){
            bmlBehaviourOut = "<gesture id=\"dismiss"+bmlId+"\" lexeme=\"dismiss\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("contemplate")){
            bmlBehaviourOut = "<gesture id=\"contemplate"+bmlId+"\" lexeme=\"contemplate\" start=\"speech1:start\" />";
        }
        else if (bmlBehaviourRequested.equals("BEAT_CHOP")){
            bmlBehaviourOut = "<gesture id=\"BEAT_CHOP"+bmlId+"\" lexeme=\"BEAT_CHOP\" start=\"speech1:start\" />";
        }
        //directed pointing
        else if (bmlBehaviourRequested.equals("pointCar")){
            bmlBehaviourOut = "<pointing id=\"pointCar"+bmlId+"\"  start=\"speech1:start\" end=\"speech1:start+4\" target=\"Car\" />";
        }
        //directed gazing
        else if (bmlBehaviourRequested.equals("gazeCar")){
            bmlBehaviourOut = "<gaze id=\"gazeCar"+bmlId+"\" start=\"speech1:start\" end=\"speech1:start+4\" target=\"Car\" />";
        }
        //face bindings
        else if (bmlBehaviourRequested.equals("disgust")){
            bmlBehaviourOut = "<faceLexeme id=\"disgust"+bmlId+"\" lexeme=\"disgust\" start=\"speech1:start\" end=\"speech1:start+1\" />";
        }
        else if (bmlBehaviourRequested.equals("afraid")){
            bmlBehaviourOut = "<faceLexeme id=\"afraid"+bmlId+"\" lexeme=\"afraid\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
        }
        else if (bmlBehaviourRequested.equals("anger")){
            bmlBehaviourOut = "<faceLexeme id=\"anger"+bmlId+"\" lexeme=\"anger\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
        }
        else if (bmlBehaviourRequested.equals("sad")){
            bmlBehaviourOut = "<faceLexeme id=\"sad"+bmlId+"\" lexeme=\"sad\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
        }
        else if (bmlBehaviourRequested.equals("surprise")){
            bmlBehaviourOut = "<faceLexeme id=\"surprise"+bmlId+"\" lexeme=\"surprise\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
        }
        else if (bmlBehaviourRequested.equals("joy")){
            bmlBehaviourOut = "<faceLexeme id=\"joy"+bmlId+"\" lexeme=\"joy\" start=\"speech1:start\"end=\"speech1:start+1\" />";
        }
        else if (bmlBehaviourRequested.equals("ask")){
            bmlBehaviourOut = "<faceLexeme id=\"ask"+bmlId+"\" lexeme=\"ask\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
        }
        else if (bmlBehaviourRequested.equals("furrow")){
            bmlBehaviourOut = "<faceLexeme id=\"furrow"+bmlId+"\" lexeme=\"furrow\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
        }
        else if (bmlBehaviourRequested.equals("expectingbrows")){
            bmlBehaviourOut = "<faceLexeme id=\"expectingbrows"+bmlId+"\" lexeme=\"expectingbrows\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
        }
        else if (bmlBehaviourRequested.equals("think")){
            bmlBehaviourOut = "<faceLexeme id=\"think"+bmlId+"\" lexeme=\"think\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
        }
        else if (bmlBehaviourRequested.equals("happy")){
            bmlBehaviourOut = "<faceLexeme id=\"happy"+bmlId+"\" lexeme=\"happy\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
        }
        else if (bmlBehaviourRequested.equals("smile")){
            bmlBehaviourOut = "<faceLexeme id=\"smile"+bmlId+"\" lexeme=\"smile\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
        }
        //voor jan
        else if (bmlBehaviourRequested.equals("dans")){
            bmlBehaviourOut = "<gesture id=\"dans"+(bmlId+1)+"\" lexeme=\"indicateleft\" start=\"speech1:start\" />"+
                    "<gesture id=\"dans"+(bmlId+2)+"\" lexeme=\"indicateright\" start=\"speech1:start+1\" />"+
                    "<gesture id=\"dans"+(bmlId+3)+"\" lexeme=\"indicateleft\" start=\"speech1:start+2\" />"+
                    "<gesture id=\"dans"+(bmlId+4)+"\" lexeme=\"indicateright\" start=\"speech1:start+3\" />"+
                    "<gesture id=\"dans"+(bmlId+5)+"\" lexeme=\"indicateleft\" start=\"speech1:start+4\" />"+
                    "<gesture id=\"dans"+(bmlId+6)+"\" lexeme=\"indicateright\" start=\"speech1:start+5\" />";
        }

        return bmlBehaviourOut;
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

            System.out.println("[sendAnswer] "+answer);
            System.out.println("[sendAnswer] "+apolloTopic);
            try{
                LOGGER.info(answer);
                con.send(answer, apolloTopic);
            } catch(Exception e) {
                System.out.println("[sendAnswer] "+e);
            }
        }
    }

    public static void sayThis(String answer, String behaviour, long bmlId) {
        String prefix = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\" id=\"bml"+(++bmlId)+"\" composition=\"REPLACE\"><speech id=\"speech1\" start=\"0\"><text>";

        behaviour = bmlBehaviour(behaviour, bmlId);

        String suffix = "</text></speech>"+behaviour+"</bml>";
        // System.out.println(prefix+answerleukebml[0]+suffix);
        sendBml(prefix+answer+suffix);
        LOGGER.info(answer);
    }

    public static void sayThis(String answer, long bmlId) {
        String prefix = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\" id=\"bml"+(++bmlId)+"\" composition=\"REPLACE\"><speech id=\"speech1\" start=\"0\"><text>";

        String suffix = "</text></speech></bml>";


        sendBml(prefix+answer+suffix);
        LOGGER.info(answer);
    }

    public static void main(String[] args){
        DialogueManager manager = new DialogueManager();

        long bmlId = new Date().getTime();

        try {
            SocketIO socket = new SocketIO("http://127.0.0.1:3001/");

            socket.connect(new IOCallback() {
                @Override
                public void onMessage(JSONObject json, IOAcknowledge ack) {
                    try {
                        System.out.println("Server said:" + json.toString(2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onMessage(String data, IOAcknowledge ack) {
                    System.out.println("Server said: " + data);
                }

                @Override
                public void onError(SocketIOException socketIOException) {
                    System.out.println("an Error occured");
                    socketIOException.printStackTrace();
                }

                @Override
                public void onDisconnect() {
                    System.out.println("Connection terminated.");
                }

                @Override
                public void onConnect() {
                    System.out.println("Connection established");
                }

                @Override
                public void on(String event, IOAcknowledge ack, Object... args) {
                    System.out.println("Server triggered event '" + event + "'");

                    String arg = (String) args[0];
                    String[] arguments = arg.split(";", -1);
                    System.out.println(arguments[0]);

                    if(arguments.length > 1) {
                        sayThis(arguments[0], arguments[1], bmlId);
                    }
                    else {
                        sayThis(arguments[0], bmlId);
                    }
                }
            });

            // This line is cached until the connection is establisched.
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message msg) {
        System.out.println(msg.getContentAsString());
    }

    @Override
    public void onError(ErrorMessage err) {
        System.out.println("[onError] "+err.getMessage());
    }
}
