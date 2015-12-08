import javax.swing.JPanel;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class ClientGame extends JPanel {
	
	private SnakeMain client;
	private JSONObject CurrentGame = null;

	/**
	 * Create the panel.
	 */
	public ClientGame(SnakeMain client, String GameName) {
		setBackground(Color.LIGHT_GRAY);
		
		this.client = client;
		
		JSONObject GameInfo = new JSONObject();
		
		try {
			
			GameInfo.put("Username", this.client.getCurrentUser());
			GameInfo.put("GameName", GameName);
			GameInfo.put("Method", "GameInfo");
			
			JSONObject Response = this.client.request(GameInfo);
			
			if (Response != null && Response.has("Result")) {
				
				if (Response.getBoolean("Result")) {
					
					this.CurrentGame = Response.getJSONObject("GameInfo");
					
				}	
			}
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (this.CurrentGame == null) {
			
			this.client.changePage(new ClientNewGame(this.client));
		}
		
		setLayout(null);
		
		JButton button = new JButton("Back");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientGame This = (ClientGame) (e.getComponent().getParent());
				
				This.client.changePage(new ClientNewGame(This.client));
			}
		});
		button.setFont(new Font("Nyala", Font.ITALIC, 18));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(10, 254, 83, 23);
		add(button);
		
		JButton button_1 = new JButton("Log out");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientGame This = (ClientGame) (e.getComponent().getParent());
				
				This.client.changePage(new ClientLogin(This.client));
			}
		});
		button_1.setFont(new Font("Nyala", Font.ITALIC, 18));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(338, 254, 90, 23);
		add(button_1);
		
		JButton btnDeleteGame = new JButton("Delete Game");
		btnDeleteGame.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnDeleteGame.setBackground(Color.LIGHT_GRAY);
		btnDeleteGame.setBounds(153, 254, 127, 23);
		add(btnDeleteGame);
		
		JLabel lblTest;
		try {
			lblTest = new JLabel(CurrentGame.getString("GameName"));
			lblTest.setBounds(89, 65, 73, 42);
			add(lblTest);
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}
}
