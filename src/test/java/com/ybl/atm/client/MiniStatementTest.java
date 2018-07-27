package com.ybl.atm.client;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.ybl.atm.dao.TransactionStore;
import com.ybl.atm.exception.AccountInfoNotFoundException;
import com.ybl.atm.exception.BankException;
import com.ybl.atm.server.BankService;
import com.ybl.atm.server.BankServiceImpl;
import com.ybl.atm.test.base.BaseTestClass;
import com.ybl.atm.vo.Account;
import com.ybl.atm.vo.TransactionEntry;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MiniStatementTest extends BaseTestClass {

	@Test
	public void testMiniStatement() throws BankException {
		long accountNumber = SessionContext.getAccountNumber();
		Collection<TransactionEntry> transactionList = BankServiceImpl.getInstance()
				.getMiniTransactionList(accountNumber);
		Assert.assertNull(transactionList);
		// Credit
		BankServiceImpl.getInstance().creditAmount(BigDecimal.valueOf(80));

		Collection<TransactionEntry> transactionList1 = BankServiceImpl.getInstance()
				.getMiniTransactionList(accountNumber);
		Assert.assertEquals(1, transactionList1.size());
		TransactionEntry[] entryArr = transactionList1.toArray(new TransactionEntry[0]);
		TransactionEntry entry1 = entryArr[0];
		Assert.assertEquals("CREDIT", entry1.getType().toString());
		Assert.assertEquals(BigDecimal.valueOf(80), entry1.getAmountValue());
		BankServiceImpl.getInstance().debitAmount(BigDecimal.valueOf(2000));

		Collection<TransactionEntry> transactionList2 = BankServiceImpl.getInstance()
				.getMiniTransactionList(accountNumber);
		TransactionEntry[] entryArr1 = transactionList2.toArray(new TransactionEntry[0]);
		TransactionEntry entry2 = entryArr1[1];
		Assert.assertEquals("DEBIT", entry2.getType().toString());
		Assert.assertEquals(BigDecimal.valueOf(2000), entry2.getAmountValue());
	}

	@After
	public void tearDown() throws AccountInfoNotFoundException {
		BankService service = BankServiceImpl.getInstance();
		Account acc = service.findAccountById(SessionContext.getAccountNumber());
		acc.setAccountBalance(BigDecimal.valueOf(5000));
		TransactionStore.INSTANCE.clearTransactions();
		service.saveAccount(acc);
	}
}
