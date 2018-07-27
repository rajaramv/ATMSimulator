package com.ybl.atm.state;

import com.ybl.atm.exception.BankException;

/**
 * Interface for all states
 * @author rviswanathan
 *
 */
public interface ATMState {
	
	public ATMState execute() throws BankException;
	
	public ATMState getNextState();
	

}
