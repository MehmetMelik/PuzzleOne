package com.aimia.puzzle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EnglishNumberReaderShould {
	
	private EnglishNumberReader enr;
	
	@Before
	public void init() {
		enr = EnglishNumberReader.getInstance();
	}

	@Test
	public void LoadPropertiesFile() {
		assertNotNull(enr.prop);
	}
	
	@Test
	public void ReturnEnglishWordsForOneDigit() {
		assertEquals("one", enr.readNumber(1));
		assertEquals("five", enr.readNumber(5));
		assertEquals("nine", enr.readNumber(9));
		
	}
	
	@Test 
	public void ReturnEnglishWordsForTwoDigits() {
		assertEquals("twelve", enr.readTwoDigits(12));
		assertEquals("eighty one", enr.readTwoDigits(81));
	}
	
	@Test
	public void ReturnEnglishWordsForThreeDigits() {
		assertEquals("nine hundred fifty seven", enr.readThreeDigits(957));
	}
	
	@Test 
	public void ReturnEnglishWordsForThousands() {
		assertEquals("two hundred sixty four thousand seven hundred forty eight", enr.read(264748));
	}
	
	@Test
	public void ReturnEnglishWordsForMillions() {
		assertEquals("fifty three million two hundred sixty four thousand seven hundred forty eight", enr.read(53264748));
	}
	
	@Test
	public void ReturnEnglishWordsForNumbersWithZero() {
		assertEquals("nine hundred million", enr.read(900000000));
		assertEquals("nine hundred million nine thousand fifty", enr.read(900009050));
		assertEquals("nine hundred million ninety thousand fifty", enr.read(900090050));
		assertEquals("nine hundred", enr.read(900));
		assertEquals("one thousand", enr.read(1000));
		assertEquals("nine hundred seven", enr.read(907));
		assertEquals("nine hundred fifty", enr.read(950));
		assertEquals("one thousand fifty", enr.read(1050));
		assertEquals("one thousand seven", enr.read(1007));
	}
	
}
