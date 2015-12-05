import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientMenu extends JPanel {
	
	private SnakeMain client;

	/**
	 * Create the panel.
	 */
	public ClientMenu(SnakeMain client) {
		this.client = client;
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(null);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome Name");
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Nyala", Font.ITALIC, 26));
		lblWelcomeToThe.setBounds(62, 25, 298, 48);
		this.add(lblWelcomeToThe);
		
		JButton button = new JButton("Start new game");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientMenu This = (ClientMenu) (e.getComponent().getParent());
				
				This.client.changePage(new ClientNewGame(This.client));
			}
		});
		button.setBackground(Color.LIGHT_GRAY);
		button.setFont(new Font("Nyala", Font.ITALIC, 18));
		button.setBounds(137, 128, 153, 23);
		this.add(button);

		JButton button_1 = new JButton("Highscore");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientMenu This = (ClientMenu) (e.getComponent().getParent());
				
				This.client.changePage(new ClientHighscore(This.client));
			}
		});
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setFont(new Font("Nyala", Font.ITALIC, 18));
		button_1.setBounds(137, 160, 153, 23);
		this.add(button_1);
		
		JButton btnLogUd = new JButton("Log out");
		btnLogUd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLogUd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ClientMenu This = (ClientMenu) (e.getComponent().getParent());
				
				This.client.changePage(new ClientLogin(This.client));
			}
		});
		btnLogUd.setBackground(Color.LIGHT_GRAY);
		btnLogUd.setFont(new Font("Nyala", Font.ITALIC, 18));
		btnLogUd.setBounds(338, 254, 90, 23);
		this.add(btnLogUd);
	}

}
