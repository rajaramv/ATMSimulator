package com.ybl.atm.dispenser;

import java.math.BigDecimal;

public class TwentyDollarDispenseSlot extends DollarDispenseSlot {
	
	private static BigDecimal TWENTY = BigDecimal.valueOf(20);

	public TwentyDollarDispenseSlot(int numOfCurrencies) {
		super(numOfCurrencies);
		// TODO Auto-generated constructor stub
	}
	
	public TwentyDollarDispenseSlot() {
		super(50);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BigDecimal getDenomination() {
		// TODO Auto-generated method stub
		return TWENTY;
	}

}
