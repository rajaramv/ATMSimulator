package com.ybl.atm.exception;

public class AccountInfoNotFoundException extends BankException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AccountInfoNotFoundException(String message) {
		super(message);
	}

}
