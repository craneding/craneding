/**
 * 
 */
package com.craneding.canvas.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
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
 * @author 丁丁
 * 
 */
public class CanvasPanel extends Widget {
	
	public CanvasPanel(Element elem) {
		setElement(elem);
		sinkEvents(Event.MOUSEEVENTS | Event.KEYEVENTS);
	}
	
	public CanvasPanel() {
		this(Document.get().createElement("canvas"));
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
	
	public void addMouseOutHandler(MouseOutHandler outHandler){
		addDomHandler(outHandler, MouseOutEvent.getType());
	}
	
	public void addMouseMoveHandler(MouseMoveHandler moveHandler){
		addDomHandler(moveHandler, MouseMoveEvent.getType());
	}
	
	public void addKeyPressHandler(KeyPressHandler keyPressHandler) {
		addDomHandler(keyPressHandler, KeyPressEvent.getType());
	}
	
	public void addKeyUpHandler(KeyUpHandler keyUpHandler) {
		addDomHandler(keyUpHandler, KeyUpEvent.getType());
	}
	
	public void addKeyDownHandler(KeyDownHandler keyDownHandler) {
		addDomHandler(keyDownHandler, KeyDownEvent.getType());
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
