package com.ericsson.minesweeper;

import java.util.Scanner;

public class Game implements Runnable {

	Scanner scanner = new Scanner(System.in);

	public Game() {
	}

	public void start() {
		System.out.println("Mine Sweeper started!");
		Thread t = new Thread(this);
		t.start();
	}

	public String newMineSweeper(int numberOfLines, int numberOfColumns, String inputMatrixContent) {
		if (numberOfColumns == 0 && numberOfLines == 0) {
			return "Good Bye";
		}
		Matrix matrix = new Matrix(numberOfLines, numberOfColumns, inputMatrixContent);
		System.out.println(matrix.showMines());
		return null;
	}

	public final static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		System.out.println("To exite please provide 0,0 for lines and columns.");
		int lines = -1;
		int columns = -1;
		while (lines != 0 && columns != 0) {
			try {
				Game.clearConsole();
				System.out.println("Number of lines:");
				lines = scanner.nextInt();
				System.out.println("Number of columns:");
				columns = scanner.nextInt();

				System.out.println("Enter the mine sweeper content: ");
				String content = scanner.next();

				this.newMineSweeper(lines, columns, content);
			} catch (Exception e) {
				System.err.println("Invalid input, please try again!!. Reason: " + e.getMessage());
			}
		}

		System.out.println("End");
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
