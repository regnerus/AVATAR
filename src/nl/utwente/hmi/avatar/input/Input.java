package nl.utwente.hmi.avatar.input;

import nl.utwente.hmi.avatar.dialogueManager.QAMatcher;

/**
 * Created by Bouke on 04/04/2017.
 */

public interface InputListener {
    void onInput(String msg);
}
public class Input {
    public static Input input;
    private InputListener listener;

    public Input() {

    }


    //launch app
    public static void main(String[] args) {
        input = new SpeechAPI("nlspraak.ewi.utwente.nl:8889", null);
    }

    public void setListener(InputListener listener) {
        this.listener = listener;
    }
}
