package nl.utwente.hmi.avatar.dialogueManager;

/**
 * Created by Bouke on 12/04/2017.
 */
public class BMLT {
    public static String BMLBehaviour(String bmlBehaviourRequested, long bmlId){
        String bmlBehaviourOut ="";

        //String[] bmlOffer = {"<gesture id=\"","\" lexeme=\"offer\" start=\"speech","\" />"};
        //"<gaze id=\"","\" target=\"Car\"    start=\"bml",":speech",":sync","\" />"}

        //gesture bindings
        if (bmlBehaviourRequested.equals("OK")){
            bmlBehaviourOut = "BML HERE";
        }
        else if (bmlBehaviourRequested.equals("HMM")){
            bmlBehaviourOut = "BML HERE";
        }

        return bmlBehaviourOut;
    }
}
