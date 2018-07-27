package com.ybl.atm.transactions;

import java.util.Collection;

import com.ybl.atm.client.Display;
import com.ybl.atm.client.SessionContext;
import com.ybl.atm.exception.BankException;
import com.ybl.atm.server.BankServiceImpl;
import com.ybl.atm.vo.TransactionEntry;
/**
 * Responsible for generating Mini statement
 * @author rviswanathan
 *
 */
public class MiniStatement implements Transaction {

	@Override
	public void execute() throws BankException {
		long accountNumber = SessionContext.getAccountNumber();
		//Fetch from the DB
		Collection<TransactionEntry> transactionList = BankServiceImpl.getInstance()
				.getMiniTransactionList(accountNumber);

		printMiniStatementHeader();
		if (transactionList == null) {
			Display.getInstance().printLineMessage("No Transactions found");
		} else {
			transactionList.forEach(transaction -> Display.getInstance().printLineMessage(transaction.toString()));
		}

		Display.getInstance().printLineMessage(
				"---------------------------------------------------------------------------------------------------");

	}

	private void printMiniStatementHeader() {
		Display disp = Display.getInstance();
		disp.printLineMessage(
				"---------------------------------------------------------------------------------------------------");
		disp.printMessage("Date \t\t");
		disp.printMessage("Time \t");
		disp.printMessage("Transaction \t");
		disp.printMessage("Amount \t");
		disp.printLineMessage("Closing Balance \t");
		disp.printLineMessage(
				"---------------------------------------------------------------------------------------------------");
	}

}
