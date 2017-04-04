package nl.utwente.hmi.avatar.dialogueManager.QAMatcher;


import pk.aamir.stompj.Connection;
import pk.aamir.stompj.MessageHandler;
import pk.aamir.stompj.ErrorHandler;
import pk.aamir.stompj.ErrorMessage;
import pk.aamir.stompj.StompJException;



public class StompErrorHandler implements ErrorHandler {
	
	public void onError(ErrorMessage err) {
		System.out.println(err);
	}
}