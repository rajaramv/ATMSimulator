package com.ybl.atm.client;

import java.util.Scanner;

public abstract class CommandLineKeyPad<T> implements KeyPad<T> {

	protected Scanner reader;

	public CommandLineKeyPad() {
		this.reader = new Scanner(System.in);
	}
}
