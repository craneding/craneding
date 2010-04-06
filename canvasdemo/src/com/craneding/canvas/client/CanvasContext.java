/**
 * 
 */
package com.craneding.canvas.client;

import com.google.gwt.user.client.Element;

/**
 * @author ¶¡¶¡
 * 
 */
public class CanvasContext extends Element {

	protected CanvasContext() {
	}

	public final native CanvasContext fillStyle(String clor) /*-{
		this.fillStyle = clor;
		return this;
	}-*/;

	public final native CanvasContext fillRect(int x, int y, int w, int h) /*-{
		this.fillRect(x, y, w, h);
		return this;
	}-*/;

	public final native CanvasContext strokeRect(int x, int y, int w, int h) /*-{
		this.strokeRect(x, y, w, h);
		return this;
	}-*/;

	public final native CanvasContext beginPath() /*-{
		this.beginPath();
		return this;
	}-*/;

	public final native CanvasContext moveTo(int x, int y) /*-{
		this.moveTo(x, y);
		return this;
	}-*/;

	public final native CanvasContext lineTo(int x, int y) /*-{
		this.lineTo(x, y);
		return this;
	}-*/;

	public final native CanvasContext fill() /*-{
		this.fill();
		return this;
	}-*/;

	public final native CanvasContext drawImage(String src, int x, int y,
			final CanvasContextCallback callback) /*-{
		var _ctx = this;
		var img = new Image();
		img.src = src;
		img.onload = function() { 
			_ctx.drawImage(img, x, y);
			callback.@com.craneding.canvas.client.CanvasContextCallback::onSuccess()(); 
			}

		return this;
	}-*/;

	public final native CanvasContext stroke() /*-{
		this.stroke();
		return this;
	}-*/;

	public final native CanvasContext clearRect(int x, int y, int w, int h) /*-{
		this.clearRect(x, y, w, h);
		return this;
	}-*/;
}
