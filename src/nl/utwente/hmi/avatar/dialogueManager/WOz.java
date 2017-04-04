package nl.utwente.hmi.avatar.dialogueManager;

import pk.aamir.stompj.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class WOz extends DialogueManager implements ErrorHandler, MessageHandler {
    private JPanel mainPanel = new JPanel(); // this is what I'll add to contentPane
    public static JFrame frame;
    static Connection con;

    JTextField ip;
    //static String appolloIP = "130.89.12.234";
    static String appolloIP = "130.89.234.44";

    JTextField port;
    static int appolloPort = 61613;

    JTextField topic;
    static String appolloTopic = "/topic/bmlRequests";

    static WOz appconnect = new WOz();

    public WOz(String appolloIP, int appolloPort){

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
    }
    public WOz() {

        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        mainPanel.setLayout(null);

        ip = new JTextField (appolloIP);
        ip.setBounds(8, 50, 190, 40);
        mainPanel.add(ip);

        port = new JTextField (Integer.toString(appolloPort));
        port.setBounds(8, 100, 190, 40);
        mainPanel.add(port);

        topic = new JTextField (appolloTopic);
        topic.setBounds(8, 150, 190, 40);
        mainPanel.add(topic);

        JButton btnWOzEN = new JButton("WOz English");
        btnWOzEN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //update internal variables from settings-fields
                appolloIP = ip.getText();
                appolloPort = Integer.parseInt(port.getText());
                appolloTopic = topic.getText();

                appconnect = new WOz(appolloIP, appolloPort);

                try {
                    OutputGuiEN outputFrame = new OutputGuiEN();

                    outputFrame.setTitle("Avatar Politie WOZ interface");
                    outputFrame.setLocation(100, 5);
                    outputFrame.setVisible(true);
                    outputFrame.setMinimumSize(new Dimension(850, 450));

                    //frame.dispose();
                    frame.hide();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        JButton btnWOzNL = new JButton("WOz Nederlands");
        btnWOzNL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //update internal variables from settings-fields
                appolloIP = ip.getText();
                appolloPort = Integer.parseInt(port.getText());
                appolloTopic = topic.getText();

                appconnect = new WOz(appolloIP, appolloPort);

                try {
                    OutputGuiNL outputFrame = new OutputGuiNL();

                    outputFrame.setTitle("Avatar Politie WOZ interface");
                    outputFrame.setLocation(100, 5);
                    outputFrame.setVisible(true);
                    outputFrame.setMinimumSize(new Dimension(850, 450));

                    //frame.dispose();
                    frame.hide();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnWOzNL.setBounds(205, 150, 150, 40);
        btnWOzEN.setBounds(205, 190, 150, 40);
        mainPanel.add(btnWOzNL);
        mainPanel.add(btnWOzEN);

        JLabel lblSelectCondition = new JLabel("Specify IP, Port and Topic:");
        lblSelectCondition.setBounds(8, 13, 269, 16);
        mainPanel.add(lblSelectCondition);

    }
    public static String getAppolloIP() {
        return appolloIP;
    }

    public static void setAppolloIP(String appolloIP) {
        WOz.appolloIP = appolloIP;
    }

    public static int getAppolloPort() {
        return appolloPort;
    }

    public static void setAppolloPort(int appolloPort) {
        WOz.appolloPort = appolloPort;
    }

    public static String getAppolloTopic() {
        return appolloTopic;
    }

    public static void setAppolloTopic(String appolloTopic) {
        WOz.appolloTopic = appolloTopic;
    }

    //launch app
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WOz woz = new WOz();

                    frame = new JFrame("Avatar Politie WOZ: Setup");
                    frame.getContentPane().add(woz.getMainComponent());
                    frame.setVisible(true);
                    frame.setResizable(true);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //frame.setContentPane(contentPane);
                    frame.setBounds(100, 100, 350, 350);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
