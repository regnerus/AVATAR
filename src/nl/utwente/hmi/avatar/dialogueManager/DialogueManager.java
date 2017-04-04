package nl.utwente.hmi.avatar.dialogueManager;

import nl.utwente.hmi.avatar.input.Input;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Bouke on 04/04/2017.
 */
public class DialogueManager {
    private static final Logger LOGGER = Logger.getLogger( DialogueManager.class.getName() );

    SimpleFormatter formatter = new SimpleFormatter();
    FileHandler fh;

    static String apolloIP = "127.0.0.1";
    static int apolloPort = 61613;
    static String appolloTopic = "/topic/bmlRequests";

    public DialogueManager() {
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

    public static void main(String[] args){
        if(args.length > 2){
            try {
                apolloIP = args[2];
                apolloPort = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }

            String language = args[1];

            if(Objects.equals(args[0], "-woz")) {
                WOz woz = new WOz(language, apolloIP, apolloPort);
            }
            if(Objects.equals(args[0], "-qa")) {

                QAMatcher qa = new QAMatcher(apolloIP,apolloPort);
                System.out.print("test");
                Input input = new Input();
                input.main();
                input.getInput().setListener(qa);
            }
        } else{
            System.err.println("Argument wrong. Format should be: (-woz|-qa) language ip port");
            System.exit(1);
        }
    }

}
