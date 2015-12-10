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

public class ClientGame extends JPanel { //ClientNewGame Nedarver funktioner fra Jpanel
	
	//Instantiering af lokalvariable
	private SnakeMain client;
	private JSONObject CurrentGame = null;
	private JTextField textField;
	private JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public ClientGame(SnakeMain client, String GameName) { //Konstuktør oprettes som ejes af SnakeMain som jeg kalder for client. Indeholder SnakeMain Client og String GameName
		setBackground(Color.LIGHT_GRAY);
		
		this.client = client; // Sætter client  ind i this.client
		
		JSONObject GameInfo = new JSONObject();  //Opretter et JSONObject som hedder GameInfo og modtager et JSONObject
		
		try {
			
			GameInfo.put("Username", this.client.getCurrentUser()); // Jeg indsætter en værdi i Gameinfo under Username som indeholder this.client.getCurrentUser()
			GameInfo.put("GameName", GameName); // Jeg indsætter en værdi i Gameinfo under GameName som indeholder GameName
			GameInfo.put("Method", "GameInfo"); // Her fortæller jeg at Jeg bruger jeg metoden GameInfo
			
			JSONObject Response = this.client.request(GameInfo); //Her oprettes der et ny JSONObject hvor GameInfo ind sættes ind den nye variabel Response
			
			if (Response != null && Response.has("Result")) { // her tjekker jeg om Response som er vores login ikke er null og det har et resultat.
				
				if (Response.getBoolean("Result")) { //Hvis Response er true kører if; Man antager altid at en boolean er true
					
					this.CurrentGame = Response.getJSONObject("GameInfo"); //this.CurrentGame indeholder Response som indeholder JSONObjectet "GameInfo"
					
				}	
			}
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (this.CurrentGame == null) { // Hvis this.CurrentGame er det samme som null så kører if'en
			
			this.client.changePage(new ClientNewGame(this.client)); // Her skifter siden til ClientNewGame hvis spillet ikke findes og det kan oprettes
		}
		
		setLayout(null);
		
		JButton button = new JButton("Back");
		button.addMouseListener(new MouseAdapter() { //Tilføjer en Mouselistener som  gør jeg kan trykke på knappen back
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientGame This = (ClientGame) (e.getComponent().getParent()); //Component spørger hvad e er, som er vores MouseClick, og getParent er hvor knappen er henne, som er min ClientGame
				
				This.client.changePage(new ClientNewGame(This.client)); // Her skifter jeg side til ClientNewGame når man trykker på back knappen back.
			}
		});
		button.setFont(new Font("Nyala", Font.ITALIC, 18));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(10, 254, 83, 23);
		add(button);
		
		JButton button_1 = new JButton("Log out");
		button_1.addMouseListener(new MouseAdapter() { //Tilføjer en Mouselistener som  gør jeg kan trykke på knappen Log out
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientGame This = (ClientGame) (e.getComponent().getParent()); //Component spørger hvad e er, som er vores MouseClick, og getParent er hvor knappen er henne, som er min ClientGame
				
				This.client.changePage(new ClientLogin(This.client)); // Her skifter jeg side til ClientLogin når man trykker på back knappen log out.
			}
		});
		button_1.setFont(new Font("Nyala", Font.ITALIC, 18));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(338, 254, 90, 23);
		add(button_1);
		
		
		try {
				
				
				String ButtonText = "Leave Game"; // Denne her knap kan kun ses af Player2
				
				if (this.client.getCurrentUser().equals(this.CurrentGame.getString("Player1"))) { // Det er altid brugeren som opretter spillet som bliver player1. Derfor er han den eneste der kan slette et spil.
																								//Der tjekkes for om det er Player1.
					ButtonText = "Delete Game";// Her kan Player1 se knappen slet spil.
					
				}
			
			JButton btnDeleteGame = new JButton(ButtonText);
			btnDeleteGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					ClientGame This = (ClientGame) (e.getComponent().getParent());// Så This kan bruges
					
					JSONObject DeleteGame = new JSONObject(); //Opretter et JSONObject som hedder DeletGame og modtager et JSONObject
					
					
					try {
						
						String Method = "LeaveGame"; // Denne her knap kan kun ses af Player2
						
						if (This.client.getCurrentUser().equals(This.CurrentGame.getString("Player1"))) {// Det er altid brugeren som opretter spillet som bliver player1. Derfor er han den eneste der kan slette et spil.
																										//Der tjekkes for om det er Player1.
							
							Method = "DeleteGame";// Her kan Player1 se knappen slet spil
						
						}
						
						DeleteGame.put("Username", This.client.getCurrentUser()); // Jeg indsætter en værdi i Deletegame under Username som indeholder This.client.getCurrentUser()
						DeleteGame.put("GameName", This.CurrentGame.getString("GameName")); // Jeg indsætter en værdi i Deletegame under Username som indeholder This.CurrentGame.getString("GameName")
						DeleteGame.put("Method", Method); // Her fortæller jeg at Jeg bruger jeg metoden Method
						
						JSONObject Response = This.client.request(DeleteGame); //Her oprettes der et ny JSONObject hvor DeleteGame ind sættes ind den nye variabel Response
						
						if (Response != null && Response.has("Result")) { // her tjekker jeg om Response som er vores login ikke er null og det har et resultat.
							
							
							if (Response.getBoolean("Result")) { //Her oprettes en boolean med navnet ThisResult som indeholder Response som indeholder getBoolean("Result")
							
								This.client.changePage(new ClientNewGame(This.client)); // Her skifter jeg side til ClientNewGame hvis det er et gylidigt login.
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
			
			lblTest = new JLabel(CurrentGame.getString("GameName")); // i denne Jlabel trækker vi GameName fra CurrentGame som viser navnet på spillet når det oprettes og når andre brugere deltager i spillet.
			lblTest.setHorizontalAlignment(SwingConstants.CENTER);
			lblTest.setFont(new Font("Nyala", Font.ITALIC, 16));
			lblTest.setBounds(153, 11, 127, 42);
			add(lblTest);
			
			JLabel lblPlayer = new JLabel(CurrentGame.getString("Player1")); // Her Indsættes Player1's Navn i JLabel så det kan ses på spilsiden
			lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlayer.setFont(new Font("Nyala", Font.ITALIC, 14));
			lblPlayer.setBounds(10, 64, 83, 14);
			add(lblPlayer);
			
			JLabel lblScore = new JLabel("" + CurrentGame.getInt("Player1Score"));// Her indsættes Player1s highscore så det kan ses på spilsiden 
			lblScore.setHorizontalAlignment(SwingConstants.CENTER);
			lblScore.setFont(new Font("Nyala", Font.ITALIC, 14));
			lblScore.setBounds(10, 89, 83, 14);
			add(lblScore);
			
			JButton btnSendTricks = new JButton("send tricks"); // her sender spillerne deres træk
			btnSendTricks.setBackground(new Color(192, 192, 192));
			btnSendTricks.setFont(new Font("Nyala", Font.ITALIC, 14));
			btnSendTricks.addMouseListener(new MouseAdapter() { //Tilføjer en Mouselistener som  gør jeg kan trykke på knappen Send tricks
				@Override
				public void mouseClicked(MouseEvent e) {
					
					ClientGame This = (ClientGame) (e.getComponent().getParent());// Så This kan bruges
					
					JSONObject UserInput = new JSONObject(); //Opretter et JSONObject som hedder JoinGame og modtager et JSONObject
					
					try {
						
						UserInput.put("Username", This.client.getCurrentUser()); // Jeg indsætter en værdi i UserInput under UserName som indeholder This.client.getCurrentUser()
						UserInput.put("GameName", This.CurrentGame.getString("GameName")); // Jeg indsætter en værdi i UserInput under GameName som indeholder This.CurrentGame.getString("GameName")
						UserInput.put("Method", "UserInput"); // Her fortæller jeg at Jeg bruger jeg metoden UserInput
						UserInput.put("UserInput", This.textField.getText());// Jeg indsætter en værdi i UserInput under UserInput som indeholder This.textField.getText()
						
						JSONObject Response = This.client.request(UserInput); //Her oprettes der et ny JSONObject hvor USerInput indsættes ind den nye variabel
						
						if (Response != null && Response.has("Result")) { // her tjekker jeg om Response som er vores login ikke er null og det har et resultat.
							
							int State = Response.getInt("Result"); // opretter en int med navnet State som indeholder response som indeholder Result.
							
							if (State != -1) { // Hvis State ikke er -1 kører if
								int Player = 1; //Player bliver sat til 1
								
								if (This.CurrentGame.getString("Player2").equals(This.client.getCurrentUser())) { //Hvis den bruger vi har fat i er den samme som brugeren i klienten kører if
									
									Player = 2; // Her sættes Player til 2
								}
								
								String Text; // String vi kalder Text
								
								if (State == Player) { //Hvis State er det samme som Player
									
									Text = "You won"; // Stringen Text viser You won
								}
								else {
									
									Text = "You lost"; // Ellers vises Stringen You Lost
								}
								
								if (State == 0) { // Hvis State er det samme som 0 kører if
									
									Text = "No one has died yet"; // Her vises Stringen Text No one has died yet
								}
								
								if (State == -2) { //Hvis State er lig med -2 kører if
									
									Text = "Wait for others move"; //Her vises Stringen Text Wait for others move
								}
								
								This.lblNewLabel.setText(Text); // Her vises teksten på spilsiden.
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
			
			if (CurrentGame.has("Player2")) { // Hvis CurrentGame indeholder Player2 kører if
			
				JLabel lblPlayer_1 = new JLabel(CurrentGame.getString("Player2")); // Her sættes player2 navn i den label der vises på spilsiden
				lblPlayer_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblPlayer_1.setFont(new Font("Nyala", Font.ITALIC, 14));
				lblPlayer_1.setBounds(338, 64, 90, 14);
				add(lblPlayer_1);
				
				JLabel label_1 = new JLabel("" + CurrentGame.getInt("Player2Score")); // Her sættes player2Score i den label der vises på spilsiden under Player2 navn
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
