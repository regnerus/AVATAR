package nl.utwente.hmi.avatar.dialogueManager;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.framing.Framedata;
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

/**
 * A simple WebSocketServer implementation. Keeps track of a "chatroom".
 */
public class DialogueManager extends WebSocketServer {
    private static Connection con;
    static String apolloIP = "127.0.0.1";
    static int apolloPort = 61613;
    static String apolloTopic = "/topic/bmlRequests";

    long bmlId = new Date().getTime();

    public DialogueManager( int port ) throws UnknownHostException {
        super( new InetSocketAddress( port ) );

        connectActiveMQ();

    }

    public DialogueManager( InetSocketAddress address ) {
        super( address );

        connectActiveMQ();
    }

    public void connectActiveMQ() {
        String feedbackTopic = "/topic/bmlFeedback";

        try {
            con = new Connection(apolloIP, apolloPort, "admin", "password");
//            con.setErrorHandler(this);
            con.connect();

            System.out.println(con);
            System.out.println("Hello World!");

        } catch (StompJException e) {
            System.out.println("Error while initialising STOMP connection: "+e.getMessage());
            e.printStackTrace();
            return;
        }

        con.subscribe(feedbackTopic, true);
//        con.addMessageHandler(feedbackTopic, this);

//        try {
//            fh = new FileHandler("logger.log");
//            fh.setFormatter(formatter);
//            LOGGER.addHandler(fh);
//
//            // the following statement is used to log any messages
//            LOGGER.info("My first log");
//
//        } catch (SecurityException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
                con.send(answer, apolloTopic);
            } catch(Exception e) {
                System.out.println("[sendAnswer] "+e);
            }
        }
    }

    public static void sayThis(String answer, String behaviour, long bmlId) {
        String prefix = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\" id=\"bml"+(++bmlId)+"\" composition=\"REPLACE\"><speech id=\"speech1\" start=\"0\"><text>";

        behaviour = BMLBehaviour.BMLBehaviour(behaviour, bmlId);

        String suffix = "</text></speech>"+behaviour+"</bml>";
        sendBml(prefix+answer+suffix);
//        LOGGER.info(answer);
    }

    public static void sayThis(String answer, long bmlId) {
        String prefix = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\" id=\"bml"+(++bmlId)+"\" composition=\"REPLACE\"><speech id=\"speech1\" start=\"0\"><text>";

        String suffix = "</text></speech></bml>";

        sendBml(prefix+answer+suffix);
//        LOGGER.info(answer);
    }

    @Override
    public void onOpen( WebSocket conn, ClientHandshake handshake ) {
        this.sendToAll( "new connection: " + handshake.getResourceDescriptor() );
        System.out.println( conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!" );
    }

    @Override
    public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
        this.sendToAll( conn + " has left the room!" );
        System.out.println( conn + " has left the room!" );
    }

    @Override
    public void onMessage( WebSocket conn, String message ) {
        this.sendToAll( message );

        String[] args = message.split(";", -1);

        if(args.length > 1) {
            sayThis(args[0], args[1], bmlId);
        }
        else {
            sayThis(args[0], bmlId);
        }

        System.out.println( conn + ": " + message );
    }

    @Override
    public void onFragment( WebSocket conn, Framedata fragment ) {
        System.out.println( "received fragment: " + fragment );
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
        System.out.println( "ChatServer started on port: " + s.getPort() );

        BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
        while ( true ) {
            String in = sysin.readLine();
            s.sendToAll( in );
            if( in.equals( "exit" ) ) {
                s.stop();
                break;
            }
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
}

//package nl.utwente.hmi.avatar.dialogueManager;
//
////import io.socket.IOAcknowledge;
////import io.socket.IOCallback;
////import io.socket.SocketIO;
////import io.socket.SocketIOException;
////import org.json.JSONException;
////import org.json.JSONObject;
//import org.java_websocket.WebSocket;
//import org.java_websocket.WebSocketImpl;
//import org.java_websocket.handshake.ClientHandshake;
//import org.java_websocket.server.WebSocketServer;
//import pk.aamir.stompj.*;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.InetSocketAddress;
//import java.net.URLEncoder;
//import java.net.UnknownHostException;
//import java.util.Collection;
//import java.util.logging.FileHandler;
//import java.util.logging.Logger;
//import java.util.logging.SimpleFormatter;
//
///**
// * Created by Bouke on 04/04/2017.
// */
//public class DialogueManager extends WebSocketServer {
//    private static final Logger LOGGER = Logger.getLogger( DialogueManager.class.getName() );
//
//    SimpleFormatter formatter = new SimpleFormatter();
//    FileHandler fh;
//
//    private static Connection con;
//    static String apolloIP = "127.0.0.1";
//    static int apolloPort = 61613;
//    static String apolloTopic = "/topic/bmlRequests";
//
//    public DialogueManager( int port ) throws UnknownHostException {
//        super( new InetSocketAddress( port ) );
//    }
//
//    public DialogueManager( InetSocketAddress address ) {
//        super( address );
//    }
//
////    public DialogueManager()  {
//////        try {
//////            con = new Connection(apolloIP, apolloPort, "admin", "password");
//////            con.setErrorHandler(this);
//////            con.connect();
//////            System.out.println("Connection initialised.");
//////        } catch (StompJException e) {
//////            System.out.println("Error while initialising STOMP connection: "+e.getMessage());
//////            e.printStackTrace();
//////            return;
//////        }
////        String feedbackTopic = "/topic/bmlFeedback";
////
////        try {
////            con = new Connection(apolloIP, apolloPort, "admin", "password");
////            con.setErrorHandler(this);
////            con.connect();
////
////            System.out.println(con);
////            System.out.println("Hello World!");
////
////        } catch (StompJException e) {
////            System.out.println("Error while initialising STOMP connection: "+e.getMessage());
////            e.printStackTrace();
////            return;
////        }
////        //topiclistener bmlfeedback
////        con.subscribe(feedbackTopic, true);
////        con.addMessageHandler(feedbackTopic, this);
////
//////        //topiclistener bmlfeedback
//////        con.subscribe(apolloTopic, true);
//////        con.addMessageHandler(apolloTopic, this);
////
////        try {
////            fh = new FileHandler("logger.log");
////            fh.setFormatter(formatter);
////            LOGGER.addHandler(fh);
////
////            // the following statement is used to log any messages
////            LOGGER.info("My first log");
////
////        } catch (SecurityException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////    }
//
//    }
//

//
//    public static void main( String[] args ) throws InterruptedException , IOException {
//        WebSocketImpl.DEBUG = true;
//        int port = 8887; // 843 flash policy port
//        try {
//            port = Integer.parseInt( args[ 0 ] );
//        } catch ( Exception ex ) {
//        }
//        DialogueManager s = new DialogueManager( port );
//        s.start();
//        System.out.println( "ChatServer started on port: " + s.getPort() );
//
//        BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
//        while ( true ) {
//            String in = sysin.readLine();
//            s.sendToAll( in );
//            if( in.equals( "exit" ) ) {
//                s.stop();
//                break;
//            }
//        }
//    }
////
////    public static void main(String[] args){
////        DialogueManager manager = new DialogueManager();
////
////        long bmlId = new Date().getTime();
//
////        try {
////            SocketIO socket = new SocketIO("http://127.0.0.1:3001/");
////
////            socket.connect(new IOCallback() {
////                @Override
////                public void onMessage(JSONObject json, IOAcknowledge ack) {
////                    try {
////                        System.out.println("Server said:" + json.toString(2));
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
////                }
////
////                @Override
////                public void onMessage(String data, IOAcknowledge ack) {
////                    System.out.println("Server said: " + data);
////                }
////
////                @Override
////                public void onError(SocketIOException socketIOException) {
////                    System.out.println("an Error occured");
////                    socketIOException.printStackTrace();
////                }
////
////                @Override
////                public void onDisconnect() {
////                    System.out.println("Connection terminated.");
////                }
////
////                @Override
////                public void onConnect() {
////                    System.out.println("Connection established");
////                }
////
////                @Override
////                public void on(String event, IOAcknowledge ack, Object... args) {
////                    System.out.println("Server triggered event '" + event + "'");
////
////                    String arg = (String) args[0];
////                    String[] arguments = arg.split(";", -1);
////                    System.out.println(arguments[0]);
////
////                    if(arguments.length > 1) {
////                        sayThis(arguments[0], arguments[1], bmlId);
////                    }
////                    else {
////                        sayThis(arguments[0], bmlId);
////                    }
////                }
////            });
////
////            // This line is cached until the connection is establisched.
////        } catch (MalformedURLException e) {
////            e.printStackTrace();
////        }
//
////    @Override
////    public void onMessage(Message msg) {
////        System.out.println(msg.getContentAsString());
////    }
////
////    @Override
////    public void onError(ErrorMessage err) {
////        System.out.println("[onError] "+err.getMessage());
////    }
//
//    @Override
//    public void onOpen( WebSocket conn, ClientHandshake handshake ) {
//        this.sendToAll( "new connection: " + handshake.getResourceDescriptor() );
//        System.out.println( conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!" );
//    }
//
//    @Override
//    public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
//        this.sendToAll( conn + " has left the room!" );
//        System.out.println( conn + " has left the room!" );
//    }
//
//    @Override
//    public void onMessage( WebSocket conn, String message ) {
//        this.sendToAll( message );
//        System.out.println( conn + ": " + message );
//    }
//
////    @Override
////    public void onFragment( WebSocket conn, Framedata fragment ) {
////        System.out.println( "received fragment: " + fragment );
////    }
//
//    @Override
//    public void onError( WebSocket conn, Exception ex ) {
//        ex.printStackTrace();
//        if( conn != null ) {
//            // some errors like port binding failed may not be assignable to a specific websocket
//        }
//    }
//
//    public void sendToAll( String text ) {
//        Collection<WebSocket> con = connections();
//        synchronized ( con ) {
//            for( WebSocket c : con ) {
//                c.send( text );
//            }
//        }
//    }
//
//}
