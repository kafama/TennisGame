package net.kata.tennis;

/**
 * Tennis set according to Sprint 2 : user story 1
 * 
 * @author kei
 *
 */
public class TennisSet extends AbstractTennis {

	private TennisGame tennisGame;

	public TennisSet() {
		super();
		this.tennisGame = new TennisGame();
	}

	public TennisSet(String player1Name, String player2Name) {
		super(player1Name, player2Name);
		this.tennisGame = new TennisGame(player1Name, player2Name);
	}

	@Override
	public String getGameScore() {
		StringBuilder sb = new StringBuilder();
		sb.append(tennisGame.getGameScore()).append(", Set score : ").append(player1.getScore())
				.append(" - ").append(player2.getScore());
		return sb.toString();
	}

	@Override
	public String getWinnerFormatted() {
		return (winner == null) ? "" : winner.getName() + " win the set";
	}

	@Override
	protected void incrementScore(Player player) {
		tennisGame.winPoint(player.getName());
		if (tennisGame.getWinner() != null) {
			if (tennisGame.getWinner().getName().equals(player.getName())) {
				player.setScore(player.getScore() + 1);
			} else {
				throw new RuntimeException("Error, the winner must be " + player.getName());
			}
		}

	}

	@Override
	protected void initScores() {
		player1.setScore(0);
		player2.setScore(0);
	}

	@Override
	protected boolean isGameFinished() {
		int max = Math.max(player1.getScore(), player2.getScore());
		int absDiff = Math.abs(player1.getScore() - player2.getScore());
		return (max == 6 && absDiff >= 2) || max == 7;
	}

}
