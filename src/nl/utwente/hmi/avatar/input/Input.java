package nl.utwente.hmi.avatar.input;

/**
 * Created by Bouke on 04/04/2017.
 */
public class Input {
    private Input input;

    public Input(String[] args) {
        String method = args[0];
        String language = args[1];

        if (method == "speech") {
            this.input = new SpeechAPI(language, null);
        }
    }
}
