/**
 * 
 */
package com.craneding.canvas.client.drawtools;

import com.craneding.canvas.client.CanvasContext;

/**
 * 画笔
 * 
 * @author 丁丁
 */
public class BrushCanvas {

	private final CanvasContext ctx;

	public BrushCanvas(CanvasContext ctx) {
		this.ctx = ctx;
	}

	public BrushCanvas LineWidth(int lineWidth) {
		ctx.lineWidth(lineWidth);
		return this;
	}

	public BrushCanvas Color(String color) {
		ctx.strokeStyle(color);
		return this;
	}

	public BrushCanvas draw(int x_s, int y_s, int x_e, int y_e) {
		ctx.beginPath();
		ctx.moveTo(x_s, y_s);
		ctx.lineTo(x_e, y_e);
		ctx.stroke();
		return this;
	}
}
