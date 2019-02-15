package net.kata.tennis;

/**
 * Tennis game according to the sprint 1 : User story 2
 * 
 * @author kei
 *
 */
public class TennisGame extends AbstractTennis {

	private static final String SEPERATOR = " - ";
	private static String[] translationScores = { "0", "15", "30", "40" };

	public TennisGame() {
		super();
	}

	/**
	 * constructor with parameters
	 * 
	 * @param player1Name first player's name
	 * @param player2Name seconde player's name
	 */
	public TennisGame(String player1Name, String player2Name) {
		super(player1Name, player2Name);
	}

	/**
	 * get the game score
	 */
	public String getGameScore() {
		String formattedScore = "";
		if (player1.getScore() < 4 && player2.getScore() < 4) {
			formattedScore = translationScores[player1.getScore()] + SEPERATOR + translationScores[player2.getScore()];
		} else {
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
		return "Game score : " + formattedScore;
	}


	/**
	 * initialize scores after set finish
	 */
	protected void initScores() {
		player1.setScore(0);
		player2.setScore(0);
	}

	/**
	 * check if the game is finished
	 * 
	 * @return
	 */
	protected boolean isGameFinished() {
		int max = Math.max(player1.getScore(), player2.getScore());
		int absDiff = Math.abs(player1.getScore() - player2.getScore());
		return max >= 4 && absDiff >= 2;
	}

	/**
	 * get the winner of the game
	 * 
	 * @return
	 */
	public String getWinnerFormatted() {
		return (winner == null) ? "" : winner.getName() + " win the game";
	}

	@Override
	protected void incrementScore(Player player) {
		player.setScore(player.getScore() + 1);
	}

}
