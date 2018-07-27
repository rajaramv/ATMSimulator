package com.ybl.atm.client;

import com.ybl.atm.exception.InvalidInputException;
import com.ybl.atm.utils.ATMUtils;

public class LongNumberKeyPad extends CommandLineKeyPad<Long> {

	private static KeyPad<Long> instance = new LongNumberKeyPad();

	public static KeyPad<Long> getInstance() {
		return instance;
	}

	@Override
	public Long getInput() throws InvalidInputException{
		if(!reader.hasNextLong()){
			reader.nextLine();
			throw new InvalidInputException(ATMUtils.INVALID_INPUT);
		}
		return reader.nextLong();	

	}

}
