package net.kata.tennis;
/**
 * Sport match factory
 * @author kei
 *
 */
public class TennisMatchFactory {

	public static final String TENNIS_SET = "tennisSet";
	public static final String TIE_BREAK = "tieBreak";
	public static final String TENNIS_GAME = "tennisGame";


	public static AbstractTennis getTennisMatchInstance(String type) {
		switch (type) {
		case TENNIS_GAME:
			return new TennisGame();
		case TIE_BREAK:
			return new TieBreakGame();
		case TENNIS_SET:
			return new TennisSet();			
		default:
			break;
		}
		return null;
	}
	
	
	public static AbstractTennis getTennisMatchInstance(String type, String player1, String player2) {
		switch (type) {
		case TENNIS_GAME:
			return new TennisGame(player1, player2);
		case TIE_BREAK:
			return new TieBreakGame(player1, player2);
		case TENNIS_SET:
			return new TennisSet(player1, player2);			
		default:
			break;
		}
		return null;
	}
	
}
