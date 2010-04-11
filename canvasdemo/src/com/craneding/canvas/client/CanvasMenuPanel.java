/**
 * 
 */
package com.craneding.canvas.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author 丁丁
 * 
 */
public class CanvasMenuPanel extends PopupPanel implements ClickHandler {

	private VerticalPanel hor = new VerticalPanel();

	public CanvasMenuPanel() {
		super(false, false);

		hor.add(new Button("Pencil", this));
		hor.add(new Button("Rectangle", this));
		hor.add(new Button("Ellipse", this));
		hor.setSpacing(5);
		
		setWidget(hor);
		CanvasSystem.setProperty("canvasToolName", "Pencil");
	}

	@Override
	public void onClick(ClickEvent event) {
		Button source = (Button) event.getSource();
		CanvasSystem.setProperty("canvasToolName", source.getText());
	}

}
