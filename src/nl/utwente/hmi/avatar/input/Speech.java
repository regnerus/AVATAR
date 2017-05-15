package nl.utwente.hmi.avatar.input;

import nl.utwente.hmi.avatar.InputListener;
import nl.utwente.hmi.avatar.SpeechAPI;

import java.util.Objects;

/**
 * Created by Bouke on 12/04/2017.
 */
public class Speech extends Input implements InputListener {
    public SpeechAPI speech;

    public Speech(String language) {
        if(Objects.equals(language, "NL")) {

            speech = new SpeechAPI("nlspraak.ewi.utwente.nl:8891", null); //dutch
        }else {
            speech = new SpeechAPI("nlspraak.ewi.utwente.nl:8891", null); //english
        }


        speech.setListener(this);
    }

    @Override
    public void onInput(String msg) {
        Input.sendInput(msg);
    }


}
