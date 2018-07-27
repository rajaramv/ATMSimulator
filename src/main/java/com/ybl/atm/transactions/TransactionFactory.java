package com.ybl.atm.transactions;

import com.ybl.atm.utils.ATMUtils;

public class TransactionFactory {

	public static Transaction getTransactionType(int type) {
		Transaction trans = null;
		switch (type) {
		case ATMUtils.DEPOSIT: // initialize as new object of chosen type
			trans = new DepositTransaction();
			break;
		case ATMUtils.WITHDRAWAL: // initialize as new object of chosen type
			trans = new WithDrawCharge( new WithDrawTransaction());
			break;
		case ATMUtils.BALANCE_INQUIRY:
			trans = new ViewAccountBalance();
			break;
		case ATMUtils.MINI_STATEMENT:
			trans = new MiniStatement();
			break;

		}
		return trans;
	}

}
