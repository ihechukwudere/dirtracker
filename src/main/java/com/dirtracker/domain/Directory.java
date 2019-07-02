package com.dirtracker.domain;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;

/**
 * An Abstract class that is a super class to other classes that represent directories.
 * @author Ihechukwudere Okoroego
 *
 */
public abstract class Directory {
	
	private Path dirPath;
	
	/**
	 * Requires a directory path
	 * @param dirPath
	 */
	public Directory(String dirPath) {
		this.dirPath = Paths.get(dirPath);
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
	public long getElapsedTimeInMillisSinceFileCreation(Path filePath)
			throws IOException {
		FileTime fileTime = getFileAttributes(filePath).creationTime();
		return (System.currentTimeMillis() - fileTime.toMillis());
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
	
}
