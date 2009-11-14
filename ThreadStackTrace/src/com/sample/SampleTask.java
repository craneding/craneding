/**
 * 
 */
package com.sample;

/**
 * @author crane.ding
 * 
 */
public class SampleTask {
	/**
	 * 需要取样的线程
	 */
	private Thread sampleThread;

	/**
	 * 需要开始取样的时间
	 */
	private long sampleStart;

	public Thread getSampleThread() {
		return sampleThread;
	}

	public void setSampleThread(Thread sampleThread) {
		this.sampleThread = sampleThread;
	}

	public long getSampleStart() {
		return sampleStart;
	}

	public void setSampleStart(long sampleStart) {
		this.sampleStart = sampleStart;
	}
}
