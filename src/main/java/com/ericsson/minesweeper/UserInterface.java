package com.ericsson.minesweeper;

public interface UserInterface {

	void show(String string);
	
	void showError(String string);

	int nextInt();

	String next();

	void clear();

}
