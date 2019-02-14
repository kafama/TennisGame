package net.kata.tennis;

public class TieBreakGame extends AbstractTennis {
	
	public TieBreakGame() {
		// TODO Auto-generated constructor stub
	}
	
	public TieBreakGame(String player1Name, String player2Name) {
		super(player1Name, player2Name);
	}

	@Override
	public String getGameScore() {
		return "Tie break score : " + player1.getScore() + " - " + player2.getScore();
	}

	@Override
	public String getWinnerFormatted() {
		return (winner == null) ? "" : winner.getName() + " win the match";
	}

	@Override
	protected void incrementScore(Player player) {
		player.setScore(player.getScore() + 1);
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
		return (max >= 7 && absDiff >= 2);
	}

}
