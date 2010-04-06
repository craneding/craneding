/**
 * 
 */
package com.craneding.canvas.client;

import com.google.gwt.user.client.Element;

/**
 * @author ¶¡¶¡
 * 
 */
public class Canvas extends Element {

	protected Canvas() {
	}

	public final native CanvasContext getContext(String text) /*-{
		return this.getContext(text);
	}-*/;

}
