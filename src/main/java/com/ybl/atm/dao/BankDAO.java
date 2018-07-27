package com.ybl.atm.dao;

import java.util.Collection;

import com.ybl.atm.vo.Account;
import com.ybl.atm.vo.TransactionEntry;

/**
 * Simple interface for all DAO operations related to banking
 * @author rviswanathan
 *
 */
public interface BankDAO {
	
	Account findAccountById(long accountNumber);
	
	Account saveAccount(Account accountInfo);
	

	void createTransactionEntry(TransactionEntry entry);
	
	Collection<TransactionEntry> getMiniTransactionList(long accountNumber);

}
