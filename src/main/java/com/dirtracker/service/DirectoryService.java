package com.dirtracker.service;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.dirtracker.domain.ConfiguredDirectory;
import com.dirtracker.domain.Directory;
import com.dirtracker.task_scheduler.PeriodicallyReadNewConfigFileInDirectory;
import com.dirtracker.task_scheduler.Task;
import com.dirtracker.viewresolver.ConsoleView;
import com.dirtracker.viewresolver.ContentReader;
import com.dirtracker.viewresolver.ContentViewResolver;

public class DirectoryService {

	private Directory directory;
	
	public void scheduleRepeatableCheckForConfiguredDirectory(long timeInterval, long delay)  {
		String dirPath = "src/main/resources/configured_directory";
		
		try {
			directory = setConfiguredDirectoryPath(Paths.get(dirPath));
			directory.setContentView(new ConsoleView());
			createTask(new PeriodicallyReadNewConfigFileInDirectory(directory), timeInterval, delay);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Directory setConfiguredDirectoryPath(Path dirPath) throws NoSuchFileException {
		return directory = new ConfiguredDirectory(dirPath);
	}
	
	private ContentViewResolver setContentViewResolver(ContentReader reader) {
		ContentViewResolver consoleView = new ConsoleView();
		consoleView.setContentReader(reader);
		return consoleView;
	}
	
	private void createTask(Task scheduledTask, long timeInterval, long delay) {
		scheduledTask.scheduleTask(timeInterval, delay);
	}
	
	
}
