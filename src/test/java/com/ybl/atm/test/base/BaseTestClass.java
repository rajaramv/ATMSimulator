package com.ybl.atm.test.base;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;

import com.ybl.atm.client.SessionContext;

public class BaseTestClass {

	protected StringBuilder outputTextBuilder;

	// protected static String inputReader = "50\n.";
	@Before
	public void setup() {
		outputTextBuilder = new StringBuilder();
		System.setOut(new PrintStream(new OutputStream() {

			@Override
			public void write(int b) throws IOException {
				String str = String.valueOf((char) b);
				outputTextBuilder.append(str);
			}
		}));
		SessionContext.setAccountNumber(12345l);
	}

	public String extractNumberFromOutPut(int index) {
		String[] output = outputTextBuilder.toString().split("\n");
		Matcher matcher = Pattern.compile("[0-9,]+").matcher(output[output.length - index]);
		matcher.find();
		// int i = Integer.valueOf(matcher.group());
		return matcher.group();
	}

	public String extractLastMessageFromOutPut(int index) {
		String[] output = outputTextBuilder.toString().split("\n");
		return output[output.length - index];
	}

}
