package com.ybl.atm.client;

import com.ybl.atm.exception.InvalidInputException;
import com.ybl.atm.utils.ATMUtils;

public class SimpleNumberKeypad extends CommandLineKeyPad<Integer> {

	private static KeyPad<Integer> instance = new SimpleNumberKeypad();

	public static KeyPad<Integer> getInstance() {
		return instance;
	}

	@Override
	public Integer getInput() throws InvalidInputException{
		
		if(!reader.hasNextInt()){
			//Clear the current line
			reader.nextLine();
			throw new InvalidInputException(ATMUtils.INVALID_INPUT);
		}
		return reader.nextInt();

	}
}
