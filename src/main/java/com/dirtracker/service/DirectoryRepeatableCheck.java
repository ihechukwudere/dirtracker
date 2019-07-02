package com.dirtracker.service;

import java.io.IOException;
import java.text.ParseException;

import com.dirtracker.domain.Directory;
import com.dirtracker.task_scheduler.RepeatableTask;

public class DirectoryRepeatableCheck extends RepeatableTask {

	private Directory dir;

	public DirectoryRepeatableCheck(Directory dir) {
		this.dir = dir;
	}


	@Override
	public void run() {
		try {
			checkDirectory();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void checkDirectory() throws IOException {
		dir.streamDirectoryFiles().forEach((filePath) ->  {
			try {
				if (dir.getElapsedTimeInMillisSinceFileCreation(filePath) < timeInterval) {
					System.out.println(timeInterval);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

}
