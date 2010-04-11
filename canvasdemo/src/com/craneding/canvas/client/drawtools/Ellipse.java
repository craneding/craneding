package com.craneding.canvas.client.drawtools;

/**
 * @author 丁丁
 * 
 */
public class Ellipse extends CanvasTools {
	private final double K = 4 * ((Math.sqrt(2) - 1) / 3);

	public Ellipse(CanvasApp app) {
		super(app);
	}

	public Ellipse draw(int x1, int y1, int x2, int y2) {
		clear();

		int minX = Math.min(x1, x2);
		int minY = Math.min(y1, y2);
		int maxX = Math.max(x1, x2);
		int maxY = Math.max(y1, y2);

		int w = Math.abs(x2 - x1);
		int h = Math.abs(y2 - y1);

		if (app.ctrl) {
			if (w > h) {
				maxY = minY + w;
				if (minY == x2) {
					minY -= w - h;
					maxY -= w - h;
				}
				h = w;
			} else {
				maxX = minX + h;
				if (minX == x2) {
					minX -= h - w;
					maxX -= h - w;
				}
				w = h;
			}
		}

		// 圆心坐标
		int w2 = w / 2;
		int h2 = h / 2;

		int ox = minX + w2;
		int oy = minY + h2;

		w2 = (int) (w2 * K);
		h2 = (int) (h2 * K);

		app.ctx.beginPath();
		app.ctx.moveTo(ox, minY);// 起始点

		app.ctx.bezierCurveTo(ox + w2, minY, maxX, oy - h2, maxX, oy);
		app.ctx.bezierCurveTo(maxX, oy + h2, ox + w2, maxY, ox, maxY);
		app.ctx.bezierCurveTo(ox - w2, maxY, minX, oy + h2, minX, oy);
		app.ctx.bezierCurveTo(minX, oy - h2, ox - w2, minY, ox, minY);

		app.ctx.stroke();

		return this;
	}

}
