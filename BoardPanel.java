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

public class BoardPanel extends JPanel {

	BufferedImage b;
	//public Player player;
	public int [][] actionTokens;
	//public HashMap<Integer, ArrayList<Bird>> board = new HashMap<>();
	public int[] eggCosts;
	
	
	public BoardPanel() {
		try {
			b = ImageIO.read(StartPagePanel.class.getResource("/images/board.jpg"));
		} 
		catch(Exception e) {
			System.out.println("error");
		}
	}
	
	public void paint(Graphics g) {

		g.drawImage(b,10 , 30, b.getWidth(), b.getHeight(), null);
	}
}
