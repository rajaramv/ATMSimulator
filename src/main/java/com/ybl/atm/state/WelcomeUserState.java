package com.ybl.atm.state;

import com.ybl.atm.client.Display;
import com.ybl.atm.client.KeyPadFactory;
import com.ybl.atm.client.SessionContext;
import com.ybl.atm.exception.BankException;
import com.ybl.atm.server.BankServiceImpl;
import com.ybl.atm.vo.Account;

/**
 * State in which the ATM is with out user account context, Prompt user to login
 * 
 * @author rviswanathan
 *
 */
public class WelcomeUserState extends AbstractATMState {

	@Override
	public ATMState execute() throws BankException {
		// Menu options for Login state
		Display display = Display.getInstance();
		display.printLineMessage("Welcome to YBL ATM");
		display.printLineMessage("Please enter your account number to continue: ");
		ATMState nextState = this;
		// Attempt to authenticate user, upon successful login move to next
		// state
		if (login()) {
			nextState = new TransactionState();
		}
		return nextState;
	}

	private boolean login() throws BankException {
		Display display = Display.getInstance();
		long accountNumber = KeyPadFactory.getLongNumberKeyPad().getInput();

		Account accountDetails = null;
		// Get details
		accountDetails = BankServiceImpl.getInstance().findAccountById(accountNumber);
		display.printLineMessage("Welcome, {0}!, Please enter your pin: ", accountDetails.getUserName());
		int pin = KeyPadFactory.getSimpleNumberKeyPad().getInput();
		boolean success = false;
		// TODO Retry for a threshold and break it
		if (pin != accountDetails.getPin()) {
			// Login failed, No state transition happens
			display.printLineMessage("Error:, Invalid PIN entered! ");
		} else {
			display.printLineMessage("Pin Validation Success!");
			SessionContext.setAccountNumber(accountNumber);
			success = true;
		}
		return success;
	}

}
