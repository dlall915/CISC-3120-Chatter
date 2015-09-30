package cisc3120.chatter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class which defines the GUI for a generic chat applet.
 * 
 * @author David Lall
 */

@SuppressWarnings("serial")
abstract public class ChatAppletGUI extends JApplet implements ActionListener{
	
	JButton connectButton, sendButton;
	static JTextArea chatArea;
	static JTextField textField;
	static String enteredText = null;
	
	/**
	 * Sets the applet size to 800x600 and arranges the GUI in a BorderLayout.
	 */
	public void init() {
		this.resize(800,600);
		this.setLayout(new BorderLayout());
		
		connectButton = new JButton("Connect");
		add(connectButton, BorderLayout.NORTH);
		connectButton.addActionListener(this);
		
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		add(chatArea, BorderLayout.CENTER);
		
		textField = new JTextField();
		add(textField, BorderLayout.SOUTH);
		textField.addKeyListener(keyListener);
		
		sendButton = new JButton("Send");
		add(sendButton, BorderLayout.EAST);
		sendButton.addActionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	/**
	 * Must be overridden to provide a connection using the method of your choosing. (Sockets, RMI, etc...)
	 */
	abstract public void connectToServer();
	
	/**
	 * Must be overridden to provide functionality for the Send button and Enter key.
	 * See captureMessage() to retrieve the text the user inputs into the JTextArea.
	 */
	abstract public void sendMessage();
	
	/**
	 * Captures the text from the JTextField and stores it.
	 * The text field is then cleared and the focus is returned to it.
	 * 
	 * @return enteredText
	 * 			String captured from the JTextField.
	 */
	public String captureMessage() {
		enteredText = textField.getText();
		displayMessage("You: " + enteredText + "\n");
		textField.setText(null);
		//moves cursor back to the text field after pressing Enter
		textField.requestFocusInWindow();
		return enteredText;
	}
	
	/**
	 * Appends the passed String in the JTextArea.
	 * 
	 * @param message
	 * 			String to be appended.
	 */
	public static void displayMessage(String message) {
		chatArea.append(message);
	}
	
	/**
	 * The send button calls sendMessage() which the subclass must define.
	 * The connect button calls connectToServer() which the subclass must also define.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendButton)
			sendMessage();
		else if (e.getSource() == connectButton)
			connectToServer();
	}
	
	KeyListener keyListener = new KeyListener() {
		/**
		 * The Enter key calls sendMessage() which the subclass must define.
		 */
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				sendMessage();
		}
		
		/**
		 * Unused
		 */
	    public void keyReleased(KeyEvent e) {}
	    
		/**
		 * Unused
		 */
	    public void keyTyped(KeyEvent e) {}
	};

}