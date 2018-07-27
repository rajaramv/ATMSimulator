package com.ybl.atm.client;

/**
 * Session context which holds the thread local
 * @author rviswanathan
 *
 */
public class SessionContext {

	private static ThreadLocal<Long> currentAccountNumber = new ThreadLocal<>();

	public static void setAccountNumber(Long accountNo) {
		currentAccountNumber.set(accountNo);
	}

	public static Long getAccountNumber() {
		return currentAccountNumber.get();
	}

	public static void cleanUp() {
		currentAccountNumber.remove();
	}

}
