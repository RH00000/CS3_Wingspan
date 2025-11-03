import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WingspanWindow extends JFrame {

	private static final int WIDTH = 1600;
	private static final int HEIGHT = 1000;
	
	StartPagePanel startPanel;
	RoundPanel roundPanel;

	JButton btn;

	public WingspanWindow(String name) {

		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);

		addStart();
		buttonClicked();
		
		setVisible(true);

	}

	public void addStart() {

		final int BTN_WIDTH = 300;
		final int BTN_HEIGHT = 100;
		final int BTN_X = WIDTH / 2 - BTN_WIDTH / 2;
		final int BTN_Y = HEIGHT - BTN_HEIGHT * 3;

		startPanel = new StartPagePanel();
		roundPanel = new RoundPanel();
		
		startPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		startPanel.setLayout(null);
		roundPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		roundPanel.setLayout(null);

		btn = new JButton("BEGIN");
		btn.setBounds(BTN_X, BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		btn.setFocusPainted(false);
		btn.setFont(new Font("Arial", Font.PLAIN, 40));
		btn.setBackground(new Color(98, 189, 175));
		btn.setForeground(Color.white);
		
		roundPanel.setVisible(false);
		startPanel.setVisible(true);
		
		
		startPanel.add(btn);
		
		add(roundPanel);
		add(startPanel);
		
	}

	public void buttonClicked() {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startPanel.setVisible(false);
				roundPanel.setVisible(true);
			}
		});
	}
}
