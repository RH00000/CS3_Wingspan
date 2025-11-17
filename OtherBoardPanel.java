import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;

public class OtherBoardPanel extends JPanel {
	
	BufferedImage b;
	
	public OtherBoardPanel() {
		
		try {
			b = ImageIO.read(StartPagePanel.class.getResource("/images/board.jpg"));

		} 
		
		catch(Exception e) {
			System.out.println("Error");
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(b, 100, 100, b.getWidth(), b.getHeight(), null);
	}
}
