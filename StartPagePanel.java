import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.*;
import java.util.*;

public class StartPagePanel extends JPanel {

	private BufferedImage logo;
	private JButton btn;

	public StartPagePanel() {

		try {
			logo = ImageIO.read(StartPagePanel.class.getResource("/images/download.jpg"));
		} catch (Exception e) {
			System.out.println("Error");
		}

		setLayout(new FlowLayout());
		setBackground(Color.white);

		// button
		btn = new JButton("BEGIN");
		add(btn);
		
		setLayout(null);
		button();
	}

	public void paint(Graphics g) {

		super.paint(g);

		try {
			g.drawImage(logo, 400, 200, logo.getWidth() * 27 / 10, logo.getHeight() * 27 / 10, null);
		} catch (Exception e) {

		}

		//g.drawString("start panel", 100, 100);
	}

	public void button() {
		final int BTN_WIDTH = 300;
		final int BTN_HEIGHT = 100;
		final int BTN_X = 1600 / 2 - BTN_WIDTH / 2;
		final int BTN_Y = 1000 - BTN_HEIGHT * 3;

		btn.setBounds(BTN_X, BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		btn.setFocusPainted(false);
		btn.setFont(new Font("Arial", Font.PLAIN, 40));
		btn.setBackground(new Color(98, 189, 175));
		btn.setForeground(Color.white);

	}
	public JButton getBtn() {
		return btn;
	}
}
