package net.kata.tennis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
/**
 * Test of all cases of scoring for the user story 1
 * @author kei
 *
 */
@RunWith(Parameterized.class)
public class TennisGameTest {

	private static final String FIRST_PLAYER = "player 1";

	private static final String FIRST_PLAYER_WINS = FIRST_PLAYER + " win the game";

	private static final String SECOND_PLAYER = "player 2";

	private static final String SECOND_PLAYER_WINS = SECOND_PLAYER + " win the game";

	@Parameters
	public static List<Object[]> pointsWithScores() {
		return Arrays
				.asList(new Object[][] { 
					{ 0, 0, "0 - 0", ""}, 
					{ 0, 1, "0 - 15", ""}, 
					{ 0, 2, "0 - 30", ""},
					{ 0, 3, "0 - 40", ""},
					{ 0, 4, "0 - 0", SECOND_PLAYER_WINS }, 
					{ 1, 0, "15 - 0", ""},
					{ 1, 1, "15 - 15", ""},
					{ 1, 2, "15 - 30", ""}, 
					{ 1, 3, "15 - 40", ""},
					{ 1, 4, "0 - 0", SECOND_PLAYER_WINS },
					{ 2, 0, "30 - 0", ""},
					{ 2, 1, "30 - 15", ""},
					{ 2, 2, "30 - 30", ""},
					{ 2, 3, "30 - 40", ""},
					{ 2, 4, "0 - 0", SECOND_PLAYER_WINS }, 
					{ 3, 0, "40 - 0", ""},
					{ 3, 1, "40 - 15", ""},
					{ 3, 2, "40 - 30", ""}, 
					{ 3, 3, "40 - 40", ""},
					{ 3, 4, "0 - 0", SECOND_PLAYER_WINS },
					{ 4, 0, "0 - 0", FIRST_PLAYER_WINS },
					{ 4, 1, "0 - 0", FIRST_PLAYER_WINS },
					{ 4, 2, "0 - 0", FIRST_PLAYER_WINS },
					{ 4, 3, "0 - 0", FIRST_PLAYER_WINS } });
	}

	private int player1Score;
	private int player2Score;
	private String expected;
	private String expectedWinner;

	public TennisGameTest(int player1Score, int player2Score, String expectedScore, String expectedWinner) {
		this.player1Score = player1Score;
		this.player2Score = player2Score;
		this.expected = expectedScore;
		this.expectedWinner = expectedWinner;
	}

	@Test
	public final void testAllCases() {
		TennisGame tennisGame = new TennisGame(FIRST_PLAYER, SECOND_PLAYER);
		int min = Math.min(player1Score, player2Score);
		int max = Math.max(player1Score, player2Score);
		
		IntStream.range(0, min).forEach(i -> {
			tennisGame.winPoint(FIRST_PLAYER);
			tennisGame.winPoint(SECOND_PLAYER);
		});
		String playerName = (max == player1Score) ? FIRST_PLAYER : SECOND_PLAYER;
		IntStream.range(min, max).forEach(i -> {
			tennisGame.winPoint(playerName);
		});
		Assert.assertEquals(expected, tennisGame.getGameScore());
		Assert.assertEquals(expectedWinner, tennisGame.getWinner());
	}
}
