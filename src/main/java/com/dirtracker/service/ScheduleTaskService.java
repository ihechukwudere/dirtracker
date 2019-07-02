package com.dirtracker.service;

import com.dirtracker.domain.ConfiguredDirectory;
import com.dirtracker.domain.Directory;
import com.dirtracker.task_scheduler.Task;

public class ScheduleTaskService {

	private Directory directory;
	
	public void scheduleRepeatableCheckForConfiguredDirectory(long timeInterval, long delay) {
		String dirPath = "src/main/resources/configured_directory";
		directory = new ConfiguredDirectory(dirPath);
		Task scheduledTask = new DirectoryRepeatableCheck(directory);
		scheduledTask.scheduleTask(timeInterval, delay);
	}
}
