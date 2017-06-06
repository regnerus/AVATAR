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
        if (bmlBehaviourRequested.equals("YEAH")){
            bmlBehaviourOut = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\"  id=\"bml1\" xmlns:bmlt=\"http://hmi.ewi.utwente.nl/bmlt\">\n" +
                    "<bmlt:audiofile id=\"v1\" fileName=\"file:/c:/yeah2.wav\" start=\"0\"/>\n" +
                    "<bmlt:facekeyframe type=\"MORPH\" id=\"fk1\">\n" +
                    " <FaceInterpolator parts=\"SVP_6 SVP_11 SVP_3 SVP_4\">\n" +
                    " 0.0 0.0 0.0 0.0 0.0\n" +
                    " 0.1 0.5 0.0 0.0 0.0\n" +
                    " 0.15 0.2 0.5 0.0 0.0\n" +
                    " 0.2 0.0 1.0 0.0 0.0\n" +
                    " </FaceInterpolator>\n" +
                    "</bmlt:facekeyframe>\n" +
                    "</bml>";
        }
        else if (bmlBehaviourRequested.equals("UHUH")){
            bmlBehaviourOut = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\"  id=\"bml1\" xmlns:bmlt=\"http://hmi.ewi.utwente.nl/bmlt\">\n" +
                    "<bmlt:audiofile id=\"v1\" fileName=\"file:/c:/uhuh2.wav\" start=\"0\"/>\n" +
                    "<bmlt:facekeyframe type=\"MORPH\" id=\"fk1\">\n" +
                    " <FaceInterpolator parts=\"SVP_6 SVP_11 SVP_3 SVP_4\">\n" +
                    " 0.0 0.0 0.0 0.0 0.0\n" +
                    " 0.1 0.5 0.0 0.0 0.0\n" +
                    " 0.2 0.3 0.2 0.0 0.0\n" +
                    " 0.3 0.5 0.0 0.0 0.0\n" +
                    " </FaceInterpolator>\n" +
                    "</bmlt:facekeyframe>\n" +
                    "</bml>\n";
        }
        else if (bmlBehaviourRequested.equals("OHH")){
            bmlBehaviourOut = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\"  id=\"bml3\" xmlns:bmlt=\"http://hmi.ewi.utwente.nl/bmlt\">\n" +
                    "<bmlt:audiofile id=\"v1\" fileName=\"file:/c:/ohh.wav\" start=\"0.05\"/>\n" +
                    "<bmlt:facekeyframe type=\"MORPH\" id=\"fk1\">\n" +
                    " <FaceInterpolator parts=\"SVP_8 SVP_9 SVP_3 SVP_4\">\n" +
                    " 0.0 0.0 1.0 0.0 0.0\n" +
                    " 0.1 0.4 0.5 0.0 0.0\n" +
                    " 0.15 0.8 0.2 0.0 0.0\n" +
                    " 0.2 1.0 0.0 0.0 0.0\n" +
                    " 0.25 0.5 0.0 0.0 0.0\n" +
                    " </FaceInterpolator>\n" +
                    "</bmlt:facekeyframe>\n" +
                    "</bml>";
        }
        else if (bmlBehaviourRequested.equals("MHMMM")){
            bmlBehaviourOut = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\"  id=\"bml3\" xmlns:bmlt=\"http://hmi.ewi.utwente.nl/bmlt\">\n" +
                    "<bmlt:audiofile id=\"v1\" fileName=\"file:/c:/mhmmm.wav\" start=\"0.05\"/>\n" +
                    "<bmlt:facekeyframe type=\"MORPH\" id=\"fk1\">\n" +
                    " <FaceInterpolator parts=\"SVP_7 SVP_12 SVP_3 SVP_4\">\n" +
                    " 0.0 0.0 0.0 0.0 0.0\n" +
                    " 0.1 0.4 0.1 0.0 0.0\n" +
                    " 0.15 0.8 0.2 0.0 0.0\n" +
                    " 0.2 1.0 0.0 0.0 0.0\n" +
                    " 0.25 0.5 0.0 0.0 0.0\n" +
                    " </FaceInterpolator>\n" +
                    "</bmlt:facekeyframe>\n" +
                    "</bml>\n";
        }
        else if (bmlBehaviourRequested.equals("HMMMM")){
            bmlBehaviourOut = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\"  id=\"bml3\" xmlns:bmlt=\"http://hmi.ewi.utwente.nl/bmlt\">\n" +
                    "<bmlt:audiofile id=\"v1\" fileName=\"file:/c:/hmmmm2.wav\" start=\"0\"/>\n" +
                    "<bmlt:facekeyframe type=\"MORPH\" id=\"fk1\">\n" +
                    " <FaceInterpolator parts=\"SVP_7 SVP_12 SVP_3 SVP_4\">\n" +
                    " 0.0 0.0 0.0 0.0 0.0\n" +
                    " 0.1 0.4 0.0 0.0 0.0\n" +
                    " 0.15 0.4 0.0 0.0 0.0\n" +
                    " 0.2 0.3 0.0 0.0 0.0\n" +
                    " </FaceInterpolator>\n" +
                    "</bmlt:facekeyframe>\n" +
                    "</bml>";
        }
        else if (bmlBehaviourRequested.equals("ENDING")){
            bmlBehaviourOut = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\"  id=\"bml6\" xmlns:bmlt=\"http://hmi.ewi.utwente.nl/bmlt\">\n" +
                    "<bmlt:audiofile id=\"v1\" fileName=\"file:/c:/ending.wav\" start=\"0\"/>\n" +
                    "<bmlt:facekeyframe type=\"MORPH\" id=\"fk1\">\n" +
                    " <FaceInterpolator parts=\"SVP_6 SVP_11 SVP_3 SVP_4\">\n" +
                    " 0.0 0.0 0.0 0.0 0.0\n" +
                    "\n" +
                    " 0.2 0.0 0.0 0.0 0.0\n" +
                    " 0.3 1.0 0.0 0.0 0.0\n" +
                    " 0.5 0.0 1.0 0.0 0.0\n" +
                    " 0.7 0.0 0.0 0.0 0.0\n" +
                    " 0.8 0.0 1.0 0.0 0.0\n" +
                    " 0.9 0.0 1.0 0.0 0.0\n" +
                    " 1.1 1.0 0.5 0.0 0.0\n" +
                    " 1.3 0.5 0.4 0.0 0.0\n" +
                    " 1.4 0.1 0.2 0.0 0.0\n" +
                    " 1.5 0.0 0.0 0.0 0.0\n" +
                    "\n" +
                    " 1.8 0.0 0.0 0.0 0.0\n" +
                    " 1.9 1.0 0.0 0.0 0.0\n" +
                    " 2.0 1.0 0.0 0.0 0.0\n" +
                    " 2.1 0.0 1.0 0.0 0.0\n" +
                    " 2.25 1.0 0.0 0.0 0.0\n" +
                    " 2.6 0.0 0.0 0.0 0.0\n" +
                    " 2.7 1.0 0.0 0.0 0.0\n" +
                    " 3.0 0.0 0.0 0.0 0.0\n" +
                    " 3.1 0.0 1.0 0.0 0.0\n" +
                    " 3.7 0.0 0.0 0.0 0.0\n" +
                    "\n" +
                    " 4.3 0.0 0.0 0.0 0.0\n" +
                    " 4.4 0.0 0.6 0.0 0.0\n" +
                    " 4.8 0.4 0.3 0.0 0.0\n" +
                    " 5.0 1.0 0.0 0.0 0.0\n" +
                    " 5.1 0.0 0.0 0.0 0.0\n" +
                    " </FaceInterpolator>\n" +
                    "</bmlt:facekeyframe>\n" +
                    "</bml>\n";
        }
        else if (bmlBehaviourRequested.equals("GREETER")){
            bmlBehaviourOut = "<bml xmlns=\"http://www.bml-initiative.org/bml/bml-1.0\"  id=\"bml6\" xmlns:bmlt=\"http://hmi.ewi.utwente.nl/bmlt\">\n" +
                    "<bmlt:audiofile id=\"v1\" fileName=\"file:/c:/greeter.wav\" start=\"0\"/>\n" +
                    "<bmlt:facekeyframe type=\"MORPH\" id=\"fk1\">\n" +
                    " <FaceInterpolator parts=\"SVP_6 SVP_11 SVP_3 SVP_4\">\n" +
                    " 0.0 0.0 0.0 0.0 0.0\n" +
                    "\n" +
                    " 0.1 0.5 0.5 0.0 0.0\n" +
                    " 0.2 1.0 0.7 0.0 0.0\n" +
                    " 0.3 0.5 0.0 0.0 0.0\n" +
                    " 0.4 0.0 0.0 0.0 0.0\n" +
                    "\n" +
                    " 0.8 0.0 0.0 0.0 0.0\n" +
                    " 0.9 1.0 0.3 0.0 0.0\n" +
                    " 1.0 0.6 0.5 0.0 0.0\n" +
                    " 1.1 1.0 0.5 0.0 0.0\n" +
                    " 1.3 0.5 0.4 0.0 0.0\n" +
                    " 1.4 0.1 0.2 0.0 0.0\n" +
                    " 1.5 0.5 0.0 0.0 0.0\n" +
                    " 1.6 1.0 0.4 0.0 0.0\n" +
                    " 1.7 0.5 0.0 0.0 0.0\n" +
                    " 1.8 0.0 0.0 0.0 0.0\n" +
                    " 1.9 1.0 0.0 0.0 0.0\n" +
                    " 2.0 1.0 0.0 0.0 0.0\n" +
                    " 2.1 0.0 1.0 0.0 0.0\n" +
                    " 2.2 0.0 0.5 0.0 0.0\n" +
                    " 2.3 0.0 0.3 0.0 0.0\n" +
                    " 2.4 0.0 0.5 0.0 0.0\n" +
                    " 2.5 0.0 0.0 0.0 0.0\n" +
                    " 2.6 0.0 0.0 0.0 0.0\n" +
                    " 2.7 0.0 0.3 0.0 0.0\n" +
                    " 2.8 0.5 0.5 0.0 0.0\n" +
                    " 2.9 0.6 0.2 0.0 0.0\n" +
                    " 3.0 0.0 0.3 0.0 0.0\n" +
                    " 3.1 0.0 0.8 0.0 0.0\n" +
                    " 3.7 0.0 0.0 0.0 0.0\n" +
                    "\n" +
                    " 4.3 0.0 0.0 0.0 0.0\n" +
                    " 4.4 0.0 0.6 0.0 0.0\n" +
                    " 4.5 0.4 0.6 0.0 0.0\n" +
                    " 4.6 0.8 0.5 0.0 0.0\n" +
                    " 4.7 0.3 0.2 0.0 0.0\n" +
                    " 4.8 0.4 0.3 0.0 0.0\n" +
                    " 4.9 0.4 0.3 0.0 0.0\n" +
                    " 5.0 1.0 0.0 0.0 0.0\n" +
                    " 5.1 0.5 0.0 0.0 0.0\n" +
                    " 5.2 0.5 0.1 0.0 0.0\n" +
                    " 5.3 0.8 0.3 0.0 0.0\n" +
                    " 5.4 0.0 0.5 0.0 0.0\n" +
                    " 5.5 0.2 0.3 0.0 0.0\n" +
                    " 5.6 0.0 0.0 0.0 0.0\n" +
                    " 5.7 0.5 0.0 0.0 0.0\n" +
                    " 5.8 0.6 0.2 0.0 0.0\n" +
                    " 5.9 0.2 0.3 0.0 0.0\n" +
                    " 6.0 0.0 0.6 0.0 0.0\n" +
                    " 6.1 0.0 0.0 0.0 0.0 \n" +
                    " 6.2 0.1 0.0 0.0 0.0\n" +
                    " 6.3 0.5 0.0 0.0 0.0\n" +
                    " 6.4 1.0 0.3 0.0 0.0\n" +
                    " 6.5 0.8 0.3 0.0 0.0\n" +
                    " 6.6 0.5 0.1 0.0 0.0\n" +
                    " 6.7 0.0 0.0 0.0 0.0\n" +
                    " 6.8 0.0 0.1 0.0 0.0\n" +
                    " 6.9 0.5 0.3 0.0 0.0\n" +
                    " 7.0 0.6 0.0 0.0 0.0\n" +
                    " 7.1 0.3 0.0 0.0 0.0\n" +
                    " 7.2 0.0 0.0 0.0 0.0\n" +
                    " 7.3 0.0 0.5 0.0 0.0\n" +
                    " 7.4 0.5 0.0 0.0 0.0\n" +
                    " 7.5 0.5 0.1 0.0 0.0\n" +
                    " 7.6 0.3 0.4 0.0 0.0\n" +
                    " 7.7 1.0 0.5 0.0 0.0\n" +
                    " 7.8 0.0 0.6 0.0 0.0\n" +
                    " 7.9 0.0 0.3 0.0 0.0\n" +
                    " 8.0 0.3 0.0 0.0 0.0\n" +
                    " 8.1 0.5 0.0 0.0 0.0\n" +
                    " 8.2 0.1 0.0 0.0 0.0\n" +
                    "\n" +
                    " 8.7 0.3 0.0 0.0 0.0\n" +
                    " 8.8 0.8 0.3 0.0 0.0\n" +
                    " 8.9 0.0 0.0 0.0 0.0\n" +
                    " 9.0 0.2 0.1 0.0 0.0\n" +
                    " 9.2 0.6 0.3 0.0 0.0\n" +
                    " 9.4 1.0 0.5 0.0 0.0\n" +
                    " 9.6 0.7 0.0 0.0 0.0\n" +
                    " 9.8 1.0 0.4 0.0 0.0\n" +
                    " 9.9 0.5 0.3 0.0 0.0\n" +
                    " 10.0 0.0 0.0 0.0 0.0\n" +
                    "\n" +
                    " 10.8 0.0 0.0 0.0 0.0\n" +
                    " 10.9 1.0 0.0 0.0 0.0\n" +
                    " 11.0 1.0 0.0 0.0 0.0\n" +
                    " 11.2 0.8 0.3 0.0 0.0\n" +
                    " 11.4 0.0 0.0 0.0 0.0\n" +
                    " 11.6 0.2 0.1 0.0 0.0\n" +
                    " 11.8 0.6 0.3 0.0 0.0\n" +
                    " 12.0 1.0 0.5 0.0 0.0\n" +
                    " 12.2  0.7 0.0 0.0 0.0\n" +
                    " 12.4 1.0 0.4 0.0 0.0\n" +
                    " 12.6 0.0 0.0 0.0 0.0\n" +
                    "\n" +
                    " 13.0 0.0 0.0 0.0 0.0\n" +
                    " 13.2 1.0 0.0 0.0 0.0\n" +
                    " 13.4 0.8 0.3 0.0 0.0\n" +
                    " 13.6 0.0 0.0 0.0 0.0\n" +
                    " 13.8 0.2 0.1 0.0 0.0\n" +
                    " 14.0 0.6 0.3 0.0 0.0\n" +
                    " 14.2 1.0 0.5 0.0 0.0\n" +
                    " 14.4 0.0 0.0 0.0 0.0\n" +
                    "\n" +
                    " 14.8 0.0 0.0 0.0 0.0\n" +
                    " 14.9 1.0 0.0 0.0 0.0\n" +
                    " 15.0 1.0 0.0 0.0 0.0\n" +
                    " 15.2 0.8 0.3 0.0 0.0\n" +
                    " 15.4 0.0 0.0 0.0 0.0\n" +
                    " 15.6 0.2 0.1 0.0 0.0\n" +
                    " 15.8 0.6 0.3 0.0 0.0\n" +
                    " 16.0 1.0 0.5 0.0 0.0\n" +
                    " 16.2 1.0 0.0 0.0 0.0\n" +
                    " 16.3 0.0 0.0 0.0 0.0\n" +
                    " </FaceInterpolator>\n" +
                    "</bmlt:facekeyframe>\n" +
                    "</bml>\n";
        }
        return bmlBehaviourOut;
    }
}
