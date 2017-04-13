package nl.utwente.hmi.avatar.input;

import nl.utwente.hmi.avatar.SpeechAPI;

/**
 * Created by Bouke on 12/04/2017.
 */
public class Speech extends Input implements InputListener {
    public SpeechAPI speech;

    public Speech() {
        speech = new SpeechAPI("nlspraak.ewi.utwente.nl:8889", null);
        speech.setListener(this);
    }

    @Override
    public void onInput(String msg) {
        Input.sendInput(msg);
    }


}
