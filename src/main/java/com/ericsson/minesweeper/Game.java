package com.ericsson.minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private UserInterface ui;
	private List<Matrix> matrixes;

	public Game() {
		matrixes= new ArrayList<Matrix>();
	}

	public void start() {
		ui.show("Mine Sweeper started!");
		ui.show("To exite please provide [0 0] for lines and columns.");
		int lines = -1;
		int columns = -1;
		while (!isColumnAndLineValueIndicatingEndOfTheGame(columns, lines)) {
			try {
				ui.clear();
				ui.show("Provide [lines columns] separated by space");
				lines = ui.nextInt();
				columns = ui.nextInt();

				String content = ".";
				if (!isColumnAndLineValueIndicatingEndOfTheGame(columns, lines)) {
					ui.show("Enter the mine sweeper content:");
					content = ui.next();
					// Leaving this on purpose to discuss best alternative to
					// cover specific cases (not just code coverage) but
					// business coverage and avoid tight coupling using mocking
					matrixes.add(new Matrix(lines, columns, content));
				} else {
					break;
				}

			} catch (Exception e) {
				ui.showError("Invalid input, please try again!!. Reason: " + e.getMessage() + "\n");
				break;
			}
		}

		for (Matrix matrix: matrixes) {
			ui.show(matrix.showMines());
			ui.show("");
		}
		
		ui.show("End");
	}

	private boolean isColumnAndLineValueIndicatingEndOfTheGame(int numberOfColumns, int numberOfLines) {
		return numberOfColumns == 0 && numberOfLines == 0;
	}

	public void setUi(UserInterface ui) {
		this.ui = ui;
	}

	public static void main(String[] args) {
		Game game = new Game();
		// MISSING Injection for the moment
		game.setUi(new ConsoleUserInterface());
		game.start();
	}

}
