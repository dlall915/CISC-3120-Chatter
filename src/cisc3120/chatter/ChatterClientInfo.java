package cisc3120.chatter;

import java.io.*;
import java.net.*;

/**
 * Contains information needed for the client to connect to the server.
 * Also sets up the PrintStream and BufferedReader to send and receive messages.
 * 
 * @author David Lall
 */
public class ChatterClientInfo extends ChatterInfo{
	
	private Socket serverSocket = null;
	private PrintStream toServer = null;
	private BufferedReader fromServer = null;
	
	/**
	 * @param serverAddress
	 * 			Server address represented as a String.
	 * @throws Exception
	 */
	void connectToServer(String serverAddress) throws Exception {
		serverSocket = new Socket(serverAddress, SERVER_PORT);
		toServer = new PrintStream(serverSocket.getOutputStream(), true);
		fromServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		connected = true;
	}
	
	void disconnectFromServer() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PrintStream getPrintStream() {
		return toServer;
	}
	
	public BufferedReader getBufferedReader() {
		return fromServer;
	}
	
}