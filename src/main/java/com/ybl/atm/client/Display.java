package com.ybl.atm.client;

import java.text.MessageFormat;

public class Display {

	private static Display instance = new Display();

	public static Display getInstance() {
		return instance;
	}

	// Prints a message to standard output
	public void printMessage(String message, Object... params) {
		System.out.print(MessageFormat.format(message, params));
	}

	// Prints a message to standard output with new line
	public void printLineMessage(String message, Object... params) {

		System.out.println(MessageFormat.format(message, params));
	}

	public void clearDisplay() {
		for (int i = 0; i < 15; i++)
			System.out.println();
	}

}
