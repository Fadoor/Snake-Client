import javax.swing.JPanel;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class ClientGame extends JPanel {
	
	private SnakeMain client;
	private JSONObject CurrentGame = null;
	private JTextField textField;
	private JLabel lblNewLabel;

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
		
		
		try {
				
				
				String ButtonText = "Leave Game";
				
				if (this.client.getCurrentUser().equals(this.CurrentGame.getString("Player1"))) {
				
					ButtonText = "Delete Game";
					
				}
			
			JButton btnDeleteGame = new JButton(ButtonText);
			btnDeleteGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					ClientGame This = (ClientGame) (e.getComponent().getParent());// Så This kan bruges
					
					JSONObject DeleteGame = new JSONObject();
					
					
					try {
						
						String Method = "LeaveGame";
						
						if (This.client.getCurrentUser().equals(This.CurrentGame.getString("Player1"))) {
							
							Method = "DeleteGame";
						
						}
						
						DeleteGame.put("Username", This.client.getCurrentUser());
						DeleteGame.put("GameName", This.CurrentGame.getString("GameName"));
						DeleteGame.put("Method", Method);
						
						JSONObject Response = This.client.request(DeleteGame);
						
						if (Response != null && Response.has("Result")) {
							
							
							if (Response.getBoolean("Result")) {
							
								This.client.changePage(new ClientNewGame(This.client));
							}
							
						}
						
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnDeleteGame.setFont(new Font("Nyala", Font.ITALIC, 18));
			btnDeleteGame.setBackground(Color.LIGHT_GRAY);
			btnDeleteGame.setBounds(153, 254, 127, 23);
			add(btnDeleteGame);
			
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JLabel lblTest;
		
		try {
			
			lblTest = new JLabel(CurrentGame.getString("GameName"));
			lblTest.setHorizontalAlignment(SwingConstants.CENTER);
			lblTest.setFont(new Font("Nyala", Font.ITALIC, 16));
			lblTest.setBounds(153, 11, 127, 42);
			add(lblTest);
			
			JLabel lblPlayer = new JLabel(CurrentGame.getString("Player1"));
			lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer.setFont(new Font("Nyala", Font.ITALIC, 14));
			lblPlayer.setBounds(10, 64, 83, 14);
			add(lblPlayer);
			
			JLabel lblScore = new JLabel("" + CurrentGame.getInt("Player1Score"));
			lblScore.setHorizontalAlignment(SwingConstants.CENTER);
			lblScore.setFont(new Font("Nyala", Font.ITALIC, 14));
			lblScore.setBounds(10, 89, 83, 14);
			add(lblScore);
			
			JButton btnSendTricks = new JButton("send tricks");
			btnSendTricks.setBackground(new Color(192, 192, 192));
			btnSendTricks.setFont(new Font("Nyala", Font.ITALIC, 14));
			btnSendTricks.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					ClientGame This = (ClientGame) (e.getComponent().getParent());// Så This kan bruges
					
					JSONObject UserInput = new JSONObject();
					
					try {
						
						UserInput.put("Username", This.client.getCurrentUser());
						UserInput.put("GameName", This.CurrentGame.getString("GameName"));
						UserInput.put("Method", "UserInput");
						UserInput.put("UserInput", This.textField.getText());
						
						JSONObject Response = This.client.request(UserInput);
						
						if (Response != null && Response.has("Result")) {
							
							int State = Response.getInt("Result");
							
							if (State != -1) {
								int Player = 1;
								
								if (This.CurrentGame.getString("Player2").equals(This.client.getCurrentUser())) {
									
									Player = 2;
								}
								
								String Text;
								
								if (State == Player) {
									
									Text = "You won";
								}
								else {
									
									Text = "You lost";
								}
								
								if (State == 0) {
									
									Text = "No one has died yet";
								}
								
								if (State == -2) {
									
									Text = "Wait for others move";
								}
								
								This.lblNewLabel.setText(Text);
							}
						}
						
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnSendTricks.setBounds(320, 202, 108, 23);
			add(btnSendTricks);
			
			textField = new JTextField();
			textField.setBackground(Color.WHITE);
			textField.setBounds(224, 203, 86, 20);
			add(textField);
			textField.setColumns(10);
			
			lblNewLabel = new JLabel();
			lblNewLabel.setBounds(190, 76, 136, 84);
			add(lblNewLabel);
			
			if (CurrentGame.has("Player2")) {
			
				JLabel lblPlayer_1 = new JLabel(CurrentGame.getString("Player2"));
				lblPlayer_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblPlayer_1.setFont(new Font("Nyala", Font.ITALIC, 14));
				lblPlayer_1.setBounds(338, 64, 90, 14);
				add(lblPlayer_1);
				
				JLabel label_1 = new JLabel("" + CurrentGame.getInt("Player2Score"));
				label_1.setHorizontalAlignment(SwingConstants.CENTER);
				label_1.setFont(new Font("Nyala", Font.ITALIC, 14));
				label_1.setBounds(338, 89, 90, 14);
				add(label_1);
		
			}
				
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}
}
