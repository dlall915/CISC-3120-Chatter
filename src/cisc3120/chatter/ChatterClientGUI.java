package cisc3120.chatter;

/**
 * An extension of ChatterXtremeClientGUI that sets up the GUI for a client.
 * 
 * @author David Lall
 */

@SuppressWarnings("serial")
public class ChatterClientGUI extends ChatterXtremeClientGUI {
	
	/**
	 * Overridden to apply decorators to the receiver and sender threads.
	 */
	@Override
	public void init() {
		super.init();
		clientReceiver = new ReceiverDecorator(clientReceiver, "...");
		clientSender = new SenderDecorator(clientSender, "...");
	}

	@Override
	public void sendMessage() {
		if (clientInfo.sendMessage)
			super.sendMessage();
		else displayMessage("Not your turn!" + "\n");
	}

}