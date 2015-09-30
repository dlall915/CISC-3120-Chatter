package cisc3120.chatter;

import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * Contains the server port and abstract functions to be used to return a PrintStream and BufferedReader.
 * 
 * @author David Lal
 */
public abstract class ChatterInfo {
	
	final static int SERVER_PORT = 3333;
	boolean sendMessage = true, connected;

	abstract PrintStream getPrintStream();
	abstract BufferedReader getBufferedReader();
}