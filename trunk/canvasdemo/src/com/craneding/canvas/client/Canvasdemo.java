package com.craneding.canvas.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;

public class Canvasdemo implements EntryPoint {

	public void onModuleLoad() {
		if (CanvasPanel.haveContext()) {// 支持Canvas
			loadCanvasPanel();
		} else { // 不支持Canvas
			loadDownLoadPanel();
		}
	}

	private void loadDownLoadPanel() {
		PopupPanel box = new PopupPanel();
	    HTML html = new HTML("<div style='width:300px;'><h3>您当前的浏览器不支持Canvas</h3>" +
	    		"推荐浏览器:<br> <a href='http://www.google.com/chrome?hl=zh-cn' target='_blank'>Chrome</a> " +
	    		"  <a href='http://www.apple.com.cn/safari/download/' target='_blank'>Safari</a> " +
	    		"  <a href='http://www.mozillaonline.com/' target='_blank'>Firefox</a> " +
	    		"  <a href='http://www.opera.com/' target='_blank'>Opera</a></div>");
	    box.setWidget(html);
	    box.setSize("300px", "300px");
	    box.center();
	}

	private void loadCanvasPanel() {
		CanvasDrawingPanel drawingPanel = new CanvasDrawingPanel();

		drawingPanel.setText("画图");
		drawingPanel.setSize("732px", "500px");
		drawingPanel.center();

		CanvasMenuPanel canvasMenuPanel = new CanvasMenuPanel();
		canvasMenuPanel.setPopupPosition(drawingPanel.getAbsoluteLeft() - 100,
				drawingPanel.getAbsoluteTop() + 30);
		canvasMenuPanel.show();
	}

}
