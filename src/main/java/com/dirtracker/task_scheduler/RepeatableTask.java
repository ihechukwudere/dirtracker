package com.dirtracker.task_scheduler;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

/**
 * An abstract class that is a sub-class of TimerTask and implements Task 
 * interface it can be extended by classes for creating task scheduler objects.
 * @author Ihechukwudere Okoroego
 */
public abstract class RepeatableTask extends TimerTask implements Task {

	protected BigDecimal timeInterval;
	protected BigDecimal delay;
	protected Timer timer;
	
	@Override
	public boolean cancel() {
		System.out.println("Task is cancelled");
		return false;
	}

	@Override
	public void scheduleTask(long timeInterval, long delay) throws NumberFormatException{
		this.timeInterval = new BigDecimal(timeInterval);
		this.delay = new BigDecimal(delay);
		timer = new Timer();
	}

	@Override
	public BigDecimal getTimeInterval() {
		return timeInterval;
	}

	@Override
	public void setTimeInterval(BigDecimal timeInterval) {
		this.timeInterval = timeInterval;
	}

	@Override
	public BigDecimal getDelay() {
		return delay;
	}

	@Override
	public void setDelay(BigDecimal delay) {
		this.delay = delay;
	}

	@Override
	public RepeatableTask start() {
		timer.schedule(this, this.delay.longValue(), this.timeInterval.longValue());
		return this;
	}

	@Override
	public void stop() {
		timer.cancel();
	}
}
