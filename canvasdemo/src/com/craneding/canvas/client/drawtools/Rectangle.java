/**
 * 
 */
package com.craneding.canvas.client.drawtools;

/**
 * 矩形
 * 
 * @author 丁丁
 * 
 */
public class Rectangle extends CanvasTools {

	public Rectangle(CanvasApp app) {
		super(app);
	}

	public Rectangle draw(int x1, int y1, int x2, int y2) {
		app.ctx.clearRect(0, 0, app.width, app.height);
		app.ctx.beginPath();
		app.ctx.strokeRect(x1, y1, x2 - x1, y2 - y1);
		app.ctx.closePath();
		return this;
	}

}
