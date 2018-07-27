package com.ybl.atm.transactions;

import com.ybl.atm.client.Display;
import com.ybl.atm.client.SessionContext;
import com.ybl.atm.exception.BankException;
import com.ybl.atm.server.BankServiceImpl;
import com.ybl.atm.vo.Account;

/**
 * View Account balance
 * @author rviswanathan
 *
 */
public class ViewAccountBalance implements Transaction {

	@Override
	public void execute() throws BankException {
		long accountNumber = SessionContext.getAccountNumber();
		Account accountDetails = BankServiceImpl.getInstance().findAccountById(accountNumber);
		Display.getInstance().printLineMessage("Your existing account balance is: ${0}",
				accountDetails.getAccountBalance());
	}

}
