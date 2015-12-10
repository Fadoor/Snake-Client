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

public class ClientNewGame extends JPanel { //ClientNewGame Nedarver funktioner fra Jpanel
	
	//Instantiering af lokalvariable
	private JTextField textField;
	private SnakeMain client;
	private JList list;

	/**
	 * Create the panel.
	 */
	public ClientNewGame(SnakeMain client) { //Konstukt�r oprettes som ejes af SnakeMain som jeg kalder for client.
		this.client = client; // S�tter client ind i this.client
		
		class Item { //Midlertidig klasse en klasse der kan holde p� highscoren og spilnavnet. Det kan returnere en string som ser flot ud til liste visning.
			
			public int Highscore;
			public String GameName;
			
			//Konstukt�r oprettes som ejes af Item som jeg kalder for Item. Den indeholder en int og en String
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
		btnLogOut.addMouseListener(new MouseAdapter() { //Tilf�jer en Mouselistener som  g�r jeg kan trykke p� knappen Log out
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientNewGame This = (ClientNewGame) (e.getComponent().getParent()); //Component sp�rger hvad e er, som er vores MouseClick, og getParent er hvor knappen er henne, som er min ClientNewGame
				
				This.client.changePage(new ClientLogin(This.client)); // Her skiftes der til ClientLogin siden, n�r der klikkes p� knappen.
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
		btnCreateGame.addMouseListener(new MouseAdapter() { //Tilf�jer en Mouselistener som  g�r jeg kan trykke p� knappen Create game
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientNewGame This = (ClientNewGame) (e.getComponent().getParent()); //Component sp�rger hvad e er, som er vores MouseClick, og getParent er hvor knappen er henne, som er min ClientNewGame
				
				JSONObject CreateGame = new JSONObject(); //Opretter et JSONObject som hedder CreateGame og modtager et JSONObject
				
				try {
					
					CreateGame.put("GameName", This.textField.getText()); //Jeg inds�tter en v�rdi i CreateGame under GameName som indeholder this.texfield.getText()
					CreateGame.put("Username", This.client.getCurrentUser()); //Vi inds�tter en v�rdi i CreateGame under Username, som indeholder This.client.getCurrentUser().
					CreateGame.put("Method", "CreateGame"); // Her fort�ller jeg at Jeg bruger jeg metoden CreateGame
					
					JSONObject Response = This.client.request(CreateGame); //Her oprettes der et ny JSONObject hvor CreateGame s�ttes ind i den nye variabel
					
					if (Response != null && Response.has("Result")) { // her tjekker jeg om Response som er vores CreateGame ikke er null og det har et resultat.
						
						if (Response.getBoolean("Result")) { //Hvis Response er true; da jeg altid antager en boolean er true
						
							This.client.changePage(new ClientGame(This.client, This.textField.getText())); // Her skifter jeg side til ClientMenu hvis CreateGame k�res rigtigt.
						
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
			public void mouseClicked(MouseEvent e) { //Tilf�jer en Mouselistener som  g�r jeg kan trykke p� knappen Back
				
				ClientNewGame This = (ClientNewGame) (e.getComponent().getParent()); //Component sp�rger hvad e er, som er vores MouseClick, og getParent er hvor knappen er henne, som er min ClientLogin
				
				This.client.changePage(new ClientMenu(This.client)); // Her skiftes der til ClientMenu siden, n�r der klikkes p� knappen.
			}
		});
		button.setFont(new Font("Nyala", Font.ITALIC, 18));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(10, 254, 83, 23);
		this.add(button);
		
		JButton btnJoinGame = new JButton("Join Game");
		btnJoinGame.addActionListener(new ActionListener() { //Tilf�jer en Mouselistener som  g�r jeg kan trykke p� knappen Join Game
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnJoinGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientNewGame This = (ClientNewGame) (e.getComponent().getParent()); //Component sp�rger hvad e er, som er vores MouseClick, og getParent er hvor knappen er henne, som er min ClientNewGame
				
				JSONObject JoinGame = new JSONObject(); //Opretter et JSONObject som hedder JoinGame og modtager et JSONObject
				
				Item SelectItem = (Item) This.list.getSelectedValue(); //Opretter et Item object som har navnet SelectItem og indeholder (Item) som indeholder This.list.getSelectedValue() som er det brugeren har markeret p� siden.
				
				if (SelectItem != null) { // Hvis SelecItem ikke er null k�rer try nedenunder
				
					try {
						
						JoinGame.put("GameName", SelectItem.GameName); // Jeg inds�tter en v�rdi i JoinGame under GameName som indeholder SelectItem.GameName
						JoinGame.put("Username", This.client.getCurrentUser()); // Jeg inds�tter en v�rdi i JoinGame under GameName som indeholder This.client.getCurrentUser()
						JoinGame.put("Method", "JoinGame"); // Her fort�ller jeg at Jeg bruger jeg metoden JoinGame
						
						JSONObject Response = This.client.request(JoinGame); //Her oprettes der et ny JSONObject hvor JoinGame inds�ttes ind den nye variabel
						
						if (Response != null && Response.has("Result")) { // her tjekker jeg om Response som er vores login ikke er null og det har et resultat.
							
							boolean ThisResult = Response.getBoolean("Result"); //Her oprettes en boolean med navnet ThisResult som indeholder Response som indeholder getBoolean("Result")
							
							if (ThisResult) { //Hvis vi f�r ThisResult forts�tter if
								
								This.client.changePage(new ClientGame(This.client, SelectItem.GameName)); // Her skifter jeg side til ClientGame hvis det er et gylidigt login.
								
							}
						}
						
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
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
		
		JSONObject Games = new JSONObject(); //Opretter et JSONObject som hedder Games og modtager et JSONObject
		
		JSONObject Response; // Vi opretter et JSONObject med navnet Respone uden nogen v�rdi
		
		JSONArray GameList = null; //Her opretter vi et JSONArray med navnet Gamelist som er sat til null
		
		try {
			
			Games.put("Method", "ShowGames"); // Her fort�ller jeg at Jeg bruger jeg metoden ShowGames
			
			Response = this.client.request(Games); //Her oprettes der et ny JSONObject hvor Games ind s�ttes ind den nye variabel
			
			if (Response != null && Response.has("Result")) { // her tjekker jeg om Response som er vores login ikke er null og det har et resultat.
				
				GameList = Response.getJSONArray("Result");	 //Her oprettes der et nyt JSONArray med navnet GameList som indeholder Response.getJSONArray("Result")
			}
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		list = new JList();
		list.setBounds(103, 159, 225, 68);
		add(list);
		
		if (GameList != null) { //Hvis Gamelist ikke er lig med null k�rer if
			
			DefaultListModel ListModel = new DefaultListModel(); // Opretter en DeaultListModel med navnet ListModel som modtager en DefaultListModel
			
			JSONObject CurrentGame; //Vi opretter et JSONObject med navnet CurrentGame uden nogen v�rdi
			
			for (int i = 0; i < GameList.length(); i++) { // i vores for laves en int som hedder i og s�tte lig med 0. Hvis i er mindre en GameList k�rer try, og loppet k�rer igen og 1 l�gges hele tiden til hver gang.
				
				try {
					
					CurrentGame = GameList.getJSONObject(i); //her indeholder CurrentGame en GameList, som indeholder getJSONObject(i)
					
					ListModel.addElement(new Item(CurrentGame.getInt("Highscore"), CurrentGame.getString("Name"))); // ListModel tilf�jer et element som jeg kalder new Item som indeholder CurrentGame.getInt("Highscore") og CurrentGame.getString("Name")
				
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			list.setModel(ListModel);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Dette g�r at man kun kan markere et spil af gangen.
			
			JScrollPane scrollPane_1 = new JScrollPane(list); // her tilf�jer jeg et ScrollPanel til siden hvor man v�lger aktive spil.
	        scrollPane_1.setBounds(103, 159, 225, 68);
	        this.add(scrollPane_1);
			
		}
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addMouseListener(new MouseAdapter() { //Tilf�jer en Mouselistener som  g�r jeg kan trykke p� knappen Refresh
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientNewGame This = (ClientNewGame) (e.getComponent().getParent()); //Component sp�rger hvad e er, som er vores MouseClick, og getParent er hvor knappen er henne, som er min ClientNewGame
				
				This.client.changePage(new ClientNewGame(This.client)); // Her skifter jeg side til ClientNewGame, som er en m�de at refrehse siden p�.
			}
		});
		btnNewButton.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(338, 171, 90, 29);
		add(btnNewButton);
		
		
		
	}
}
