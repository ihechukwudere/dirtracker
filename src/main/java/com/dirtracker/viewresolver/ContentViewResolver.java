package com.dirtracker.viewresolver;

import java.nio.file.Path;

import com.dirtracker.exception_handlers.ContentReaderException;

public abstract class ContentViewResolver {

	protected FileContentReader fileContentReader;
	
	public void displayFileContent(Path filePath) throws Exception {
		hasContentReader();
	};

	public FileContentReader getContentReader() throws Exception {
		return fileContentReader;
	}

	public void setContentReader(FileContentReader fileContentReader) {
		this.fileContentReader = fileContentReader;
	}
	
	
	protected void hasContentReader() throws ContentReaderException {
		if (fileContentReader == null) 
			throw new ContentReaderException("The file content could not displayed, no"
					+ " content reader is implemented.");
	}
	
}
