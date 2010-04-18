/**
 * 
 */
package com.craneding.canvas.client;

import com.craneding.canvas.client.tools.Ellipse;
import com.craneding.canvas.client.tools.Pencil;
import com.craneding.canvas.client.tools.Rectangle;
import com.craneding.canvas.client.tools.CanvasTools.CanvasApp;
import com.craneding.canvas.client.ui.CanvasContext;
import com.craneding.canvas.client.ui.CanvasPanel;
import com.craneding.canvas.client.ui.CanvasSystem;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author 丁丁
 * 
 */
public class CanvasDrawingPanel extends DialogBox implements MouseDownHandler,
		MouseMoveHandler, MouseUpHandler, MouseOutHandler, KeyUpHandler, KeyDownHandler {
	final HTMLPanel htmlPanel = new HTMLPanel("<div id='canvasroot' onkeydown=\"javascript:alert('ffff')\"></div>");
	final CanvasPanel canvasBox = new CanvasPanel();
	final CanvasPanel canvasTmp = new CanvasPanel();

	{
		DOM.setElementAttribute(canvasBox.getElement(), "id", "box");
		DOM.setStyleAttribute(canvasBox.getElement(), "left", "0");
		DOM.setStyleAttribute(canvasBox.getElement(), "top", "20");
		DOM.setStyleAttribute(canvasBox.getElement(), "position", "absolute");

		DOM.setElementAttribute(canvasTmp.getElement(), "id", "tmp");
		DOM.setStyleAttribute(canvasTmp.getElement(), "left", "0");
		DOM.setStyleAttribute(canvasTmp.getElement(), "top", "20");
		DOM.setStyleAttribute(canvasTmp.getElement(), "position", "absolute");
	}

	final CanvasContext ctxBox = canvasBox.getContext2D();
	final CanvasContext ctxTmp = canvasTmp.getContext2D();

	final CanvasApp app = new CanvasApp(ctxTmp, 0, 0);

	final Pencil pencil = new Pencil(app);
	final Ellipse ellipse = new Ellipse(app);
	final Rectangle rectangle = new Rectangle(app);

	int x1 = 0;
	int y1 = 0;
	boolean down = false;

	public CanvasDrawingPanel() {
		super(false, false);
		
		setWidget(htmlPanel);

		htmlPanel.add(canvasBox, "canvasroot");
		htmlPanel.add(canvasTmp, "canvasroot");

		addStyleName("Canvas-DrawingPanel");
		setAnimationEnabled(true);

		canvasTmp.addMouseDownHandler(this);
		canvasTmp.addMouseMoveHandler(this);
		canvasTmp.addMouseUpHandler(this);
		canvasTmp.addMouseOutHandler(this);
		
		addDomHandler(this, KeyUpEvent.getType());
		addDomHandler(this, KeyDownEvent.getType());
		
		addKeyBoardEvent();
	}
	
	@Override
	public void center() {
		super.center();
		app.width = canvasTmp.getOffsetWidth();
		app.height = canvasTmp.getOffsetHeight();
	}

	@Override
	public void setSize(String width, String height) {
		htmlPanel.setSize(width, height);
		canvasBox.setSize(width, height);
		canvasTmp.setSize(width, height);
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		if (event.getSource() == canvasTmp) {
			down = true;
			x1 = event.getRelativeX(canvasTmp.getElement());
			y1 = event.getRelativeY(canvasTmp.getElement());
			
			if("pencil".equalsIgnoreCase(CanvasSystem.getProperty("canvasToolName")))
				pencil.moveTo(x1, y1);
		}
	}

	@Override
	public void onMouseMove(MouseMoveEvent event) {
		if (event.getSource() == canvasTmp && down) {
			int x2 = event.getRelativeX(canvasTmp.getElement());
			int y2 = event.getRelativeY(canvasTmp.getElement());
			
			app.rgba = CanvasSystem.getProperty("rgba");
			
			if("pencil".equalsIgnoreCase(CanvasSystem.getProperty("canvasToolName")))
				pencil.draw(x2, y2);
			else if("rectangle".equalsIgnoreCase(CanvasSystem.getProperty("canvasToolName")))
				rectangle.draw(x1, y1, x2, y2);
			else if("ellipse".equalsIgnoreCase(CanvasSystem.getProperty("canvasToolName")))
				ellipse.draw(x1, y1, x2, y2);
		}
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		if (event.getSource() == canvasTmp && down) {
			down = false;
			ctxBox.drawImage(canvasTmp.getCanvas(), 0, 0);
		}
	}

	@Override
	public void onMouseOut(MouseOutEvent event) {
		if (event.getSource() == canvasTmp && down) {
			down = false;
			ctxTmp.clearRect(0, 0, app.width, app.height);
		}
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		if (app.ctrl)
			app.ctrl = false;
	}

	@Override
	public void onKeyDown(KeyDownEvent event) {
		if (down && event.isControlKeyDown())
			app.ctrl = true;
	}
	
	private native void addKeyBoardEvent() /*-{
		var _self = this;
		_self.@com.craneding.canvas.client.CanvasDrawingPanel::sinkEvents(I)(128 | 256 | 512);
		function keyhandler(evt)
		{
			evt = evt ? evt : window.event;
			_self.@com.craneding.canvas.client.CanvasDrawingPanel::onBrowserEvent(Lcom/google/gwt/user/client/Event;)(evt);
		}
		$doc.onkeydown = keyhandler;
		$doc.onkeyup = keyhandler;
	}-*/;

}
