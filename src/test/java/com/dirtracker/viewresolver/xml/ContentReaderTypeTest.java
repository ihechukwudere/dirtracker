package com.dirtracker.viewresolver.xml;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.dirtracker.exception_handlers.InvalidFileExtensionException;
import com.dirtracker.viewresolver.ContentReaderType;

public class ContentReaderTypeTest {

	@Rule
	public ExpectedException exceptionThrown = ExpectedException.none();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests invalid file extension exception for XML implementation.
	 * @throws InvalidFileExtensionException
	 */
	@Test
	public void shouldThrowInvalidFileExtensionExceptionWithCustomMessage() throws InvalidFileExtensionException {
		String invalidFileExt = "book-config.txt";
		ContentReaderType readerType = new StAXPullParser(new XMLContentReader());
		exceptionThrown.expect(InvalidFileExtensionException.class);
		String expectedMessage = invalidFileExt + " extension dose not match ." + readerType.getFileExtention() + " as required.";
		exceptionThrown.expectMessage(expectedMessage);
		readerType.isValidFileExtension(invalidFileExt);
		
	}
	
	@Test
	public void shouldReturnTrueForvalidFileExtension() throws InvalidFileExtensionException {
		String invalidFileExt = "book-config.xml";
		ContentReaderType readerType = new StAXPullParser(new XMLContentReader());
		assertTrue(readerType.isValidFileExtension(invalidFileExt));
	}

}
