/**
 * 
 */
package com.craneding.canvas.client.custom;

import com.craneding.canvas.client.template.HTMLTemplatePanel;
import com.craneding.canvas.client.ui.CanvasContext;
import com.craneding.canvas.client.ui.CanvasPanel;

/**
 * 调色板模板界面
 * 
 * @author 丁丁
 * 
 */
public class ColorBoardTemplatePanel extends HTMLTemplatePanel {
	CanvasPanel redCanvas;
	CanvasPanel greenCanvas;
	CanvasPanel blueCanvas;

	CanvasContext redctx;
	CanvasContext greenctx;
	CanvasContext bluectx;

	public ColorBoardTemplatePanel() {
		super("../template/colorBoardTemplate.html");
	}

	@Override
	protected void onBefore() {
	}

	@Override
	protected void onAfter() {
		redCanvas = (CanvasPanel) getAutoWidget("${red}");
		greenCanvas = (CanvasPanel) getAutoWidget("${green}");
		blueCanvas = (CanvasPanel) getAutoWidget("${blue}");

		redctx = redCanvas.getContext2D();
		greenctx = greenCanvas.getContext2D();
		bluectx = blueCanvas.getContext2D();

		int dr = 255, dg = 128, db = 128, da = 1, w = 226, h = 18;

		draw(redctx, dr, dg, db, da, w, h);
		draw(greenctx, dr, dg, db, da, w, h);
		draw(bluectx, dr, dg, db, da, w, h);
	}

	void draw(CanvasContext ctx, int r, int g, int b, int a, int w, int h) {
		ctx.clearRect(0, 0, w, h);

		int max = 255;
		for (int i = max; i >= 0; i = i - 2) {
			if (ctx == redctx)
				r = i;
			else if (ctx == greenctx)
				g = i;
			if (ctx == bluectx)
				b = i;

			ctx.beginPath();
			ctx.fillStyle("rgba(" + r + "," + g + "," + b + "," + a + ")");
			ctx.fillRect(max - i, 0, 2, h);
			ctx.closePath();
		}
	}

}
