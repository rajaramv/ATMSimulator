package com.ybl.atm.client;

import com.ybl.atm.exception.BankException;
import com.ybl.atm.state.ATMStateMachine;

public class AutomatedTeller {

	public Display display = Display.getInstance();

	/**
	 * Facade entry point for the ATM functions
	 */
	public void start() {

		// State pattern. Check class level documentation
		ATMStateMachine stateMachine = new ATMStateMachine();
		// ATM never stops, it keeps servicing different accounts!
		while (true) {
			try {
				// Keep executing the state flow, check the workflow given above
				// Starts from WelcomeUserState.java
				stateMachine.execute();
			} catch (BankException ex) {
				// Print the exception to the display and continue
				display.printLineMessage(ex.getMessage());
			}
		}

	}

}
