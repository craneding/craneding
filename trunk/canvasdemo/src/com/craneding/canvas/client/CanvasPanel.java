/**
 * 
 */
package com.craneding.canvas.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author ¶¡¶¡
 * 
 */
public class CanvasPanel extends Widget {

	public CanvasPanel() {
		setElement(Document.get().createElement("canvas"));
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
