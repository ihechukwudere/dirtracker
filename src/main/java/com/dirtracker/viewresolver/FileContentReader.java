package com.dirtracker.viewresolver;

import java.nio.file.Path;
import java.util.List;

import com.dirtracker.domain.FileResource;
import com.dirtracker.exception_handlers.InvalidFileExtensionException;

public abstract class FileContentReader {

	private String fileName;
	private String fileExtention;
	private List<FileResource> fileResourceContainer = null;

	public abstract List<FileResource> readFile(Path filePath);

	/**
	 * 
	 * @param file
	 * @return
	 * @throws InvalidFileExtensionException
	 */
	public boolean isValidFileExtension(String file) throws InvalidFileExtensionException {
		return false;
	};

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<FileResource> getFileResourceContainer() {
		return fileResourceContainer;
	}

	public void setFileResourceContainer(List<FileResource> fileResourceContainer) {
		this.fileResourceContainer = fileResourceContainer;
	}

	public String getFileExtention() {
		return fileExtention;
	}

	public void setFileExtention(String fileExtention) {
		this.fileExtention = fileExtention;
	}
	
	
}
