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
 * @author lenovo
 *
 */
@RunWith(Parameterized.class)
public class TennisSetTest extends TestConstantes {
	
	@Mock
	TennisGame tennisGame;
	
	@InjectMocks
	TennisSet tennisSet = new TennisSet(FIRST_PLAYER, SECOND_PLAYER);
	
	@Parameters
	public static List<Object[]> pointsWithScores() {
		return Arrays
				.asList(new Object[][] { 
					{ 0, 0, "0 - 0", XXX_WIN_THE_GAME}, 
					{ 0, 1, "0 - 1", XXX_WIN_THE_GAME},  
					{ 0, 2, "0 - 2", XXX_WIN_THE_GAME},  
					{ 0, 3, "0 - 3", XXX_WIN_THE_GAME},
					{ 0, 4, "0 - 4", XXX_WIN_THE_GAME},
					{ 0, 5, "0 - 5", XXX_WIN_THE_GAME},
					{ 0, 6, "0 - 0", SECOND_PLAYER_WIN_SET }, 
					{ 1, 0, "1 - 0", XXX_WIN_THE_GAME},  
					{ 1, 1, "1 - 1", XXX_WIN_THE_GAME}, 
					{ 1, 2, "1 - 2", XXX_WIN_THE_GAME},  
					{ 1, 3, "1 - 3", XXX_WIN_THE_GAME},
					{ 1, 4, "1 - 4", XXX_WIN_THE_GAME},
					{ 1, 5, "1 - 5", XXX_WIN_THE_GAME},  
					{ 1, 6, "0 - 0", SECOND_PLAYER_WIN_SET },
					{ 2, 0, "2 - 0", XXX_WIN_THE_GAME},  
					{ 2, 1, "2 - 1", XXX_WIN_THE_GAME},  
					{ 2, 2, "2 - 2", XXX_WIN_THE_GAME},  
					{ 2, 3, "2 - 3", XXX_WIN_THE_GAME},
					{ 2, 4, "2 - 4", XXX_WIN_THE_GAME},
					{ 2, 5, "2 - 5", XXX_WIN_THE_GAME},
					{ 2, 6, "0 - 0", SECOND_PLAYER_WIN_SET }, 
					{ 3, 0, "3 - 0", XXX_WIN_THE_GAME},  
					{ 3, 1, "3 - 1", XXX_WIN_THE_GAME}, 
					{ 3, 2, "3 - 2", XXX_WIN_THE_GAME},  
					{ 3, 3, "3 - 3", XXX_WIN_THE_GAME}, 
					{ 3, 4, "3 - 4", XXX_WIN_THE_GAME},
					{ 3, 5, "3 - 5", XXX_WIN_THE_GAME},
					{ 3, 6, "0 - 0", SECOND_PLAYER_WIN_SET },
					{ 4, 0, "4 - 0", XXX_WIN_THE_GAME },
					{ 4, 1, "4 - 1", XXX_WIN_THE_GAME },
					{ 4, 2, "4 - 2", XXX_WIN_THE_GAME },
					{ 4, 3, "4 - 3", XXX_WIN_THE_GAME },
					{ 4, 4, "4 - 4", XXX_WIN_THE_GAME },
					{ 4, 5, "4 - 5", XXX_WIN_THE_GAME },
					{ 4, 6, "0 - 0", SECOND_PLAYER_WIN_SET },
					{ 5, 0, "5 - 0", XXX_WIN_THE_GAME },
					{ 5, 1, "5 - 1", XXX_WIN_THE_GAME },
					{ 5, 2, "5 - 2", XXX_WIN_THE_GAME },
					{ 5, 3, "5 - 3", XXX_WIN_THE_GAME },
					{ 5, 4, "5 - 4", XXX_WIN_THE_GAME },
					{ 5, 5, "5 - 5", XXX_WIN_THE_GAME },
					{ 5, 6, "5 - 6", XXX_WIN_THE_GAME },
					{ 5, 7, "0 - 0", SECOND_PLAYER_WIN_SET },					
					{ 6, 0, "0 - 0", FIRST_PLAYER_WIN_SET },
					{ 6, 1, "0 - 0", FIRST_PLAYER_WIN_SET },
					{ 6, 2, "0 - 0", FIRST_PLAYER_WIN_SET },
					{ 6, 3, "0 - 0", FIRST_PLAYER_WIN_SET },
					{ 6, 4, "0 - 0", FIRST_PLAYER_WIN_SET },
					{ 6, 5, "6 - 5", XXX_WIN_THE_GAME },
					{ 6, 6, "6 - 6", XXX_WIN_THE_GAME },
					{ 6, 7, "6 - 6, Tie break score : 0 - 1", "" },
					{ 7, 6, "6 - 6, Tie break score : 1 - 0", "" },
					{ 13, 11, "0 - 0, Tie break score : 0 - 0", FIRST_PLAYER + " win the match" },
					{ 12, 14, "0 - 0, Tie break score : 0 - 0", SECOND_PLAYER + " win the match" },
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
		//we suppose that a game is finished thus the game score is 0 - 0
		Mockito.when(tennisGame.getGameScore()).thenReturn(GAME_SCORE_0_0);
		if("".equals(expectedWinner) || XXX_WIN_THE_GAME.equals(expectedWinner)) {
			Mockito.when(tennisGame.getWinnerFormatted()).thenReturn(expectedWinner);
		}
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
