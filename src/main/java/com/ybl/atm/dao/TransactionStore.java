package com.ybl.atm.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.ybl.atm.client.SessionContext;
import com.ybl.atm.vo.TransactionEntry;

/**
 * This represents the physical store of the transaction details.
 * @author rviswanathan
 *
 */
public enum TransactionStore {
	
	INSTANCE;
	
	private  Map<Long,Collection<TransactionEntry>> transactionMap;
	
	//Singleton, This represents the DATA store which is single instance for all
	private TransactionStore() {
		transactionMap =  new HashMap<>();
	}

	
	
	public Collection<TransactionEntry> getTransactionList(long accountNumber) {
		Collection<TransactionEntry> entryList = transactionMap.get(accountNumber);
		if(entryList != null) {
			return entryList.stream().limit(10).collect(Collectors.toList());
		}
		return entryList;
	}
	
	public void addTransactionEntry(TransactionEntry entry) {
		Collection<TransactionEntry> entryList = transactionMap.get(entry.getAccountNumber());
		
		if(entryList == null) {
			entryList = new TreeSet<>();
		}
		entryList.add(entry);
		transactionMap.put(entry.getAccountNumber(), entryList);
		
	}
	
	public void clearTransactions() {
		long accountNo = SessionContext.getAccountNumber();
		transactionMap.remove(accountNo);
	}
	
	
	
	
	

}
