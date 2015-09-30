package cisc3120.chatter;

import java.awt.event.ActionEvent;

/**
 * An extension of ChatterXtremeServerGUI that sets up the GUI for a server.
 * 
 * @author David Lall
 */

@SuppressWarnings("serial")
public class ChatterServerGUI extends ChatterXtremeServerGUI {
	
	/**
	 * Overridden to apply decorators to the receiver and sender threads.
	 */
	@Override
	public void init() {
		super.init();
		serverReceiver = new ReceiverDecorator(serverReceiver, "...");
		serverSender = new SenderDecorator(serverSender, "...");
	}
	
	@Override
	public void sendMessage() {
		if (serverInfo.sendMessage)
			super.sendMessage();
		else displayMessage("Not your turn!" + "\n");
	}
	
	/**
	 * Overridden to set the sendMessage boolean to true.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendButton)
			captureMessage();
		else if (e.getSource() == connectButton) {
			connectToServer();
			if (serverInfo.connected) {
				displayMessage("Enter \"EXIT\" to disconnect server." + "\n");
				serverReceiver.start();
				serverSender.start();
				serverSender.messageVector.add("Whatcha want?");
				displayMessage("Whatcha want?" + "\n");
				serverInfo.sendMessage = true;
			}
		}
	}

}