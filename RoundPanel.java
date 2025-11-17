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

public class RoundPanel extends JPanel implements KeyListener {

	/*
	 * private HandPanel handpanel;
	 * 
	 * private DeckPanel deckpanel;
	 * 
	 * 
	 */
	private TrayPanel traypanel;
	private ActionPanel actionpanel;
	private BoardPanel boardpanel;
	private RoundGoalPanel roundgoalpanel;
	private GameStatePanel gamestatepanel1;
	private GameStatePanel gamestatepanel2;
	private BirdFeederPanel birdfeederpanel;
	
	private Player player1;
	private Player player2;
	private Player currentPlayer;

	public RoundPanel() {

		setLayout(null);
		setFocusable(true);

		try {

		} catch (Exception e) {
			System.out.println("error!");
		}

		actionpanel = new ActionPanel();
		boardpanel = new BoardPanel();
		roundgoalpanel = new RoundGoalPanel();
		traypanel = new TrayPanel();
		gamestatepanel1 = new GameStatePanel();
		gamestatepanel2 = new GameStatePanel();
		birdfeederpanel = new BirdFeederPanel();
		
		currentPlayer = player1;

		addPanels();
		
		addKeyListener(this);

	}

	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.blue);
		// g.drawString("round panel", 100, 100);

		actionpanel.rerollDice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				birdfeederpanel.reroll();
			}
		});
		
		actionpanel.gainFoodBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPlayer.isChoosing = true;
				
			}
		});

	}

	public void addPanels() {

		add(actionpanel);
		add(boardpanel);
		add(roundgoalpanel);
		add(traypanel);
		add(gamestatepanel1);
		add(gamestatepanel2);
		add(birdfeederpanel);

		actionpanel.setBounds(0, 0, 1000, 50);
		boardpanel.setLocation(0, 0);
		boardpanel.setPreferredSize(new Dimension(800, 550));
		boardpanel.setBounds(0, 50, 800, 550);

		roundgoalpanel.setBounds(1150, 650, 400, 264);

		traypanel.setBounds(820, 325, 420, 210);

		gamestatepanel1.setBounds(1300, 10, 300, 300);
		gamestatepanel2.setBounds(1300, 320, 300, 300);

		birdfeederpanel.setBounds(700, 610, 420, 300);
	}
	
	public void switchPlayer() {
		if(currentPlayer.equals(player1)) {
			currentPlayer = player2;
			return;
		}
		
		currentPlayer = player1;
		
	}

	@Override
	public void keyTyped(KeyEvent e) { //NOT WORKING
		// TODO Auto-generated method stub
		System.out.println("sldkjfsf");
		System.out.println(e.getKeyChar());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

}
