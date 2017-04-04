package nl.utwente.hmi.avatar.dialogueManager.qaMatcher;
import java.util.*;

/**
 * DialogStore is a store containing Dialog elements  
 * that contains matching question and answer pairs
 * The main public method is bestMatch, which return an answer string given a question and an attribute value pair
 * The DialogStore is created and filled by reading an xml file with a DomDialogParser
 *
 * DialogStore has an attribute name and value fields
 * They can be used to filter thise best responses that fit the state of the responder agent
 * For example: the answer that is given to question depends on
 * the stance of the agent that owns this DialogStore.
 * The response given to a question is then the one that fits the stance of the agent. 
 */
public class DialogStore{

// the attribute name, value pair used to filter when looking for best response
// Currently only one pair can be handled
// This should be a List of Attributes
String attname="";
String attvalue="";


List<Dialog> dialogs;
private static final String DEFAULT_ANSWER = "I do not understand what you mean.";

public DialogStore(){
	dialogs = new ArrayList<Dialog>();
}

public void add(Dialog d){ dialogs.add(d);
}

public int size(){
	return dialogs.size();
}
	

public String xml(){
	String result="<dialoglist>\n";
	for (int i=0; i< dialogs.size();i++){
		result += (dialogs.get(i)).xml();
	}
	result += "</dialoglist>\n";
	return result;
}

/**
 * set the attribute-value pair that is used to filter the dialogs when searching for best match
 */
public void setAttribute(String attName,String attValue){
	this.attname = attName;
	this.attvalue = attValue;
}

/**
 * @return String that is answer of given Dialog d and attribute name and attribute value
 * @return DEFAULT_ANSWER if no answer is found
 */ 
public String answerString(Dialog d, String attName, String attValue){
	for(int j=0;j<d.answerSize();j++){
		AnswerType at = d.getAnswer(j);
		String value = at.valueOfAttribute(attName);
		if ((value!=null) && (value.equals(attValue)))
			return at.answer;
	}
	return DEFAULT_ANSWER;
}

/**
 * @return String that is best answer to given question in a dialog that satisfies the set attribute value pair
 * @return DEFAULT_ANSWER
 */
public String bestMatch(String question){
	return bestMatch(question, this.attname, this.attvalue);
}


/**
 * @return String that is best answer to given question in a dialog that satisfies given name and attribute value
 * @return DEFAULT_ANSWER
 */
public String bestMatch(String question, String attName, String attValue){
	String answer = "";
	// This does not filter on given attribute value !
	Dialog d = getBestMatchingDialog(question);
	if (d!=null){
	        // Here we look at the attribute value !
		answer = answerString(d, attName , attValue );
	}else{
		answer = DEFAULT_ANSWER;
	}
	return answer;
}

/**
 * @return the Dialog in this DialogStore with a question that best matches the given query 
 */
public Dialog getBestMatchingDialog(String query){
	Dialog bestDialog = null;
	double bestMatch = -0.1;
	for(int i=0;i<dialogs.size();i++){
		Dialog d = dialogs.get(i);
		for(int j=0;j<d.questionSize();j++){
			String q = d.getQuestion(j);
			double match = similarity(query, q);
			if (match>bestMatch){
				bestMatch = match;
				bestDialog = d;
			}
		}
	}
	return bestDialog;
}

/**
 * Computes similarity between two Strings
 * The current implementation computes the relative size of the intersection of the sets of n-grams
 * of words in the two given strings
 * @return a value in [0,1] the similarity between two given Strings str1 and str2
 */
public static double similarity(String str1, String str2){
	List<String> ngrams1 = ToolSet.generateNgramsUpto(str1, 3);
	List<String> ngrams2 = ToolSet.generateNgramsUpto(str2, 3);
	Set<String> interset = intersection(ngrams1,ngrams2);
	//System.out.println("Intersection="+interset.toString());
	Set<String> union = union(ngrams1,ngrams2);
	//System.out.println("Union="+union.toString());
	double len1 = interset.size();
	double len2 = union.size();
	return len1/len2;
}

private static Set<String> intersection(List<String> lst1, List<String> lst2){
Set<String> s1 = new TreeSet<String>(lst1);
Set<String> s2 = new TreeSet<String>(lst2);
s1.retainAll(s2);
return s1;
}

private static Set<String> union (List<String> lst1, List<String> lst2){
Set<String> s1 = new TreeSet<String>(lst1);
Set<String> s2 = new TreeSet<String>(lst2);
s1.addAll(s2);
return s1;
}

}