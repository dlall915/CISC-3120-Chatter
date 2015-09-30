package cisc3120.chatter;

/**
 * Modifies a ChatterSender to inform the user through the GUI that it is still their turn after sending a message that ends with the specified pattern.
 * 
 * @author David Lall
 */
public class SenderDecorator extends ChatterSender{
	
	protected ChatterSender decoratedSender;
	String pattern;
	
	/**
	 * The ability to send messages is set to false on creation.
	 * 
	 * @param sender
	 * 			ChatterSender to be modified.
	 * @param pattern
	 * 			Pattern that is checked for at the end of the message to allow the user to keep on sending messages.
	 */
	public SenderDecorator(ChatterSender sender, String pattern) {
		super(sender.chatterinfo);
		decoratedSender = sender;
		this.pattern = pattern;
		chatterinfo.sendMessage = false;
	}
    
	@Override
	public void sendMessage() {
		if (!messageVector.isEmpty()) {
			outgoing.println(messageVector.lastElement());
			if (messageVector.lastElement().endsWith(pattern))
				ChatterClientGUI.displayMessage("Your turn!" + "\n");
			else chatterinfo.sendMessage = false;
			messageVector.remove(messageVector.lastElement());
		}
	}
	
}