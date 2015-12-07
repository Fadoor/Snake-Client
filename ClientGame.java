import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientGame extends JPanel {
	
	private SnakeMain client;

	/**
	 * Create the panel.
	 */
	public ClientGame(SnakeMain client) {
		setBackground(Color.LIGHT_GRAY);
		
		this.client = client;
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

	}

}
