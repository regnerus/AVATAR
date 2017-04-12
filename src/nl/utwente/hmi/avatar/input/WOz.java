package nl.utwente.hmi.avatar.input;


import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Created by Bouke on 12/04/2017.
 */
public class WOz extends Input{
    private JPanel mainPanel = new JPanel(); // this is what I'll add to contentPane

    public WOz() {
        startGUI("EN");
    }

    public WOz(String lang) {
        startGUI(lang);
    }

    private void startGUI(String language){
//        super(socketIP, socketPort);

        try {
            if(Objects.equals(language, "NL")) {
                OutputGuiNL outputFrame = new OutputGuiNL();

                outputFrame.setTitle("Avatar Politie WOZ interface");
                outputFrame.setLocation(100, 5);
                outputFrame.setVisible(true);
                outputFrame.setMinimumSize(new Dimension(850, 450));
            }
            else {
                OutputGuiEN outputFrame = new OutputGuiEN();

                outputFrame.setTitle("Avatar Politie WOZ interface");
                outputFrame.setLocation(100, 5);
                outputFrame.setVisible(true);
                outputFrame.setMinimumSize(new Dimension(850, 450));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JComponent getMainComponent() {
        return mainPanel;
    }

}

