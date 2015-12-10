import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class ClientLogin extends JPanel { //ClientLogin Nedarver funktioner fra Jpanel
	//Instantiering af lokalvariable
	private JTextField textField;
	private JLabel lblUsername;
	private JLabel label;
	private JPasswordField passwordField;
	private JButton btnLogIn;
	private SnakeMain client;
	
	/**
	 * Create the panel.
	 */
	public ClientLogin(SnakeMain client) { //Konstuktør oprettes som ejes af SnakeMain som jeg kalder for client 
		
		this.client = client; // Sætter client ind i this.client
		this.client.setCurrentUser(null);
		
		//Skrifttype og farve
		this.setFont(new Font("Segoe Script", Font.PLAIN, 11));
		this.setBackground(new Color(192, 192, 192));
		this.setLayout(null);
		
		//JLabel navn, skrifttype, og koordinater
		JLabel lblWelcomeToThe = new JLabel("Welcome To The Snakepit");
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Nyala", Font.ITALIC, 26));
		lblWelcomeToThe.setBounds(62, 25, 298, 48);
		this.add(lblWelcomeToThe);
		
		//textfield oprettes med tilhørende skrifttype, farve, grænser.
		textField = new JTextField();
		textField.setBorder(null);
		textField.setForeground(new Color(0, 0, 0));
		textField.setBackground(Color.WHITE);
		textField.setFont(new Font("Nyala", Font.ITALIC, 16));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(103, 101, 218, 30);
		this.add(textField);
		textField.setColumns(10);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Nyala", Font.ITALIC, 18));
		lblUsername.setBounds(103, 84, 98, 19);
		this.add(lblUsername);
		
		label = new JLabel("Password:");
		label.setFont(new Font("Nyala", Font.ITALIC, 18));
		label.setBounds(103, 142, 98, 19);
		this.add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(null);
		passwordField.setBounds(103, 158, 218, 30);
		this.add(passwordField);
		
		btnLogIn = new JButton("Log in");
		btnLogIn.addMouseListener(new MouseAdapter() { //Tilføjer en Mouselistener som  gør jeg kan trykke på knappen LogIn
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientLogin This = (ClientLogin) (e.getComponent().getParent()); //Component spørger hvad e er, som er vores MouseClick, og getParent er hvor knappen er henne, som er min ClientLogin
				
				JSONObject Login = new JSONObject(); //Opretter et JSONObject som hedder Login og modtager et JSONObject
				
				try {
					
					Login.put("Username", This.textField.getText()); // Jeg indsætter en værdi i Login under Username som indeholder this.texfield.getText()
					Login.put("Password", new String(This.passwordField.getPassword())); //Vi indsætter en værdi i login under password, som indeholder This.passwordField.getPassword().
					Login.put("Method", "Login"); // Her fortæller jeg at Jeg bruger jeg metoden Login
					
					JSONObject Response = This.client.request(Login); //Her oprettes der et ny JSONObject hvor Login ind sættes ind den nye variabel Response
					
					if (Response != null && Response.has("Result")) { // her tjekker jeg om Response som er vores login ikke er null og det har et resultat.
						
						if (Response.getBoolean("Result")) { //Hvis Response er true; da jeg altid antager en boolean er true
							
							This.client.setCurrentUser(Response.getString("Username")); //Her sættes Username til at være CurrentUser
						
							This.client.changePage(new ClientMenu(This.client)); // Her skifter jeg side til ClientMenu hvis det er et gylidigt login.
						
						}
						
					}
					
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnLogIn.setBackground(Color.LIGHT_GRAY);
		btnLogIn.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnLogIn.setBounds(103, 199, 89, 23);
		this.add(btnLogIn);
	}
}
