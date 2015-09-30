package cisc3120.chatter;

/**
 * Modifies a ChatterReceiver to keep on receiving and displaying messages if the message ends with a specified pattern.
 * 
 * @author David Lall
 */
public class ReceiverDecorator extends ChatterReceiver{
	
	protected ChatterReceiver decoratedReceiver;
	String pattern;
	
	/**
	 * 
	 * @param receiver
	 * 			ChatterReceiver to be modified.
	 * @param pattern
	 * 			Pattern that is checked for at the end of the message.
	 */
	public ReceiverDecorator(ChatterReceiver receiver, String pattern) {
		super(receiver.chatterinfo);
		decoratedReceiver = receiver;
		this.pattern = pattern;
	}
    
	@Override
	public void waitForMessage() throws Exception {
		do {
			super.waitForMessage();
		} while (incomingMessage.endsWith(pattern));
		
		ChatterClientGUI.displayMessage("Your turn! " + "\n");
		chatterinfo.sendMessage = true;
	}
	
}