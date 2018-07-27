package com.ybl.atm.state;

import com.ybl.atm.exception.BankException;

/**
 * State pattern, this class will be responsible for executing the states and
 * state transitions 
 * States work flow is as follows: 
 * WelcomeUserState (Initial state) -> 
 * <LOOP UNTIL EXIT> Transaction State  -> 
 * <UPON EXIT> Exit Session (Cleans up session) -> 
 * WelcomeUserState (Initial state)
 * @author rviswanathan
 *
 */
public class ATMStateMachine {

	//Initial state, no user information available hence in not authenticated state
	private ATMState atmState = new WelcomeUserState();

	public void setATMState(ATMState currentState) {
		this.atmState = currentState;
	}

	public void execute() throws BankException {
		//Execute the current state,Make transition to next state
		atmState = atmState.execute();
	}
}
