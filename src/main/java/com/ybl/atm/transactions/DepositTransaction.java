package com.ybl.atm.transactions;

import java.math.BigDecimal;

import com.ybl.atm.client.Display;
import com.ybl.atm.client.KeyPadFactory;
import com.ybl.atm.dispenser.DispenseManager;
import com.ybl.atm.dispenser.DispenserSlotChain;
import com.ybl.atm.exception.BankException;
import com.ybl.atm.exception.InvalidInputException;
import com.ybl.atm.server.BankService;
import com.ybl.atm.server.BankServiceImpl;
import com.ybl.atm.utils.ATMUtils;

public class DepositTransaction implements Transaction {

	BankService service = BankServiceImpl.getInstance();

	@Override
	public void execute() throws BankException {

		Display.getInstance()
				.printLineMessage("Enter deposit denominations with enter key and terminated by '.' Ex: 50  20  10  .");
		BigDecimal totalAmout = BigDecimal.ZERO;
		totalAmout = verifyAndAddDenominations(totalAmout);
		service.creditAmount(totalAmout);
		Display.getInstance().printLineMessage("Total amount deposited : {0}", totalAmout);
	}

	private BigDecimal verifyAndAddDenominations(BigDecimal totalAmout) throws InvalidInputException {
		String number = "";
		while (!number.equals(".")) {
			number = KeyPadFactory.getDenominationKeyPad().getInput();
			if(number.equals(".")) {
				break;
			}
			if (ATMUtils.isNumeric(number)) {
				BigDecimal denomiation = BigDecimal.valueOf(Integer.parseInt(number));
				DispenserSlotChain denominationSlot = DispenseManager.getInstance()
						.getDispenseSlotForDenomination(denomiation);
				if (denominationSlot == null) {
					Display.getInstance().printLineMessage(
							"Invalid denomination {0}, Denomination rejected", denomiation);
					continue;
				}
				denominationSlot.addDenomination();
				totalAmout = totalAmout.add(denomiation);
			} else {
				Display.getInstance().printLineMessage(
						"Invalid denomination {0}, Denomination rejected", number);
			}
			
		}
		return totalAmout;
	}

}
