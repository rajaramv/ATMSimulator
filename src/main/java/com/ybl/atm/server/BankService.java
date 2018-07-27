package com.ybl.atm.server;

import java.math.BigDecimal;
import java.util.Collection;

import com.ybl.atm.exception.AccountInfoNotFoundException;
import com.ybl.atm.exception.BankException;
import com.ybl.atm.vo.Account;
import com.ybl.atm.vo.TransactionEntry;

public interface BankService {
	
	Account findAccountById(long accountNumber) throws AccountInfoNotFoundException;
	
	Account saveAccount(Account accountInfo);

	void performTransaction(int choice) throws BankException;
	
	boolean creditAmount(BigDecimal amount) throws BankException;
	
	boolean debitAmount(BigDecimal amount) throws BankException;
	
	void createTransactionEntry(TransactionEntry entry) throws BankException;
	
	Collection<TransactionEntry> getMiniTransactionList(long accountNumber);
}
