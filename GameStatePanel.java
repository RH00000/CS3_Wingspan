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

public class GameStatePanel extends JPanel{
	public ArrayList<Integer> numFood = new ArrayList<>();
	public ArrayList<Integer> numCards = new ArrayList<>();
	public ArrayList<Integer> foodInside;
	public int numActions;
	public int numEggs;
	public boolean isFirst;
	
	
	public GameStatePanel() {
		try {
			numFood = new ArrayList<>();
			numCards = new ArrayList<>();
			foodInside = new ArrayList<>();
			numActions = 8;
			numEggs = 0;
			isFirst = true;
		}
		catch(Exception e) {
			System.out.println("error");
		}
	}
	
	public void paint(Graphics g) {
		//super.paint(g);
		g.setColor(Color.black);
		g.drawRect(0,0, this.getWidth()-30, this.getHeight()-30);
		g.setColor(Color.black);
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g.drawString("Player #", 10, 30);
	}
	
	public void drawGameState(Graphics g) {
		super.paint(g);
	}
	
public void updateGameState(ArrayList<Integer> numFood, ArrayList<Integer> foodInside, ArrayList<Integer> numCards, int numActions, int numEggs, boolean isFirst ) {
		
		this.numFood = numFood;
		this.foodInside = foodInside;
		this.numCards = numCards;
		this.numActions = numActions;
		this.numEggs = numEggs;
		this.isFirst = isFirst;
	}
}
