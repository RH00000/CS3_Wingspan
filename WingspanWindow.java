import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class WingspanWindow extends JFrame {
	
	private static final int WIDTH = 1600;
	private static final int HEIGHT = 1000;
	
	public WingspanWindow(String name) {
		
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);		
		
		addStart();
		
		setVisible(true);
	}
	
	public void addStart() {
		
		final int BTN_WIDTH = 300;
		final int BTN_HEIGHT = 100;
		final int BTN_X = WIDTH/2 - BTN_WIDTH/2;
		final int BTN_Y = HEIGHT-BTN_HEIGHT*3;
		
		StartPagePanel startPanel = new StartPagePanel();
		startPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		startPanel.setLayout(null);
		
		JButton btn = new JButton("BEGIN");
		btn.setBounds(BTN_X, BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		btn.setFocusPainted(false);
		btn.setFont(new Font("Arial", Font.PLAIN, 40));
		btn.setBackground(new Color(98, 189, 175));
		btn.setForeground(Color.white);
		
		startPanel.add(btn);
		
		add(startPanel);
	}
}
