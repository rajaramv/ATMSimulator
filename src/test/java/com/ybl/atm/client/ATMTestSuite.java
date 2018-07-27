package com.ybl.atm.client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ViewBalanceTest.class, DepositTransactionTest.class, WithDrawalTest.class,
		MiniStatementTest.class })
public class ATMTestSuite {

}
