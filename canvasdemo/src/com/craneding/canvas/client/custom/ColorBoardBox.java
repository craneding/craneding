/**
 * 
 */
package com.craneding.canvas.client.custom;

import com.craneding.canvas.client.ui.CanvasContext;
import com.craneding.canvas.client.ui.CanvasPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

/**
 * 调色板
 * 
 * @author 丁丁
 */
public class ColorBoardBox extends DialogBox {
	final FlowPanel flowPanel = new FlowPanel();
	final CanvasPanel redCanvas = new CanvasPanel();
	final CanvasPanel greenCanvas = new CanvasPanel();
	final CanvasPanel blueCanvas = new CanvasPanel();

	final HTML redMove = new HTML();
	final HTML greenMove = new HTML();
	final HTML blueMove = new HTML();

	final String width = "226px";
	final int height = 18;

	public ColorBoardBox() {
		super(false, false);

		int r = 255, g = 128, b = 128, a = 1;

		flowPanel.add(redCanvas);
		flowPanel.add(greenCanvas);
		flowPanel.add(blueCanvas);
		
		/*
		flowPanel.add(redMove);
		flowPanel.add(greenMove);
		flowPanel.add(blueMove);
		*/
		
		redCanvas.setSize(width, height + "px");
		greenCanvas.setSize(width, height + "px");
		blueCanvas.setSize(width, height + "px");

		redMove.setStyleName("select");
		greenMove.setStyleName("select");
		blueMove.setStyleName("select");
		flowPanel.setStyleName("ColorPalette");

		/*
		DOM.setStyleAttribute(greenMove.getElement(), "top", "18px");
		DOM.setStyleAttribute(greenMove.getElement(), "left", "128px");
		DOM.setStyleAttribute(blueMove.getElement(), "top", "36px");
		DOM.setStyleAttribute(blueMove.getElement(), "left", "128px");
		*/

//		setWidget(flowPanel);
		setWidget(new ColorBoardTemplatePanel());
		setStyleName("ColorPaletteBox");

		draw(redCanvas.getContext2D(), r, g, b, a);
		draw(greenCanvas.getContext2D(), r, g, b, a);
		draw(blueCanvas.getContext2D(), r, g, b, a);
	}

	void draw(CanvasContext ctx, int r, int g, int b, int a) {
		ctx.clearRect(0, 0, getOffsetWidth(), height);

		int max = 255;
		for (int i = max; i >= 0; i--) {
			if (ctx == redCanvas.getContext2D())
				r = i;
			else if (ctx == greenCanvas.getContext2D())
				g = i;
			if (ctx == blueCanvas.getContext2D())
				b = i;

			ctx.beginPath();
			ctx.fillStyle("rgba(" + r + "," + g + "," + b + "," + a + ")");
			ctx.fillRect(max - i, 0, 1, height);
			ctx.closePath();
		}
	}

}
