package com.kinettikmx.minesweeper;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUserInterface implements UserInterface {
	private Scanner scanner = new Scanner(System.in);
	private PrintStream out = System.out;
	private PrintStream err = System.err;
	
	public void show(String message) {
		out.println(message);
	}

	public int nextInt() {
		return scanner.nextInt();
	}

	public String next() {
		return scanner.next();
	}

	public void showError(String message) {
		err.println(message);
	}

	public final void clear() {
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

}
