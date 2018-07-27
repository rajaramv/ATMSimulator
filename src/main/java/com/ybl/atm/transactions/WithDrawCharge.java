package com.ybl.atm.transactions;

import com.ybl.atm.client.Display;
import com.ybl.atm.exception.BankException;

/**
 * Decorator for Withdraw transation. This adds a charge for withdrawal
 * 
 * @author rviswanathan
 *
 */
public class WithDrawCharge implements Transaction {

	private Transaction withDrawTransaction;

	public WithDrawCharge(Transaction transaction) {
		this.withDrawTransaction = transaction;
	}

	@Override
	public void execute() throws BankException {
		//Execure the original Transaction
		withDrawTransaction.execute();
		//Decorate it further! Dummy implementation
		Display.getInstance().printLineMessage("Your withdrawal charges will be reflected in next bill");
	}

}
