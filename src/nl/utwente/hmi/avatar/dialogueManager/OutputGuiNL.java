package nl.utwente.hmi.avatar.dialogueManager;
/*
Code: Merijn Bruijnes
Date: Feb 2017
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import java.awt.Component;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


@SuppressWarnings("serial")
public class OutputGuiNL extends JFrame {

	FileHandler fh;
	Logger logger = Logger.getLogger("MyLog");
	long bmlId = new Date().getTime();
	
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
	
	final String[] mark = null;

	public Random random = new Random(); 
	
	public String bmlBehaviour(String bmlBehaviourRequested){
		String bmlBehaviourOut ="";
		
		//String[] bmlOffer = {"<gesture id=\"","\" lexeme=\"offer\" start=\"speech","\" />"};
		//"<gaze id=\"","\" target=\"Car\"    start=\"bml",":speech",":sync","\" />"}
		
		//gesture bindings
		if (bmlBehaviourRequested.equals("offer")){
			bmlBehaviourOut = "<gesture id=\"offer"+bmlId+"\" lexeme=\"offer\" start=\"speech1:start\" />";
		} 
		else if (bmlBehaviourRequested.equals("SHAKE")){
			bmlBehaviourOut = "<head id=\"SHAKE"+bmlId+"\" lexeme=\"SHAKE\" start=\"speech1:start\" />";
		}
		else if (bmlBehaviourRequested.equals("NOD")){
			bmlBehaviourOut = "<head id=\"NOD"+bmlId+"\" lexeme=\"NOD\" start=\"speech1:start\" />";
		}
		else if (bmlBehaviourRequested.equals("BEAT_LOW")){
			bmlBehaviourOut = "<gesture id=\"BEAT_LOW"+bmlId+"\" lexeme=\"BEAT_LOW\" start=\"speech1:start\" />";
		}
		else if (bmlBehaviourRequested.equals("BEAT")){
			bmlBehaviourOut = "<gesture id=\"BEAT"+bmlId+"\" lexeme=\"BEAT\" start=\"speech1:start\" />";
		}
		else if (bmlBehaviourRequested.equals("BEAT")){
			bmlBehaviourOut = "<gesture id=\"BEAT"+bmlId+"\" lexeme=\"BEAT\" start=\"speech1:start\" />";
		}
		else if (bmlBehaviourRequested.equals("BEAT_MID")){
			bmlBehaviourOut = "<gesture id=\"BEAT_MID"+bmlId+"\" lexeme=\"BEAT_MID\" start=\"speech1:start\" />";
		}
		else if (bmlBehaviourRequested.equals("deictic_you")){
			bmlBehaviourOut = "<gesture id=\"deictic_you"+bmlId+"\" lexeme=\"deictic_you\" start=\"speech1:start\" />";
		}
		else if (bmlBehaviourRequested.equals("deictic_self")){
			bmlBehaviourOut = "<gesture id=\"deictic_self"+bmlId+"\" lexeme=\"deictic_self\" start=\"speech1:start\" />";
		}
		else if (bmlBehaviourRequested.equals("stop")){
			bmlBehaviourOut = "<gesture id=\"stop"+bmlId+"\" lexeme=\"stop\" start=\"speech1:start\" />";
		}
		else if (bmlBehaviourRequested.equals("why")){
			bmlBehaviourOut = "<gesture id=\"why"+bmlId+"\" lexeme=\"why\" start=\"speech1:start\" />";
		}
		else if (bmlBehaviourRequested.equals("indicateright")){
			bmlBehaviourOut = "<gesture id=\"indicateright"+bmlId+"\" lexeme=\"indicateright\" start=\"speech1:start\" />";
		}
		else if (bmlBehaviourRequested.equals("indicateleft")){
			bmlBehaviourOut = "<gesture id=\"indicateleft"+bmlId+"\" lexeme=\"indicateleft\" start=\"speech1:start\" />";
		}
		else if (bmlBehaviourRequested.equals("dismiss")){
			bmlBehaviourOut = "<gesture id=\"dismiss"+bmlId+"\" lexeme=\"dismiss\" start=\"speech1:start\" />";
		}
		else if (bmlBehaviourRequested.equals("contemplate")){
			bmlBehaviourOut = "<gesture id=\"contemplate"+bmlId+"\" lexeme=\"contemplate\" start=\"speech1:start\" />";
		}
		else if (bmlBehaviourRequested.equals("BEAT_CHOP")){
			bmlBehaviourOut = "<gesture id=\"BEAT_CHOP"+bmlId+"\" lexeme=\"BEAT_CHOP\" start=\"speech1:start\" />";
		}
		//directed pointing
		else if (bmlBehaviourRequested.equals("pointCar")){
			bmlBehaviourOut = "<pointing id=\"pointCar"+bmlId+"\"  start=\"speech1:start\" end=\"speech1:start+4\" target=\"Car\" />";
		}
		//directed gazing
		else if (bmlBehaviourRequested.equals("gazeCar")){
			bmlBehaviourOut = "<gaze id=\"gazeCar"+bmlId+"\" start=\"speech1:start\" end=\"speech1:start+4\" target=\"Car\" />";
		}
		//face bindings
		else if (bmlBehaviourRequested.equals("disgust")){
			bmlBehaviourOut = "<faceLexeme id=\"disgust"+bmlId+"\" lexeme=\"disgust\" start=\"speech1:start\" end=\"speech1:start+1\" />";
		}
		else if (bmlBehaviourRequested.equals("afraid")){
			bmlBehaviourOut = "<faceLexeme id=\"afraid"+bmlId+"\" lexeme=\"afraid\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
		}
		else if (bmlBehaviourRequested.equals("anger")){
			bmlBehaviourOut = "<faceLexeme id=\"anger"+bmlId+"\" lexeme=\"anger\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
		}
		else if (bmlBehaviourRequested.equals("sad")){
			bmlBehaviourOut = "<faceLexeme id=\"sad"+bmlId+"\" lexeme=\"sad\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
		}
		else if (bmlBehaviourRequested.equals("surprise")){
			bmlBehaviourOut = "<faceLexeme id=\"surprise"+bmlId+"\" lexeme=\"surprise\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
		}
		else if (bmlBehaviourRequested.equals("joy")){
			bmlBehaviourOut = "<faceLexeme id=\"joy"+bmlId+"\" lexeme=\"joy\" start=\"speech1:start\"end=\"speech1:start+1\" />";
		}
		else if (bmlBehaviourRequested.equals("ask")){
			bmlBehaviourOut = "<faceLexeme id=\"ask"+bmlId+"\" lexeme=\"ask\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
		}
		else if (bmlBehaviourRequested.equals("furrow")){
			bmlBehaviourOut = "<faceLexeme id=\"furrow"+bmlId+"\" lexeme=\"furrow\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
		}
		else if (bmlBehaviourRequested.equals("expectingbrows")){
			bmlBehaviourOut = "<faceLexeme id=\"expectingbrows"+bmlId+"\" lexeme=\"expectingbrows\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
		}
		else if (bmlBehaviourRequested.equals("think")){
			bmlBehaviourOut = "<faceLexeme id=\"think"+bmlId+"\" lexeme=\"think\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
		}
		else if (bmlBehaviourRequested.equals("happy")){
			bmlBehaviourOut = "<faceLexeme id=\"happy"+bmlId+"\" lexeme=\"happy\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
		}
		else if (bmlBehaviourRequested.equals("smile")){
			bmlBehaviourOut = "<faceLexeme id=\"smile"+bmlId+"\" lexeme=\"smile\" start=\"speech1:start\" end=\"speech1:start+1\"/>";
		}
		//voor jan
		else if (bmlBehaviourRequested.equals("dans")){
			bmlBehaviourOut = "<gesture id=\"dans"+(bmlId+1)+"\" lexeme=\"indicateleft\" start=\"speech1:start\" />"+
					"<gesture id=\"dans"+(bmlId+2)+"\" lexeme=\"indicateright\" start=\"speech1:start+1\" />"+
					"<gesture id=\"dans"+(bmlId+3)+"\" lexeme=\"indicateleft\" start=\"speech1:start+2\" />"+
					"<gesture id=\"dans"+(bmlId+4)+"\" lexeme=\"indicateright\" start=\"speech1:start+3\" />"+
					"<gesture id=\"dans"+(bmlId+5)+"\" lexeme=\"indicateleft\" start=\"speech1:start+4\" />"+
					"<gesture id=\"dans"+(bmlId+6)+"\" lexeme=\"indicateright\" start=\"speech1:start+5\" />";
		}
		
		return bmlBehaviourOut;
	}
	
	public void sendBml(String bml) {
		String prefix = "{ \"bml\": { \"content\": \"";
		String suffix = "\" } }";
		
		try {
			bml = bml.replace("\r", " ").replace("\n", " ");
			String jsonthing = prefix+URLEncoder.encode(bml, "UTF-8")+suffix;
			System.out.println("[sendBml] Sending: "+bml);
			WOz.con.send(jsonthing, WOz.appolloTopic);
		} catch (UnsupportedEncodingException e) {
			System.out.println("[sendBml] Encoding failed.");
		}
	}
	
	public void sayThis(String answer, String behaviour) {
        String prefix = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\" id=\"bml"+(++bmlId)+"\" composition=\"REPLACE\"><speech id=\"speech1\" start=\"0\"><text>";
        
        behaviour = bmlBehaviour(behaviour);
        
        String suffix = "</text></speech>"+behaviour+"</bml>";
       // System.out.println(prefix+answerleukebml[0]+suffix);
        sendBml(prefix+answer+suffix);
		logger.info(answer);
    }
	
		
	public OutputGuiNL() {
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
