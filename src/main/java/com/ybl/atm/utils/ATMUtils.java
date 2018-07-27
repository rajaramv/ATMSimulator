package com.ybl.atm.utils;

public class ATMUtils {

	// constants corresponding to main menu options
	public static final int DEPOSIT = 1;
	public static final int WITHDRAWAL = 2;
	public static final int BALANCE_INQUIRY = 3;
	public static final int MINI_STATEMENT = 4;
	public static final int EXIT = 5;
	
	public static final String INVALID_INPUT= "Error : Invalid Input, Please enter correct value";

	public static boolean isNumeric(String str) {
		return str.matches("\\d+(\\.\\d+)?"); 
	}

}
