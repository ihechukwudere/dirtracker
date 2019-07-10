package com.dirtracker.viewresolver;

import java.nio.file.Path;

public class ConsoleView extends ContentViewResolver {

	
	@Override
	public void displayFileContent(Path filePath) throws Exception {
		super.displayFileContent(filePath);
		if( fileContentReader.readFile(filePath) != null) {
			System.out.println(fileContentReader.readFile(filePath));
		}
		
	}
	
}
