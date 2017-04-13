package nl.utwente.hmi.avatar.input.wozOutput;
/*
Code: Merijn Bruijnes
Date: Feb 2017
*/

import nl.utwente.hmi.avatar.input.Input;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


@SuppressWarnings("serial")
public class OutputGuiNL extends JFrame {
	private JPanel mainPanel;
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
		
	public OutputGuiNL() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}		
		
		
		final JButton btnIntro = new JButton("Intro: U heeft wat gezien?");	
		btnIntro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {					
				String intro = "Goedemiddag, ik begreep dat u getuige bent geweest van een poging tot inbraak. Kunt u vertellen wat u heeft gezien?";
				sayThis(intro,"smile");
			}
		});
		
		final JButton btnQ1 = new JButton("Verdachte personen beschrijven");	
		btnQ1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				String q1 = "U noemde een aantal verdachte personen, kunt u deze nog wat meer beschrijven?";
				sayThis(q1,"deictic_you");
			}
		});
		
		final JButton btnQ2 = new JButton("Auto beschrijven");	
		btnQ2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				String q2 = "En die auto waar ze wat probeerden, kunt u daar nog iets meer over zeggen?";
				sayThis(q2,"smile");
			}
		});
		
		final JButton btnQ3 = new JButton("Parkeerplaats beschrijven");	
		btnQ3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				String q3 = "En de parkeerplaats? Is u daar iets opgevallen, iets wat ik nog zou moeten noteren?";
				sayThis(q3,"why");
			}
		});
		
		final JButton btnQ4 = new JButton("Andere getuigen beschrijven");	
		btnQ4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				String q4 = "Ok, ik zou graag nog iets meer informatie krijgen over andere getuigen. Die hebben misschien nog meer informatie. Kunt u zich daar nog iets van herinneren?";
				sayThis(q4,"NOD");
			}
		});
		
		final JButton btnQ5 = new JButton("Eigenaren beschrijven");	
		btnQ5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				String q5 = "Wat weet u zich nog te herinneren van de eigenaren?";
				sayThis(q5,"pointCar");
			}
		});
		
		final JButton btnQ6 = new JButton("Wat deden verdachten?");	
		btnQ6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				String q6 = "Wat deden die verdachte personen precies?";
				sayThis(q6,"gazeCar");
			}
		});
		
		final JButton btnQ7 = new JButton("Waar kwamen verdachten vandaan");	
		btnQ7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				String q7 = "Van welke kant kwamen die verdachten eigenlijk aanlopen?";
				sayThis(q7,"indicateright");
			}
		});
		
		final JButton btnQ8 = new JButton("Waar gingen verdachten heen");	
		btnQ8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				String q8 = "En waar gingen de verdachten heen?";
				sayThis(q8,"indicateleft");
			}
		});
		final JButton btnQ9 = new JButton("Waar stond u?");	
		btnQ9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				String q9 = "Waar stond u ongeveer en stond u daar de hele tijd, of?";
				sayThis(q9,"deictic_you");
			}
		});
		
		final JButton btnQ10 = new JButton("Heeft u nog meer info");	
		btnQ10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				String q10 = "Heel mooi, ik heb denk ik een aardig beeld van wat er gebeurd is. Heeft u nog verdere dingen die u graag zou willen noemen?";
				sayThis(q10,"NOD");
			}
		});
		
		final JButton btnClose = new JButton("Afsluiting.");	
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				String close = "Goed, dank u voor uw medewerking. Mocht u later nog wat te binnen schieten, dan kunt u altijd even met de wijkagent bellen. Dank u wel, en tot ziens.";
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
					sayThis("Ja OK.","NOD");
				}
			}
		});
		
		final JButton btnJa = new JButton("Ja");
		btnJa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				int r = random.nextInt(2);
				if (r == 0 ){
					sayThis("Ja.","NOD");
				}else if (r == 1){
					sayThis("Jaja.","NOD");
				}
			}
		});
		
		final JButton btnNee = new JButton("Nee");
		btnNee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				int r = random.nextInt(2);
				if (r == 0 ){
					sayThis("Nee.","SHAKE");
				}else{
					sayThis("Nee.","sad");
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
		
		final JButton btnNou = new JButton("Nou");
		btnNou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				int r = random.nextInt(2);
				if (r == 0 ){
					sayThis("Nou.","why");
				}else{
					sayThis("Nou.","offer");
				}
			}
		});
		
		final JButton btnMooi = new JButton("Mooi");
		btnMooi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				sayThis("Mooi.","NOD");
			}
		});
		
//buttons for evade questions pane
		final JButton btnEvade1 = new JButton("Wat u wilt");
		btnEvade1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				sayThis("Wat u wilt.","stop");
			}
		});
		
		final JButton btnEvade2 = new JButton("Dat moet u zelf weten");
		btnEvade2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				sayThis("Dat moet u zelf weten.","BEAT_LOW");
			}
		});
		
		final JButton btnEvade3 = new JButton("Ga gerust verder");
		btnEvade3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				sayThis("Gaat u gerust verder.","offer");
			}
		});
		
		final JButton btnALLEvade4 = new JButton("Geen idee eigenlijk");
		btnALLEvade4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				sayThis("Geen idee eigenlijk.","contemplate");
			}
		});
		
		final JButton btnEvade5 = new JButton("Niet van belang denk ik");
		btnEvade5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				sayThis("Dat is niet van belang denk ik.","deictic_self");
			}
		});
		
		final JButton btnEvade6 = new JButton("En toen?");
		btnEvade6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				sayThis("En toen?","ask");
			}
		});
		
		final JButton btnEvade7 = new JButton("En daarna?");
		btnEvade7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				sayThis("En daarna?","ask");
			}
		});
		
		final JButton btnEvade8 = new JButton("En verder?");
		btnEvade8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				sayThis("En verder?","ask");
			}
		});
		
		final JButton btnEvade9 = new JButton("Weet u nog meer?");
		btnEvade9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				sayThis("Weet u nog meer?","deictic_you");
			}
		});
		
		final JButton btnEvade10 = new JButton("Magic button");
		btnEvade10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				int r = random.nextInt(4);
				if (r == 0 ){
					sayThis("Hoorde ik iemand iets over taart zeggen? Ik heb best trek!","deictic_self");
				}else if (r == 1 ) {
					sayThis("I am trying to sell fine leather jackets.","deictic_self");
				}else if (r == 2 ){
					sayThis("Wat heeft u een leuk pak aan.","joy");
				}else{
					sayThis("1, 2, 3. Ik ben AVATAR, en ik ga je leren dansen.","dans");
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
		mainPanel = new JPanel();
		mainPanel.setBounds(200, 500, 2000, 1000);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		final JLabel lbl = new JLabel("Politie: ");
		final JLabel lblInterviewQ = new JLabel("Vragen: ");
		final JLabel lblRemarks = new JLabel("Feedback: ");
		final JLabel lblEvadeQs = new JLabel ("Ontwijkgedrag: ");
		final JLabel lblOpenSpeech = new JLabel("Voer text in:   ");
		
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
		mainPanel.add(remarkPane,BorderLayout.WEST);
		mainPanel.add(Box.createRigidArea(new Dimension(25,0)));
		mainPanel.add(interviewPane, BorderLayout.CENTER);
		mainPanel.add(Box.createRigidArea(new Dimension(25,0)));
		mainPanel.add(evadeQPane, BorderLayout.EAST);
		mainPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		mainPanel.add(Box.createRigidArea(new Dimension(25,0)));
		mainPanel.add(openSpeechPane, BorderLayout.SOUTH);
		mainPanel.add(Box.createRigidArea(new Dimension(5,0)));
		mainPanel.add(openSpeechButtonPane, BorderLayout.NORTH);
		mainPanel.add(Box.createRigidArea(new Dimension(25,0)));
	}
}
