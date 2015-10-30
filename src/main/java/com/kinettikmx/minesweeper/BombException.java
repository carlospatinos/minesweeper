package com.kinettikmx.minesweeper;

public class BombException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BombException(String s) {
        super(s);
    }
}