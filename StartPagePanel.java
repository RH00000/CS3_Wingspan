import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.*;

public class StartPagePanel extends JPanel implements MouseListener {

	private BufferedImage logo;
	private JButton btn;

	public StartPagePanel() {
		
		addMouseListener();

		try {
			logo = ImageIO.read(StartPagePanel.class.getResource("/images/download.jpg"));
		} catch (Exception e) {
			System.out.println("Error");
		}

		setPreferredSize(new Dimension(getWidth(), getHeight()));
		setLayout(new GridBagLayout());
		setBackground(Color.white);

		// button
		btn = new JButton("BEGIN");

		final int BTN_WIDTH = 300;
		final int BTN_HEIGHT = 100;
		final int BTN_X = getWidth() / 2 - BTN_WIDTH / 2;
		final int BTN_Y = getHeight() - BTN_HEIGHT * 3;

		btn.setBounds(BTN_X, BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		btn.setFocusPainted(false);
		btn.setFont(new Font("Arial", Font.PLAIN, 40));
		btn.setBackground(new Color(98, 189, 175));
		btn.setForeground(Color.black);

		add(btn);
		
	}

	public void paint(Graphics g) {

		super.paint(g);

		try {
			//g.drawImage(logo, 400, 200, logo.getWidth() * 27 / 10, logo.getHeight() * 27 / 10, null);
		} catch (Exception e) {

		}

		g.drawString("start panel", 100, 100);
	}

	public JButton getBtn() {
		return btn;
	}

	private void addMouseListener() {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
