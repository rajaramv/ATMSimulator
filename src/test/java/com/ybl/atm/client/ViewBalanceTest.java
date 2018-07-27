package com.ybl.atm.client;

import org.junit.Assert;
import org.junit.Test;

import com.ybl.atm.exception.BankException;
import com.ybl.atm.test.base.BaseTestClass;
import com.ybl.atm.transactions.ViewAccountBalance;

public class ViewBalanceTest extends BaseTestClass {

	@Test
	public void testViewBalance() throws BankException {

		ViewAccountBalance balance = new ViewAccountBalance();
		balance.execute();
		Assert.assertEquals("Your existing account balance is: $5,000".trim(), outputTextBuilder.toString().trim());
	}

}
