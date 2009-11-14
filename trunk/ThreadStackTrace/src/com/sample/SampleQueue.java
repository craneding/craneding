/**
 * 
 */
package com.sample;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author crane.ding
 * 
 */
public class SampleQueue extends Thread {

	private static final List<SampleTask> TASKS = new ArrayList<SampleTask>();

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (TASKS.isEmpty())
				return;
			for (SampleTask sampleTask : TASKS) {
				if (sampleTask.getSampleStart() < System.currentTimeMillis()){
					Exception exception = new Exception();
					exception.setStackTrace(sampleTask.getSampleThread().getStackTrace());
					exception.printStackTrace();
				}
			}
		}
	}
	
	public void printStackTrace(StackTraceElement[] traceElements) {
		final PrintStream s = System.err;
		for (StackTraceElement traceElement : traceElements) {
			s.println("\tat " + traceElement.getLineNumber());
		}
    }

	public void addTask(SampleTask sampleTask){
		TASKS.add(sampleTask);
	}
}
