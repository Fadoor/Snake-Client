import java.awt.Container;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.JSONException;
import org.json.JSONObject;

public class SnakeMain {
	//Instantiering af lokalvariable
	private String address = "localhost";
	private int port = 10800;
	private Socket ServerSocket;
	private String CurrentUser = null;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { //Mainfunktionen oprettes, og indeholder et String array
		EventQueue.invokeLater(new Runnable() {
			
			public void run() { //RunFunktionen oprettes
				try { 
					SnakeMain window = new SnakeMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SnakeMain() { //SnakeMain konstruktør oprettes
		
		try {
			BufferedReader Reader = new BufferedReader(new FileReader("C:\\Users\\Fadoor\\Desktop\\Distribuerede Systemer 2015 Eksamen\\ClientConfig.txt"));
			
			try {
				JSONObject Setup = new JSONObject(Reader.readLine());
				
				this.address = Setup.getString("Address");
				this.port = Setup.getInt("Port");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Lukker framet
		
		JPanel JPanel = new ClientLogin(this); // Vi fortæller JPanel hvad der er hovedprogrammet, og at de ejes af SnakeMain klassen.
		frame.getContentPane().add(JPanel);
		
	}
	
	public void changePage(JPanel newJPanel) { // Skifte side metode som vil have et nyt JPanel
		Container panel = frame.getContentPane();
		panel.removeAll();
		panel.add(newJPanel);
		panel.repaint();
		panel.revalidate();
	}
	
	public JSONObject request(JSONObject message) { //Funktion med JSONObject der indeholder message
		
		System.out.println(message);
		
		JSONObject ServerMessage = null; // jeg antager at serveren svarer med null
		
		try {
			
			this.ServerSocket = new Socket(this.address, this.port); //Serversocket i denne klasse indeholder en ny socket som indeholder adress og port fra denne klasse
			PrintWriter out = new PrintWriter(this.ServerSocket.getOutputStream(), true); //Bruges til at sende beskeder til server
			BufferedReader in = new BufferedReader(new InputStreamReader(this.ServerSocket.getInputStream())); // Bruges til at modtage beskeder fra server
		
			out.println(message.toString()); //Sender en besked som indeholder beskeden messeage
			
			try {
				
				ServerMessage = new JSONObject(in.readLine()); //Hvis serveren sender alt andet end et JSONObject så returnerer den null
			
			} catch (JSONException e) {
				
				ServerMessage = null; //Antager at serveren altid svarer med null
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.ServerSocket.close(); // her lukkes vores socket/forbindelse når jeg har fået det vil vil have.
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(ServerMessage); //Her printes og returnes serverbeskeden altså vores svar
		return ServerMessage;
		
	}
	//Get og Set funktionerne til CurrentUser som returnerer CurrentUser
	public String getCurrentUser() {
		return CurrentUser;
	}

	public void setCurrentUser(String currentUser) {
		CurrentUser = currentUser;
	}

}
