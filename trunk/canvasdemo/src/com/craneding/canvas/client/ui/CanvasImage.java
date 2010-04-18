/**
 * 
 */
package com.craneding.canvas.client.ui;

/**
 * @author 丁丁
 * 
 */
public abstract class CanvasImage {

	private final String src;
	private final int x;
	private final int y;

	public CanvasImage(String src, int x, int y) {
		this.src = src;
		this.x = x;
		this.y = y;
	}

	abstract void onSuccess();

	public String getSrc() {
		return src;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
