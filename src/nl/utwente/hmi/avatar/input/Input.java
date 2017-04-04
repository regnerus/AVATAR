package nl.utwente.hmi.avatar.input;

import nl.utwente.hmi.avatar.dialogueManager.QAMatcher;

public class Input {
    private static Input input;
    public static InputListener listener;

    public Input() {
        System.out.print("dsfsdf");


        System.out.print(input);
    }
    public static void main(){
        input = new SpeechAPI("nlspraak.ewi.utwente.nl:8889", null);
    }

    public static Input getInput() {
        return input;
    }

    public void setListener(InputListener listener) {
        this.listener = listener;
    }
}
