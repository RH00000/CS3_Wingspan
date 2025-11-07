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
	private BirdFeederPanel birdfeederpanel;
	private HandPanel handpanel;
	
	private DeckPanel deckpanel;
	
	
	*/
	private TrayPanel traypanel;
	private ActionPanel actionpanel;
	private BoardPanel boardpanel;
	private RoundGoalPanel roundgoalpanel;
	private GameStatePanel gamestatepanel1;
	private GameStatePanel gamestatepanel2;


	
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
		traypanel = new TrayPanel();
		gamestatepanel1 = new GameStatePanel();
		gamestatepanel2 = new GameStatePanel();
		
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
		add(traypanel);
		add(gamestatepanel1);
		add(gamestatepanel2);
		
		actionpanel.setBounds(0, 0, 1000, 50);
		boardpanel.setLocation(0, 0);
		boardpanel.setPreferredSize(new Dimension(800, 550));
		boardpanel.setBounds(0, 50, 800, 550);
		
		roundgoalpanel.setBounds(1150, 650, 400, 264);
		
		traypanel.setBounds(820, 325, 420,210);
		
		gamestatepanel1.setBounds(1300, 10, 300, 300 );
		gamestatepanel2.setBounds(1300, 320, 300, 300);
	}

}
