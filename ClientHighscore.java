import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientHighscore extends JPanel { //ClientHighscore Nedarver funktioner fra Jpanel
	
	private SnakeMain client;

	/**
	 * Create the panel.
	 */
	public ClientHighscore(SnakeMain client) { //Konstuktør oprettes som ejes af SnakeMain som jeg kalder for client 
		this.client = client; // Sætter client ind i this.client
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(null);
		
		//JLabel navnet "My Highscore", men skriftstørrelse og koordinater
		JLabel lblNewLabel = new JLabel("My Highscore:");
		lblNewLabel.setFont(new Font("Nyala", Font.ITALIC, 18));
		lblNewLabel.setBounds(62, 48, 111, 26);
		this.add(lblNewLabel);
		
		//JLabel navnet "Global Highscore", men skriftstørrelse og koordinater
		JLabel lblGlobalHighscore = new JLabel("Global Highscore:");
		lblGlobalHighscore.setFont(new Font("Nyala", Font.ITALIC, 18));
		lblGlobalHighscore.setBounds(250, 48, 129, 26);
		this.add(lblGlobalHighscore);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addMouseListener(new MouseAdapter() { //Tilføjer en Klikkefunktion
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientHighscore This = (ClientHighscore) (e.getComponent().getParent()); //Component spørger hvad e er, som er vores MouseClick, og getParent er hvor knappen er henne, som er min ClientHighscore
				
				This.client.changePage(new ClientLogin(This.client)); //Her skiftes der side til ClientLogin
			}
		});
		////JLabel farve, skriftstørrelse og koordinater
		btnLogOut.setBackground(Color.LIGHT_GRAY);
		btnLogOut.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnLogOut.setBounds(338, 254, 90, 23);
		this.add(btnLogOut);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() { //Tilføjer en Klikkefunktion
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientHighscore This = (ClientHighscore) (e.getComponent().getParent()); //Component spørger hvad e er, som er vores MouseClick, og getParent er hvor knappen er henne, som er min ClientHighscore
				
				This.client.changePage(new ClientMenu(This.client)); //Skifter side til ClientMenu
			}
		});
		button.setFont(new Font("Nyala", Font.ITALIC, 18));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(10, 254, 83, 23);
		this.add(button);
		
		JSONObject UserHighscore = new JSONObject(); //Opretter et JSONObject som hedder UserHighscore og modtager et JSONObject
		
		int Highscore = -1; //For at vi kan se at der er en fejl og at den ikke har modtaget Highscoren fra serveren
		
		try {
			
			UserHighscore.put("Username", this.client.getCurrentUser()); // jeg indsætter en værdi UserHighscore under Username som indeholder this.client.getCurrentUser()
			UserHighscore.put("Method", "UserHighscore"); // Her fortæller jeg at jeg bruger jeg metoden Highscore
			
			JSONObject Response = this.client.request(UserHighscore); //Her oprettes der et ny JSONObject hvor UserHighscore ind i den nye variabel
			
			if (Response != null && Response.has("Result")) { // her tjekker jeg om Response som er min UserHighscore ikke er null og det har et resultat.
				
				Highscore = Response.getInt("Result");	//Her sættes respone som indeholder Result ind i Highscore
			}
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblNewLabel_1 = new JLabel(Integer.toString(Highscore)); // Her indsættes Highscore i label
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(90, 85, 83, 23);
		add(lblNewLabel_1);
		
		JSONObject GlobalHighscore = new JSONObject(); //Opretter et JSONObject som hedder GlobalHighscore og modtager et JSONObject
		
		Highscore = -1; //For at vi kan se at der er en fejl og at den ikke har modtaget Highscoren fra serveren
		
		try {
			
			GlobalHighscore.put("Method", "GlobalHighscore"); // Her fortæller jeg at jeg bruger metoden Highscore
			
			JSONObject Response = this.client.request(GlobalHighscore); //JSONObjektet kalder jeg Response som indeholder min GlobalHighscore
			
			if (Response != null && Response.has("Result")) { // her tjekker jeg om Response som er vores GloabHighscore ikke er null og det har et resultat.
				
				Highscore = Response.getInt("Result");	//Her sættes Result ind i Highscore
			}
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel label = new JLabel(Integer.toString(Highscore)); // Her indsættes Highscore i label
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(297, 85, 59, 23);
		add(label);
	}
}
