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
public class TennisGameTest extends TestConstantes {

	@Parameters
	public static List<Object[]> pointsWithScores() {
		return Arrays
				.asList(new Object[][] { 
					{ 0, 0, "0 - 0", ""}, 
					{ 0, 1, "0 - 15", ""},  
					{ 0, 2, "0 - 30", ""},  
					{ 0, 3, "0 - 40", ""}, 
					{ 0, 4, "0 - 0", SCOND_PLAYER_WIN_GAME }, 
					{ 1, 0, "15 - 0", ""},  
					{ 1, 1, "15 - 15", ""}, 
					{ 1, 2, "15 - 30", ""},  
					{ 1, 3, "15 - 40", ""},  
					{ 1, 4, "0 - 0", SCOND_PLAYER_WIN_GAME },
					{ 2, 0, "30 - 0", ""},  
					{ 2, 1, "30 - 15", ""},  
					{ 2, 2, "30 - 30", ""},  
					{ 2, 3, "30 - 40", ""}, 
					{ 2, 4, "0 - 0", SCOND_PLAYER_WIN_GAME }, 
					{ 3, 0, "40 - 0", ""},  
					{ 3, 1, "40 - 15", ""}, 
					{ 3, 2, "40 - 30", ""},  
					{ 3, 3, "40 - 40", ""}, 
					{ 3, 4, "40 - ADV", ""}, 
					{ 3, 5, "0 - 0", SCOND_PLAYER_WIN_GAME },
					{ 4, 0, "0 - 0", FIRST_PLAYER_WIN_GAME },
					{ 4, 1, "0 - 0", FIRST_PLAYER_WIN_GAME },
					{ 4, 2, "0 - 0", FIRST_PLAYER_WIN_GAME },
					{ 4, 3, "ADV - 40", ""}, 
					{ 4, 4, "DEUCE - DEUCE", ""}, 
					{ 4, 5, "40 - ADV", ""}, 
					{ 4, 6, "0 - 0", SCOND_PLAYER_WIN_GAME },
					{ 5, 3, "0 - 0", FIRST_PLAYER_WIN_GAME },
					{ 5, 4, "ADV - 40", ""}, 
					{ 5, 5, "DEUCE - DEUCE" , ""}
				});
	}

	private int player1Score;
	private int player2Score;
	private String expected;
	private String expectedWinner;

	public TennisGameTest(int player1Score, int player2Score, String expected, String expectedWinner) {
		this.player1Score = player1Score;
		this.player2Score = player2Score;
		this.expected = expected;
		this.expectedWinner = expectedWinner;
	}

	@Test
	public final void testAllCases() {
		TennisGame tg = new TennisGame(FIRST_PLAYER, SECOND_PLAYER);
		int min = Math.min(player1Score, player2Score);
		int max = Math.max(player1Score, player2Score);
		IntStream.range(0, min).forEach(i -> {
			tg.winPoint(FIRST_PLAYER);
			tg.winPoint(SECOND_PLAYER);
		});
		String playerName = (max == player1Score) ? FIRST_PLAYER : SECOND_PLAYER;
		IntStream.range(min, max).forEach(i -> {
			tg.winPoint(playerName);
		});
		Assert.assertEquals(tg.getGameScore(), "Game score : " + expected);
		Assert.assertEquals(tg.getWinnerFormatted(), expectedWinner);
	}
}
