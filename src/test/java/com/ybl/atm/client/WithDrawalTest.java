package com.ybl.atm.client;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.ybl.atm.dao.TransactionStore;
import com.ybl.atm.exception.AccountInfoNotFoundException;
import com.ybl.atm.exception.BankException;
import com.ybl.atm.exception.DenominationNotAvailableException;
import com.ybl.atm.exception.InvalidInputException;
import com.ybl.atm.server.BankService;
import com.ybl.atm.server.BankServiceImpl;
import com.ybl.atm.test.base.BaseTestClass;
import com.ybl.atm.transactions.ViewAccountBalance;
import com.ybl.atm.transactions.WithDrawTransaction;
import com.ybl.atm.vo.Account;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WithDrawalTest extends BaseTestClass {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Before
	@Override
	public void setup() {
		super.setup();
		// WithDraw 2000, Balance will be 3000. Withdraw 3000, Dispenser does
		// not have cash.Withdraw 60, verify denominations 1 10$ 1 50$
		String inputReader = "2000\n3000\n60\n5";
		InputStream stream = IOUtils.toInputStream(inputReader, StandardCharsets.UTF_8);
		System.setIn(stream);
	}

	@Test
	/**
	 * Normal withdrawal, should reduce the balance by 2000
	 * 
	 * @throws BankException
	 * @throws IOException
	 */
	public void testWithDrawalTransaction_1() throws BankException, IOException {

		WithDrawTransaction withDraw = new WithDrawTransaction();
		withDraw.execute();
		ViewAccountBalance balanceTrans = new ViewAccountBalance();
		balanceTrans.execute();
		Assert.assertEquals("3,000", extractNumberFromOutPut(1));

	}

	@Test
	/**
	 * Machine has only 2000 balance, try withdrwaing 3000 and verify error
	 * 
	 * @throws BankException
	 * @throws IOException
	 */
	public void testWithDrawalTransaction_2() throws BankException, IOException {

		exceptionRule.expect(DenominationNotAvailableException.class);
		exceptionRule.expectMessage(
				"Not enough denominations available for the entered amount, Please enter lesser withdrawal amount : ");
		WithDrawTransaction withDraw1 = new WithDrawTransaction();
		withDraw1.execute();
	}

	@Test
	/**
	 * Withdraw $60 and verify denominations
	 * 
	 * @throws BankException
	 * @throws IOException
	 */
	public void testWithDrawalTransaction_3() throws BankException, IOException {

		WithDrawTransaction withDraw1 = new WithDrawTransaction();
		withDraw1.execute();
		Assert.assertEquals("Dispensing 1 50$  currency", extractLastMessageFromOutPut(1));
		Assert.assertEquals("Dispensing 1 10$  currency", extractLastMessageFromOutPut(2));
	}

	@Test
	/**
	 * Withdraw $5 and verify exception
	 * 
	 * @throws BankException
	 * @throws IOException
	 */
	public void testWithDrawalTransaction_4() throws BankException, IOException {

		exceptionRule.expect(InvalidInputException.class);
		exceptionRule.expectMessage("Amount should be in multiples of 10");
		WithDrawTransaction withDraw1 = new WithDrawTransaction();
		withDraw1.execute();
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
