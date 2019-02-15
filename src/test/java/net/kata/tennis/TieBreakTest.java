package net.kata.tennis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TieBreakTest extends TestConstantes {
	
	@Parameters
	public static List<Object[]> pointsWithScores() {
		return Arrays
				.asList(new Object[][] { 
					{ 0, 0, "0 - 0", ""}, 
					{ 0, 1, "0 - 1", ""},  
					{ 0, 2, "0 - 2", ""},  
					{ 0, 3, "0 - 3", ""},
					{ 0, 4, "0 - 4", ""},
					{ 0, 5, "0 - 5", ""},
					{ 0, 6, "0 - 6", ""},
					{ 0, 7, "0 - 0", SECOND_PLAYER_WIN }, 
					{ 1, 0, "1 - 0", ""},  
					{ 1, 1, "1 - 1", ""}, 
					{ 1, 2, "1 - 2", ""},  
					{ 1, 3, "1 - 3", ""},
					{ 1, 4, "1 - 4", ""},
					{ 1, 5, "1 - 5", ""},
					{ 1, 6, "1 - 6", ""},
					{ 1, 7, "0 - 0", SECOND_PLAYER_WIN },
					{ 2, 0, "2 - 0", ""},  
					{ 2, 1, "2 - 1", ""},  
					{ 2, 2, "2 - 2", ""},  
					{ 2, 3, "2 - 3", ""},
					{ 2, 4, "2 - 4", ""},
					{ 2, 5, "2 - 5", ""},
					{ 2, 6, "2 - 6", ""},
					{ 2, 7, "0 - 0", SECOND_PLAYER_WIN }, 
					{ 3, 0, "3 - 0", ""},  
					{ 3, 1, "3 - 1", ""}, 
					{ 3, 2, "3 - 2", ""},  
					{ 3, 3, "3 - 3", ""}, 
					{ 3, 4, "3 - 4", ""},
					{ 3, 5, "3 - 5", ""},
					{ 3, 6, "3 - 6", ""},
					{ 3, 7, "0 - 0", SECOND_PLAYER_WIN },
					{ 4, 0, "4 - 0", "" },
					{ 4, 1, "4 - 1", "" },
					{ 4, 2, "4 - 2", "" },
					{ 4, 3, "4 - 3", "" },
					{ 4, 4, "4 - 4", "" },
					{ 4, 5, "4 - 5", "" },
					{ 4, 6, "4 - 6", "" },
					{ 4, 7, "0 - 0", SECOND_PLAYER_WIN },
					{ 5, 0, "5 - 0", "" },
					{ 5, 1, "5 - 1", "" },
					{ 5, 2, "5 - 2", "" },
					{ 5, 3, "5 - 3", "" },
					{ 5, 4, "5 - 4", "" },
					{ 5, 5, "5 - 5", "" },
					{ 5, 6, "5 - 6", "" },
					{ 5, 7, "0 - 0", SECOND_PLAYER_WIN },					
					{ 6, 0, "6 - 0", "" },
					{ 6, 1, "6 - 1", "" },
					{ 6, 2, "6 - 2", "" },
					{ 6, 3, "6 - 3", ""},
					{ 6, 4, "6 - 4", "" },
					{ 6, 5, "6 - 5", "" },
					{ 6, 6, "6 - 6", "" },
					{ 6, 7, "6 - 7", "" },
					{ 6, 8, "0 - 0", SECOND_PLAYER_WIN },
					{ 7, 0, "0 - 0", FIRST_PLAYER_WIN },
					{ 7, 1, "0 - 0", FIRST_PLAYER_WIN },
					{ 7, 2, "0 - 0", FIRST_PLAYER_WIN },
					{ 7, 3, "0 - 0", FIRST_PLAYER_WIN },
					{ 7, 4, "0 - 0", FIRST_PLAYER_WIN },
					{ 7, 5, "0 - 0", FIRST_PLAYER_WIN },
					{ 7, 6, "7 - 6", "" },
					{ 7, 7, "7 - 7", "" },
					{ 7, 8, "7 - 8", "" },
					{ 7, 9, "0 - 0", SECOND_PLAYER_WIN },
					
				});
		}
	private int player1Score;
	private int player2Score;
	private String expected;
	private String expectedWinner;

	public TieBreakTest(int player1Score, int player2Score, String expected, String expectedWinner) {
		this.player1Score = player1Score;
		this.player2Score = player2Score;
		this.expected = expected;
		this.expectedWinner = expectedWinner;
	}
	@Test
	public final void testAllCases() {
		TieBreakGame tieBreak = new TieBreakGame(FIRST_PLAYER, SECOND_PLAYER);
		int min = Math.min(player1Score, player2Score);
		int max = Math.max(player1Score, player2Score);
		IntStream.range(0, min).forEach(i -> {
			tieBreak.winPoint(FIRST_PLAYER);
			tieBreak.winPoint(SECOND_PLAYER);
		});
		String playerName = (max == player1Score) ? FIRST_PLAYER : SECOND_PLAYER;
		IntStream.range(min, max).forEach(i -> {
			tieBreak.winPoint(playerName);
		});
		Assert.assertEquals(tieBreak.getGameScore(), "Tie break score : " + expected);
		Assert.assertEquals(tieBreak.getWinnerFormatted(), expectedWinner);
	}
}
