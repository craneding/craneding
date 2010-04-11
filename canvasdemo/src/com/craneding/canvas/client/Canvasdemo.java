package com.craneding.canvas.client;

import com.google.gwt.core.client.EntryPoint;

public class Canvasdemo implements EntryPoint {

	public void onModuleLoad() {
		CanvasDrawingPanel drawingPanel = new CanvasDrawingPanel();
		
		drawingPanel.setText("画图");
		drawingPanel.setSize("732px", "500px");
		drawingPanel.center();
		
		CanvasMenuPanel canvasMenuPanel = new CanvasMenuPanel();
		canvasMenuPanel.setPopupPosition(drawingPanel.getAbsoluteLeft() - 100, drawingPanel.getAbsoluteTop());
		canvasMenuPanel.show();
	}

}
