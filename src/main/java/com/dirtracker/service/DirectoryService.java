package com.dirtracker.service;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dirtracker.domain.ConfiguredDirectory;
import com.dirtracker.domain.Directory;
import com.dirtracker.task_scheduler.PeriodicallyReadNewConfigFileInDirectory;
import com.dirtracker.task_scheduler.Task;
import com.dirtracker.viewresolver.ConsoleView;
import com.dirtracker.viewresolver.ContentViewResolver;
import com.dirtracker.viewresolver.FileContentReader;
import com.dirtracker.viewresolver.xml.StAXPullParser;
import com.dirtracker.viewresolver.xml.XMLContentReader;

@Service
public class DirectoryService {

	private Directory directory;
	
	@Autowired
	private TaskSchedulerService taskScheduler;
	
	public void scheduleRepeatableCheckForConfiguredDirectory(long timeInterval, long delay)  {
		String dirPath = "src/main/resources/configured_directory";
		try {
			directory = new ConfiguredDirectory(Paths.get(dirPath));
			ContentViewResolver contentView  = new ConsoleView();
			FileContentReader xmlReader = new XMLContentReader();
			xmlReader = new StAXPullParser(xmlReader);
			contentView.setContentReader(xmlReader);
			directory.setContentView(contentView);
			Task task = new PeriodicallyReadNewConfigFileInDirectory(directory);
			taskScheduler.createTask(task, timeInterval, delay);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
