package net.kata.tennis;
/**
 * represents a sport match
 * @author kei
 *
 */
public interface SportMatch {
	
	/**
	 * get the current score of the game
	 * @return
	 */
	String getGameScore();
	
	/**
	 * the player wins a point
	 * @param playerName
	 */
	void winPoint(String playerName);
	
	/**
	 * get the winner if the game is finished
	 * @return
	 */
	String getWinnerFormatted();
	
}
