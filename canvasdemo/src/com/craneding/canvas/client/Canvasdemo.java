package com.craneding.canvas.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.user.client.ui.Widget;

public class Canvasdemo implements EntryPoint {

	public void onModuleLoad() {
//		RootPanel.get().add(draw());
//		
//		RootPanel.get().add(drawImage());
//		
//		RootPanel.get().add(drawthegoneheart());
		
		CanvasDrawingPanel drawingPanel = new CanvasDrawingPanel();
		
		drawingPanel.setText("画图");
		drawingPanel.setSize("732px", "500px");
		drawingPanel.center();
	}

	Widget drawthegoneheart() {
		final CanvasPanel canvasPanel = new CanvasPanel();
		final CanvasContext context = canvasPanel.getContext2D();
		
		context.beginPath();
		context.arc(75, 75, 50, 0, (Math.PI * 2), true); // Outer circle
		context.moveTo(110, 75);
		context.arc(75, 75, 35, 0, Math.PI, false); // Mouth (clockwise)
		context.moveTo(65, 65);
		context.arc(60, 65, 5, 0, (Math.PI * 2), true); // Left eye
		context.moveTo(95, 65);
		context.arc(90, 65, 5, 0, (Math.PI * 2), true); // Right eye
		context.stroke();
					
		context.beginPath();
		context.moveTo(40, 75);
		context.lineTo(60, 65);
		context.lineTo(90, 65);
		context.moveTo(110, 75);
		context.lineTo(125, 75);
		context.stroke();
		
		canvasPanel.addMouseMoveHandler(new MouseMoveHandler() {
			int x = 0, y = 0;
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				context.beginPath();
				context.moveTo(x, y);
				x = event.getRelativeX(canvasPanel.getElement());
				y = event.getRelativeY(canvasPanel.getElement());
				context.lineTo(x, y);
				context.stroke();
			}
		});
		
		return canvasPanel;
	}

	CanvasPanel drawImage() {
		CanvasPanel canvasPanel = new CanvasPanel();
		final CanvasContext context = canvasPanel.getContext2D();
		
		context.drawImage(new CanvasImage("../images/backdrop.png", 0, 0) {
			@Override
			void onSuccess() {
				context.beginPath();
				context.moveTo(30, 96);
				context.lineTo(70, 66);
				context.lineTo(103, 76);
				context.lineTo(170, 15);
				context.stroke();
			}
		});
		
		return canvasPanel;
	}

	CanvasPanel draw() {
		CanvasPanel canvasPanel = new CanvasPanel();
		
		CanvasContext context = canvasPanel.getContext2D();
		context.fillStyle("rgb(200, 0, 0)");
		context.fillRect(0, 0, 55, 50);
		
		// Draws a filled rectangle
		context.fillStyle("rgba(0, 0, 200, 0.5)");//
		context.fillRect(30, 30, 55, 50);

		// Draws a rectangular outline
		context.fillStyle("rgba(300, 300, 300, 0.5)");
		context.strokeRect(60, 60, 55, 50);

		// Clears the specified area and makes it fully transparent
		//context.clearRect(25, 25, 55, 50);

		context.fillStyle("rgb(200,0,0)");
		context.beginPath();
		context.moveTo(90, 30);
		context.lineTo(120, 0);
		context.lineTo(120, 60);
		context.fill();
		return canvasPanel;
	}
}
