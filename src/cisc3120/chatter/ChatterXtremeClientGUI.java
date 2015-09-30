package cisc3120.chatter;

import java.awt.event.ActionEvent;

/**
 * An extension of ChatAppletGUI that sets up the GUI for a client.
 * 
 * @author David Lall
 */

@SuppressWarnings("serial")
public class ChatterXtremeClientGUI extends ChatAppletGUI {
	
	final static String SERVER_ADDRESS = "localhost";

	ChatterClientInfo clientInfo = new ChatterClientInfo();
	ChatterReceiver clientReceiver = new ChatterReceiver(clientInfo);
	ChatterSender clientSender = new ChatterSender(clientInfo);
	
	/**
	 * Connects to the server.
	 */
	@Override
	public void connectToServer() {
		try {
			clientInfo.connectToServer(SERVER_ADDRESS);
		} catch (Exception e) {
			clientInfo.connected = false;
			displayMessage("Server not found.");
		}
	}
	
	/**
	 * Sends a message in response to the Send button or Enter key if sendMessage is true.  
	 * sendMessage will be true if the last message from the server did not contain "...".
	 * If sendMessage is false then "Not your turn!" is appended to the chat area.
	 * If the message is "EXIT" then the client will disconnect from the server.
	 */
	@Override
	public void sendMessage() {
		captureMessage();
		if (enteredText.matches("EXIT")) {
			clientInfo.disconnectFromServer();
			displayMessage("Disconnected from server." + "\n");
		}
		else if (enteredText != null) {
			clientSender.getMessage(enteredText);
			enteredText = null;
		}
	}
	
	/**
	 * Overridden to allow the connect button to also start the receiving and sending threads.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendButton)
			captureMessage();
		else if (e.getSource() == connectButton) {
			connectToServer();
			if (clientInfo.connected) {
				displayMessage("Enter \"EXIT\" to disconnect from server." + "\n");
				clientReceiver.start();
				clientSender.start();
			}
		}
	}
	
}