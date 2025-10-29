import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.*;

public class StartPagePanel extends JPanel implements MouseListener{

	private BufferedImage logo;
	private JButton startBtn;
	
	public StartPagePanel() {
		addMouseListener();
		
		try {
			logo = ImageIO.read(StartPagePanel.class.getResource("/images/download.jpg"));
		} 
		catch(Exception e) {
			System.out.println("Error");
		}
		
		
		//button
		startBtn = new JButton("BEGIN");
		
		
	}
	

	public void paint(Graphics g) {
		// g.drawImage(logo,500, 200, logo.getWidth(), logo.getHeight(), null);
	}
	private void addMouseListener() {}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	
}
