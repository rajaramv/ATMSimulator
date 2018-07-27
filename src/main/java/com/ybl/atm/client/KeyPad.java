package com.ybl.atm.client;

import com.ybl.atm.exception.InvalidInputException;

public interface KeyPad<T> {
	  // Integer reader
	  public T getInput() throws InvalidInputException;
}
