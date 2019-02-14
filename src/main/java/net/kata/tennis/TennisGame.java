package net.kata.tennis;

/**
 * Tennis game according to the  sprint 1 : User story 2
 * 
 * @author kei
 *
 */
public class TennisGame implements SportMatch {

	public static final String DEFAULT_PLAYER_1 = "Player 1";
	public static final String DEFAULT_PLAYER_2 = "Player 2";
	private static final String SEPERATOR = " - ";
	private static String[] translationScores = { "0", "15", "30", "40" };

	private Player player1;

	private Player player2;

	private Player winner;

	/**
	 * default constructor
	 */
	public TennisGame() {
		this.player1 = new Player(DEFAULT_PLAYER_1);
		this.player2 = new Player(DEFAULT_PLAYER_2);
	}

	/**
	 * constructor with parameters
	 * 
	 * @param player1Name first player's name
	 * @param player2Name seconde player's name
	 */
	public TennisGame(String player1Name, String player2Name) {
		this.player1 = new Player(player1Name);
		this.player2 = new Player(player2Name);
	}

	/**
	 * get the game score
	 */
	public String getGameScore() {
		return formatScore();
	}

	/**
	 * when player win a ball this method is called to increment his score
	 * 
	 * @param playerName
	 */
	public void winPoint(String playerName) {
		Player playerToIncrementScore = null;
		if (playerName.equals(player1.getName())) {
			playerToIncrementScore = player1;
		} else {
			playerToIncrementScore = player2;
		}
		playerToIncrementScore.incrementScore();
		if (isGameFinished()) {
			this.winner = playerToIncrementScore;
			initScores();
		}
	}

	/**
	 * initialize scores after set finish
	 */
	private void initScores() {
		player1.setScore(0);
		player2.setScore(0);
	}

	/**
	 * check if the game is finished
	 * 
	 * @return
	 */
	private boolean isGameFinished() {
		int max = Math.max(player1.getScore(), player2.getScore());
		int absDiff = Math.abs(player1.getScore() - player2.getScore());
		return max >= 4 && absDiff >= 2;
	}

	/**
	 * get the winner of the game
	 * 
	 * @return
	 */
	public String getWinner() {
		return (winner == null) ? "" : winner.getName() + " win the game";
	}

	/**
	 * format the current score (15 - 30 , 40 - 40, DEUCE - DEUCE)
	 * 
	 * @return string representing the current score
	 */
	private String formatScore() {
		String formattedScore = "";
		if(player1.getScore() < 4 && player2.getScore() < 4) {
			formattedScore = translationScores[player1.getScore()] + SEPERATOR + translationScores[player2.getScore()];
		}else {
			int difference = player1.getScore() - player2.getScore();
			switch (difference) {
			case 0:
				formattedScore = "DEUCE - DEUCE";
				break;
			case 1:
				formattedScore = "ADV - 40";
				break;
			case -1:
				formattedScore = "40 - ADV";
				break;
			default:
				break;
			}
		}
		return formattedScore;
	}

}
