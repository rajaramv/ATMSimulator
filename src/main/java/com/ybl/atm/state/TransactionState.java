package com.ybl.atm.state;

import com.ybl.atm.client.Display;
import com.ybl.atm.client.KeyPadFactory;
import com.ybl.atm.exception.BankException;
import com.ybl.atm.server.BankServiceImpl;
import com.ybl.atm.utils.ATMUtils;

/**
 * Transaction state, helps the user to perform bank transactions upon succesful login
 * This state will be exited if user chooses Exit option from the menu
 * @author rviswanathan
 *
 */
public class TransactionState extends AbstractATMState {
	
	private void showMainMenu() {
		Display display = Display.getInstance();
		display.printLineMessage("\n[Main menu]");
		display.printLineMessage("1 - Deposit");
		display.printLineMessage("2 - Withdraw cash");
		display.printLineMessage("3 - Display Balance");
		display.printLineMessage("4 - Mini Statement");
		display.printLineMessage("5 - Exit");
		display.printLineMessage("Select an option: "); 
	}

	@Override
	public ATMState execute() throws BankException {
		showMainMenu();
		int choice = KeyPadFactory.getSimpleNumberKeyPad().getInput();
		//Move to the Exit state, User decided to logout
		if(choice == ATMUtils.EXIT){
			return new ExitState();
		}
		//Perform the transaction per user selection
		BankServiceImpl.getInstance().performTransaction(choice);
		return this;
	}

}
