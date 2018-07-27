package com.ybl.atm.vo;

import java.math.BigDecimal;

public class Account {
	
	

	//Unique identifier for the account
	private long accountNumber;
	
	// Keeping this simple. Could be a composed User object too
	private String userName;
	
	private BigDecimal accountBalance;
	
	
	//Assumption : Plain text pin. Ideally it should be encrypted. We can use a Hash/SALT algorithm
	private int pin;
	
	
	

	public Account(long accountNumber, String userName, BigDecimal accountBalance, int pin) {
		super();
		this.accountNumber = accountNumber;
		this.userName = userName;
		this.accountBalance = accountBalance;
		this.pin = pin;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

}
