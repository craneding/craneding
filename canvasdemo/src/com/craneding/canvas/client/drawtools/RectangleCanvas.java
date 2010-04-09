/**
 * 
 */
package com.craneding.canvas.client.drawtools;

import com.craneding.canvas.client.CanvasContext;

/**
 * 矩形
 * 
 * @author 丁丁
 * 
 */
public class RectangleCanvas {
	private int x;
	private int y;
	private int w;
	private int h;
	
	private final CanvasContext ctx;

	public RectangleCanvas(CanvasContext ctx) {
		this.ctx = ctx;
	}
	
	public RectangleCanvas init(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public RectangleCanvas Color(String color) {
		ctx.fillStyle(color);
		return this;
	}
	
	public RectangleCanvas draw(int x_e, int y_e) {
		ctx.clearRect(x, y, w, h);
		
		w = x_e - x;
		h = y_e - y;
		
		ctx.fillRect(x, y, w, h);
		
		return this;
	}

	public RectangleCanvas clear() {
		x = 0;
		y = 0;
		w = 0;
		h = 0;
		return this;
	}
}
