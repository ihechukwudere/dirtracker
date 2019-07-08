package com.dirtracker.service;

import org.springframework.stereotype.Service;

import com.dirtracker.task_scheduler.Task;

@Service
public class TaskSchedulerService {

	public void createTask(Task scheduledTask, long timeInterval, long delay) {
		scheduledTask.scheduleTask(timeInterval, delay);
	}
}
