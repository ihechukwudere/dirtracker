package com.dirtracker.domain;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;

import com.dirtracker.viewresolver.ContentViewResolver;

/**
 * An Abstract class that is a super class to other classes that represent directories.
 * @author Ihechukwudere Okoroego
 *
 */
public abstract class Directory {
	
	private Path dirPath;
	private ContentViewResolver contentViewResolver;
	
	/**
	 * Requires a directory path
	 * @param dirPath
	 * @throws NoSuchFileException 
	 */
	public Directory(Path dirPath) throws NoSuchFileException   {
		if(!dirPath.toFile().exists()) 
			throw new NoSuchFileException("No directory found in this path: " + dirPath);
		this.dirPath = dirPath;
	}

	public DirectoryStream<Path> streamDirectoryFiles()
			throws IOException {
		 return Files.newDirectoryStream(dirPath);
	}
	
	
	/**
	 * Calculates the elapsed time (milliseconds) since the file 
	 * was created in the directory.
	 * @param filePath
	 * @return Returns a long value which is the elapsed time in milliseconds.
	 * @throws IOException
	 * @throws ParseException 
	 */
	public BigDecimal getElapsedTimeInMillisSinceFileCreation(Path filePath)
			throws IOException {
		FileTime fileTime = getFileAttributes(filePath).creationTime();
		return new BigDecimal(System.currentTimeMillis() - fileTime.toMillis());
	}

	public Path getDirPath() {
		return dirPath;
	}

	public void setDirPath(Path dirPath) {
		this.dirPath = dirPath;
	}
	
	private BasicFileAttributes getFileAttributes(Path filePath) throws IOException {
		 return Files.readAttributes(filePath,
						BasicFileAttributes.class);
	}

	public ContentViewResolver getContentView() {
		return contentViewResolver;
	}

	public void setContentView(ContentViewResolver contentViewResolver) {
		this.contentViewResolver = contentViewResolver;
	}
	
	public boolean hasContentViewResolver() throws Exception {
		if (contentViewResolver == null)
			throw new Exception("No content view resolver is set for " + this.getClass().getSimpleName());
		return true;
	}
	
}
