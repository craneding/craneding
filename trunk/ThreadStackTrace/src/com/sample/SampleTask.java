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
	 * ��Ҫȡ�����߳�
	 */
	private Thread sampleThread;

	/**
	 * ��Ҫ��ʼȡ����ʱ��
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
