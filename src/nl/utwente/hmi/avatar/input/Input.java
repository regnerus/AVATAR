package nl.utwente.hmi.avatar.input;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class Input {
    private static Input input;
    public static InputListener listener;

    private static SocketIO socket;

    public Input() {

    }

    public Input(String socketIP, int socketPort) {
        try {
            socket = new SocketIO("http://127.0.0.1:3001/");

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
                }
            });

            // This line is cached until the connection is establisched.
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        input = new SpeechAPI("nlspraak.ewi.utwente.nl:8889", null);
//        input = new WOz("EN", "130.89.227.191", 61613);
    }


    public static void sendInput(String message, String behaviour) {
        socket.send(message + ";" + behaviour);
    }

    public static void sendInput(String message) {
        socket.send(message);
    }

    public static Input getInput() {
        return input;
    }

    public void setListener(InputListener listener) {
        this.listener = listener;
    }
}
