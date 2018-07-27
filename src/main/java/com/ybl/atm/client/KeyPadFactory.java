package com.ybl.atm.client;

import java.math.BigDecimal;

public class KeyPadFactory {

	public static KeyPad<Long> getLongNumberKeyPad() {
		return LongNumberKeyPad.getInstance();
	}

	public static KeyPad<Integer> getSimpleNumberKeyPad() {
		return SimpleNumberKeypad.getInstance();
	}

	public static KeyPad<BigDecimal> getCurrencyNumberKeyPad() {
		return CurrencyNumberKeyPad.getInstance();
	}

	public static KeyPad<String> getDenominationKeyPad() {
		return DenomiationKeyPad.getInstance();
	}
}
