package com.ybl.atm.dao;

import java.util.Collection;

import com.ybl.atm.vo.Account;
import com.ybl.atm.vo.TransactionEntry;

public class InMemoryBankDAOImpl implements BankDAO {

	// Assumption , early initialization to avoid double check locks
	private static BankDAO instance = new InMemoryBankDAOImpl();

	private InMemoryBankDAOImpl() {
	}

	public static BankDAO getInstance() {
		return instance;
	}

	@Override
	public Account findAccountById(long accountNumber) {
		return AccountStore.INSTANCE.findAccountById(accountNumber);
	}

	@Override
	public Account saveAccount(Account accountInfo) {
		AccountStore.INSTANCE.saveAccount(accountInfo);
		return findAccountById(accountInfo.getAccountNumber());
	}

	@Override
	public void createTransactionEntry(TransactionEntry entry) {
		TransactionStore.INSTANCE.addTransactionEntry(entry);
		
	}

	@Override
	public Collection<TransactionEntry> getMiniTransactionList(long accountNumber) {
		return TransactionStore.INSTANCE.getTransactionList(accountNumber);
	}
	
	
	
	

}
