import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.awt.List;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientNewGame extends JPanel {

	private JTextField textField;
	private SnakeMain client;
	private JList list;

	/**
	 * Create the panel.
	 */
	public ClientNewGame(SnakeMain client) {
		this.client = client;
		
		class Item {
			
			public int Highscore;
			public String GameName;
			
			public Item(int Highscore, String GameName) {
				
				this.Highscore = Highscore;
				this.GameName = GameName;
				
			}
			
			public String toString() {
				
				return this.GameName+ "      " + this.Highscore;
				
			}
			
		}
		
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(null);
		
		JLabel lblGameName = new JLabel("Game Name:");
		lblGameName.setFont(new Font("Nyala", Font.ITALIC, 17));
		lblGameName.setBounds(10, 91, 90, 14);
		this.add(lblGameName);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientNewGame This = (ClientNewGame) (e.getComponent().getParent());
				
				This.client.changePage(new ClientLogin(This.client));
			}
		});
		btnLogOut.setBackground(Color.LIGHT_GRAY);
		btnLogOut.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnLogOut.setBounds(338, 254, 90, 23);
		this.add(btnLogOut);
		
		JLabel lblCreatejoinGame = new JLabel("Create/Join Game");
		lblCreatejoinGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreatejoinGame.setFont(new Font("Nyala", Font.ITALIC, 26));
		lblCreatejoinGame.setBounds(62, 25, 298, 48);
		this.add(lblCreatejoinGame);
		
		JButton btnCreateGame = new JButton("Create Game");
		btnCreateGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientNewGame This = (ClientNewGame) (e.getComponent().getParent());
				
				JSONObject CreateGame = new JSONObject();
				
				try {
					
					CreateGame.put("GameName", This.textField.getText());
					CreateGame.put("Method", "CreateGame");
					
					JSONObject Response = This.client.request(CreateGame);
					
					if (Response != null && Response.has("Result")) {
						
						if (Response.getBoolean("Result")) {
						
							This.client.changePage(new ClientGame(This.client));
						
						}
						
					}
					
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnCreateGame.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnCreateGame.setBackground(Color.LIGHT_GRAY);
		btnCreateGame.setBounds(142, 125, 132, 23);
		this.add(btnCreateGame);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Nyala", Font.ITALIC, 16));
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(Color.WHITE);
		textField.setBounds(103, 84, 225, 30);
		this.add(textField);
		
		JButton button = new JButton("Back");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientNewGame This = (ClientNewGame) (e.getComponent().getParent());
				
				This.client.changePage(new ClientMenu(This.client));
			}
		});
		button.setFont(new Font("Nyala", Font.ITALIC, 18));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(10, 254, 83, 23);
		this.add(button);
		
		JButton btnJoinGame = new JButton("Join Game");
		btnJoinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnJoinGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientNewGame This = (ClientNewGame) (e.getComponent().getParent());
				
				JSONObject JoinGame = new JSONObject();
				
				try {
					
					Item SelectItem = (Item) This.list.getSelectedValue();
					
					JoinGame.put("GameName", SelectItem.GameName);
					JoinGame.put("Username", This.client.getCurrentUser());
					JoinGame.put("Method", "JoinGame");
					
					JSONObject Response = This.client.request(JoinGame);
					
					if (Response != null && Response.has("Result")) {
						
						boolean ThisResult = Response.getBoolean("Result");
						
						if (ThisResult) {
							
							This.client.changePage(new ClientGame(This.client));
							
						}
					}
					
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnJoinGame.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnJoinGame.setBackground(Color.LIGHT_GRAY);
		btnJoinGame.setBounds(142, 238, 132, 23);
		this.add(btnJoinGame);
		
		JLabel label_1 = new JLabel("Game Name:");
		label_1.setFont(new Font("Nyala", Font.ITALIC, 17));
		label_1.setBounds(10, 170, 90, 30);
		this.add(label_1);
		
		JSONObject Games = new JSONObject();
		
		JSONObject Response;
		
		JSONArray GameList = null;
		
		try {
			
			Games.put("Method", "ShowGames");
			
			Response = this.client.request(Games);
			
			if (Response != null && Response.has("Result")) {
				
				GameList = Response.getJSONArray("Result");	
			}
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		list = new JList();
		list.setBounds(103, 159, 225, 68);
		add(list);
		
		if (GameList != null) {
			
			DefaultListModel ListModel = new DefaultListModel();
			
			JSONObject CurrentGame;
			
			for (int i = 0; i < GameList.length(); i++) {
				
				try {
					
					CurrentGame = GameList.getJSONObject(i);
					
					ListModel.addElement(new Item(CurrentGame.getInt("Highscore"), CurrentGame.getString("Name")));
				
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			list.setModel(ListModel);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane scrollPane_1 = new JScrollPane(list);
	        scrollPane_1.setBounds(103, 159, 225, 68);
	        this.add(scrollPane_1);
			
		}
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientNewGame This = (ClientNewGame) (e.getComponent().getParent());
				
				This.client.changePage(new ClientLogin(This.client));
			}
		});
		btnNewButton.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(338, 171, 90, 29);
		add(btnNewButton);
		
		
		
	}
}
