package com.ybl.atm.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.ybl.atm.vo.Account;

/**
 * This represents the physical store of the account details.
 * @author rviswanathan
 *
 */
public enum AccountStore {
	
	INSTANCE;
	
	private  Map<Long,Account> accountMap;
	
	//Singleton, This represents the DATA store which is single instance for all
	private AccountStore() {
		accountMap =  new HashMap<>();
		initializeAccountMap();
	}

	private void initializeAccountMap() {
		Account acc1 = new Account(12345, "Rajaram Viswanathan", BigDecimal.valueOf(5000),1234);
		Account acc2 = new Account(12346, "Alex Parandian", BigDecimal.ZERO,1234);
		Account acc3 = new Account(12347, "Joe John", BigDecimal.ZERO,1234);
		Account acc4 = new Account(12348, "Kennedy Paul", BigDecimal.ZERO,1234);
		accountMap.put(acc1.getAccountNumber(), acc1);
		accountMap.put(acc2.getAccountNumber(), acc2);
		accountMap.put(acc3.getAccountNumber(), acc3);
		accountMap.put(acc4.getAccountNumber(), acc4);
		
	}
	
	public Account findAccountById(long accountNumber) {
		return accountMap.get(accountNumber);
	}
	
	public void saveAccount(Account acc) {
		accountMap.put(acc.getAccountNumber(), acc);
	}
	
	
	

}
