package com.dirtracker.task_scheduler;

/**
 * An interface that can be implemented by classes 
 * and interfaces for creating task scheduler objects.
 * @author Ihechukwudere Okoroego
 *
 */
public interface Task {
	
	/**
	 * Schedule a repeatable or one-time task
	 * @param timeInterval
	 * @param delay
	 */
	public void scheduleTask(long timeInterval, long delay);
	public void cancelTask();
}
