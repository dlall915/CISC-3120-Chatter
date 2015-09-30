package cisc3120.chatter;

import java.io.BufferedReader;

/**
 * A thread dedicated to checking for and displaying messages as they come in.
 * 
 * @author David Lall
 */
public class ChatterReceiver extends Thread {
	
	private boolean running = true;
	private BufferedReader incoming = null;
	String incomingMessage;
	ChatterInfo chatterinfo;
	
	public ChatterReceiver(ChatterInfo info) {
		chatterinfo = info;
	}

	public void waitForMessage() throws Exception {
		incomingMessage = incoming.readLine();
		ChatterClientGUI.displayMessage(incomingMessage + "\n");
	}
	
    public void run() {
    	incoming = chatterinfo.getBufferedReader();
    	
    	while (running) {
			try {
				waitForMessage();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
    
}