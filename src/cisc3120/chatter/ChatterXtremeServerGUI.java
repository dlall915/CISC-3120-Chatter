package cisc3120.chatter;

import java.awt.event.ActionEvent;

/**
 * An extension of ChatAppletGUI that sets up the GUI for a server.
 * 
 * @author David Lall
 */

@SuppressWarnings("serial")
public class ChatterXtremeServerGUI extends ChatAppletGUI {
	
	ChatterServerInfo serverInfo = new ChatterServerInfo();
	ChatterReceiver serverReceiver = new ChatterReceiver(serverInfo);
	ChatterSender serverSender = new ChatterSender(serverInfo);
	
	/**
	 * Starts the server.
	 */
	@Override
	public void connectToServer() {
		try {
			serverInfo.waitForConnection();
		} catch (Exception e) {
			serverInfo.connected = false;
			System.err.println("Server not found." + "\n");
		}
	}

	/**
	 * Sends a message in response to the Send button or Enter key if sendMessage is true.  
	 * sendMessage will be true if the last message from the client did not contain "...".
	 * If sendMessage is false then "Not your turn!" is appended to the chat area.
	 * If the message is "EXIT" then the server will be disconnected.
	 */
	@Override
	public void sendMessage() {
		captureMessage();
		if (enteredText.matches("EXIT")) {
			serverInfo.disconnectServer();
			displayMessage("Disconnected server." + "\n");
		}
		else if (enteredText != null) {
			serverSender.getMessage(enteredText);
			enteredText = null;
		}
	}
	
	/**
	 * Overridden to allow the connect button to also start the receiving and sending threads.
	 * It also sends out the first message "Watcha want?".
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
			}
		}
	}
	
}