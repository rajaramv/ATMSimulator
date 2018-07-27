package com.ybl.atm.vo;

import java.math.BigDecimal;

public class Currency {
	
	private BigDecimal amount;

	public Currency(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
