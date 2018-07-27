package com.ybl.atm.state;

import com.ybl.atm.client.Display;
import com.ybl.atm.client.SessionContext;
import com.ybl.atm.exception.BankException;

/**
 * Final state, used to clean up any state and exit
 * @author rviswanathan
 *
 */
public class ExitState extends AbstractATMState {

	@Override
	public ATMState execute() throws BankException {
		Display display = Display.getInstance();
		SessionContext.cleanUp();
		display.printLineMessage("Thank you for using YBL ATM, Have a Good Day!");
		display.clearDisplay();
		//Make a transition to initial state
		return new WelcomeUserState();
		

	}

}
