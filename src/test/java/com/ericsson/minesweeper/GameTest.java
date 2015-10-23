package com.ericsson.minesweeper;


import java.util.Arrays;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class GameTest {
	
	@Parameter(value = 0)
	public int numberOfLines;
	@Parameter(value = 1)
	public int numberOfColumns;
	@Parameter(value = 2)
	public String inputMatrixContent;
	@Parameter(value = 3)
	public String expetedResponse;
	@Parameter(value = 4)
	public Class<Throwable> exceptionClass;
	@Parameter(value = 5)
	public String exceptionMessage;
	
	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Parameters(name = "{index}: Matrix Size ({0}, {1}) with Content \"{2}\" => Expected matrix outout is {3} and expected exception : {4} with message: \"{5}\"")
	public static Iterable<Object[]> data() {
		return Arrays.asList(
			new Object[][] { 
				{ 0, 0, "", "Good Bye", null, ""},
			});
	}
	
	@Test
    public void testInput() {
        if (exceptionClass != null) {
            expected.expect(exceptionClass);
            expected.expectMessage(exceptionMessage);
        }

        Game game = new Game();
        String message = game.newMineSweeper(numberOfLines, numberOfColumns, inputMatrixContent);
		Assert.assertEquals(expetedResponse, message);
		
    }

}
