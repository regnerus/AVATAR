package nl.utwente.hmi.avatar.input;

/*
Code: Merijn Bruijnes
Date: Feb 2017
*/

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


@SuppressWarnings("serial")
public class OutputGuiEN extends JFrame {
    private JPanel contentPane;
    private JPanel interviewPane;
    private JPanel remarkPane;
    private JPanel evadeQPane;
    private JPanel openSpeechPane;
    private JPanel openSpeechButtonPane;

    static String openSpeech1 = "";
    static String openSpeech2 = "";
    static String openSpeech3 = "";
    static String SayThisEmo = "";

    public Random random = new Random();

    public void sayThis(String answer, String behaviour) {
        Input.sendInput(answer, behaviour);
    }

    public OutputGuiEN() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }


        //final JButton btnIntro = new JButton("Intro: U heeft wat gezien?");
        final JButton btnIntro = new JButton("Intro: You saw something?");
        btnIntro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //String intro = "Goedemiddag, ik begreep dat u getuige bent geweest van een poging tot inbraak. Kunt u vertellen wat u heeft gezien?";
                String intro = "Good day, I understood that you witnessed an attempted break in. Could you please tell me what you saw?";
                sayThis(intro,"smile");
            }
        });

        //final JButton btnQ1 = new JButton("Verdachte personen beschrijven");
        final JButton btnQ1 = new JButton("Describe suspects");
        btnQ1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //String q1 = "U noemde een aantal verdachte personen, kunt u deze nog wat meer beschrijven?";
                String q1 = "You mentioned some suspicious persons, could you describe them some more?";
                sayThis(q1,"deictic_you");
            }
        });

        //final JButton btnQ2 = new JButton("Auto beschrijven");
        final JButton btnQ2 = new JButton("Describe car");
        btnQ2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //String q2 = "En die auto waar ze wat probeerden, kunt u daar nog iets meer over zeggen?";
                String q2 = "And the car where they tried something, could you say something more about it?";
                sayThis(q2,"smile");
            }
        });

        //final JButton btnQ3 = new JButton("Parkeerplaats beschrijven");
        final JButton btnQ3 = new JButton("Describe parking lot");
        btnQ3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //String q3 = "En de parkeerplaats? Is u daar iets opgevallen, iets wat ik nog zou moeten noteren?";
                String q3 = "And the parking lot? Did you notice anything there, something that I should note down?";
                sayThis(q3,"why");
            }
        });

        //final JButton btnQ4 = new JButton("Andere getuigen beschrijven");
        final JButton btnQ4 = new JButton("Describe other witnesses");
        btnQ4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //String q4 = "Ok, ik zou graag nog iets meer informatie krijgen over andere getuigen. Die hebben misschien nog meer informatie. Kunt u zich daar nog iets van herinneren?";
                String q4 = "I would like some more information about other witnesses. Perhaps they have some more information for me. Can you remember any other witnesses?";
                sayThis(q4,"NOD");
            }
        });

        //final JButton btnQ5 = new JButton("Eigenaren beschrijven");
        final JButton btnQ5 = new JButton("Describe owners");
        btnQ5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //String q5 = "Wat weet u zich nog te herinneren van de eigenaren?";
                String q5 = "What do you remember of the owners?";
                sayThis(q5,"pointCar");
            }
        });

        //final JButton btnQ6 = new JButton("Wat deden verdachten?");
        final JButton btnQ6 = new JButton("What did the suspects do");
        btnQ6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //String q6 = "Wat deden die verdachte personen precies?";
                String q6 = "What did the suspects do exactly?";
                sayThis(q6,"gazeCar");
            }
        });

        //final JButton btnQ7 = new JButton("Waar kwamen verdachten vandaan");
        final JButton btnQ7 = new JButton("Where suspects came from");
        btnQ7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //String q7 = "Van welke kant kwamen die verdachten eigenlijk aanlopen?";
                String q7 = "From which side did the suspects come?";
                sayThis(q7,"indicateright");
            }
        });

        //final JButton btnQ8 = new JButton("Waar gingen verdachten heen");
        final JButton btnQ8 = new JButton("Where suspects went");
        btnQ8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //String q8 = "En waar gingen de verdachten heen?";
                String q8 = "And where did the suspects go?";
                sayThis(q8,"indicateleft");
            }
        });

        //final JButton btnQ9 = new JButton("Waar stond u?");
        final JButton btnQ9 = new JButton("Where were you");
        btnQ9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //String q9 = "Waar stond u ongeveer en stond u daar de hele tijd, of?";
                String q9 = "Whereabout were you standing and were you standing there all the time, or?";
                sayThis(q9,"deictic_you");
            }
        });

        //final JButton btnQ10 = new JButton("Heeft u nog meer info");
        final JButton btnQ10 = new JButton("More info?");
        btnQ10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //String q10 = "Heel mooi, ik heb denk ik een aardig beeld van wat er gebeurd is. Heeft u nog verdere dingen die u graag zou willen noemen?";
                String q10 = "Very nice, I think I have a good idea about what happenened. Do you have other things you would like to mention?";
                sayThis(q10,"NOD");
            }
        });

        //final JButton btnClose = new JButton("Afsluiting.");
        final JButton btnClose = new JButton("Closing.");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //String close = "Goed, dank u voor uw medewerking. Mocht u later nog wat te binnen schieten, dan kunt u altijd even met de wijkagent bellen. Dank u wel, en tot ziens.";
                String close = "Alright, thank you for your cooperation. If you think of anything else later, please feel free to contact your local police officer. Thank you, and good bye.";
                sayThis(close,"NOD");
            }
        });


//buttons for remarks pane ('ok' etc)
        final JButton btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int r = random.nextInt(3);
                if (r == 0 ){
                    sayThis("OK.","NOD");
                }else if (r == 1){
                    sayThis("Ah, OK.","smile");
                }else {
                    sayThis("OK.","smile");
                }
            }
        });

        final JButton btnJa = new JButton("Yes");
        btnJa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int r = random.nextInt(2);
                if (r == 0 ){
                    sayThis("Yes.","NOD");
                }else if (r == 1){
                    sayThis("Yes.","smile");
                }
            }
        });

        final JButton btnNee = new JButton("No");
        btnNee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int r = random.nextInt(2);
                if (r == 0 ){
                    sayThis("No.","SHAKE");
                }else{
                    sayThis("No.","sad");
                }
            }
        });

        final JButton btnMh = new JButton("Mh");
        btnMh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int r = random.nextInt(2);
                if (r == 0 ){
                    sayThis("Ah.","smile");
                }else{
                    sayThis("Hm.","NOD");
                }
            }
        });

        final JButton btnNou = new JButton("Well...");
        btnNou.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int r = random.nextInt(2);
                if (r == 0 ){
                    sayThis("Well...","why");
                }else{
                    sayThis("Well...","offer");
                }
            }
        });

        final JButton btnMooi = new JButton("Good");
        btnMooi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int r = random.nextInt(2);
                if (r == 0 ){
                    sayThis("Good.","NOD");
                }else{
                    sayThis("Good.","smile");
                }
            }
        });

//buttons for evade questions pane
        final JButton btnEvade1 = new JButton("What you want");
        btnEvade1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                sayThis("What you want.","stop");
            }
        });

        final JButton btnEvade2 = new JButton("Up to you");
        btnEvade2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                sayThis("That is up to you.","BEAT_LOW");
            }
        });

        final JButton btnEvade3 = new JButton("Please continue");
        btnEvade3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                sayThis("Please continue.","offer");
            }
        });

        final JButton btnALLEvade4 = new JButton("I'm not sure");
        btnALLEvade4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                sayThis("Actually, I'm not sure.","contemplate");
            }
        });

        final JButton btnEvade5 = new JButton("Not important");
        btnEvade5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                sayThis("That isn't really important, I think.","deictic_self");
            }
        });

        final JButton btnEvade6 = new JButton("And then?");
        btnEvade6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                sayThis("And then?","ask");
            }
        });

        final JButton btnEvade7 = new JButton("After that?");
        btnEvade7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                sayThis("And after than?","ask");
            }
        });

        final JButton btnEvade8 = new JButton("What else?");
        btnEvade8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                sayThis("And what else?","ask");
            }
        });

        final JButton btnEvade9 = new JButton("Do you know more?");
        btnEvade9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                sayThis("Do you know more?","deictic_you");
            }
        });

        final JButton btnEvade10 = new JButton("Magic button");
        btnEvade10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int r = random.nextInt(4);
                if (r == 0 ){
                    sayThis("Did I hear someone mention cake? I'm actually hungry!","deictic_self");
                }else if (r == 1 ) {
                    sayThis("I am trying to sell fine leather jackets.","deictic_self");
                }else if (r == 2 ){
                    sayThis("I'd like to complement you on your nice outfit.","joy");
                }else{
                    sayThis("1, 2, 3. I'm AVATAR and I'm gonna teach you how to dance.","dans");
                }

            }
        });

        //open speech input
        final JTextField openSpeechField1 = new JTextField(openSpeech1);
        openSpeechField1.setBounds(10, 50, 190, 40);
        final JTextField openSpeechField2 = new JTextField(openSpeech2);
        openSpeechField2.setBounds(80, 50, 190, 40);
        final JTextField openSpeechField3 = new JTextField(openSpeech3);
        openSpeechField3.setBounds(150, 50, 190, 40);

        final JButton openSpeechEmo1Send = new JButton("Say it happy");
        openSpeechEmo1Send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SayThisEmo = "happy";
            }
        });
        final JButton openSpeechEmo2Send = new JButton("Say it angry");
        openSpeechEmo2Send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SayThisEmo = "anger";
            }
        });
        final JButton openSpeechEmo3Send = new JButton("Say it sad");
        openSpeechEmo3Send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SayThisEmo = "sad";
            }
        });
        final JButton openSpeechEmo4Send = new JButton("Say it afraid");
        openSpeechEmo4Send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SayThisEmo = "afraid";
            }
        });
        final JButton openSpeechEmo5Send = new JButton("Say it neutral");
        openSpeechEmo5Send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SayThisEmo = "";
            }
        });

        final JButton openSpeech1Send = new JButton("Say this!");
        openSpeech1Send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                openSpeech1 = openSpeechField1.getText();
                sayThis(openSpeech1,SayThisEmo);
            }
        });
        final JButton openSpeech2Send = new JButton("Say this!");
        openSpeech2Send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                openSpeech2 = openSpeechField2.getText();
                sayThis(openSpeech2,SayThisEmo);
            }
        });
        final JButton openSpeech3Send = new JButton("Say this!");
        openSpeech3Send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                openSpeech3 = openSpeechField3.getText();
                sayThis(openSpeech3,SayThisEmo);
            }
        });

        //create panes and labels
        contentPane = new JPanel();
        contentPane.setBounds(200, 500, 2000, 1000);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        final JLabel lbl = new JLabel("Police: ");
        final JLabel lblInterviewQ = new JLabel("Questions: ");
        final JLabel lblRemarks = new JLabel("Feedback: ");
        final JLabel lblEvadeQs = new JLabel ("Evasive: ");
        final JLabel lblOpenSpeech = new JLabel("Enter text:   ");

        openSpeechPane = new JPanel();
        openSpeechPane.setLayout(new BoxLayout(openSpeechPane, BoxLayout.Y_AXIS));
        openSpeechPane.setAlignmentY(Component.TOP_ALIGNMENT);
        openSpeechPane.add(lblOpenSpeech);
        openSpeechPane.add(Box.createRigidArea(new Dimension(100,10)));
        openSpeechPane.add(openSpeechField1);
        openSpeechPane.add(Box.createRigidArea(new Dimension(100,5)));
        openSpeechPane.add(openSpeechField2);
        openSpeechPane.add(Box.createRigidArea(new Dimension(100,5)));
        openSpeechPane.add(openSpeechField3);

        openSpeechButtonPane = new JPanel();
        openSpeechButtonPane.setLayout(new BoxLayout(openSpeechButtonPane, BoxLayout.Y_AXIS));
        openSpeechButtonPane.setAlignmentY(Component.TOP_ALIGNMENT);
        //openSpeechButtonPane.add(lblOpenSpeech);
        openSpeechButtonPane.add(Box.createRigidArea(new Dimension(0,160)));
        openSpeechButtonPane.add(openSpeech1Send);
        openSpeechButtonPane.add(openSpeech2Send);
        openSpeechButtonPane.add(openSpeech3Send);
        openSpeechButtonPane.add(Box.createRigidArea(new Dimension(0,10)));
        openSpeechButtonPane.add(openSpeechEmo1Send);
        openSpeechButtonPane.add(openSpeechEmo2Send);
        openSpeechButtonPane.add(openSpeechEmo3Send);
        openSpeechButtonPane.add(openSpeechEmo4Send);
        openSpeechButtonPane.add(openSpeechEmo5Send);


        //add the remarks like 'ok' 'uhuh', should be left
        remarkPane = new JPanel();
        remarkPane.setLayout(new BoxLayout(remarkPane, BoxLayout.Y_AXIS));
        remarkPane.setAlignmentY(Component.TOP_ALIGNMENT);
        remarkPane.add(lblRemarks);
        remarkPane.add(Box.createRigidArea(new Dimension(0,15)));
        remarkPane.add(btnOK);
        remarkPane.add(btnJa);
        remarkPane.add(btnNee);
        remarkPane.add(btnMh);
        remarkPane.add(btnNou);
        remarkPane.add(btnMooi);
        remarkPane.add(Box.createRigidArea(new Dimension(0,25)));
        remarkPane.add(Box.createRigidArea(new Dimension(0,15)));

        //add the buttons for the questions, should be center aligned
        interviewPane = new JPanel();
        interviewPane.setLayout(new BoxLayout(interviewPane, BoxLayout.Y_AXIS));
        interviewPane.add(lbl);
        interviewPane.add(lblInterviewQ);
        interviewPane.add(Box.createRigidArea(new Dimension(0,15)));
        interviewPane.add(btnIntro);
        interviewPane.add(Box.createRigidArea(new Dimension(0,15)));
        interviewPane.add(btnQ1);
        interviewPane.add(btnQ2);
        interviewPane.add(btnQ3);
        interviewPane.add(btnQ4);
        interviewPane.add(btnQ5);
        interviewPane.add(btnQ6);
        interviewPane.add(btnQ7);
        interviewPane.add(btnQ8);
        interviewPane.add(btnQ9);
        interviewPane.add(btnQ10);
        interviewPane.add(Box.createRigidArea(new Dimension(0,15)));
        interviewPane.add(btnClose);

        //add the responses to evade answers, right aligned
        evadeQPane = new JPanel();
        evadeQPane.setLayout(new BoxLayout(evadeQPane, BoxLayout.Y_AXIS));
        evadeQPane.setAlignmentY(Component.TOP_ALIGNMENT);
        evadeQPane.add(lblEvadeQs);
        evadeQPane.add(Box.createRigidArea(new Dimension(0,15)));
        evadeQPane.add(btnEvade1);
        evadeQPane.add(btnEvade2);
        evadeQPane.add(btnEvade3);
        evadeQPane.add(btnALLEvade4);
        evadeQPane.add(btnEvade5);
        evadeQPane.add(btnEvade6);
        evadeQPane.add(btnEvade7);
        evadeQPane.add(btnEvade8);
        evadeQPane.add(btnEvade9);
        evadeQPane.add(btnEvade10);

        //add panes
        contentPane.add(remarkPane,BorderLayout.WEST);
        contentPane.add(Box.createRigidArea(new Dimension(25,0)));
        contentPane.add(interviewPane, BorderLayout.CENTER);
        contentPane.add(Box.createRigidArea(new Dimension(25,0)));
        contentPane.add(evadeQPane, BorderLayout.EAST);
        contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
        contentPane.add(Box.createRigidArea(new Dimension(25,0)));
        contentPane.add(openSpeechPane, BorderLayout.SOUTH);
        contentPane.add(Box.createRigidArea(new Dimension(5,0)));
        contentPane.add(openSpeechButtonPane, BorderLayout.NORTH);
        contentPane.add(Box.createRigidArea(new Dimension(25,0)));
    }
}