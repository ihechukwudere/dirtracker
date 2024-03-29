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
	public boolean isValidFileExtension(String file) throws InvalidFileExtensionException {
		Pattern fileExtPattern =  Pattern.compile("([^\\s]+(\\.(?i)(" +getFileExtention()+"))$)");
		Matcher matcher = fileExtPattern.matcher(file);
		if(!matcher.matches())
			throw new InvalidFileExtensionException(file 
					+ " extension dose not match ." + getFileExtention() + " as required.");
		return matcher.matches();
	}
}
