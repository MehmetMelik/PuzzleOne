package com.aimia.puzzle;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Aimia Puzzle One
 * @author Mehmet Melik Kose
 *
 */

public class EnglishNumberReader implements NumberReaderInterface {
	
	private static final EnglishNumberReader instance = new EnglishNumberReader();
	
	Properties prop;
	
	private EnglishNumberReader() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("src/com/aimia/resources/number_en.properties"));
		} catch (IOException _ex) {
			_ex.printStackTrace();
		}
	}
	
	/**
	 * Singleton getInstace method.
	 * @return
	 */
	public static EnglishNumberReader getInstance() {
		return instance;
	}
	
	/**
	 * Reads from properties file
	 * @param i
	 * @return
	 */
	public String readNumber(int i) {
		return prop.getProperty(String.valueOf(i));
	}
	
	/**
	 * Reads two-digit-numbers
	 * @param i
	 * @return
	 */
	public String readTwoDigits(int i) {
		// int would provide enough boundaries since the MAX_VALUE for Integer is 2,147,483,647
		int firstDigit = i % TEN;
		String remainder = "";
		if (firstDigit>0)
			remainder = readNumber(firstDigit);
		int secondDigit = i / TEN;
		if(secondDigit == 1) {
			remainder = readNumber(i);
		} else if (secondDigit>1) {
			remainder = readNumber(secondDigit*TEN) + ((firstDigit == 0) ? "" : " " + remainder);
		} else {
			// zero = do nothing.
		}
		return remainder;
	}
	
	/**
	 * Reads three-digit-numbers
	 * @param i
	 * @return
	 */
	public String readThreeDigits(int i) {
		int thirdDigit = i / HUNDRED;
		int nextTwoDigit = i%HUNDRED;
		if (thirdDigit > 0) {
			return readNumber(thirdDigit) + " " + readNumber(HUNDRED) + " "
					+ ((nextTwoDigit == 0) ? "" : readTwoDigits(nextTwoDigit));
		} else {
			return readTwoDigits(i);
		}
	}

	/**
	 * A recursive function that returns millions and thousands.
	 * @param i
	 * @param scale
	 * @return 
	 */
	public String readNext(int i, int scale) {
		int threeDigits = i / scale;
		if (scale == 1) {
			return readThreeDigits(i);
		} else return
			((threeDigits == 0) ? "" : readThreeDigits(threeDigits).trim() + " " + readNumber(scale) + " ")
			+ readNext(i%scale, scale/THOUSAND);
	}
	
	/**
	 * The method that is going to be called.
	 * @param i
	 * @return
	 */
	public String read(int i) {
		return readNext(i, MILLION).trim();
	}
}
