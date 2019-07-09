package com.dirtracker.viewresolver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dirtracker.exception_handlers.InvalidFileExtensionException;

public abstract class ContentReaderType extends FileContentReader {

	protected FileContentReader fileContentReader;

	public ContentReaderType(FileContentReader fileContentReader) {
		this.fileContentReader = fileContentReader;
	}
	
	@Override
	public boolean isNotValidExtension(String file) throws InvalidFileExtensionException {
		Pattern fileExtPattern =  Pattern.compile("([^\\s]+(\\.(?i)(" +getFileExtention()+"))$)");
		Matcher matcher = fileExtPattern.matcher(file);
		return matcher.matches();
	}
}
