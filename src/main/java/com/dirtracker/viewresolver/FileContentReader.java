package com.dirtracker.viewresolver;

import java.nio.file.Path;
import java.util.List;

import com.dirtracker.domain.FileResource;
import com.dirtracker.exception_handlers.InvalidFileExtensionException;

public abstract class FileContentReader {

	private String fileName;
	private String fileExtention;
	private List<FileResource<? extends Object>> resourceContainer = null;

	public abstract List<FileResource<? extends Object>> readFile(Path filePath);

	/**
	 * 
	 * @param file
	 * @return
	 * @throws InvalidFileExtensionException
	 */
	public boolean hasValidExtension(String file) throws InvalidFileExtensionException {
		return false;
	};

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<FileResource<? extends Object>> getResourceContainer() {
		return resourceContainer;
	}

	public void setResourceContainer(List<FileResource<? extends Object>> resourceContainer) {
		this.resourceContainer = resourceContainer;
	}

	public String getFileExtention() {
		return fileExtention;
	}

	public void setFileExtention(String fileExtention) {
		this.fileExtention = fileExtention;
	}
	
	
}
