/**
 * 
 */
package com.craneding.canvas.client.tools;


/**
 * 画笔
 * 
 * @author 丁丁
 */
public class Pencil extends CanvasTools {

	private int x1;
	private int y1;

	public Pencil(CanvasApp app) {
		super(app);
	}
	
	public Pencil moveTo(int x1, int y1) {
		this.x1 = x1;
		this.y1 = y1;
		return this;
	}

	public Pencil draw(int x2, int y2) {
		app.ctx.strokeStyle(app.rgba);
		app.ctx.beginPath();
		app.ctx.moveTo(x1, y1);
		app.ctx.lineTo(x1 = x2, y1 = y2);
		app.ctx.stroke();
		return this;
	}
}
