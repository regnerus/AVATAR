package nl.utwente.hmi.avatar.dialogueManager.QAMatcher;

import java.net.URLEncoder;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;
import pk.aamir.stompj.Connection;
import pk.aamir.stompj.MessageHandler;
import pk.aamir.stompj.Message;
import pk.aamir.stompj.ErrorHandler;
import pk.aamir.stompj.ErrorMessage;
import pk.aamir.stompj.StompJException;

/* Simple implementation of using the stompj library to communicate bml with ASAP
 * only documentation I found for stompj is here:
 *   https://code.google.com/archive/p/stompj/wikis/GettingStarted.wiki
 */
public class TestStompJ implements ErrorHandler, MessageHandler {
    
    private Connection con;
	private String inTopic;
	private String outTopic;
    private int bmlId;

    public TestStompJ(String apolloIP, int apolloPort) {
		inTopic = "/topic/bmlFeedback";
		outTopic = "/topic/bmlRequests";
        bmlId = 0;

        try {
            con = new Connection(apolloIP, apolloPort, "admin", "password");
            con.setErrorHandler(this);
			con.connect();
		} catch (StompJException e) {
			System.out.println("Error while initialising STOMP connection: "+e.getMessage());
			e.printStackTrace();
			return;
		}

		con.subscribe(inTopic, true);
		con.addMessageHandler(inTopic, this);
    }

	public void sendBml(String bml) {
		String prefix = "{ \"bml\": { \"content\": \"";
		String suffix = "\" } }";
		try {
			con.send(prefix+URLEncoder.encode(bml, "UTF-8")+suffix, outTopic);
		} catch (UnsupportedEncodingException e) {
			System.out.println("[sendBml] Encoding failed.");
		}
	}

    public void say(String text) {
        String prefix = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\" id=\"bml"+(++bmlId)+"\"><speech id=\"speech1\" start=\"0\"><text>";
        String suffix = "</text></speech></bml>";
        sendBml(prefix+text+suffix);
    }

    public void onError(ErrorMessage err) {
        System.out.println("[onError] "+err.getMessage());
    }

	public void onMessage(Message msg) {
		try {
		    System.out.println("[onMessage] "+URLDecoder.decode(msg.getContentAsString(), "UTF-8"));	
		} catch (UnsupportedEncodingException e) {
			System.out.println("[onMessage] Decoding failed.");
		}
	}

    public static void main(String[] args) {
		String apolloIP = "127.0.0.1";
		int apolloPort = 61613;
        TestStompJ app = new TestStompJ(apolloIP, apolloPort);
        while (true) {
            System.out.println("Wizard: ");
            String text = System.console().readLine();
            app.say(text);
        }
    }
}
