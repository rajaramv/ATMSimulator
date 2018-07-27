package com.ybl.atm.client;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ybl.atm.dao.TransactionStore;
import com.ybl.atm.exception.AccountInfoNotFoundException;
import com.ybl.atm.exception.BankException;
import com.ybl.atm.server.BankService;
import com.ybl.atm.server.BankServiceImpl;
import com.ybl.atm.test.base.BaseTestClass;
import com.ybl.atm.transactions.DepositTransaction;
import com.ybl.atm.transactions.ViewAccountBalance;
import com.ybl.atm.vo.Account;

public class DepositTransactionTest extends BaseTestClass{
	
	@Before
	@Override
	public void setup() {
		super.setup();
		//The first set of input deposits 20/50/10 which is valid, the next set tries 30/INVALID/50 , of which 50 is accepted and 30 and INVALID are rejected
		String inputReader = "20\n50\n10\n.\n30\n\nINVALID\n50\n.\n";
		InputStream stream = IOUtils.toInputStream(inputReader, StandardCharsets.UTF_8);
		System.setIn(stream);
	}
	@Test()
	public void testDepositTransaction() throws BankException, IOException {
		//Valid case 20,50,10 deposited and accepted
		DepositTransaction transaction = new DepositTransaction();
		transaction.execute();
		Assert.assertEquals("80", extractNumberFromOutPut(1));
		ViewAccountBalance balanceTrans = new ViewAccountBalance();
		balanceTrans.execute();
		Assert.assertEquals("5,080", extractNumberFromOutPut(1));
		
		// Invalid denomination 30 rejected, however a successful denomination of 50 is deposited!
		DepositTransaction transaction1 = new DepositTransaction();
		transaction1.execute();
		Assert.assertEquals("Invalid denomination 30, Denomination rejected", extractLastMessageFromOutPut(3));
		Assert.assertEquals("Invalid denomination INVALID, Denomination rejected", extractLastMessageFromOutPut(2));
		ViewAccountBalance balanceTrans1 = new ViewAccountBalance();
		balanceTrans1.execute();
		// 80 from previous transaction and 50 in this transaction puts the balance at 5130
		Assert.assertEquals("5,130", extractNumberFromOutPut(1));
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
