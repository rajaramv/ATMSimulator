package com.ybl.atm.state;

/**
 * Abstract base class,convinient to hold next state
 * @author rviswanathan
 *
 */
public abstract class AbstractATMState implements ATMState {

	protected ATMState nextState;

	public AbstractATMState() {
		this.nextState = this;
	}


	@Override
	public ATMState getNextState() {
		return nextState;
	}
}
