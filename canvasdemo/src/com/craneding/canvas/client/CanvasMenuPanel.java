/**
 * 
 */
package com.craneding.canvas.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * @author 丁丁
 * 
 */
public class CanvasMenuPanel extends DialogBox implements ClickHandler {

	public CanvasMenuPanel() {
		super(false, false);
		
		CanvasSystem.setProperty("canvasToolName", "Pencil");

		Label l = null;
		HTMLPanel htmlPanel = new HTMLPanel("<ul><li id='Pencil' class='Tool'></li><li id='Rectangle' class='Tool'></li><li id='Ellipse' class='Tool'></li>");
		htmlPanel.add(l = new Label("Pencil"), "Pencil");
		l.addClickHandler(this);
		
		htmlPanel.add(l = new Label("Rectangle"), "Rectangle");
		l.addClickHandler(this);
		
		htmlPanel.add(l = new Label("Ellipse"), "Ellipse");
		l.addClickHandler(this);

		setHTML("<div style='background:url(images/win_MT.png) -10px;color:#BBBBBB;cursor:move;font-weight:bold;text-align:center;height:20px;'>工具</div>");
		setWidget(htmlPanel);
		setWidth("120px");
		
		DOM.setStyleAttribute(getElement(), "border", "1px dashed black");
	}

	@Override
	public void onClick(ClickEvent event) {
		Label source = (Label) event.getSource();
		CanvasSystem.setProperty("canvasToolName", source.getText());
	}

}
