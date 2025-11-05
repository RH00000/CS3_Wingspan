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
	
	private GameStatePanel gamestatepanel;
	private BirdFeederPanel birdfeederpanel;
	private HandPanel handpanel;
	private RoundGoalPanel roundgoalpanel;
	private TrayPanel traypanel;
	private DeckPanel deckpanel;
	private ActionPanel actionpanel;
	private BoardPanel boardpanel;

	
	public RoundPanel() {
		
		try {
			
		}
		catch(Exception e) {
			System.out.println("error!");
		}
		
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.blue);
		g.drawString("round panel", 100, 100);
	}

}
