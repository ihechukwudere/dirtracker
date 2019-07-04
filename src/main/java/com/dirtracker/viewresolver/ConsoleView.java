package com.dirtracker.viewresolver;

import java.nio.file.Path;

import com.dirtracker.exception_handlers.ContentReaderException;

public class ConsoleView extends ContentViewResolver {

	@Override
	public void displayFileContent(Path filePath) throws ContentReaderException {
		super.displayFileContent(filePath);
		System.out.println(contentReader.readFile(filePath));
	}
	
}
