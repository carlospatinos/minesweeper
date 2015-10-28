package com.ericsson.minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

	private Position[][] content;
	public final int matrixNumberOfLines;
	public final int matrixNumberOfColumns;

	private static final int MAX_NUM_COLUMNS = 100;
	private static final int MAX_NUM_LINES = 100;
	private static final int MIN_NUM_COLUMNS = 1;
	private static final int MIN_NUM_LINES = 1;
	private static final int FIRST_COLUMN_IN_MATRIX = 0;
	private static final int FIRST_LINE_IN_MATRIX = 0;

	public Matrix(int numberOfLines, int numberOfColumns, String plainContent) {
		if (numberOfLines < MIN_NUM_COLUMNS || numberOfLines > MAX_NUM_COLUMNS) {
			throw new IllegalArgumentException("Line values must be between 0 and 100");
		}

		if (numberOfColumns < MIN_NUM_LINES || numberOfColumns > MAX_NUM_LINES) {
			throw new IllegalArgumentException("Column values must be between 0 and 100");
		}

		int expectedNumberOfCharacters = numberOfColumns * numberOfLines;
		if (plainContent == null || plainContent.length() == 0 || plainContent.length() != expectedNumberOfCharacters) {
			throw new IllegalArgumentException("Invalid values for the matrix");
		}

		this.matrixNumberOfLines = numberOfLines;
		this.matrixNumberOfColumns = numberOfColumns;
		this.content = buildContent(plainContent);
		assignNeighborgs();
	}

	private void assignNeighborgs() {
		for (Position[] lines : content) {
			for (Position position : lines) {
				List<Position> neighborgs = getNeighborgs(position, content);
				position.setNeighborgs(neighborgs);
			}
		}
	}

	/**
	 * Algorithm works on the assumption that the number of lines have always
	 * the same number of columns.
	 * 
	 * @param position
	 * @param content
	 * @return
	 */
	private List<Position> getNeighborgs(Position position, Position[][] content) {
		List<Position> neighborgs = new ArrayList<Position>();
		int positionLine = position.getLine();
		int positionColumn = position.getColumn();
		final int NUMBER_OF_COLUMNS = content.length;

		// combinations are
		// line -1, column [-1 .. +1]
		// line same, column [-1, +1]
		// line +1, column [-1 .. +1]

		for (int subtractionFromLine = -1; subtractionFromLine <= 1; subtractionFromLine++) {
			for (int subtractionFromColumn = -1; subtractionFromColumn <= 1; subtractionFromColumn++) {
				int positionNeighborgLine = positionLine + subtractionFromLine;
				int positionNeighborgColumn = positionColumn + subtractionFromColumn;

				if (positionNeighborgColumn == positionColumn && positionNeighborgLine == positionLine) {
					continue;
				}
				if (positionNeighborgLine < FIRST_LINE_IN_MATRIX || positionNeighborgColumn < FIRST_COLUMN_IN_MATRIX
						|| positionNeighborgColumn >= NUMBER_OF_COLUMNS // LIMIT
						|| (NUMBER_OF_COLUMNS > 1 && positionNeighborgLine >= content[0].length)
						|| positionNeighborgLine >= matrixNumberOfLines || positionNeighborgColumn >= matrixNumberOfColumns) {
					continue;
				}
				neighborgs.add(content[positionNeighborgLine][positionNeighborgColumn]);
			}
		}

		return neighborgs;
	}

	/**
	 * This algoritm will remove the enters from the string
	 * 
	 * @param matrixContent
	 * @return
	 */
	private Position[][] buildContent(String matrixContent) {
		matrixContent = matrixContent.replaceAll(System.lineSeparator(), "");
		Position[][] matrix = new Position[matrixNumberOfLines][matrixNumberOfColumns];
		String[] subMatrix = matrixContent.split("(?<=\\G.{" + matrixNumberOfColumns + "})");

		for (int lineIndex = 0; lineIndex < matrixNumberOfLines; lineIndex++) {
			for (int columnIndex = 0; columnIndex < matrixNumberOfColumns; columnIndex++) {
				matrix[lineIndex][columnIndex] = new Position(lineIndex, columnIndex,
						subMatrix[lineIndex].charAt(columnIndex));
			}
		}
		return matrix;
	}

	public String showMines() {
		StringBuffer result = new StringBuffer();
		for (Position[] lines : content) {
			for (Position columnInLine : lines) {
				result.append(columnInLine.showAnswer());
			}
			// result.append(System.lineSeparator());
		}
		return result.toString();
	}

}