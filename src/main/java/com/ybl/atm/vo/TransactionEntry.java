package com.ybl.atm.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.ybl.atm.client.SessionContext;

public class TransactionEntry implements Comparable<TransactionEntry>{

	private String transactionId;

	private LocalDateTime createdDate;

	private long accountNumber;

	private BigDecimal amountValue;

	private TransactionType type;
	
	private BigDecimal closingBalance;

	public TransactionEntry(BigDecimal amountValue, TransactionType type, BigDecimal closingBalance) {
		super();
		this.accountNumber = SessionContext.getAccountNumber();
		this.createdDate = LocalDateTime.now();
		this.transactionId = UUID.randomUUID().toString();
		this.amountValue = amountValue;
		this.type = type;
		this.closingBalance = closingBalance;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public BigDecimal getAmountValue() {
		return amountValue;
	}

	public TransactionType getType() {
		return type;
	}

	public String getTransactionId() {
		return transactionId;
	}
	
	public BigDecimal getClosingBalance() {
		return closingBalance;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.createdDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		builder.append('\t');
		builder.append(this.createdDate.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		builder.append('\t');
		builder.append(this.type);
		builder.append('\t');
		builder.append(this.amountValue);
		builder.append('\t');
		builder.append(this.closingBalance);
		builder.append('\t');
		return builder.toString();
	}

	@Override
	public int compareTo(TransactionEntry entry) {
		return this.createdDate.compareTo(entry.getCreatedDate());
	}

	

}
