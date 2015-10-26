package com.ericsson.minesweeper;

public class Game {
	private UserInterface ui;

	public Game() {
	}

	public void start() {
		ui.show("Mine Sweeper started!");
		ui.show("To exite please provide 0,0 for lines and columns.");
		int lines = -1;
		int columns = -1;
		while (!isColumnAndLineValueIndicatingEndOfTheGame(columns, lines)) {
			try {
				ui.clear();
				ui.show("Number of lines:");
				lines = ui.nextInt();
				ui.show("Number of columns:");
				columns = ui.nextInt();

				String content = ".";
				if (!isColumnAndLineValueIndicatingEndOfTheGame(columns, lines)) {
					ui.show("Enter the mine sweeper content:");
					content = ui.next();
					// Leaving this on purpose to discuss best alternative to
					// cover specific cases (not just code coverage) but
					// business coverage and aboid tight coupling using mocking
					Matrix matrix = new Matrix(lines, columns, content);
					ui.show(matrix.showMines());
				} else {
					break;
				}

			} catch (Exception e) {
				ui.showError("Invalid input, please try again!!. Reason: " + e.getMessage());
			}
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
