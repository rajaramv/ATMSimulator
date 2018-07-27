package com.ybl.atm.client;

public class DenomiationKeyPad extends CommandLineKeyPad<String> {
	private static KeyPad<String> instance = new DenomiationKeyPad();

	public static KeyPad<String> getInstance() {
		return instance;
	}

	@Override
	public String getInput() {
		return reader.next();
	}

}
