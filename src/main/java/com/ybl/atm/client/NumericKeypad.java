package com.ybl.atm.client;

import java.util.Scanner;

public class NumericKeypad implements KeyPad<Long> {
	
	// Reader which can read from command line or any other input stream based
	// on config
	private Scanner reader;

	private NumericKeypad() {
		reader = new Scanner(System.in);
	}

	private static NumericKeypad instance = new NumericKeypad();

	public static KeyPad<Long> getInstance() {
		return instance;
	}

	@Override
	public Long getInput() {
		return reader.nextLong();
	}

}
