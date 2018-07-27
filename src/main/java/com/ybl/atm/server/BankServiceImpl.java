package com.ybl.atm.server;

import java.math.BigDecimal;
import java.util.Collection;

import com.ybl.atm.client.SessionContext;
import com.ybl.atm.dao.BankDAO;
import com.ybl.atm.dao.InMemoryBankDAOImpl;
import com.ybl.atm.exception.AccountInfoNotFoundException;
import com.ybl.atm.exception.BankException;
import com.ybl.atm.exception.InSufficientBalanceException;
import com.ybl.atm.exception.InvalidInputException;
import com.ybl.atm.transactions.Transaction;
import com.ybl.atm.transactions.TransactionFactory;
import com.ybl.atm.vo.Account;
import com.ybl.atm.vo.TransactionEntry;
import com.ybl.atm.vo.TransactionType;

/**
 * Facade class for all Bank operations. Violates SRP!
 * @author rviswanathan
 *
 */
public class BankServiceImpl implements BankService {

	BankDAO bankDAO = InMemoryBankDAOImpl.getInstance();

	private static BankService instance = new BankServiceImpl();

	public static BankService getInstance() {
		return instance;
	}

	@Override
	public Account findAccountById(long accountNumber) throws AccountInfoNotFoundException {
		Account accountDetails = bankDAO.findAccountById(accountNumber);

		if (accountDetails == null) {
			// Specific Exceptions are always clean
			throw new AccountInfoNotFoundException("Account information could not be retrieved, Please re enter");
		}

		return accountDetails;
	}

	@Override
	public Account saveAccount(Account accountInfo) {
		return bankDAO.saveAccount(accountInfo);
	}

	@Override
	/**
	 * Entry method to perform bank transactions based on User selection
	 */
	public void performTransaction(int choice) throws BankException {
		// Factory method to determine which transaction to perform
		Transaction trans = TransactionFactory.getTransactionType(choice);
		// No valid options chosen
		if (trans == null) {
			throw new InvalidInputException("Invalid menu option, Please choose from the above option:");
		}
		//Polymorphism in action, Exhibits LSP and Open closed.
		trans.execute();

	}

	@Override
	/**
	 * Credits amount to the selected account.
	 * Creates Transaction record also
	 */
	public boolean creditAmount(BigDecimal amount) throws BankException {
		long currentAccountNumber = SessionContext.getAccountNumber();
		Account currentAccount = findAccountById(currentAccountNumber);
		// Only one thread can enter and credit the amount
		synchronized (this) {
			//Credit!
			currentAccount.setAccountBalance(currentAccount.getAccountBalance().add(amount));
			//Simulate DB call. Goes to InMemory Map
			saveAccount(currentAccount);
			TransactionEntry entry = new TransactionEntry(amount, TransactionType.CREDIT,
					currentAccount.getAccountBalance());
			// Audit the transaction
			createTransactionEntry(entry);
		}

		return true;
	}

	/**
	 * Debits amount to the selected account.
	 * Creates Transaction record also
	 */
	@Override
	public boolean debitAmount(BigDecimal amount) throws BankException {
		long currentAccountNumber = SessionContext.getAccountNumber();
		Account currentAccount = findAccountById(currentAccountNumber);
		// Only one thread can enter and debit the amount
		synchronized (this) {
			//Make sure the account have sufficient balance
			if (currentAccount.getAccountBalance().compareTo(amount) < 0) {
				throw new InSufficientBalanceException("Error: Insufficient Balance, Please try with lesser amount : ");
			}
			//Debit!
			currentAccount.setAccountBalance(currentAccount.getAccountBalance().subtract(amount));
			saveAccount(currentAccount);
			TransactionEntry entry = new TransactionEntry(amount, TransactionType.DEBIT,
					currentAccount.getAccountBalance());
			// Audit the transaction
			createTransactionEntry(entry);
		}
		
		return true;
	}

	@Override
	public void createTransactionEntry(TransactionEntry entry) throws BankException {
		bankDAO.createTransactionEntry(entry);
	}

	/**
	 * Mini statement API, the statement is limited to 10 transactions from the DAO layer
	 */
	@Override
	public Collection<TransactionEntry> getMiniTransactionList(long accountNumber) {
		return bankDAO.getMiniTransactionList(accountNumber);
	}

}
