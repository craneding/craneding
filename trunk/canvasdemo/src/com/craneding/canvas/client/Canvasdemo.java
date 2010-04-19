package com.craneding.canvas.client;

import com.craneding.canvas.client.custom.ColorBoardBox;
import com.craneding.canvas.client.custom.IEBrowserBox;
import com.craneding.canvas.client.custom.ToolsBox;
import com.craneding.canvas.client.ui.CanvasPanel;
import com.google.gwt.core.client.EntryPoint;

public class Canvasdemo implements EntryPoint {

	public void onModuleLoad() {
		if (CanvasPanel.haveContext()) {// 支持Canvas
			loadCanvasPanel();
		} else { // 不支持Canvas
			loadDownLoadPanel();
		}
	}

	private void loadDownLoadPanel() {
		IEBrowserBox ieBrowserBox = new IEBrowserBox();
		ieBrowserBox.center();
	}

	private void loadCanvasPanel() {
		CanvasDrawingPanel drawingPanel = new CanvasDrawingPanel();

		drawingPanel.setText("画图");
		drawingPanel.setSize("732px", "500px");
		drawingPanel.center();

		ToolsBox toolsBox = new ToolsBox();
		toolsBox.setPopupPosition(drawingPanel.getAbsoluteLeft() - 100, drawingPanel.getAbsoluteTop() + 30);
		toolsBox.show();
		
		int left = drawingPanel.getAbsoluteLeft() + drawingPanel.getOffsetWidth() - 100;
		int top = drawingPanel.getAbsoluteTop() + 30;
		ColorBoardBox colorPalettePanel = new ColorBoardBox();
		colorPalettePanel.setText("调色板");
		colorPalettePanel.setPopupPosition(left, top);
		colorPalettePanel.show();
	}

}
