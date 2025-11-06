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

public class RoundPanel extends JPanel {
	
	/*
	private GameStatePanel gamestatepanel;
	private BirdFeederPanel birdfeederpanel;
	private HandPanel handpanel;
	
	private TrayPanel traypanel;
	private DeckPanel deckpanel;
	
	
	*/
	
	private ActionPanel actionpanel;
	private BoardPanel boardpanel;
	private RoundGoalPanel roundgoalpanel;
	
	public RoundPanel() {
		
		setLayout(null);
		
		try {
			
		}
		catch(Exception e) {
			System.out.println("error!");
		}
		
		actionpanel = new ActionPanel();
		boardpanel = new BoardPanel();
		roundgoalpanel = new RoundGoalPanel();
		
		addPanels();
		
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.blue);
		//g.drawString("round panel", 100, 100);
		
	}
	
	public void addPanels() {
		
		add(actionpanel);
		add(boardpanel);
		add(roundgoalpanel);
		
		actionpanel.setBounds(0, 0, 1600, 100);
		boardpanel.setLocation(0, 0);
		boardpanel.setPreferredSize(new Dimension(800, 550));
		boardpanel.setBounds(0, 100, 800, 550);
		
		roundgoalpanel.setBounds(1100, 600, 500, 330);
	}

}
