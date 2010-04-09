/**
 * 
 */
package com.craneding.canvas.client;

import com.google.gwt.user.client.Element;

/**
 * @author 丁丁
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
	 * 创建一个路径
	 */
	public final native CanvasContext beginPath() /*-{
		this.beginPath();
		return this;
	}-*/;
	
	/**
	 * 关闭路径
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
	 * 绘制各种线条 Lines
	 */
	public final native CanvasContext lineTo(int x, int y) /*-{
		this.lineTo(x, y);
		return this;
	}-*/;

	/**
	 * 填充出一个实心图形
	 * 注意：当调用 fill 时，开放的路径会自动闭合，而无须调用 closePath 
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
	 * 缩放
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
	 * 绘制图形的边框
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
	 * 绘制弧线或圆
	 * @param x 圆心坐标
	 * @param y 圆心坐标
	 * @param radius 是半径
	 * @param startAngle 起弧度（以 x 轴为基准）
	 * @param endAngle 末弧度（以 x 轴为基准）
	 * @param anticlockwise true 表示逆时针，反之顺时针
	 */
	public final native CanvasContext arc(int x, int y, int radius, int startAngle, double endAngle, boolean anticlockwise) /*-{
		this.arc(x, y, radius, startAngle, endAngle, anticlockwise);
		return this;
	}-*/;
	
	/**
	 * 贝塞尔曲线
	 * @param cp1x 第一个控制点的坐标
	 * @param cp1y 第一个控制点的坐标
	 * @param x 终点坐标
	 * @param y 终点坐标
	 */
	public final native CanvasContext quadraticCurveTo(int cp1x, int cp1y, int x, int y) /*-{
		this.quadraticCurveTo(cp1x, cp1y, x, y);
		return this;
	}-*/;
	
	/**
	 * 贝塞尔曲线
	 * @param cp1x 第一个控制点的坐标
	 * @param cp1y 第一个控制点的坐标
	 * @param cp2x 第二个控制点的坐标
	 * @param cp2y 第二个控制点的坐标
	 * @param x 终点坐标
	 * @param y 终点坐标
	 */
	public final native CanvasContext bezierCurveTo(int cp1x, int cp1y, int cp2x, int cp2y, int x, int y) /*-{
		this.bezierCurveTo(cp1x, cp1y, cp2x, cp2y, x, y);
		return this;
	}-*/;

	/**
	 * 矩形路径
	 * @param x 左上角坐标
	 * @param y 左上角坐标
	 * @param width 宽
	 * @param height 高
	 */
	public final native CanvasContext rect(int x, int y, int width, int height) /*-{
		this.rect(x, y, width, height);
		return this;
	}-*/;

	public final native CanvasContext strokeStyle(String color) /*-{
		this.strokeStyle = color;
		return this;
	}-*/;

	public final native CanvasContext lineWidth(int w) /*-{
		this.lineWidth = w;
		return this;
	}-*/;
}
