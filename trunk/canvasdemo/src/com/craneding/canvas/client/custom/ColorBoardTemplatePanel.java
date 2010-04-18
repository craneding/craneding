/**
 * 
 */
package com.craneding.canvas.client.custom;

import com.craneding.canvas.client.template.HTMLTemplatePanel;
import com.craneding.canvas.client.ui.CanvasContext;
import com.craneding.canvas.client.ui.CanvasPanel;
import com.craneding.canvas.client.ui.CanvasSystem;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * 调色板模板界面
 * 
 * @author 丁丁
 * 
 */
public class ColorBoardTemplatePanel extends HTMLTemplatePanel implements
		MouseDownHandler, MouseUpHandler, MouseOutHandler, MouseMoveHandler {
	CanvasPanel redCanvas;
	CanvasPanel greenCanvas;
	CanvasPanel blueCanvas;

	CanvasContext redctx;
	CanvasContext greenctx;
	CanvasContext bluectx;

	HTML redNum;
	HTML greenNum;
	HTML blueNum;
	
	HTML redIcon;
	HTML greenIcon;
	HTML blueIcon;

	RGBA rgba = new RGBA();
	
	int w = 226, h = 18, max = 256;

	boolean down = false;
	
	{
		rgba.r = 255;
		rgba.g = 128;
		rgba.b = 128;
		rgba.alpha = 100;
	}

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

		redNum = (HTML) getAutoWidget("${red-num}");
		greenNum = (HTML) getAutoWidget("${green-num}");
		blueNum = (HTML) getAutoWidget("${blue-num}");
		
		redIcon = (HTML) getAutoWidget("${red-icon}");
		greenIcon = (HTML) getAutoWidget("${green-icon}");
		blueIcon = (HTML) getAutoWidget("${blue-icon}");

		redNum.setText(rgba.r + "");
		greenNum.setText(rgba.g + "");
		blueNum.setText(rgba.b + "");

		redctx = redCanvas.getContext2D();
		greenctx = greenCanvas.getContext2D();
		bluectx = blueCanvas.getContext2D();

		draw(redctx, rgba.copy(), w, h);
		draw(greenctx, rgba.copy(), w, h);
		draw(bluectx, rgba.copy(), w, h);
		
		redCanvas.addMouseDownHandler(this);
		redCanvas.addMouseMoveHandler(this);
		redCanvas.addMouseOutHandler(this);
		redCanvas.addMouseUpHandler(this);
		greenCanvas.addMouseDownHandler(this);
		greenCanvas.addMouseMoveHandler(this);
		greenCanvas.addMouseOutHandler(this);
		greenCanvas.addMouseUpHandler(this);
		blueCanvas.addMouseDownHandler(this);
		blueCanvas.addMouseMoveHandler(this);
		blueCanvas.addMouseOutHandler(this);
		blueCanvas.addMouseUpHandler(this);
	}

	void draw(CanvasContext ctx, RGBA rgba, int w, int h) {
		ctx.clearRect(0, 0, w, h);
		
		for (int i = max; i >= 0; i = i - 2) {
			if (ctx == redctx)
				rgba.r = i;
			else if (ctx == greenctx)
				rgba.g = i;
			if (ctx == bluectx)
				rgba.b = i;

			ctx.beginPath();
			ctx.fillStyle("rgba(" + rgba.r + "," + rgba.g + "," + rgba.b + "," + rgba.alpha + ")");
			ctx.fillRect(max - i, 0, 2, h);
			ctx.closePath();
		}
	}

	public RGBA rgba() {
		return rgba;
	}

	public static class RGBA {
		int r;
		int g;
		int b;
		int alpha;/* 0-100 */

		public RGBA copy() {
			RGBA copy = new RGBA();
			copy.r = this.r;
			copy.g = this.g;
			copy.b = this.b;
			copy.alpha = this.alpha;
			return copy;
		}
		
		@Override
		public String toString() {
			return "rgba(" + r + "," + g + "," + b + "," + alpha + ")";
		}
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		down = true;
		
		Object source = event.getSource();
		
		int x1 = event.getRelativeX(((Widget)source).getElement());
		int x2 = max - x1 * 2;
		
		if(source == redCanvas) {
			selectRed(x1, x2);
		} else if(source == greenCanvas) {
			selectGreen(x1, x2);
		} else if(source == blueCanvas) {
			selectBlue(x1, x2);
		}
		
		CanvasSystem.setProperty("rgba", rgba.toString());
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		Object source = event.getSource();
		if(source == redCanvas || source == greenCanvas || source == blueCanvas)
			down = false;
	}

	@Override
	public void onMouseOut(MouseOutEvent event) {
		Object source = event.getSource();
		if(source == redCanvas || source == greenCanvas || source == blueCanvas)
			down = false;
	}

	@Override
	public void onMouseMove(MouseMoveEvent event) {
		if(down) {
			Object source = event.getSource();
			int x1 = event.getRelativeX(((Widget)source).getElement());
			int x2 = max - x1 * 2;
			
			if(source == redCanvas) {
				selectRed(x1, x2);
			} else if(source == greenCanvas) {
				selectGreen(x1, x2);
			} else if(source == blueCanvas) {
				selectBlue(x1, x2);
			}
			
			CanvasSystem.setProperty("rgba", rgba.toString());
		}
	}

	private void selectBlue(int x1, int x2) {
		DOM.setStyleAttribute(blueIcon.getElement(), "left", x1 + 16 + "px");
		blueNum.setText(x2 + "");
		rgba.b = x2;
		draw(redCanvas.getContext2D(), rgba.copy(), w, h);
		draw(greenCanvas.getContext2D(), rgba.copy(), w, h);
	}

	private void selectGreen(int x1, int x2) {
		DOM.setStyleAttribute(greenIcon.getElement(), "left", x1 + 16 + "px");
		greenNum.setText(x2 + "");
		rgba.g = x2;
		draw(redCanvas.getContext2D(), rgba.copy(), w, h);
		draw(blueCanvas.getContext2D(), rgba.copy(), w, h);
	}

	private void selectRed(int x1, int x2) {
		DOM.setStyleAttribute(redIcon.getElement(), "left", x1 + 16 + "px");
		redNum.setText(x2 + "");
		rgba.r = x2;
		draw(greenCanvas.getContext2D(), rgba.copy(), w, h);
		draw(blueCanvas.getContext2D(), rgba.copy(), w, h);
	}
	
	
}
