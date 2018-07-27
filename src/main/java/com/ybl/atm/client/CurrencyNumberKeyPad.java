package com.ybl.atm.client;

import java.math.BigDecimal;

import com.ybl.atm.exception.InvalidInputException;
import com.ybl.atm.utils.ATMUtils;

public class CurrencyNumberKeyPad extends CommandLineKeyPad<BigDecimal> {

	private static KeyPad<BigDecimal> instance = new CurrencyNumberKeyPad();

	public static KeyPad<BigDecimal> getInstance() {
		return instance;
	}

	@Override
	public BigDecimal getInput() throws InvalidInputException{
		if(!reader.hasNextBigDecimal()) {
			reader.nextLine();
			throw new InvalidInputException(ATMUtils.INVALID_INPUT);
		}
		return reader.nextBigDecimal();
	}

}
