package nl.utwente.hmi.avatar;

public class Main {


    public static void main(String[] args) {
//        String method = args[0];
//        String language = args[1];
//
//        if (method == "speech") {
//            this.input = new SpeechAPI(language, null);
//        }

//        SpeechAPI.RecognitionEventAccumulator eventAccumulator = new SpeechAPI.RecognitionEventAccumulator();

        SpeechAPI speech = new SpeechAPI("nlspraak.ewi.utwente.nl:8889", null);
    }


}
