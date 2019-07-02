package com.dirtracker.task_scheduler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An abstract class that is a sub-class of TimerTask and implements Task 
 * interface it can be extended by classes for creating task scheduler objects.
 * @author Ihechukwudere Okoroego
 */
public abstract class RepeatableTask extends TimerTask implements Task {

	protected long timeInterval;
	protected long delay;
	protected Timer timer;
	
	@Override
	public void cancelTask() {
		System.out.println("Task is cancelled");
	}

	@Override
	public void scheduleTask(long timeInterval, long delay) {
		this.timeInterval = timeInterval;
		this.delay = delay;
		timer = new Timer();
		timer.schedule(this, delay, timeInterval);
	}
	
	public long getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(long timeInterval) {
		this.timeInterval = timeInterval;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}
	

}
