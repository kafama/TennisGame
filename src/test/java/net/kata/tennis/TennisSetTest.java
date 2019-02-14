package net.kata.tennis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * unit test for TennisSet (sprint 2 : user story 1)
 * @author kei
 *
 */
@RunWith(Parameterized.class)
public class TennisSetTest {

	private static final String GAME_SCORE_0_0 = "Game score : 0 - 0";

	private static final String FIRST_PLAYER = "player 1";

	private static final String FIRST_PLAYER_WIN = FIRST_PLAYER + " win the set";
	
	private static final String SECOND_PLAYER = "player 2";

	private static final String SECOND_PLAYER_WIN = SECOND_PLAYER + " win the set";
	
	@Mock
	TennisGame tennisGame;
	
	@InjectMocks
	TennisSet tennisSet = new TennisSet(FIRST_PLAYER, SECOND_PLAYER);
	
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
					{ 0, 6, "0 - 0", SECOND_PLAYER_WIN }, 
					{ 1, 0, "1 - 0", ""},  
					{ 1, 1, "1 - 1", ""}, 
					{ 1, 2, "1 - 2", ""},  
					{ 1, 3, "1 - 3", ""},
					{ 1, 4, "1 - 4", ""},
					{ 1, 5, "1 - 5", ""},  
					{ 1, 6, "0 - 0", SECOND_PLAYER_WIN },
					{ 2, 0, "2 - 0", ""},  
					{ 2, 1, "2 - 1", ""},  
					{ 2, 2, "2 - 2", ""},  
					{ 2, 3, "2 - 3", ""},
					{ 2, 4, "2 - 4", ""},
					{ 2, 5, "2 - 5", ""},
					{ 2, 6, "0 - 0", SECOND_PLAYER_WIN }, 
					{ 3, 0, "3 - 0", ""},  
					{ 3, 1, "3 - 1", ""}, 
					{ 3, 2, "3 - 2", ""},  
					{ 3, 3, "3 - 3", ""}, 
					{ 3, 4, "3 - 4", ""},
					{ 3, 5, "3 - 5", ""},
					{ 3, 6, "0 - 0", SECOND_PLAYER_WIN },
					{ 4, 0, "4 - 0", "" },
					{ 4, 1, "4 - 1", "" },
					{ 4, 2, "4 - 2", "" },
					{ 4, 3, "4 - 3", "" },
					{ 4, 4, "4 - 4", "" },
					{ 4, 5, "4 - 5", "" },
					{ 4, 6, "0 - 0", SECOND_PLAYER_WIN },
					{ 5, 0, "5 - 0", "" },
					{ 5, 1, "5 - 1", "" },
					{ 5, 2, "5 - 2", "" },
					{ 5, 3, "5 - 3", "" },
					{ 5, 4, "5 - 4", "" },
					{ 5, 5, "5 - 5", "" },
					{ 5, 6, "5 - 6", "" },
					{ 5, 7, "0 - 0", SECOND_PLAYER_WIN },					
					{ 6, 0, "0 - 0", FIRST_PLAYER_WIN },
					{ 6, 1, "0 - 0", FIRST_PLAYER_WIN },
					{ 6, 2, "0 - 0", FIRST_PLAYER_WIN },
					{ 6, 3, "0 - 0", FIRST_PLAYER_WIN },
					{ 6, 4, "0 - 0", FIRST_PLAYER_WIN },
					{ 6, 5, "6 - 5", "" },
					{ 6, 6, "6 - 6", "" },
					{ 6, 7, "0 - 0", SECOND_PLAYER_WIN },
					{ 7, 6, "0 - 0", FIRST_PLAYER_WIN }
				});
		}
	
	private int player1Score;
	private int player2Score;
	private String expected;
	private String expectedWinner;
	
	public TennisSetTest(int player1Score, int player2Score, String expected, String expectedWinner) {
		this.player1Score = player1Score;
		this.player2Score = player2Score;
		this.expected = expected;
		this.expectedWinner = expectedWinner;
	}
	
	@Before 
	public void beforeEach() {
        MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public final void testAllCases() {
		int min = Math.min(player1Score, player2Score);
		int max = Math.max(player1Score, player2Score);
		Mockito.when(tennisGame.getGameScore()).thenReturn(GAME_SCORE_0_0);
		IntStream.range(0, min).forEach(i -> {
			Mockito.when(tennisGame.getWinner()).thenReturn(tennisSet.getPlayer1());
			tennisSet.winPoint(FIRST_PLAYER);
			Mockito.when(tennisGame.getWinner()).thenReturn(tennisSet.getPlayer2());
			tennisSet.winPoint(SECOND_PLAYER);
		});
		String playerName = (max == player1Score) ? FIRST_PLAYER : SECOND_PLAYER;
		Player player = (max == player1Score) ? tennisSet.getPlayer1() : tennisSet.getPlayer2();
		IntStream.range(min, max).forEach(i -> {
			Mockito.when(tennisGame.getWinner()).thenReturn(player);
			tennisSet.winPoint(playerName);
		});
		Assert.assertEquals(GAME_SCORE_0_0 + ", Set score : " + expected, tennisSet.getGameScore());
		Assert.assertEquals(expectedWinner, tennisSet.getWinnerFormatted());
	}
}
