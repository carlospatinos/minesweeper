package com.kinettikmx.minesweeper;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.kinettikmx.minesweeper.Matrix;

@RunWith(Parameterized.class)
public class MatrixTest {
	@Parameter(value = 0)
	public int numberOfLines;
	@Parameter(value = 1)
	public int numberOfColumns;
	@Parameter(value = 2)
	public String inputMatrixContent;
	@Parameter(value = 3)
	public String expectedMatrixContent;
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
				{ 1, -1, ".", "", IllegalArgumentException.class, "Column values must be between 0 and 100" },
				{-1, 1, ".", "", IllegalArgumentException.class, "Line values must be between 0 and 100" }, 
				{1, 101, ".", "", IllegalArgumentException.class, "Column values must be between 0 and 100" },
				{101, 1, ".", "", IllegalArgumentException.class, "Line values must be between 0 and 100" },
				{1, 1, "", "", IllegalArgumentException.class, "Invalid values for the matrix" },
				{1, 1, null, "", IllegalArgumentException.class, "Invalid values for the matrix" },
				{2, 2, ".", "", IllegalArgumentException.class, "Invalid values for the matrix" },
				{1, 1, "..", "", IllegalArgumentException.class, "Invalid values for the matrix" },
				{2, 2, "...z", "", IllegalArgumentException.class, "Invalid character found" },
				{ 1, 1, ".", "0", null, "" },
				{ 2, 2, "....", "0000", null, "" },
				{ 3, 3, ".........", "000000000", null, "" },
				{ 3, 2, "......", "000000", null, "" },
				{ 2, 3, "......", "000000", null, "" },
				{ 2, 2, "*...", "*111", null, "" },
				{ 2, 2, ".*..", "1*11", null, "" },
				{ 2, 2, "..*.", "11*1", null, "" },
				{ 2, 2, "...*", "111*", null, "" },
				{ 3, 3, "*........", "*10110000", null, "" },
				{ 3, 3, ".*.......", "1*1111000", null, "" },
				{ 4, 4, "*........*......", "*10022101*101110", null, "" },
				{ 3, 5, "**.........*...", "**100332001*100", null, "" },
				{ 2, 2, "****", "****", null, "" },
			});
	}
	
	@Test
    public void testInput() {
        if (exceptionClass != null) {
            expected.expect(exceptionClass);
            expected.expectMessage(exceptionMessage);
        }

        Matrix matrix = new Matrix(numberOfLines, numberOfColumns, inputMatrixContent);
		Assert.assertEquals(numberOfLines, matrix.matrixNumberOfLines);
		Assert.assertEquals(numberOfColumns, matrix.matrixNumberOfColumns);
		Assert.assertEquals(expectedMatrixContent, matrix.showMines());
    }
}