package com.dirtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dirtracker.service.ScheduleTaskService;

@SpringBootApplication
public class DirectoryTrackerApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DirectoryTrackerApplication.class, args);
	
		ScheduleTaskService confDirTask = new ScheduleTaskService();
		confDirTask.scheduleRepeatableCheckForConfiguredDirectory(3000, 0);
		
		/* To schedule a task that periodically checks 
		 * a directory and read new xml configuration files
		 * 
		 * 1. Create a directory.
		 * 2. Set the view type for displaying the file content(root entity tag) and property (file name).
		 * 3. Set a reader (in this case an XML reader)
		 * 4. 
		 */
	}

}
