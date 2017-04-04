package nl.utwente.hmi.avatar.behaviourPlanner;

/**
 * Created by Bouke on 04/04/2017.
 */

import asap.bml.ext.bmlt.BMLTInfo;
import asap.environment.AsapEnvironment;
import asap.environment.AsapVirtualHuman;
import hmi.audioenvironment.AudioEnvironment;
import hmi.environmentbase.ClockDrivenCopyEnvironment;
import hmi.environmentbase.Environment;
import hmi.jcomponentenvironment.JComponentEnvironment;
import hmi.mixedanimationenvironment.MixedAnimationEnvironment;
import hmi.physicsenvironment.OdePhysicsEnvironment;
import hmi.util.Console;
import hmi.worldobjectenvironment.WorldObjectEnvironment;
import lombok.extern.slf4j.Slf4j;
import saiba.bml.BMLInfo;
import saiba.bml.core.FaceLexemeBehaviour;
import saiba.bml.core.HeadBehaviour;
import saiba.bml.core.PostureShiftBehaviour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


@Slf4j
public class ASAPStarter
{

    protected static JFrame mainJFrame = new JFrame("AsapRealizer demo");

    public ASAPThread.Status status;
    public ArrayList<Environment> environments;
    String spec;
    String mode;
    String lang;

    public ASAPStarter(String mode, String lang)
    {
        this.mode = mode;
        this.lang = lang;
        this.status = ASAPThread.Status.STARTING;
    }

    public void InitFunc(boolean gui) throws IOException {

        MixedAnimationEnvironment mae = new MixedAnimationEnvironment();
        final OdePhysicsEnvironment ope = new OdePhysicsEnvironment();
        WorldObjectEnvironment we = new WorldObjectEnvironment();
        AudioEnvironment aue = new AudioEnvironment("LJWGL_JOAL");

        BMLTInfo.init();
        BMLInfo.addCustomFloatAttribute(FaceLexemeBehaviour.class, "http://asap-project.org/convanim", "repetition");
        BMLInfo.addCustomStringAttribute(HeadBehaviour.class, "http://asap-project.org/convanim", "spindirection");
        BMLInfo.addCustomFloatAttribute(PostureShiftBehaviour.class, "http://asap-project.org/convanim", "amount");

        environments = new ArrayList<Environment>();
        if(gui) {final JComponentEnvironment jce = setupJComponentEnvironment(); }
        final AsapEnvironment ee = new AsapEnvironment();

        ClockDrivenCopyEnvironment ce = new ClockDrivenCopyEnvironment(1000 / 60);

        ce.init();
        ope.init();
        mae.init(ope, 0.002f);
        we.init();
        aue.init();
        environments.add(ee);
        environments.add(ope);
        environments.add(mae);
        environments.add(we);

        environments.add(ce);
        if(gui) { environments.add(jce); }
        environments.add(aue);

        ee.init(environments, ope.getPhysicsClock());
        ope.addPrePhysicsCopyListener(ee);

        status = ASAPThread.Status.WAITING_FOR_AGENTSPEC;
        AsapVirtualHuman avh = ee.loadVirtualHuman("", spec, "AsapRealizer demo");
        ope.startPhysicsClock();

        if(gui) {
            mainJFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(WindowEvent winEvt) {
                    System.exit(0);
                }
            });

            mainJFrame.setSize(1000, 600);
            mainJFrame.setVisible(true);
        }

        avh.getRealizerPort().performBML("<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\" id=\"bml1\"><speech id=\"speech0\" start=\"0.2\"><text>Hi</text></speech></bml>");

    }

    public void Init(String scenario) {
        this.spec = "clevr/agentspec_"+mode+"_"+scenario+"_"+lang+".xml";
        System.out.println("\tUsing spec "+spec);
        try {
            if (mode.equals("nogui")) {
                InitFunc(false);
            } else {
                InitFunc(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.status = ASAPThread.Status.RUNNING;
    }

    public static void main(String[] args) throws IOException
    {
        Console.setEnabled(false);
        System.out.println("Args: (using first argument for \"mode\" and second for \"lang\")");
        for (int a = 0; a<args.length; a++) {
            System.out.println("\tArg "+a+": "+args[a]);
        }

        String mode = "gui";
        String lang = "NL";

        if (args.length > 0) {
            mode = args[0];
        } if (args.length > 1) {
        lang = args[1];
    }

        System.out.println("Starting with: ");
        System.out.println("\tmode: "+mode);
        System.out.println("\tlang: "+lang);
        ASAPStarter aa = new ASAPStarter(mode, lang);
        ASAPThread ast = new ASAPThread(aa);
        ast.start();
    }

    private static JComponentEnvironment setupJComponentEnvironment()
    {
        final JComponentEnvironment jce = new JComponentEnvironment();
        try
        {
            SwingUtilities.invokeAndWait(() -> {
                mainJFrame.setLayout(new BorderLayout());

                JPanel jPanel = new JPanel();
                jPanel.setPreferredSize(new Dimension(400, 40));
                jPanel.setLayout(new GridLayout(1, 1));
                jce.registerComponent("textpanel", jPanel);
                mainJFrame.add(jPanel, BorderLayout.SOUTH);
            });
        }
        catch (InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        return jce;
    }

}