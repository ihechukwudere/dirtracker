package com.dirtracker.service;

import org.springframework.stereotype.Service;

import com.dirtracker.task_scheduler.Task;

@Service
public class TaskSchedulerService {
	
	private Task task;
	
	/**
	 * Creates a task but would not start the task until the start method is run.
	 * @param task
	 * @param timeInterval
	 * @param delay
	 */
	public void createTask(Task task, long timeInterval, long delay) {
		setTask(task);
		task.scheduleTask(timeInterval, delay);
	}
	
	public Task start() {
		return task.start();
	}
	/*
	 * It possible to add a function that stops or cancels the tasks
	 * in the instances of this class
	 */

	
	public Task getTask() {
		return task;
	}

	protected void setTask(Task task) {
		this.task = task;
	}
}
