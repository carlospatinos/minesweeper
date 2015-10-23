package com.ericsson.minesweeper;


public class Game {
	private int lines;
	private int columns;
	private Matrix inputMatrix;

	public Game(final int lines, final int columns) {
		
		this.lines = lines;
		this.columns = columns;
	}

	public int getLines() {
		return lines;
	}

	public int getColumns() {
		return columns;
	}

	public void setValuesForMatrix(String matrixContent) {
		this.inputMatrix = new Matrix(lines, columns, matrixContent);
	}
}
