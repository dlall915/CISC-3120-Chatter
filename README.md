# README.md

The long assignment description is [here](http://bc-cisc3120-f14.github.io/project2-chatter).

## Class and Method Summary
**ChatAppletGUI class**
	A generic chat GUI that extends JApplet.
	
	public void init();
	-Sets the applet size to 800x600 and arranges the GUI in a BorderLayout.
	
	abstract public void connectToServer();
	-Must be overridden to provide a connection using the method of your choosing (Sockets, RMI, etc...).
	
	abstract public void sendMessage();
	-Must be overridden to provide functionality for the Send button and Enter key.
	
	public String captureMessage();
	-Captures the text from the JTextField and stores it.  The text field is then cleared and the focus is returned to it.
	
	public static void displayMessage(String message);
	-Appends the passed String in the JTextArea.
	
	public void actionPerformed(ActionEvent e);
	-The send button calls sendMessage() which the subclass must define.  The connect button calls connectToServer() which the subclass must also define.
	
	public void keyPressed(KeyEvent e);
	-The Enter key provides the same function as the Send button. 
	
**ChatterXtremeServerGUI class**
	The GUI for the server for ChatterXtreme that extends ChatAppletGUI.
	
	public void connectToServer();
	-Starts the server and waits for a connection.
	
	public void sendMessage();
	-Sends a message to the client if it is not null.  If the message is "EXIT", the server is disconnected.
	
	public void actionPerformed(ActionEvent e);
	-This method is overridden to allow the connect button to also start the receiving and sending threads.  It also tells the user that "EXIT" stops the server and sends out the first message "Watcha want?".
	
**ChatterXtremeClientGUI class**
	The GUI for the client for ChatterXtreme that extends ChatAppletGUI.
	
	public void connectToServer();
	-Connects to the server.
	
	public void sendMessage();
	-Sends a message to the client if it is not null.  If the message is "EXIT", the client disconnects from the server.
	
	public void actionPerformed(ActionEvent e);
	-This method is overridden to allow the connect button to also start the receiving and sending threads.  It also tells the user that "EXIT" disconnects from the server.
	
**ChatterServerGUI class**
	An extension of ChatterXtremeServerGUI that sets up the GUI for a server.
	
	public void init();
	-Overridden to apply decorators to the receiver and sender threads.
	
	public void sendMessage();
	-Overridden to only allow sending messages if the sendMessage boolean is true, otherwise it outputs "Not your turn!" to the chat area.
	
	public void actionPerformed(ActionEvent e)
	-Overridden to set the sendMessage boolean to true.
	
**ChatterClientGUI class**
	An extension of the ChatterXtremeClientGUI that sets up the GUI for a client.
	
	public void init();
	-Overridden to apply decorators to the receiver and sender threads.
	
	public void sendMessage();
	-Overridden to only allow sending messages if the sendMessage boolean is true, otherwise it outputs "Not your turn!" to the chat area.
	
**ChatterInfo class**
	Contains the server port and abstract functions to be used to return a PrintStream and BufferedReader.
	
	abstract PrintStream getPrintStream();
	abstract BufferedReader getBufferedReader();

**ChatterServerInfo class**
	Contains information needed to start the server.  Also sets up the PrintStream and BufferedReader to send and receive messages.
	
	void waitForConnection();
	-Creates a new ServerSocket and waits for a connection.
	
	void disconnectServer();
	public PrintStream getPrintStream();
	public BufferedReader getBufferedReader();

**ChatterClientInfo class**
	Contains information needed for the client to connect to the server.  Also sets up the PrintStream and BufferedReader to send and receive messages.

	void connectToServer(String serverAddress);
	void disconnectFromServer();
	public PrintStream getPrintStream();
	public BufferedReader getBufferedReader();

**ChatterReceiver class**
	A thread dedicated to checking for and displaying messages as they come in.
	
	public ChatterReceiver(ChatterInfo info);
	public void waitForMessage();
	public void run();

**ChatterSender class**
	A thread dedicated to sending messages.  The messages are captures from the GUI and put into a vector.  The vector is checked and messages are sent if it is not empty twice per second.
	
	public ChatterReceiver(ChatterInfo info);
	
	public void getMessage(String message);
	-Gets the message and places it into the vector.
	
	public void sendMessage();
	-Sends a message if the vector is not null and removes the last message sent.
	
	public void run();
	-Checks for messages to send 2 times per second.

**ReceiverDecorator class**
	Modifies a ChatterReceiver to keep on receiving and displaying messages if the message ends with a specified pattern.
	
	public ReceiverDecorator(ChatterReceiver receiver, String pattern);
	public void waitForMessage();

**SenderDecorator class**
	Modifies a ChatterSender to inform the user through the GUI that it is still their turn after sending a message that ends with the specified pattern.
	
	public SenderDecorator(ChatterSender sender, String pattern);
	public void sendMessage();

## Works Cited
	http://inetjava.sourceforge.net/lectures/part1_sockets/InetJava-1.9-Chat-Client-Server-Example.html
	-Helped give me ideas on how to split up the Chatter program into different classes and threads.


## Extra Credit Behavior
	ChatterXtreme is a 2 person chat room where the client and server are both free to send messages whenever they want.  The regular Chatter program turned out to be an extended version of this.