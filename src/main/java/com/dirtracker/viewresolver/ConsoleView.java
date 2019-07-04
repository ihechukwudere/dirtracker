package com.dirtracker.viewresolver;

import java.nio.file.Path;

public class ConsoleView extends ContentViewResolver {

	@Override
	public void displayFileContent(Path filePath) throws Exception {
		super.displayFileContent(filePath);
		System.out.println(contentReader.readFile(filePath));
	}
	
}
