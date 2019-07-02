package com.dirtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dirtracker.service.ScheduleTaskService;

@SpringBootApplication
public class DirectoryTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DirectoryTrackerApplication.class, args);
		
		ScheduleTaskService confDirTask = new ScheduleTaskService();
		confDirTask.scheduleRepeatableCheckForConfiguredDirectory(3000, 0);
	}

}
