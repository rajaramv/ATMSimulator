package com.ybl.atm.dispenser;

import java.math.BigDecimal;

import com.ybl.atm.exception.DenominationNotAvailableException;
import com.ybl.atm.vo.Currency;

public class TenDollarDispenseSlot extends DollarDispenseSlot{
	
	private static BigDecimal TEN = BigDecimal.valueOf(10);

	public TenDollarDispenseSlot(int numOfCurrencies) {
		super(numOfCurrencies);
	}
	
	public TenDollarDispenseSlot() {
		super(50);
	}
	
	@Override
	public void dispenseCurrency(Currency amount) throws DenominationNotAvailableException {
		int num = amount.getAmount().divide(TEN).intValue();
		if(num > numOfCurrencies) {
			throw new DenominationNotAvailableException("Not enough denominations available for the entered amount, Please enter lesser withdrawal amount : ");
		}
		super.dispenseCurrency(amount);
	}

	@Override
	public BigDecimal getDenomination() {
		return TEN;
	}

}
