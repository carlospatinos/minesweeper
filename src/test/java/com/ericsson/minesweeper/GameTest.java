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
import org.mockito.stubbing.OngoingStubbing;

@RunWith(Parameterized.class)
public class GameTest {
	
	@Parameter(value = 0)
	public int[] numberOfLines;
	@Parameter(value = 1)
	public int[] numberOfColumns;
	@Parameter(value = 2)
	public String inputMatrixContent;
	@Parameter(value = 3)
	public List<String> expetedResponses;
	@Parameter(value = 4)
	public int[] expetedTimesOfResponses;
	
	private static final String STARTED = "Mine Sweeper started!";
	private static final String INSTRUCTIONS = "To exite please provide [0 0] for lines and columns.";
	private static final String INPUT_MSG = "Provide [lines columns] separated by space";
	private static final String MATRIX_CONTENT_MSG = "Enter the mine sweeper content:";
	private static final String END_MSG = "End";
	
	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Parameters(name = "{index}: Matrix Size ({0}, {1}) with Content \"{2}\" => Expected output message is {3} ({4} time(s))")
	public static Iterable<Object[]> data() {
		return Arrays.asList(
			new Object[][] { 
				{ new int[]{0}, new int[]{0}, "", Arrays.asList(STARTED, INSTRUCTIONS, INPUT_MSG, MATRIX_CONTENT_MSG, END_MSG), new int[]{1, 1, 1, 0, 1} },
				{ new int[]{1, 0}, new int[]{1, 0}, ".", Arrays.asList(STARTED, INSTRUCTIONS, INPUT_MSG, MATRIX_CONTENT_MSG, END_MSG), new int[]{1, 1, 2, 1, 1}},
				{ new int[]{1, 1, 0}, new int[]{1, 1, 0}, ".", Arrays.asList(STARTED, INSTRUCTIONS, INPUT_MSG, MATRIX_CONTENT_MSG, END_MSG), new int[]{1, 1, 3, 2, 1}},
				{ new int[]{1, 1, 1, 0}, new int[]{1, 1, 1, 0}, ".", Arrays.asList(STARTED, INSTRUCTIONS, INPUT_MSG, MATRIX_CONTENT_MSG, END_MSG), new int[]{1, 1, 4, 3, 1}},
			});
	}
	
	@Test
    public void testInput() {

        Game game = new Game();
		UserInterface ui = Mockito.mock(UserInterface.class);
		game.setUi(ui );
		OngoingStubbing<Integer> stubbing = when(ui.nextInt());
		for (int numberOfMatrix = 0; numberOfMatrix < numberOfLines.length; numberOfMatrix++) {
			stubbing = stubbing.thenReturn(numberOfLines[numberOfMatrix]);
			stubbing = stubbing.thenReturn(numberOfColumns[numberOfMatrix]);
		}
		when(ui.next()).thenReturn(inputMatrixContent);
		
        game.start();
        
        verify(ui, times(expetedTimesOfResponses[0])).show(expetedResponses.get(0));
        verify(ui, times(expetedTimesOfResponses[1])).show(expetedResponses.get(1));
        verify(ui, times(expetedTimesOfResponses[2])).show(expetedResponses.get(2));
        verify(ui, times(expetedTimesOfResponses[3])).show(expetedResponses.get(3));
        verify(ui, times(expetedTimesOfResponses[4])).show(expetedResponses.get(4));
		
        
    }

}
