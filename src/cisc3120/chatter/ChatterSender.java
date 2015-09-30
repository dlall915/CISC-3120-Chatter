package cisc3120.chatter;

import java.io.PrintStream;
import java.util.Vector;

/**
 * A thread dedicated to sending messages.  The messages are captures from the GUI and put into a vector.  The vector is checked and messages are sent if it is not two times per second.
 * 
 * @author David Lall
 */
public class ChatterSender extends Thread {
	
	private boolean running = true;
	protected PrintStream outgoing = null;
    Vector<String> messageVector = new Vector<String>(10,1);
	ChatterInfo chatterinfo;
	
	public ChatterSender(ChatterInfo info) {
		chatterinfo = info;
	}
	
	/**
	 * @param message
	 * 			String to be placed into the message vector.
	 */
	public void getMessage(String message) {
			messageVector.add(message);
	}
	
	public void sendMessage() {
		if (!messageVector.isEmpty()) {
			outgoing.println(messageVector.lastElement());
			messageVector.remove(messageVector.lastElement());
		}
	}
	
    public void run() {
		outgoing = chatterinfo.getPrintStream();

    	while (running) {
    		
    		if (chatterinfo.sendMessage) {
    			sendMessage();
    		}
    		
    		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
    
}