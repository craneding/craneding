/**
 * 
 */
package com.sample;

/**
 * @author crane.ding
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SampleQueue queue = new SampleQueue();
		queue.start();
		
		Thread thread = new Thread(){
			public void run() {
				int i = 0;
				while(true){
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(i++);
				}
			};
		};
		thread.start();
		
		final SampleTask sampleTask = new SampleTask();
		sampleTask.setSampleStart(System.currentTimeMillis() + 30*1000L);
		sampleTask.setSampleThread(thread);
		
		queue.addTask(sampleTask);
	}

}
