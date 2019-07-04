package com.dirtracker.task_scheduler;

import java.io.IOException;
import java.nio.file.Path;

import com.dirtracker.domain.Directory;

/**
 * A subclass of RepeatableTask class. It periodically checks
 * the configured directory to read the newly created XML 
 * configuration files only once.
 * @author Ihechukwudere Okoroego
 *
 */
public class PeriodicallyReadNewConfigFileInDirectory extends RepeatableTask {

	private Directory dir;

	public PeriodicallyReadNewConfigFileInDirectory(Directory dir) {
		this.dir = dir;
	}


	/**
	 * Starts directory stream periodically
	 */
	@Override
	public void run() {
		try {
			startDirectoryStream();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Calls directory streaming operation and then calls
	 * getElapsedTimeInMillisSinceFileCreation() method to calculate 
	 * the period of time since each file creation.  It finds newly
	 * created file/s by comparing the time since the file/s creation
	 * the time interval. If new file is found, it sends the file to 
	 * content view resolver. 
	 * @throws IOException
	 */
	public void startDirectoryStream() throws IOException {
		dir.streamDirectoryFiles().forEach((filePath) ->  {
			try {
				if (dir.getElapsedTimeInMillisSinceFileCreation(filePath).longValue() < timeInterval.longValue()) {
					dir.hasContentViewResolver();
					sendFileToContentViewResolver(filePath);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
	}
	
	private void sendFileToContentViewResolver(Path filePath) throws Exception {
		dir.getContentView().displayFileContent(filePath);
	}

}
