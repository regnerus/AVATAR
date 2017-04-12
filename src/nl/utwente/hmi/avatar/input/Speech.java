package nl.utwente.hmi.avatar.input;

//import nl.utwente.hmi.avatar.SpeechAPI;

import nl.utwente.hmi.avatar.SpeechAPI;

/**
 * Created by Bouke on 12/04/2017.
 */
public class Speech extends Input {
    public SpeechAPI speech;

    public Speech() {
//        SpeechAPI.RecognitionEventAccumulator eventAccumulator = new SpeechAPI.RecognitionEventAccumulator();

        speech = new SpeechAPI("nlspraak.ewi.utwente.nl:8889", null);


//        eventAccumulator.onRecognitionEvent();
    }

    public void setListener(InputListener listener) {
//        speech.listener = listener;
    }

//    public void onRecognitionEvent(RecognitionEvent event)
//    {
//        System.out.println("****** " + event);
//        listener.onInput(event.getResult().toString());
//        if (event.getStatus() == 10) {
//            System.out.println("****** PINGED" + event);
//            sendPong=true;
//        } else {
//            events.add(event);
//        }
////            Input.sendInput(event.getResult().getHypotheses().get(0).getTranscript());
////            System.out.println(event.getResult().getHypotheses().get(0).getTranscript());
////
//    }
}
