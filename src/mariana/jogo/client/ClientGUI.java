package mariana.jogo.client;

import mariana.jogo.client.EnumConstants.*;
import mariana.jogo.ChatMessage;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/*
 * The Client with its GUI
 */
public class ClientGUI extends JFrame implements ActionListener {
	
	private JPanel screen;
	private static final long serialVersionUID = 1L;
	// will first hold "Username:", later on "Enter message"
	private JLabel label;
	// to hold the Username and later on the messages
	private JTextField tf;
	// to hold the server address an the port number
	private JTextField tfServer, tfPort;
	// to Logout and get the list of the users
	private JButton login, logout, whoIsIn;
	// for the chat room
	private JTextArea ta;
	// if it is for connection
	private boolean connected;
	// the Client object
	private Client client;
	// the default port number
	private int defaultPort;
	private String defaultHost;
	private JPanel chat;
	private JPanel board;
	
	private JLabel text1;
	private JTextPane txtpnWelcomeToChat;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	// Constructor connection receiving a socket number
	ClientGUI(String host, int port) {
		
		super("Game");
		defaultPort = port;
		defaultHost = host;

		screen = new JPanel();
		screen.setLayout(new GridLayout (5,1));
	//	screen.setSize(1360, 768);

		getContentPane().add(screen, BorderLayout.WEST);
		
		JButton btnGiveUp = new JButton("Desistir");
		btnGiveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));

			}
		});
	
		JButton btnRestart = new JButton("Restart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {Bizingo.RESTART.getValue();
			} 
		});
		JPanel jpanel = new JPanel();
		JPanel jpanel2 = new JPanel();
		
		screen.add(jpanel);
		
		screen.add(btnGiveUp);
		screen.add(jpanel2);

		screen.add(btnRestart);

		
		
		board = new JPanel();
		getContentPane().add(board, BorderLayout.CENTER);
		text1 = new JLabel("");
//		text1.setIcon(new ImageIcon(ClientGUI.class.getResource("/com/sun/javafx/scene/control/skin/caspian/images/vk-hide.png")));
		board.add(text1,BorderLayout.CENTER);
		
		
		//set absolute path
		text1.setIcon(new ImageIcon("D:\\Dropbox\\javaprojects\\Jogo\\resources\\BOARD.png"));

		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("resources/BOARD.png"));
		} catch (IOException e) {
		}
		
		
		chat = new JPanel();
		getContentPane().add(chat, BorderLayout.EAST);
		chat.setLayout(new BorderLayout(0, 0));
		
		
		
		// The NorthPanel with:
		JPanel northPanel = new JPanel(new GridLayout(3,1));
		chat.add(northPanel, BorderLayout.NORTH);
		// the server name anmd the port number
		JPanel serverAndPort = new JPanel(new GridLayout(1,5, 1, 3));
		// the two JTextField with default value for server address and port number
		tfServer = new JTextField(host);
		tfPort = new JTextField("" + port);
		tfPort.setHorizontalAlignment(SwingConstants.RIGHT);
		
				serverAndPort.add(new JLabel("Server Address:  "));
				serverAndPort.add(tfServer);
				serverAndPort.add(new JLabel("Port Number:  "));
				serverAndPort.add(tfPort);
				serverAndPort.add(new JLabel(""));
				// adds the Server an port field to the GUI
				northPanel.add(serverAndPort);
				
						// the Label and the TextField
						label = new JLabel("Enter your username below", SwingConstants.LEFT);
						northPanel.add(label);
						tf = new JTextField("Anonymous");
						tf.setBackground(Color.WHITE);
						northPanel.add(tf);
						JPanel centerPanel = new JPanel(new GridLayout(1,1));
						chat.add(centerPanel, BorderLayout.CENTER);
						JScrollPane scrollPane = new JScrollPane();
						centerPanel.add(scrollPane);
						
						txtpnWelcomeToChat = new JTextPane();
						txtpnWelcomeToChat.setText("Welcome to chat!!!");
						scrollPane.setColumnHeaderView(txtpnWelcomeToChat);
					/*	
						// The CenterPanel which is the chat room
						ta = new JTextArea("Welcome to the Chat room\n", 80, 80);
						centerPanel.add(ta,BorderLayout.NORTH);
						ta.setEditable(false);
						
						*/
								// the 3 buttons
								login = new JButton("Login");
								login.addActionListener(this);
								logout = new JButton("Logout");
								logout.addActionListener(this);
								logout.setEnabled(false);		// you have to login before being able to logout
								whoIsIn = new JButton("Who is in");
								whoIsIn.addActionListener(this);
								whoIsIn.setEnabled(false);		// you have to login before being able to Who is in
								
										JPanel southPanel = new JPanel();
										chat.add(southPanel, BorderLayout.SOUTH);
										southPanel.add(login);
										southPanel.add(logout);
										southPanel.add(whoIsIn);
										
												
						tf.requestFocus();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1360, 768);
		setVisible(true);

	}

	// called by the Client to append text in the TextArea 
	void append(String str) {
		ta.append(str);
		ta.setCaretPosition(ta.getText().length() - 1);
	}
	// called by the GUI is the connection failed
	// we reset our buttons, label, textfield
	void connectionFailed() {
		login.setEnabled(true);
		logout.setEnabled(false);
		whoIsIn.setEnabled(false);
		label.setText("Enter your username below");
		tf.setText("Anonymous");
		// reset port number and host name as a construction time
		tfPort.setText("" + defaultPort);
		tfServer.setText(defaultHost);
		// let the user change them
		tfServer.setEditable(false);
		tfPort.setEditable(false);
		// don't react to a <CR> after the username
		tf.removeActionListener(this);
		connected = false;
	}
		
	/*
	* Button or JTextField clicked
	*/
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		// if it is the Logout button
		if(o == logout) {
			client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
			return;
		}
		// if it the who is in button
		if(o == whoIsIn) {
			client.sendMessage(new ChatMessage(ChatMessage.WHOISIN, ""));				
			return;
		}

		// ok it is coming from the JTextField
		if(connected) {
			// just have to send the message
			client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, tf.getText()));				
			tf.setText("");
			return;
		}
		

		if(o == login) {
			// ok it is a connection request
			String username = tf.getText().trim();
			// empty username ignore it
			if(username.length() == 0)
				return;
			// empty serverAddress ignore it
			String server = tfServer.getText().trim();
			if(server.length() == 0)
				return;
			// empty or invalid port numer, ignore it
			String portNumber = tfPort.getText().trim();
			if(portNumber.length() == 0)
				return;
			int port = 0;
			try {
				port = Integer.parseInt(portNumber);
			}
			catch(Exception en) {
				return;   // nothing I can do if port number is not valid
			}

			// try creating a new Client with GUI
			client = new Client(server, port, username, this);
			// test if we can start the Client
			if(!client.start()) 
				return;
			tf.setText("");
			label.setText("Enter your message below");
			connected = true;
			
			// disable login button
			login.setEnabled(false);
			// enable the 2 buttons
			logout.setEnabled(true);
			whoIsIn.setEnabled(true);
			// disable the Server and Port JTextField
			tfServer.setEditable(false);
			tfPort.setEditable(false);
			// Action listener for when the user enter a message
			tf.addActionListener(this);
		}

	}

	// to start the whole thing the server
	public static void main(String[] args) {
		new ClientGUI("localhost", 1500);
	}

}
