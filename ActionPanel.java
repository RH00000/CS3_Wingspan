import java.util.*;

import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class ActionPanel extends JPanel{

	public JButton gainFoodBtn = new JButton("Gain Food");
	public JButton layEggsBtn = new JButton("Lay Eggs");
	public JButton drawCardsBtn = new JButton("Draw Bird");
	public JButton playBirdBtn = new JButton("Play Bird");
	public JButton exchangeFood = new JButton("Exchange");
	public JButton exchangeDice = new JButton();
	public JButton exchangeCard = new JButton();
	public JButton rerollDice = new JButton("ReRoll Dice");

	public ActionPanel() {
		
		setPreferredSize(new Dimension(1600, 100));
		//setBackground(Color.red); //testing only
		
		try {			
			setLayout(new FlowLayout());
			this.add(gainFoodBtn);
			this.add(layEggsBtn);
			this.add(drawCardsBtn);
			this.add(playBirdBtn);
			this.add(exchangeFood);
			this.add(rerollDice);

			setLayout(null);
			gainFoodBtn.setBounds(10, 10, 100, 30);
			gainFoodBtn.setBackground(new Color(98, 189, 175));
			gainFoodBtn.setForeground(Color.white);
			
			layEggsBtn.setBounds(120, 10, 100, 30);
			layEggsBtn.setBackground(new Color(98, 189, 175));
			layEggsBtn.setForeground(Color.white);
			
			drawCardsBtn.setBounds(230, 10, 100, 30);
			drawCardsBtn.setBackground(new Color(98, 189, 175));
			drawCardsBtn.setForeground(Color.white);
			
			playBirdBtn.setBounds(340, 10, 100, 30);
			playBirdBtn.setBackground(new Color(98, 189, 175));
			playBirdBtn.setForeground(Color.white);
			
			exchangeFood.setBounds(340, 10, 100, 30);
			exchangeFood.setBackground(new Color(98, 189, 175));
			exchangeFood.setForeground(Color.white);
			
			rerollDice.setBounds(450, 10, 100, 30);
			rerollDice.setBackground(new Color(98, 189, 175));
			rerollDice.setForeground(Color.white);
		}
		
		catch(Exception e) {
			System.out.println("e");
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		
	}

	//public void drawActions(Graphics g) {}
	
	//public void updateActions(GameEvent e) { }




	
	
}
