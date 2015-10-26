package com.ericsson.minesweeper;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

@RunWith(Parameterized.class)
public class GameTest {
	
	@Parameter(value = 0)
	public int numberOfLines;
	@Parameter(value = 1)
	public int numberOfColumns;
	@Parameter(value = 2)
	public String inputMatrixContent;
	@Parameter(value = 3)
	public List<String> expetedResponses;
	@Parameter(value = 4)
	public int[] expetedTimesOfResponses;
	
	private static final String STARTED = "Mine Sweeper started!";
	private static final String INSTRUCTIONS = "To exite please provide 0,0 for lines and columns.";
	private static final String LINES_MSG = "Number of lines:";
	private static final String COLUMN_MSG = "Number of columns:";
	private static final String MATRIX_CONTENT_MSG = "Enter the mine sweeper content:";
	private static final String END_MSG = "End";
	
	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Parameters(name = "{index}: Matrix Size ({0}, {1}) with Content \"{2}\" => Expected matrix outout is {3}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(
			new Object[][] { 
				{ 0, 0, "", Arrays.asList(STARTED, INSTRUCTIONS, LINES_MSG, COLUMN_MSG, MATRIX_CONTENT_MSG, END_MSG), new int[]{1, 1, 1, 1, 0, 1} },
				{ 1, 1, ".", Arrays.asList(STARTED, INSTRUCTIONS, LINES_MSG, COLUMN_MSG, MATRIX_CONTENT_MSG, END_MSG), new int[]{1, 1, 2, 2, 1, 0}},
			});
	}
	
	@Test
    public void testInput() {

        Game game = new Game();
		UserInterface ui = Mockito.mock(UserInterface.class);
		game.setUi(ui );
		when(ui.nextInt()).thenReturn(numberOfLines).thenReturn(numberOfColumns).thenReturn(0).thenReturn(0);
		when(ui.next()).thenReturn(inputMatrixContent);
		
        game.start();
        
        verify(ui, times(expetedTimesOfResponses[0])).show(expetedResponses.get(0));
        verify(ui, times(expetedTimesOfResponses[1])).show(expetedResponses.get(1));
        verify(ui, times(expetedTimesOfResponses[2])).show(expetedResponses.get(2));
        verify(ui, times(expetedTimesOfResponses[3])).show(expetedResponses.get(3));
        verify(ui, times(expetedTimesOfResponses[4])).show(expetedResponses.get(4));
		
        
    }

}
