package nl.utwente.hmi.avatar.dialogueManager;

import nl.utwente.hmi.avatar.dialogueManager.QAMatcher.*;
import pk.aamir.stompj.*;
import nl.utwente.hmi.avatar.input.*;
import java.util.logging.Logger;

public class QAMatcher extends DialogueManager implements ErrorHandler, MessageHandler ,InputListener{
    private static final Logger LOGGER = Logger.getLogger( DialogueManager.class.getName() );

    private static Connection con;
    private static String inTopic = "/topic/FlipperQAMatcher1Question";	//where this qa matcher is listening for questions
    private static String outTopic = "/topic/bmlRequests";				//where this qamatcher sends the answer

    static String apolloIP = "127.0.0.1";
    static int apolloPort = 61613;

    //the file that holds the specification of the matching Q and A's, found in directory resources/qamatcher
    static String filename = "vragen.xml";

    //the qa parser etc
    static DomDialogsParser ddp = new DomDialogsParser(filename);
    static DialogStore store= ddp.getDialogStore();

    public QAMatcher(String apolloIP, int apolloPort){

        try {
            con = new Connection(apolloIP, apolloPort, "admin", "password");
            con.setErrorHandler(this);
            con.connect();
            System.out.println("Connection initialised.");
        } catch (StompJException e) {
            System.out.println("Error while initialising STOMP connection: "+e.getMessage());
            e.printStackTrace();
            return;
        }

        try {
            //topiclistener
            con.subscribe(inTopic, true);
            con.addMessageHandler(inTopic, this);
            System.out.println("Subscriptions done.");
        } catch (Exception e) {
            System.out.println("Error while subscribing: "+e.getMessage());
            e.printStackTrace();
            return;
        }
    }

    public static void main(String[] args){

//        QAMatcher qaResponder = new QAMatcher(apolloIP, apolloPort);

        String query = "";
        System.out.println("Question: ");

        //while (true){
        while ((query = Console.readString()) != ""){//this is for debugging easy, the qamatcher works with console
            String answer = getAnswer(query);
            sendAnswer(answer);
        }
    }

    public static void sendAnswer(String answer) {
        if (answer!=null){
            String prefix = "{ \"bml\": { \"content\": \"";
            String suffix = "\" } }";
            answer = prefix+answer+suffix; //quick and dirty jason
            answer = answer.replace("  ", " ");//remove double spaces
            answer = answer.replace("", "").replace("\r", " ").replace("\n", " ");;//remove double spaces and linebreaks

            System.out.println("[sendAnswer] "+answer);
            System.out.println("[sendAnswer] "+outTopic);
            try{
                LOGGER.info(answer);
                con.send(answer, outTopic);
            } catch(Exception e) {
                System.out.println("[sendAnswer] "+e);
            }
        }
    }

    @Override
    public void onMessage(Message msg) {

        String msgstring = msg.getContentAsString();
        if (msgstring!=null){
            String answer = getAnswer(msgstring);
            sendAnswer(answer);
        }

    }

    public static String getAnswer (String query){
        // 		set value of attribute used to filter answer given in response to question
        String attName = "type"; 		//"type"
        String attValue = "certain"; 	// "certain" or "uncertain"
        //
        Dialog d = store.getBestMatchingDialog(query);
        //		String answer = store.answerString(d, attName , attValue );
        // 		the above two lines have the same effect as the next line
        //		String answer = store.bestMatch(query, attName, attValue);
        // 		alternative: first set the attribute for filtering answers
        store.setAttribute(attName,attValue);
        String answer = store.bestMatch(query);

        System.out.print("Answer: "+answer);
        System.out.print("Question: ");
        System.out.flush();
        return answer;
    }

    @Override
    public void onError(ErrorMessage err) {
        System.out.println("[onError] "+err.getMessage());
    }

    @Override
    public void onInput(String msg) {
        if (msg!=null){
            String answer = getAnswer(msg);
            sendAnswer(answer);
        }


    }
}
