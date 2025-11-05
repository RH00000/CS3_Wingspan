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

public class GamePanel extends JPanel {

	private StartPagePanel startPanel;
	private RoundPanel roundPanel;
	private JButton btn;
	
	private CardLayout c;
	
	public GamePanel(CardLayout c) {
		
		this.c = c;
		startPanel = new StartPagePanel();
		roundPanel = new RoundPanel();
		btn = startPanel.getBtn();
		
		setup();
		
		switchPanels();
		
	}
	

	public void paint(Graphics g) {
		super.paint(g);
	}
	
	public void setup() {
		
		setLayout(c);
		startPanel.setPreferredSize(new Dimension(1600, 1000));
		roundPanel.setPreferredSize(new Dimension(1600, 1000));
		
		add(startPanel, "start");
		add(roundPanel, "round");
	}
	
	public void switchPanels() {
		// TODO Auto-generated method stub
		btn.addActionListener((e) -> {
			
			c.show(this, "round");
		});
	}
	
}
