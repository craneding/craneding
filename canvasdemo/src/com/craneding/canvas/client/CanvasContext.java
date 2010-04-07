/**
 * 
 */
package com.craneding.canvas.client;

import com.google.gwt.user.client.Element;

/**
 * @author ����
 * 
 */
public class CanvasContext extends Element {

	protected CanvasContext() {
	}

	public final native CanvasContext fillStyle(String clor) /*-{
		this.fillStyle = clor;
		return this;
	}-*/;

	/**
	 * Draws a filled rectangle
	 */
	public final native CanvasContext fillRect(int x, int y, int w, int h) /*-{
		this.fillRect(x, y, w, h);
		return this;
	}-*/;

	/**
	 * Draws a rectangular outline
	 */
	public final native CanvasContext strokeRect(int x, int y, int w, int h) /*-{
		this.strokeRect(x, y, w, h);
		return this;
	}-*/;

	/**
	 * ����һ��·��
	 */
	public final native CanvasContext beginPath() /*-{
		this.beginPath();
		return this;
	}-*/;
	
	/**
	 * �ر�·��
	 */
	public final native CanvasContext closePath() /*-{
		this.closePath();
		return this;
	}-*/;

	public final native CanvasContext moveTo(int x, int y) /*-{
		this.moveTo(x, y);
		return this;
	}-*/;

	/**
	 * ���Ƹ������� Lines
	 */
	public final native CanvasContext lineTo(int x, int y) /*-{
		this.lineTo(x, y);
		return this;
	}-*/;

	/**
	 * ����һ��ʵ��ͼ��
	 * ע�⣺������ fill ʱ�����ŵ�·�����Զ��պϣ���������� closePath 
	 */
	public final native CanvasContext fill() /*-{
		this.fill();
		return this;
	}-*/;

	public final native CanvasContext drawImage(final CanvasImage ci) /*-{
		var _ctx = this;
		var img = new Image();
		img.src = ci.@com.craneding.canvas.client.CanvasImage::getSrc()();
		img.onload = function() { 
			_ctx.drawImage(img, ci.@com.craneding.canvas.client.CanvasImage::getX()(), ci.@com.craneding.canvas.client.CanvasImage::getY()());
			ci.@com.craneding.canvas.client.CanvasImage::onSuccess()(); 
			}
		return this;
	}-*/;
	
	/**
	 * ����
	 */
	public final native CanvasContext drawImage(final CanvasImage ci, int width, int height) /*-{
		var _ctx = this;
		var img = new Image();
		img.src = ci.@com.craneding.canvas.client.CanvasImage::getSrc()();
		img.onload = function() { 
			_ctx.drawImage(img, ci.@com.craneding.canvas.client.CanvasImage::getX()(), ci.@com.craneding.canvas.client.CanvasImage::getY()(), width, height);
			ci.@com.craneding.canvas.client.CanvasImage::onSuccess()(); 
			}
		return this;
	}-*/;
	
	/**
	 * ����ͼ�εı߿�
	 */
	public final native CanvasContext stroke() /*-{
		this.stroke();
		return this;
	}-*/;

	/**
	 * Clears the specified area and makes it fully transparent
	 */
	public final native CanvasContext clearRect(int x, int y, int w, int h) /*-{
		this.clearRect(x, y, w, h);
		return this;
	}-*/;

	/**
	 * ���ƻ��߻�Բ
	 * @param x Բ������
	 * @param y Բ������
	 * @param radius �ǰ뾶
	 * @param startAngle �𻡶ȣ��� x ��Ϊ��׼��
	 * @param endAngle ĩ���ȣ��� x ��Ϊ��׼��
	 * @param anticlockwise true ��ʾ��ʱ�룬��֮˳ʱ��
	 */
	public final native CanvasContext arc(int x, int y, int radius, int startAngle, double endAngle, boolean anticlockwise) /*-{
		this.arc(x, y, radius, startAngle, endAngle, anticlockwise);
		return this;
	}-*/;
	
	/**
	 * ����������
	 * @param cp1x ��һ�����Ƶ������
	 * @param cp1y ��һ�����Ƶ������
	 * @param x �յ�����
	 * @param y �յ�����
	 */
	public final native CanvasContext quadraticCurveTo(int cp1x, int cp1y, int x, int y) /*-{
		this.quadraticCurveTo(cp1x, cp1y, x, y);
		return this;
	}-*/;
	
	/**
	 * ����������
	 * @param cp1x ��һ�����Ƶ������
	 * @param cp1y ��һ�����Ƶ������
	 * @param cp2x �ڶ������Ƶ������
	 * @param cp2y �ڶ������Ƶ������
	 * @param x �յ�����
	 * @param y �յ�����
	 */
	public final native CanvasContext bezierCurveTo(int cp1x, int cp1y, int cp2x, int cp2y, int x, int y) /*-{
		this.bezierCurveTo(cp1x, cp1y, cp2x, cp2y, x, y);
		return this;
	}-*/;

	/**
	 * ����·��
	 * @param x ���Ͻ�����
	 * @param y ���Ͻ�����
	 * @param width ��
	 * @param height ��
	 */
	public final native CanvasContext rect(int x, int y, int width, int height) /*-{
		this.rect(x, y, width, height);
		return this;
	}-*/;
}
