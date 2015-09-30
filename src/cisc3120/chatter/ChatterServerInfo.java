package cisc3120.chatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Contains information needed to start the server.
 * Also sets up the PrintStream and BufferedReader to send and receive messages.
 * 
 * @author David Lall
 */
public class ChatterServerInfo extends ChatterInfo {
	
	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;
	private static PrintStream toClient = null;
	private static BufferedReader fromClient = null;
	
	/**
	 * Creates a new ServerSocket and waits for a connection.
	 * 
	 * @throws IOException
	 */
	void waitForConnection() throws IOException {
		ChatterServerGUI.displayMessage("Waiting for a client" + "\n");
		serverSocket = new ServerSocket(SERVER_PORT);
		
		clientSocket = serverSocket.accept();
		ChatterServerGUI.displayMessage("Connection requested from: " + clientSocket.getLocalAddress() + "\n");
		
		toClient = new PrintStream(clientSocket.getOutputStream(), true);
		fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		connected = true;
	}
	
	void disconnectServer() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			ChatterServerGUI.displayMessage("Server socket is not open." + "\n");
		}
	}
	
	@Override
	public PrintStream getPrintStream() {
		return toClient;
	}
	
	@Override
	public BufferedReader getBufferedReader() {
		return fromClient;
	}
	
}