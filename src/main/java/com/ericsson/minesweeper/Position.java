package com.ericsson.minesweeper;

import java.util.List;

public class Position {
	private static final int UKNOWN_NUMBER_OF_MINES = -1;
	private static final char MINE = '*';
	private char value;
	private int line;
	private int column;
	private int minesAroundMe = UKNOWN_NUMBER_OF_MINES;
	private List<Position> neighborgs;

	public Position(int line, int column, char value) {
		this.value = value;
		this.line = line;
		this.column = column;
		validate();
	}

	private void validate() {
		if (value != '.' && value != '*') {
			throw new IllegalArgumentException("Invalid character found");
		}
	}
	
	public boolean isMine() {
		return value == MINE;
	}

	public void setNeighborgs(List<Position> neighborgs) {
		this.neighborgs = neighborgs;
	}

	public char getValue() {
		return value;
	}

	public int getMinesAroundMe() {
		if (minesAroundMe == UKNOWN_NUMBER_OF_MINES) {
			minesAroundMe = calculateMinesAroundMe();
		}
		return minesAroundMe;
	}

	private int calculateMinesAroundMe() {
		if (this.isMine()) {
			return 0;
		}
		if (neighborgs == null) {
			throw new IllegalArgumentException("Neigborgs must be provided");
		}
		int minesAroundMe = 0;
		for (Position neighborg : neighborgs) {
			if (neighborg.isMine()) {
				minesAroundMe++;
			}
		}
		return minesAroundMe;
	}

	@Override
	public String toString() {
		return "(" + this.line + ","+ this.column + ") =>" + String.valueOf(value);
	}

	public String showAnswer() {
		if (this.isMine()) {
			return String.valueOf(this.value);
		} else {
			return String.valueOf(getMinesAroundMe());
		}
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}
}
