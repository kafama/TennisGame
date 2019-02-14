package net.kata.tennis;

/**
 * Tennis game according to the sprint 1 : User story 2
 * 
 * @author kei
 *
 */
public abstract class AbstractTennis implements SportMatch {

	public static final String DEFAULT_PLAYER_1 = "Player 1";
	public static final String DEFAULT_PLAYER_2 = "Player 2";

	protected Player player1;

	protected Player player2;

	protected Player winner;

	/**
	 * default constructor
	 */
	public AbstractTennis() {
		this.player1 = new Player(DEFAULT_PLAYER_1);
		this.player2 = new Player(DEFAULT_PLAYER_2);
	}

	/**
	 * constructor with parameters
	 * 
	 * @param player1Name first player's name
	 * @param player2Name seconde player's name
	 */
	public AbstractTennis(String player1Name, String player2Name) {
		this.player1 = new Player(player1Name);
		this.player2 = new Player(player2Name);
	}

	/**
	 * when player win a ball this method is called to increment his score
	 * 
	 * @param playerName
	 */
	public void winPoint(String playerName) {
		Player playerToIncrementScore = getPlayerToIncrement(playerName);
		incrementScore(playerToIncrementScore);
		if (isGameFinished()) {
			this.winner = playerToIncrementScore;
			initScores();
		}
	}

	/**
	 * return the player object winning this point
	 * 
	 * @param playerName
	 * @return
	 */
	protected Player getPlayerToIncrement(String playerName) {
		Player playerToIncrementScore = null;
		if (playerName.equals(player1.getName())) {
			playerToIncrementScore = player1;
		} else {
			playerToIncrementScore = player2;
		}
		return playerToIncrementScore;
	}

	/**
	 * add a point to the player
	 * 
	 * @param player
	 */
	protected abstract void incrementScore(Player player);

	/**
	 * initialize scores after set finish
	 */
	protected abstract void initScores();

	/**
	 * check if the game is finished
	 * 
	 * @return
	 */
	protected abstract boolean isGameFinished();

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Player getWinner() {
		return winner;
	}

}
