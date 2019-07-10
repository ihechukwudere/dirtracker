package com.dirtracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import com.dirtracker.service.DirectoryService;
import com.dirtracker.service.TaskSchedulerService;


@SpringBootApplication
@ComponentScan(basePackages= {"com.dirtracker"})
public class DirectoryTrackerApplication {
	
	@Autowired
	private DirectoryService dirService;
	
	public static void main(String[] args) throws Exception {
		ApplicationContext context =
				SpringApplication.run(DirectoryTrackerApplication.class, args);
		DirectoryTrackerApplication appRunner = new DirectoryTrackerApplication();
		context.getAutowireCapableBeanFactory().autowireBean(appRunner);
		TaskSchedulerService taskSchedulerService =
				appRunner.dirService.scheduleRepeatableCheckForConfiguredDirectory(3000, 0);
		taskSchedulerService.start();
		
		/* To schedule a task that periodically checks 
		 * a directory and read new xml configuration files
		 * 
		 * 1. Create a directory.
		 * 2. Set the view type for displaying the file content(root entity tag) and property (file name).
		 * 3. Set the type of reader (in this case an XML reader)
		 * 4. 
		 */
	}

}
