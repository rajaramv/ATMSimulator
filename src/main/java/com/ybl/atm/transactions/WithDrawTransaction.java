package com.ybl.atm.transactions;

import java.math.BigDecimal;

import com.ybl.atm.client.Display;
import com.ybl.atm.client.KeyPadFactory;
import com.ybl.atm.client.SessionContext;
import com.ybl.atm.dispenser.DispenseManager;
import com.ybl.atm.exception.BankException;
import com.ybl.atm.exception.InSufficientBalanceException;
import com.ybl.atm.server.BankServiceImpl;
import com.ybl.atm.vo.Account;
import com.ybl.atm.vo.Currency;

/**
 * Withdraw Amount from the account
 * 
 * @author rviswanathan
 *
 */
public class WithDrawTransaction implements Transaction {

	@Override
	public void execute() throws BankException {
		Account currentAccount = BankServiceImpl.getInstance().findAccountById(SessionContext.getAccountNumber());
		Display.getInstance().printLineMessage("Please enter withdrawal amount: ");
		BigDecimal withdrawalAmount = KeyPadFactory.getCurrencyNumberKeyPad().getInput();
		// Make sure the account have sufficient balance
		if (currentAccount.getAccountBalance().compareTo(withdrawalAmount) < 0) {
			throw new InSufficientBalanceException("Error: Insufficient Balance, Please try with lesser amount : ");
		}
		DispenseManager manager = DispenseManager.getInstance();
		manager.dispenseCurrency(new Currency(withdrawalAmount));
		BankServiceImpl.getInstance().debitAmount(withdrawalAmount);
	}

}
