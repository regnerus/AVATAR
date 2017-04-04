package nl.utwente.hmi.avatar.dialogueManager;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Bouke on 04/04/2017.
 */
public class DialogueManager {
    Logger logger = Logger.getLogger("MyLog");
    FileHandler fh;
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

}
