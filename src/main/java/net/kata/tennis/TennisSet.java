package net.kata.tennis;

import static net.kata.tennis.TennisMatchFactory.TENNIS_GAME;
import static net.kata.tennis.TennisMatchFactory.TIE_BREAK;

/**
 * Tennis set according to Sprint 2 : user story 1
 * 
 * @author kei
 *
 */
public class TennisSet extends AbstractTennis {

	private AbstractTennis tennisGame;
	private AbstractTennis tieBreakGame;

	private boolean isTieBreak;

	public TennisSet() {
		super();
		this.tennisGame = TennisMatchFactory.getTennisMatchInstance(TENNIS_GAME);
		this.tieBreakGame = TennisMatchFactory.getTennisMatchInstance(TIE_BREAK);
	}

	public TennisSet(String player1Name, String player2Name) {
		super(player1Name, player2Name);
		this.tennisGame = TennisMatchFactory.getTennisMatchInstance(TENNIS_GAME, player1Name, player2Name);
		this.tieBreakGame = TennisMatchFactory.getTennisMatchInstance(TIE_BREAK, player1Name, player2Name);
	}

	@Override
	public String getGameScore() {
		StringBuilder sb = new StringBuilder();
		sb.append(tennisGame.getGameScore()).append(", Set score : ").append(player1.getScore()).append(" - ")
				.append(player2.getScore());
		if (isTieBreak) {
			sb.append(", " + tieBreakGame.getGameScore());
		}
		return sb.toString();
	}

	@Override
	public String getWinnerFormatted() {
		if (winner != null) {
			if (isTieBreak) {
				return tieBreakGame.getWinnerFormatted();
			} else {
				return winner.getName() + " win the set";
			}
		}
		return tennisGame.getWinnerFormatted();
	}

	@Override
	protected void incrementScore(Player player) {
		SportMatch subGame = null;
		checkTieBreakActivation();
		if (isTieBreak) {
			subGame = tieBreakGame;
		} else {
			subGame = tennisGame;
		}
		subGame.winPoint(player.getName());
		if (subGame.getWinner() != null) {
			player.setScore(player.getScore() + 1);
		}

	}

	public void checkTieBreakActivation() {
		if (!isTieBreak && player1.getScore() == 6 && player2.getScore() == 6) {
			isTieBreak = true;
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
		return (max == 6 && absDiff >= 2) || max == 7 || (isTieBreak && tieBreakGame.isGameFinished());
	}

}
