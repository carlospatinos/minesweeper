package com.kinettikmx.minesweeper;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.kinettikmx.minesweeper.Position;


@RunWith(Parameterized.class)
public class PositionTest {
	@Parameter(value = 0)
	public char value;
	@Parameter(value = 1)
	public List<Position> neighborgs;
	@Parameter(value = 2)
	public int expectedNumberOfMines;
	@Parameter(value = 3)
	public Class<Throwable> exceptionClass;
	@Parameter(value = 4)
	public String exceptionMessage;
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Parameters(name = "{index}: Position value ({0}) with list of neigborgs {1} => Expected mines around are {2} and expected exception is {3} with message: \"{4}\"")
	public static Iterable<Object[]> data() {
		return Arrays.asList(
			new Object[][] { 
				{ 'q', Arrays.asList(new Position(1,1,'.')), 0, IllegalArgumentException.class, "Invalid character found"},
				{ ',', Arrays.asList(new Position(1,1,'.')), 0, IllegalArgumentException.class, "Invalid character found"},
				{ '1', Arrays.asList(new Position(1,1,'.')), 0, IllegalArgumentException.class, "Invalid character found"},
				{ '@', Arrays.asList(new Position(1,1,'.')), 0, IllegalArgumentException.class, "Invalid character found"},
				{ '.', null, 0, IllegalArgumentException.class, "Neigborgs must be provided"},
				{ '.', Arrays.asList(new Position(1,1,'.')), 0, null, ""},
				{ '.', Arrays.asList(new Position(1,1,'.'),new Position(1,2,'*')), 1, null, ""},
				{ '.', Arrays.asList(new Position(1,1,'*'),new Position(1,2,'*')), 2, null, ""},
				{ '.', Arrays.asList(new Position(1,1,'*'),new Position(1,2,'.')), 1, null, ""},
				{ '.', Arrays.asList(new Position(1,1,'*'),new Position(1,2,'*'), new Position(1,3,'.')), 2, null, ""},
				{ '.', Arrays.asList(new Position(1,1,'.'),new Position(1,2,'*'), new Position(1,3,'*')), 2, null, ""},
				{ '.', Arrays.asList(new Position(1,1,'*'),new Position(1,2,'*'), new Position(1,3,'*')), 3, null, ""},
				{ '*', Arrays.asList(new Position(1,1,'*'),new Position(1,2,'*'), new Position(1,3,'*')), 0, null, ""},
				
			});
	}
	
	@Test
    public void testInput() {
        if (exceptionClass != null) {
            expected.expect(exceptionClass);
            expected.expectMessage(exceptionMessage);
        }

        Position position = new Position(0,0,value);
        position.setNeighborgs(neighborgs);
		Assert.assertEquals(value, position.getValue());
		Assert.assertEquals(expectedNumberOfMines, position.getMinesAroundMe());
		Assert.assertEquals(expectedNumberOfMines, position.getMinesAroundMe());
    }
	
}
