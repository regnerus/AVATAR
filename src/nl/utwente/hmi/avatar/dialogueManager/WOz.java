package nl.utwente.hmi.avatar.dialogueManager;

import pk.aamir.stompj.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

@SuppressWarnings("serial")
public class WOz extends DialogueManager implements ErrorHandler, MessageHandler {
    private JPanel mainPanel = new JPanel(); // this is what I'll add to contentPane
    public static JFrame frame;
    static Connection con;

    public WOz(String language, String appolloIP, int appolloPort){
        String feedbackTopic = "/topic/bmlFeedback";

        try {
            con = new Connection(appolloIP, appolloPort, "admin", "password");
            con.setErrorHandler(this);
            con.connect();
        } catch (StompJException e) {
            System.out.println("Error while initialising STOMP connection: "+e.getMessage());
            e.printStackTrace();
            return;
        }
        //topiclistener bmlfeedback
        con.subscribe(feedbackTopic, true);
        con.addMessageHandler(feedbackTopic, this);
        if(Objects.equals(language, "EN")) {
            try {
                OutputGuiEN outputFrame = new OutputGuiEN();

                outputFrame.setTitle("Avatar Politie WOZ interface");
                outputFrame.setLocation(100, 5);
                outputFrame.setVisible(true);
                outputFrame.setMinimumSize(new Dimension(850, 450));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(Objects.equals(language, "NL")) {
            try {
                OutputGuiNL outputFrame = new OutputGuiNL();

                outputFrame.setTitle("Avatar Politie WOZ interface");
                outputFrame.setLocation(100, 5);
                outputFrame.setVisible(true);
                outputFrame.setMinimumSize(new Dimension(850, 450));

            } catch (Exception e) {
                e.printStackTrace();
            }
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

    public JComponent getMainComponent() {
        return mainPanel;
    }

}
