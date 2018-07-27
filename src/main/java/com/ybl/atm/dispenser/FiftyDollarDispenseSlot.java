package com.ybl.atm.dispenser;

import java.math.BigDecimal;

public class FiftyDollarDispenseSlot extends DollarDispenseSlot {

	

	public FiftyDollarDispenseSlot(int numOfCurrencies) {
		super(numOfCurrencies);
		// TODO Auto-generated constructor stub
	}

	public FiftyDollarDispenseSlot() {
		super(50);
	}

	@Override
	public BigDecimal getDenomination() {
		return FIFTY;
	}

}
