package com.ybl.atm.dispenser;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.ybl.atm.exception.BankException;
import com.ybl.atm.exception.InvalidInputException;
import com.ybl.atm.vo.Currency;

/**
 * This class is responsible for managing the currency slots and dispense requested money
 * Based on Chain of responsibility pattern.
 * @author rviswanathan
 *
 */
public class DispenseManager {
	
	// The Dispense consists of Fifty,Twenty and Ten denomination slots
	private DispenserSlotChain fiftySlot;
	private DispenserSlotChain twentySlot;
	private DispenserSlotChain tenSlot;
	
	//Default 50 each per currency
	private final static int DEFAULT_NUM_FIFTIES = 50;
	private final static int DEFAULT_NUM_TWENTIES = 50 ;
	private final static int DEFAULT_NUM_TENS = 50;
	
	private static DispenseManager manager = new DispenseManager();
	
	//Denomination to SLOT object mappin
	private Map<BigDecimal, DispenserSlotChain> denominationSlotMap;
	
	public static DispenseManager getInstance() {
		return manager;
	}
	
	//Singleton initialized
	private DispenseManager() {
		this.fiftySlot = new FiftyDollarDispenseSlot(DEFAULT_NUM_FIFTIES);
		this.twentySlot = new TwentyDollarDispenseSlot(DEFAULT_NUM_TWENTIES);
		this.tenSlot = new TenDollarDispenseSlot(DEFAULT_NUM_TENS);
		
		//Chain the slots
		this.fiftySlot.setNextDispenseSlotChain(twentySlot);
		this.twentySlot.setNextDispenseSlotChain(tenSlot);
		initializeMap();
	}
	
	private void initializeMap() {
		denominationSlotMap = new HashMap<>();
		denominationSlotMap.put(DispenserSlotChain.FIFTY, fiftySlot);
		denominationSlotMap.put(DispenserSlotChain.TWENTY, twentySlot);
		denominationSlotMap.put(DispenserSlotChain.TEN, tenSlot);
		
	}
	
	/**
	 * For a given denomiation finds if there exists a slot
	 * @param denomination - FIFT,TWENTY etx
	 * @return SLOT
	 */
	public DispenserSlotChain getDispenseSlotForDenomination(BigDecimal denomination) {
		return denominationSlotMap.get(denomination);
	}

	/**
	 * Chain of reponsibility pattern implemented. Checks every slot if it can perform, if not pass it on to next one
	 * @param amount
	 * @throws BankException
	 */
	public void dispenseCurrency(Currency amount) throws BankException {
		
		//Validation, We do not deal with 1s and 2s
		if(!amount.getAmount().remainder(BigDecimal.TEN).equals(BigDecimal.ZERO)) {
			throw new InvalidInputException("Amount should be in multiples of 10");
		}
		//Chained in order FIFTY,TWENTY and TEN
		fiftySlot.dispenseCurrency(amount);
	}

}
