package com.craneding.canvas.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Canvasdemo implements EntryPoint {

	public void onModuleLoad() {
		RootPanel.get().add(draw());
		
		RootPanel.get().add(drawImage());
	}

	private CanvasPanel drawImage() {
		CanvasPanel canvasPanel = new CanvasPanel();
		final CanvasContext context = canvasPanel.getContext2D();
		
		context.drawImage("../images/backdrop.png", 0, 0);
		context.beginPath();
		context.moveTo(30, 96);
		context.lineTo(70, 66);
		context.lineTo(103, 76);
		context.lineTo(170, 15);
		context.stroke();
		return canvasPanel;
	}

	private CanvasPanel draw() {
		CanvasPanel canvasPanel = new CanvasPanel();
		
		CanvasContext context = canvasPanel.getContext2D();
		context.fillStyle("rgb(200, 0, 0)");
		context.fillRect(0, 0, 55, 50);
		
		// Draws a filled rectangle
		context.fillStyle("rgba(0, 0, 200, 0.5)");// Í¸Ã÷¶È
		context.fillRect(30, 30, 55, 50);

		// Draws a rectangular outline
		context.fillStyle("rgba(300, 300, 300, 0.5)");
		context.strokeRect(60, 60, 55, 50);

		// Clears the specified area and makes it fully transparent
		//ctx.clearRect(350, 200, 55, 50);

		context.fillStyle("rgb(200,0,0)");
		context.beginPath();
		context.moveTo(90, 30);
		context.lineTo(120, 0);
		context.lineTo(120, 60);
		context.fill();
		return canvasPanel;
	}
}
