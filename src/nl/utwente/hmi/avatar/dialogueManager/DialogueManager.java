package nl.utwente.hmi.avatar.dialogueManager;

import nl.utwente.hmi.avatar.dialogueManager.QAMatcher.SimpleQAResponder;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Bouke on 04/04/2017.
 */
public class DialogueManager {
    Logger logger = Logger.getLogger("MyLog");
    FileHandler fh;
    static String apolloIP = "127.0.0.1";
    static int apolloPort = 61613;

    public DialogueManager() {
        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("test.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("My first log");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        System.out.print(args[1]);
        if(args.length > 0){
            try {
                apolloPort= Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
            apolloIP = args[2];
            String language = args[1];
            if(Objects.equals(args[0], "-woz")) {
                WOz woz = new WOz(language, apolloIP, apolloPort);
            }
            if(Objects.equals(args[0], "-qa")) {
                SimpleQAResponder qa = new SimpleQAResponder(apolloIP,apolloPort);
                qa.main(args);
            }
        }else{
            System.err.println("Argument wrong.");
            System.exit(1);
        }
    }

}
