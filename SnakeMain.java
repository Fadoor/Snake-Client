import java.awt.Container;
import java.awt.EventQueue;
import java.io.BufferedReader;
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
	
	private String adress = "localhost";
	private int port = 10800;
	private Socket ServerSocket;
	private String CurrentUser = null;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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
	public SnakeMain() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel JPanel = new ClientLogin(this);
		frame.getContentPane().add(JPanel);
		
	}
	
	public void changePage(JPanel newJPanel) { // Skifte Panel metode
		Container panel = frame.getContentPane();
		panel.removeAll();
		panel.add(newJPanel);
		panel.repaint();
		panel.revalidate();
	}
	
	public JSONObject request(JSONObject message) {
		
		System.out.println(message);
		
		JSONObject ServerMessage = null; // vi antager at serven svarer med null
		
		try {
			
			this.ServerSocket = new Socket(this.adress, this.port);
			PrintWriter out = new PrintWriter(this.ServerSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(this.ServerSocket.getInputStream()));
		
			out.println(message.toString());
			
			try {
				
				ServerMessage = new JSONObject(in.readLine()); //Hvis serveren sender alt andet end et JSONObject så returnerer den null
			
			} catch (JSONException e) {
				
				ServerMessage = null;
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.ServerSocket.close();
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(ServerMessage);
		return ServerMessage;
		
	}

	public String getCurrentUser() {
		return CurrentUser;
	}

	public void setCurrentUser(String currentUser) {
		CurrentUser = currentUser;
	}

}
