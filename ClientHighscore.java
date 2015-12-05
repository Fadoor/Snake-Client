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

public class ClientHighscore extends JPanel {
	
	private SnakeMain client;

	/**
	 * Create the panel.
	 */
	public ClientHighscore(SnakeMain client) {
		this.client = client;
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("My Highscore:");
		lblNewLabel.setFont(new Font("Nyala", Font.ITALIC, 15));
		lblNewLabel.setBounds(40, 48, 83, 26);
		this.add(lblNewLabel);
		
		JLabel lblGameHighscores = new JLabel("Game Highscores:");
		lblGameHighscores.setFont(new Font("Nyala", Font.ITALIC, 15));
		lblGameHighscores.setBounds(163, 48, 107, 26);
		this.add(lblGameHighscores);
		
		JLabel lblGlobalHighscore = new JLabel("Global Highscore:");
		lblGlobalHighscore.setFont(new Font("Nyala", Font.ITALIC, 15));
		lblGlobalHighscore.setBounds(299, 48, 105, 26);
		this.add(lblGlobalHighscore);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientHighscore This = (ClientHighscore) (e.getComponent().getParent());
				
				This.client.changePage(new ClientLogin(This.client));
			}
		});
		btnLogOut.setBackground(Color.LIGHT_GRAY);
		btnLogOut.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnLogOut.setBounds(338, 254, 90, 23);
		this.add(btnLogOut);
		
		JButton button = new JButton("Back");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientHighscore This = (ClientHighscore) (e.getComponent().getParent());
				
				This.client.changePage(new ClientMenu(This.client));
			}
		});
		button.setFont(new Font("Nyala", Font.ITALIC, 18));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(10, 254, 83, 23);
		this.add(button);
		
		List list = new List();
		list.setBounds(160, 80, 110, 180);
		add(list);
		
		JSONObject UserHighscore = new JSONObject();
		
		int Highscore = -1;
		
		try {
			
			UserHighscore.put("Username", this.client.getCurrentUser());
			UserHighscore.put("Method", "UserHighscore");
			
			JSONObject Response = this.client.request(UserHighscore);
			
			if (Response != null && Response.has("Result")) {
				
				Highscore = Response.getInt("Result");	
			}
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblNewLabel_1 = new JLabel(Integer.toString(Highscore));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(40, 85, 83, 23);
		add(lblNewLabel_1);
		
		JSONObject GlobalHighscore = new JSONObject();
		
		Highscore = -1;
		
		try {
			
			GlobalHighscore.put("Method", "GlobalHighscore");
			
			JSONObject Response = this.client.request(GlobalHighscore);
			
			if (Response != null && Response.has("Result")) {
				
				Highscore = Response.getInt("Result");	
			}
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel label = new JLabel(Integer.toString(Highscore));
		label.setBounds(299, 85, 83, 23);
		add(label);
	}
}
