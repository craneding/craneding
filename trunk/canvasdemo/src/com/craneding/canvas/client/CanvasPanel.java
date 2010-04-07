/**
 * 
 */
package com.craneding.canvas.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author ����
 * 
 */
public class CanvasPanel extends Widget {
	
	public CanvasPanel() {
		setElement(Document.get().createElement("canvas"));
		
		sinkEvents(Event.MOUSEEVENTS);
	}
	
	public void addMouseDownHandler(MouseDownHandler downHandler){
		addDomHandler(downHandler, MouseDownEvent.getType());
	}
	
	public void addMouseUpHandler(MouseUpHandler upHandler){
		addDomHandler(upHandler, MouseUpEvent.getType());
	}
	
	public void addMouseOverHandler(MouseOverHandler overHandler){
		addDomHandler(overHandler, MouseOverEvent.getType());
	}
	
	public void addMouseOverHandler(MouseOutHandler outHandler){
		addDomHandler(outHandler, MouseOutEvent.getType());
	}
	
	public void addMouseMoveHandler(MouseMoveHandler moveHandler){
		addDomHandler(moveHandler, MouseMoveEvent.getType());
	}
	
	public Canvas getCanvas() {
		return (Canvas) getElement();
	}
	
	public CanvasContext getContext2D() {
		return getCanvas().getContext("2d");
	}
	
	@Override
	public void setWidth(String width) {
		DOM.setElementAttribute(getElement(), "width", width);
	}
	
	@Override
	public void setHeight(String height) {
		DOM.setElementAttribute(getElement(), "height", height);
	}
}
