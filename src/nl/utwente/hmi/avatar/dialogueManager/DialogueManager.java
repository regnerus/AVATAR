package nl.utwente.hmi.avatar.dialogueManager;

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
        System.out.print(args[0]);
        if(args.length > 0){
            if(Objects.equals(args[0], "-woz")) {
                WOz woz = new WOz();
                woz.main(args);
            }
            if(Objects.equals(args[0], "-qa")) {
                QAMatcher qa = new QAMatcher(apolloIP,apolloPort);
                qa.main(args);
            }
        } else{
            System.err.println("Argument wrong.");
            System.exit(1);
        }
    }

}
