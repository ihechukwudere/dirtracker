package com.dirtracker.viewresolver;

import java.nio.file.Path;

import com.dirtracker.exception_handlers.ContentReaderException;

public abstract class ContentViewResolver {

	protected ContentReader contentReader;
	
	public void displayFileContent(Path filePath) throws ContentReaderException {
		if (contentReader == null) 
			throw new ContentReaderException("The file content could be displayed because no"
					+ " content reader is implemented");
	};

	public ContentReader getContentReader() {
		return contentReader;
	}

	public void setContentReader(ContentReader contentReader) {
		this.contentReader = contentReader;
	}
	
	
}
