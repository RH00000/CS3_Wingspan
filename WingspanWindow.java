import javax.swing.*;

public class WingspanWindow extends JFrame {
	
	private static final int WIDTH = 1600;
	private static final int HEIGHT = 1000;
	
	public WingspanWindow(String name) {
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
	}
}
