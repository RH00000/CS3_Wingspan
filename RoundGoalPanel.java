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
import java.util.*;


public class RoundGoalPanel extends JPanel{

	//public ArrayList<RoundGoal> goals = new ArrayList<>();
	//public HashMap<RoundGoal, int[]> roundScores = new ArrayList<>();
	BufferedImage rb;
	
	public RoundGoalPanel() {
		try {
			rb = ImageIO.read(StartPagePanel.class.getResource("/images/roundgoal.jpg"));
		}
		catch(Exception e) {
			System.out.println("error");
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(rb, 0,0, this.getWidth(), this.getHeight(), null );
	}
	
	public void drawRoundGoal(Graphics g) {
		
	}
	
	//public void updateRoundGoal(Player p, int round, int score) {}

}
