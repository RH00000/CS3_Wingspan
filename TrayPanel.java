import java.util.*;

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

public class TrayPanel extends JPanel{

	BufferedImage f;
//	public ArrayList<Bird> trayCards = new ArrayList<>();
	
	public TrayPanel() {
		try {
			f = ImageIO.read(StartPagePanel.class.getResource("/images/tray.png"));
		}
		catch(Exception e) {
			System.out.println("error");
		}
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(f, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	public void drawTray(Graphics g) {
		
	}
	//public void updateTray(ArrayList<Bird> trayCards) {}
}
