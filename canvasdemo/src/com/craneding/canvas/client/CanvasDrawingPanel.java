/**
 * 
 */
package com.craneding.canvas.client;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.DialogBox;

/**
 * @author 丁丁
 * 
 */
public class CanvasDrawingPanel extends DialogBox implements MouseDownHandler,
		MouseMoveHandler, MouseUpHandler, MouseOutHandler {

	final CanvasPanel canvasPanel = new CanvasPanel();
	final CanvasContext ctx = canvasPanel.getContext2D();
	boolean down = false;
	int x_s = 0;
	int y_s = 0;

	public CanvasDrawingPanel() {
		setWidget(canvasPanel);
		setStyleName("Canvas-DrawingPanel");

		canvasPanel.addMouseDownHandler(this);
		canvasPanel.addMouseMoveHandler(this);
		canvasPanel.addMouseUpHandler(this);
		canvasPanel.addMouseOutHandler(this);
	}
	
	@Override
	public void show() {
		super.show();
		drawHell();
	}
	
	void drawHell() {
		ctx.beginPath();
		ctx.arc(75, 75, 50, 0, (Math.PI * 2), true); // Outer circle
		ctx.moveTo(110, 75);
		ctx.arc(75, 75, 35, 0, Math.PI, false); // Mouth (clockwise)
		ctx.moveTo(65, 65);
		ctx.arc(60, 65, 5, 0, (Math.PI * 2), true); // Left eye
		ctx.moveTo(95, 65);
		ctx.arc(90, 65, 5, 0, (Math.PI * 2), true); // Right eye
		ctx.stroke();
					
		ctx.beginPath();
		ctx.moveTo(40, 75);
		ctx.lineTo(60, 65);
		ctx.lineTo(90, 65);
		ctx.moveTo(110, 75);
		ctx.lineTo(125, 75);
		ctx.stroke();
	}

	@Override
	public void setSize(String width, String height) {
		canvasPanel.setSize(width, height);
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		if (event.getSource() == canvasPanel) {
			down = true;
			x_s = event.getRelativeX(canvasPanel.getElement());
			y_s = event.getRelativeY(canvasPanel.getElement());
		}
	}

	@Override
	public void onMouseMove(MouseMoveEvent event) {
		if (event.getSource() == canvasPanel && down) {

			ctx.strokeStyle("black");
			ctx.lineWidth(10);
			ctx.beginPath();
			ctx.moveTo(x_s, y_s);
			x_s = event.getRelativeX(canvasPanel.getElement());
			y_s = event.getRelativeY(canvasPanel.getElement());
			ctx.lineTo(x_s, y_s);
			ctx.stroke();
		}
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		if(event.getSource() == canvasPanel) {
			down = false;
		}
	}

	@Override
	public void onMouseOut(MouseOutEvent event) {
		if(event.getSource() == canvasPanel) {
			down = false;
		}
	}

}
