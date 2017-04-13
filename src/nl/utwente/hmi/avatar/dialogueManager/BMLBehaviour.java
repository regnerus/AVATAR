package nl.utwente.hmi.avatar.dialogueManager;

/**
 * Created by Bouke on 12/04/2017.
 */
public class BMLBehaviour {
    public static String BMLBehaviour(String bmlBehaviourRequested, long bmlId){
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
}
