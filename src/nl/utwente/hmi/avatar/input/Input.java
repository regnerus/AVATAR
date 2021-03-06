package nl.utwente.hmi.avatar.input;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class Input {
    private static Input input;
    private static WebSocketClient client;


    public Input( ) {

    }

    public static void main( String[] args ) throws URISyntaxException {

        String language =  "EN";
        language =  args[1];

        if(Objects.equals(args[0], "WOZ")) {

            input = new WOz(language);
        }else {
            input = new Speech(language);
        }


        connectClient();
    }

    public static void connectClient() throws URISyntaxException {
        // cc = new ChatClient(new URI(uriField.getText()), area, ( Draft ) draft.getSelectedItem() );
        client = new WebSocketClient( new URI("ws://130.89.233.163:1337"), new Draft_17() ) {

            @Override
            public void onOpen( ServerHandshake handshakedata ) {
                System.out.println( "opened connection" );
                // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
            }

            @Override
            public void onMessage( String message ) {
                System.out.println( "received: " + message );
            }


            @Override
            public void onClose( int code, String reason, boolean remote ) {
                // The codecodes are documented in class org.java_websocket.framing.CloseFrame
                System.out.println( "Connection closed by " + ( remote ? "remote peer" : "us" ) );
            }

            @Override
            public void onError( Exception ex ) {
                ex.printStackTrace();
                // if the error is fatal then onClose will be called additionally
            }

        };


        client.connect();
    }


    public static void sendInput(String message, String behaviour) {
        client.send(message + ";" + behaviour);
    }

    public static void sendInput(String message) {
        client.send(message);
    }

    public static Input getInput() {
        return input;
    }

}
