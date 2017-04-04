package nl.utwente.hmi.avatar.dialogueManager.QAMatcher;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import org.json.simple.parser.JSONParser;

//import com.sun.xml.internal.ws.util.StringUtils;

import pk.aamir.stompj.Connection;
import pk.aamir.stompj.ErrorHandler;
import pk.aamir.stompj.ErrorMessage;
import pk.aamir.stompj.Message;
import pk.aamir.stompj.MessageHandler;
import pk.aamir.stompj.StompJException;

public class TestQAResponder implements ErrorHandler, MessageHandler {

	
	String [][] keywordList = {	//gestures
								{" ik", "<gesture id=\"","\" lexeme=\"deictic_self\" start=\"bml",":speech",":sync","\" />"},								
								{"mijzelf", "<gesture id=\"","\" lexeme=\"deictic_self\" start=\"bml",":speech",":sync","\" />"},
								{" u ", "<gesture id=\"","\" lexeme=\"deictic_you\" start=\"bml",":speech",":sync","\" />"},
								{"beetje", "<gesture id=\"","\" lexeme=\"BEAT_CHOP\" start=\"bml",":speech",":sync","\" />"},
								{"dus", "<gesture id=\"","\" lexeme=\"BEAT_LOW\" start=\"bml",":speech",":sync","\" />"},
								{"niet", "<gesture id=\"","\" lexeme=\"stop\" start=\"bml",":speech",":sync","\" />"},
								{"en toen", "<gesture id=\"","\" lexeme=\"contemplate\" start=\"bml",":speech",":sync","\" />"},
			   					{"de auto", "<gesture id=\"","\" lexeme=\"indicateright\" start=\"bml",":speech",":sync","\" />"},
								{"rechts", "<gesture id=\"","\" lexeme=\"indicateright\" start=\"bml",":speech",":sync","\" />"},
								{"mannen", "<gesture id=\"","\" lexeme=\"indicateleft\" start=\"bml",":speech",":sync","\" />"},
								{"links", "<gesture id=\"","\" lexeme=\"indicateleft\" start=\"bml",":speech",":sync","\" />"},
								{"types", "<gesture id=\"","\" lexeme=\"indicateleft\" start=\"bml",":speech",":sync","\" />"},
								//gazes
			   					{"eigenaren", "<gaze id=\"","\" target=\"VictimArrivalDirection\" start=\"bml",":speech",":sync","\" />"},
			   					{"eigenaar", "<gaze id=\"","\" target=\"VictimArrivalDirection\" start=\"bml",":speech",":sync","\" />"},
								//facelexemes
								{"hallo", "<faceLexeme id=\"","\" lexeme=\"happy\" start=\"bml",":speech",":sync","\" />"},
								{" ok ", "<faceLexeme id=\"","\" lexeme=\"happy\" start=\"bml",":speech",":sync","\" />"},
								{" ok.", "<faceLexeme id=\"","\" lexeme=\"happy\" start=\"bml",":speech",":sync","\" />"},
								{" ok,", "<faceLexeme id=\"","\" lexeme=\"happy\" start=\"bml",":speech",":sync","\" />"},
								{"goed", "<faceLexeme id=\"","\" lexeme=\"joy\" start=\"bml",":speech",":sync","\" />"},
								{"best", "<faceLexeme id=\"","\" lexeme=\"smile\" start=\"bml",":speech",":sync","\" />"},
								{"raam", "<faceLexeme id=\"","\" lexeme=\"anger\" start=\"bml",":speech",":sync","\" />"},
								{"forceren", "<faceLexeme id=\"","\" lexeme=\"anger\" start=\"bml",":speech",":sync","\" />"},
								{"betrouwbaar", "<faceLexeme id=\"","\" lexeme=\"anger\" start=\"bml",":speech",":sync","\" />"},
								{"geschrokken", "<faceLexeme id=\"","\" lexeme=\"afraid\" start=\"bml",":speech",":sync","\" />"},
								{" eng ", "<faceLexeme id=\"","\" lexeme=\"afraid\" start=\"bml",":speech",":sync","\" />"},
								{"bang", "<faceLexeme id=\"","\" lexeme=\"afraid\" start=\"bml",":speech",":sync","\" />"},
								{" gek ", "<faceLexeme id=\"","\" lexeme=\"surprise\" start=\"bml",":speech",":sync","\" />"},
								{" gek.", "<faceLexeme id=\"","\" lexeme=\"surprise\" start=\"bml",":speech",":sync","\" />"},
								{" gek,", "<faceLexeme id=\"","\" lexeme=\"surprise\" start=\"bml",":speech",":sync","\" />"},
								{"sorry", "<faceLexeme id=\"","\" lexeme=\"sad\" start=\"bml",":speech",":sync","\" />"},
								{"toch", "<faceLexeme id=\"","\" lexeme=\"ask\" start=\"bml",":speech",":sync","\" />"},
								{"denk", "<faceLexeme id=\"","\" lexeme=\"think\" start=\"bml",":speech",":sync","\" />"},
								{"lijkt", "<faceLexeme id=\"","\" lexeme=\"think\" start=\"bml",":speech",":sync","\" />"},
								{"weet", "<faceLexeme id=\"","\" lexeme=\"furrow\" start=\"bml",":speech",":sync","\" />"},
								//head
								{" ja.", "<head id=\"","\" lexeme=\"NOD\" start=\"bml",":speech",":sync","\" />"},
								{" ja,", "<head id=\"","\" lexeme=\"NOD\" start=\"bml",":speech",":sync","\" />"},
								{" ja ", "<head id=\"","\" lexeme=\"NOD\" start=\"bml",":speech",":sync","\" />"},
								{" ok.", "<head id=\"","\" lexeme=\"NOD\" start=\"bml",":speech",":sync","\" />"},
								{" ok,", "<head id=\"","\" lexeme=\"NOD\" start=\"bml",":speech",":sync","\" />"},
								{" ok ", "<head id=\"","\" lexeme=\"NOD\" start=\"bml",":speech",":sync","\" />"},
								{" nee ", "<head id=\"","\" lexeme=\"SHAKE\" start=\"bml",":speech",":sync","\" />"},
								{" nee,", "<head id=\"","\" lexeme=\"SHAKE\" start=\"bml",":speech",":sync","\" />"},
								{" nee.", "<head id=\"","\" lexeme=\"SHAKE\" start=\"bml",":speech",":sync","\" />"},
			  				};
	
	//static Connection con;
    private Connection con;
	private Boolean suspectScenario = true;
	private static String inTopic;
	private static String outTopic;
	private static String feedbackTopic;
	private static String speechfinalTopic;
    private int bmlId = 0;//new Date().getTime();
	
    //private static long i=0;
    private Boolean readyforBML = true;
    private String leukebmlcalls = "";
	private int syncpointcounter = 0;
    
    //static String apolloIP = "130.89.12.234";
	static String apolloIP = "127.0.0.1";
	static int apolloPort = 61613;
	static TestQAResponder testqaresponder = new TestQAResponder(apolloIP, apolloPort);
	
	JSONParser parser = new JSONParser();
	
	//the specification of the matching Q and A's in directory resources
	static String filename = "vragen.xml";
	
	//de qa parser etc
	static DomDialogsParser ddp = new DomDialogsParser(filename);
	static DialogStore store= ddp.getDialogStore();
    
    public TestQAResponder(String apolloIP, int apolloPort){
		inTopic = "/topic/CleVRCmdFeedback";//CleVRFeedback dingetje
		outTopic = "/topic/bmlRequests";//CleVRCmd
		feedbackTopic = "/topic/bmlFeedback"; //asap bml feedback
		speechfinalTopic = "/topic/speechFinal";
		//inTopic = "/topic/bmlFeedback";//CleVRFeedback dingetje
		//outTopic = "/topic/bmlRequests";//CleVRCmd
		//<MiddlewareProperty name="iTopic" value="/topic/CleVRFeedback"/>
        //<MiddlewareProperty name="oTopic" value="/topic/CleVRCmd"/>
        //<MiddlewareProperty name="iTopic" value="/topic/bmlRequests"/>
        //<MiddlewareProperty name="oTopic" value="/topic/bmlFeedback"/>
        //bmlId = 0;

        try {
            con = new Connection(apolloIP, apolloPort, "admin", "password");
            con.setErrorHandler(this);
			con.connect();
			System.out.println("Connection initialised.");
		} catch (StompJException e) {
			System.out.println("Error while initialising STOMP connection: "+e.getMessage());
			e.printStackTrace();
			return;
		}
        
        try {
			//topiclisteners clevr
			con.subscribe(inTopic, true);
			con.addMessageHandler(inTopic, this);
			
			//topic speech asr ding
			con.subscribe(speechfinalTopic, true);
			con.addMessageHandler(speechfinalTopic, this);
			
			//topiclisteners bmlfeedback
			con.subscribe(feedbackTopic, true);
			con.addMessageHandler(feedbackTopic, this);
			
			System.out.println("Subscriptions done.");			
		} catch (Exception e) {
			System.out.println("Error while subscribing: "+e.getMessage());
			e.printStackTrace();
			return;
		}
    }
    
   
	
	public static void main(String[] args){
		String query = "";
		
		System.out.println("Question: ");
		
		//while (true){ 
		while ((query = Console.readString()) != ""){
			String answer = getAnswer(query);
			ttsFriendlyAnswer(answer);
		}
		//answer = LeukereBMLs(answer);
	}
	
	
	public String[] LeukereBMLs(String answerIN, int bmlSpeechID) {
        //per keyword
         
        answerIN =  answerIN.toLowerCase();
        String[] inOut = {answerIN,""};
        int indexKey = 0;
        if (indexKey==keywordList.length) {
        	return inOut; // we zijn klaar
        } else {
        	replace(inOut, indexKey, keywordList, syncpointcounter, bmlSpeechID);  
        }
        return inOut;
	}
	
	
	// Deze recursive methode levert in out de resultaat string op.
	// indexKey is de index van het keyword dat vervangen wordt.
	// counter is de counter die gebruikt wordt voor het eerst volgende sync point.
	private void replace(String[] inOut, int indexKey, String[][] keywordList, int syncpointcounter, int bmlSpeechID){
	        // proces de string inout en geef deze een nieuwe waarde voordat je deze
	        // methode weer aanroept 
	        
	        int aantalKw = 0;
	        // tel hoe vaak het kewyord voorkomt in inout.
	        String [] bmlBehaviours = {""};
	        String keyword = keywordList[indexKey][0];
	        String action = "";
	            
	        //bepalen hoeveel keer dit keyword in de anwswerIN zit
	        int idx = 0;
	        //aantalKw = 0;
	        while ((idx = inOut[0].indexOf(keywordList[indexKey][0], idx)) != -1)
	        {
	            idx++;
	            aantalKw++;
	        }
	        //System.out.println("Aantal maal dat \""+keyword+"\" voorkomt: "+aantalKw);
	        
	        if (aantalKw==0){
	        	indexKey++;
	        	if (indexKey==keywordList.length){
	        		return; // we zijn klaar
	        	} else {
	        		replace(inOut, indexKey, keywordList, syncpointcounter, bmlSpeechID); // volgende keyword afhandelen 
	        	}
	        }
	        else { 
	            // vervang de string[] inOut door een nieuwe string 
	        	//code waarin iedere instantie van het keyword vervangt door een stukje string
	        	
	        	int nextIndex = inOut[0].indexOf(keyword), oldIndex = 0;
	        	String tempnew = "";
	        	//counter = 0;
				while(nextIndex != -1) {
					tempnew += inOut[0].substring(oldIndex, nextIndex) + " <sync id=\"sync" + syncpointcounter + "\" /> ";
					//System.out.println("in while loop, tempnew: "+tempnew);
				    oldIndex = nextIndex;
				    nextIndex = inOut[0].indexOf(keyword, nextIndex+1);
				    ++syncpointcounter; 
				}
				tempnew += inOut[0].substring(oldIndex);
				
				inOut[0] = tempnew;
				//System.out.println(inOut[0]);
				
				addBehaviour(inOut, indexKey, keywordList, syncpointcounter, bmlSpeechID, aantalKw);
				
	        	//inOut = ... ;
	        	indexKey++;
	        	if (indexKey == keywordList.length) return;
	        	// roep replace opnieuw aan met het volgende keyword en een nieuwe counter waarde
	        	replace(inOut, indexKey, keywordList, syncpointcounter, bmlSpeechID);
	        } //System.out.println(inOut[0]);
	        
	}
	
	
	public void addBehaviour(String[] inOut, int indexKey, String[][] keywordList, int syncpointcounter, int bmlSpeechID, int aantalKw){
		// dan de gestures toevoegen en relateren aan de syncpoints
		String temp = "";
		if (aantalKw == 0){
			return; //klaar: bij geen keywords hoeft er geen behaviour te worden toegevoegd
		}
		String keyword = keywordList[indexKey][0];
		
		int tellertje = (syncpointcounter - aantalKw); //0;//sync# tellertje
		//System.out.println(syncpointcounter+" "+aantalKw+" "+tellertje);
		
		for (int k =0; k < aantalKw; k++){ //voor elk keyword-occurance (syncpoint in de text) moeten we een behaviour toevoegen
			//System.out.println("k: "+k);
			//for het k-de keyword, de bml tags die gemaakt moeten worden (verzamelen in inOut[1], later achter alle speechtags plaatsten)
			for (int j = 1; j < keywordList[ indexKey ].length; j++){
				//voorbeeld: {"ik", "<gesture id=\"","\" lexeme=\"offer\" start=\"bml",":speech",":sync","\" />"}
				//				0	1				  2									3		  4 	  5
				if (j==0){
					//dit is altijd het keyword, dus niets doen
				}else if (j==1){
					temp = temp+keywordList[indexKey][j]+keyword+k; //"<gesture id=\""+ (k dus voor elk keyword wordt er geteld hoevaak ze voorkomen: resultaat bv: "ik ik" id=ik0, id=ik1)
				}else if (j==2){
					temp = temp+keywordList[indexKey][j]+bmlSpeechID;//"\" lexeme=\"offer\" start=\"bml"+
				}else if (j==3){
					temp = temp+keywordList[indexKey][j]+0;//":speech"+
				}else if (j==4){
					temp = temp+keywordList[indexKey][j]+tellertje;//":sync"+
				}else if (j==5){
					temp = temp+keywordList[indexKey][j];//"\" />"
					//hier is temp een volledige behaviour tag
					//System.out.println("temp j : "+temp);
				}							
				//System.out.print(keywords[ i ][ j ] + " ");
				//System.out.println("temp: "+temp);
				//bmlBehaviours[i] = temp;				
            }
			//hier temp (volledige btag) toevoegen aan een vorige btag
			//System.out.println("temp k : "+temp);
			inOut[1] += temp;
			//System.out.println("inOut[1]: "+inOut[1]);
			temp = "";
			tellertje++;
		}
	}
	
	public void sendBml(String bml) {
		String prefix = "{ \"bml\": { \"content\": \"";
		String suffix = "\" } }";
		
		bml = bml.replace("  ", " ");//dubbele spaties uit de bml verwijderen
		
		try {
			bml = bml.replace("\r", " ").replace("\n", " ");
			String jsonthing = prefix+URLEncoder.encode(bml, "UTF-8")+suffix;
			//jsonthing = jsonthing.replace("%0A", " ");//%0A
			//System.out.println(bml);
			
			if (suspectScenario == true){
				//System.out.println(jsonthing);
				con.send(jsonthing, outTopic);
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("[sendBml] Encoding failed.");
		}
	}

    public void sayshort(String answer) {
        String prefixspeech = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\" id=\"bml"+(++bmlId)+"\" composition=\"REPLACE\"><speech id=\"speech0\" start=\"0\"><text>";
        String prefixbehaviour = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\" id=\"bml"+(bmlId)+"behaviour\">";
		
		
        String answerleukebml[] = LeukereBMLs(answer,bmlId); // 2e parameter is speechid, kan aangepast worden om naar bmlids te verwijzen
        
        //String suffix = "</text></speech>"+answerleukebml[1]+"</bml>";
		String suffixspeech = "</text></speech>"+"</bml>";
		String suffixbehaviour = "</bml>";
       // System.out.println(prefix+answerleukebml[0]+suffix);
	   
        sendBml(prefixspeech+answerleukebml[0]+suffixspeech); //speech
		try {
		Thread.sleep(20);//dit is lelijk maar cruciaal om verkeerde volgorde van ontvangen berichten te ondervangen
		} 
		catch (Exception e) {
			
		}
		sendBml(prefixbehaviour+answerleukebml[1]+suffixbehaviour); //behaviour
		
		answerleukebml[0] = ""; 
		answerleukebml[1] = "";
		
    }
    
    public void saylong (String answer){
		String subanswer = "";
		//String subanswerleukebml[];// = {"",""};
		String longanswer = "";
		String startAt = "0";
		int j = 0;
		
		String prefix = "";
		String suffix = "";
		
		//zoeken in lange antwoorden naar punten, op de punten splitsen we de string en maken er een langere bml van
		while (answer.contains(".")){
			prefix = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\" id=\"bml"+(++bmlId)+"\" >";//composition=\"APPEND\" //MERGE (default), APPEND, REPLACE
			
			int i = answer.indexOf( '.' );
			//System.out.println("answer1: "+answer);
			
			subanswer = answer.substring(0, (i+1));
			//System.out.println("subanswer1: "+subanswer);
			
			String subanswerleukebml[] = LeukereBMLs(subanswer,j); //j is speechID moet nog aangepast naar bmlID
			//System.out.println("j: "+j);
			//System.out.println("subbml1: "+subanswerleukebml[0]+subanswerleukebml[1]);
			
			answer = answer.replace(subanswer, "");
			//System.out.println("answer2: "+answer);
			if (j==0){
				startAt = "0";
			}else {
				startAt = "bml"+(bmlId-1)+":speech"+(j-1)+":end";
			}		
			
			longanswer = "<speech id=\"speech"+j+"\" start=\""+startAt+"\"><text>"+subanswerleukebml[0]+"</text></speech>";
			
			j++;

			suffix = subanswerleukebml[1]+"</bml>";

			sendBml(prefix+longanswer+suffix);
			
			//dingen leegmaken voor nieuwe subanswer
			subanswerleukebml[0] = "";
			subanswerleukebml[1] = "";
			suffix = "</bml>";
		}
		j=0;
    }
	
	@Override
	public void onMessage(Message msg) {			

		String msgstring = msg.getContentAsString();
		//System.out.println(msgstring);
		
		if (msgstring.substring(0, 5) .equals("{ \"se")){
			//asr input: { "sentence": "...." } }
			String userSaid = decodeASRmsg(msg);
			userSaid = userSaid.trim();
			if (userSaid.length() > 1){
				System.out.println("ASR!");
				String answer = getAnswer(userSaid);
				ttsFriendlyAnswer(answer);
			}

			
		}else if (msgstring.substring(0, 5) .equals("{\"use")){
			//clevr gui text input
			System.out.println("clevrtext!");
			String userSaid = decodeCleVRmsg(msg);
			String answer = getAnswer(userSaid);
			ttsFriendlyAnswer(answer);			
		}else if (msgstring.contains("bmla:status=\"DONE\"")){
			System.out.println("bml done!");
			//bml done
		}		
		
		if (msgstring.contains("{\"scenarioId\":\"Witness\"")){
			System.out.println("Scenario witness, I'm talking");
			suspectScenario = true;
		}
		if (msgstring.contains("{\"scenarioId\":\"PoliceAgent\"")){
			System.out.println("Scenario politieagent, I'm shutting up");
			suspectScenario = false;
		}
		
		//{"scenarioId":"Witness"
		//{"scenarioId":"PoliceAgent"
	}
	
	public static void ttsFriendlyAnswer(String answer){
		//als het een kort antwoord is kan het gelijk, anders moet het in stukken voor de tts
		int i = answer.length();
		//System.out.println("Answerlength: "+Integer.toString(i));
		testqaresponder.sayshort(answer);
		/* if (i<50){
			testqaresponder.sayshort(answer);
		} else {
			testqaresponder.saylong(answer);
		}	 */
	}	
	
	public String decodeCleVRmsg(Message msg){
		String msgstring = "";
		try {
			msgstring = URLDecoder.decode(msg.getContentAsString(), "UTF-8");
			msgstring = msgstring.replace("{\"userResponse\":\"","");
			int index = msgstring.indexOf("\"");
			msgstring = msgstring.substring(0, index);
			System.out.println(msgstring);			
		} catch (UnsupportedEncodingException e) {
			System.out.println("[decodeCleVRmsg] Decoding failed.");
			e.printStackTrace();
		}
		return msgstring;	
	}
	
	public String decodeASRmsg(Message msg){
		String msgstring = "";				
		try {	
			msgstring = URLDecoder.decode(msg.getContentAsString(), "UTF-8");
			//dingen weggooien uit de herkende string waar ik niets mee kan
			msgstring = msgstring.replace("{ \"sentence\": \"","");
			msgstring = msgstring.replace("<unk>","");
			msgstring = msgstring.replace("."," ");
			int index = msgstring.indexOf("\"");
			msgstring = msgstring.substring(0, index);
			System.out.println(msgstring);			
		} catch (UnsupportedEncodingException e) {
			System.out.println("[decodeASRmsg] Decoding failed.");
			e.printStackTrace();
		}
		return msgstring;	
	}
	
	public static String getAnswer (String query){				
		// 		set value of attribute used to filter answer given in response to question	
		String attName = "type"; 		//"type"
		String attValue = "certain"; 	// "certain" or "uncertain"				
		//		Dialog d = store.getBestMatchingDialog(query);
		//		String answer = store.answerString(d, attName , attValue );
		// 		the above two lines have the same effect as the next line
		//		String answer = store.bestMatch(query, attName, attValue);
		// 		alternative: first set the attribute for filtering answers
		store.setAttribute(attName,attValue);
		String answer = store.bestMatch(query);		
		
		System.out.print("Answer: "+answer);
		System.out.print("Question: ");
		System.out.flush();
		return answer;
	}

	@Override
	public void onError(ErrorMessage err) {
		System.out.println("[onError] "+err.getMessage());
	}

}