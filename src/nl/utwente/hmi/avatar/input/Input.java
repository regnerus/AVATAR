package nl.utwente.hmi.avatar.input;

/**
 * Created by Bouke on 04/04/2017.
 */
public class Input {
    public Input() {

    }

    //launch app
    public static void main(String[] args) {
        new SpeechAPI("nlspraak.ewi.utwente.nl:8889", null);
    }
}
