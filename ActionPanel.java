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


public class ActionPanel extends JPanel{

	public JButton gainFoodBtn = new JButton("Gain Food");
	public JButton layEggsBtn = new JButton("Lay Eggs");
	public JButton drawCardsBtn = new JButton("Draw Bird");
	public JButton playBirdBtn = new JButton("Play Bird");
	public JButton exchangeFood = new JButton("Exchange Food");
	public JButton exchangeDice = new JButton("Exchange Dice");
	public JButton exchangeCard = new JButton("Exchange Card");
	public JButton rerollDice = new JButton("ReRoll Dice");
	public JButton seeOtherBoard = new JButton("View Other Board");

	public ActionPanel() {
		try {
			setLayout(null);
			gainFoodBtn.setBounds(10, 10, 100, 30);
			gainFoodBtn.setBackground(Color.blue);
			gainFoodBtn.setForeground(Color.white);
			this.add(gainFoodBtn);
			
			setLayout(null);
			layEggsBtn.setBounds(120, 10, 100, 30);
			layEggsBtn.setBackground(Color.blue);
			layEggsBtn.setForeground(Color.white);
			this.add(layEggsBtn);
			
			setLayout(null);
			drawCardsBtn.setBounds(230, 10, 100, 30);
			drawCardsBtn.setBackground(Color.blue);
			drawCardsBtn.setForeground(Color.white);
			this.add(drawCardsBtn);
			
			setLayout(null);
			playBirdBtn.setBounds(340, 10, 100, 30);
			playBirdBtn.setBackground(Color.blue);
			playBirdBtn.setForeground(Color.white);
			this.add(playBirdBtn);
			
			setLayout(null);
			exchangeFood.setBounds(340, 10, 100, 30);
			exchangeFood.setBackground(Color.blue);
			exchangeFood.setForeground(Color.white);
			this.add(exchangeFood);
			
			setLayout(null);
			rerollDice.setBounds(450, 10, 100, 30);
			rerollDice.setBackground(Color.blue);
			rerollDice.setForeground(Color.white);
			this.add(rerollDice);
			
			setLayout(null);
			exchangeFood.setBounds(560, 10, 120, 30);
			exchangeFood.setBackground(Color.blue);
			exchangeFood.setForeground(Color.white);
			this.add(exchangeFood);
			
			setLayout(null);
			exchangeCard.setBounds(690, 10, 120, 30);
			exchangeCard.setBackground(Color.blue);
			exchangeCard.setForeground(Color.white);
			this.add(exchangeCard);
			
			
			setLayout(null);
			seeOtherBoard.setBounds(690+140, 10, 150, 30);
			seeOtherBoard.setBackground(Color.blue);
			seeOtherBoard.setForeground(Color.white);
			this.add(seeOtherBoard);
			
		}
		
		catch(Exception e) {
			System.out.println("e");
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		
	}

	
	//public void updateActions(GameEvent e) { }




	
	
}
