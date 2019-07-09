package com.dirtracker.viewresolver;

import java.nio.file.Path;
import java.util.List;

import com.dirtracker.domain.FileResource;

public class ConsoleView extends ContentViewResolver {

	List<FileResource<? extends Object>> fileResourceContainer;
	
	@Override
	public void displayFileContent(Path filePath) throws Exception {
		super.displayFileContent(filePath);
		fileResourceContainer = fileContentReader.readFile(filePath);
		if( fileResourceContainer != null) {
			System.out.println(fileContentReader.readFile(filePath));
		}
		
	}
	
}
