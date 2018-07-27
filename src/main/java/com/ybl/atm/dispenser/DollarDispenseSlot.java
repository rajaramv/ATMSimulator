package com.ybl.atm.dispenser;

import java.math.BigDecimal;

import com.ybl.atm.client.Display;
import com.ybl.atm.exception.DenominationNotAvailableException;
import com.ybl.atm.vo.Currency;

/**
 * Abstract base class, Does the core logic of finding the denominations and
 * pass on to next in chain
 * 
 * @author rviswanathan
 *
 */
public abstract class DollarDispenseSlot implements DispenserSlotChain {

	protected int numOfCurrencies = 0;

	DispenserSlotChain nextSlot;

	@Override
	public void setNextDispenseSlotChain(DispenserSlotChain next) {
		this.nextSlot = next;

	}

	public DollarDispenseSlot(int numOfCurrencies) {
		this.numOfCurrencies = numOfCurrencies;
	}

	@Override
	public void dispenseCurrency(Currency amount) throws DenominationNotAvailableException {

		//Do not process if it does not match the current denomination
		if (amount.getAmount().compareTo(getDenomination()) >= 0) {
			//Find how many notes required for the current denomination
			int num = amount.getAmount().divide(getDenomination()).intValue();
			// Not enough notes present, but include how many ever notes present
			if (num >= numOfCurrencies) {
				num = numOfCurrencies;
			}
			//Find the balance and pass on to next slot to see if it can handle the number of notes
			BigDecimal remainder = amount.getAmount().subtract(getDenomination().multiply(BigDecimal.valueOf(num)));
			try {
				//Move on
				if (!remainder.equals(BigDecimal.ZERO))
					this.nextSlot.dispenseCurrency(new Currency(remainder));
				//Notes dispensed, reduce it from the total count
				numOfCurrencies -= num;
				if(num > 0){
					Display.getInstance().printLineMessage("Dispensing {0} {1}$  currency", num, getDenomination());
				}
			} catch (DenominationNotAvailableException ex) {
				// Restore the count
				numOfCurrencies += num;
				throw ex;
			}
		} else {
			this.nextSlot.dispenseCurrency(amount);
		}

	}

	@Override
	public void addDenomination() {
		numOfCurrencies++;

	}

}
