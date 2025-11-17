import java.util.*;

public class Player {
	
	public String name;
	public String board; // make this Board object later
	public HashMap<Integer, Integer> food;
	public ArrayList<String> hand; //make this bird object later
	public ArrayList<String> bonusCards; //make this BonusCard object later
	public boolean isChoosing;
	
	public Player( ) {
		
		isChoosing = false;
	}
}
