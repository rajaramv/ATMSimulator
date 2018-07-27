package com.ybl.atm.dispenser;

import java.math.BigDecimal;

import com.ybl.atm.exception.DenominationNotAvailableException;
import com.ybl.atm.vo.Currency;

public interface DispenserSlotChain {
	
	BigDecimal FIFTY = BigDecimal.valueOf(50);
	BigDecimal TWENTY = BigDecimal.valueOf(20);
	BigDecimal TEN = BigDecimal.valueOf(10);
	
	void setNextDispenseSlotChain(DispenserSlotChain next);
	
	void dispenseCurrency(Currency amount) throws DenominationNotAvailableException;
	
	BigDecimal getDenomination();
	
	void addDenomination();
	

}
