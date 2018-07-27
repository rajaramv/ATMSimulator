package com.ybl.atm.transactions;

import com.ybl.atm.exception.BankException;

public interface Transaction {

	void execute() throws BankException;

}
