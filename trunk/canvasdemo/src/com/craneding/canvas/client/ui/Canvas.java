/**
 * 
 */
package com.craneding.canvas.client.ui;

import com.google.gwt.user.client.Element;

/**
 * @author 丁丁
 * 
 */
public class Canvas extends Element {

	protected Canvas() {
	}

	public final native CanvasContext getContext(String text) /*-{
		return this.getContext(text);
	}-*/;
	
}
