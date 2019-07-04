package com.dirtracker.viewresolver;

import java.nio.file.Path;

import com.dirtracker.exception_handlers.ContentReaderException;

public abstract class ContentViewResolver {

	protected ContentReader contentReader;
	
	public void displayFileContent(Path filePath) throws Exception {
		hasContentReader();
	};

	public ContentReader getContentReader() throws Exception {
		return contentReader;
	}

	public void setContentReader(ContentReader contentReader) {
		this.contentReader = contentReader;
	}
	
	
	protected void hasContentReader() throws ContentReaderException {
		if (contentReader == null) 
			throw new ContentReaderException("The file content could not displayed, no"
					+ " content reader is implemented.");
	}
	
}
