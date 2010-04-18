/**
 * 
 */
package com.craneding.canvas.client.custom;

import com.craneding.canvas.client.template.HTMLTemplatePanel;
import com.craneding.canvas.client.ui.CanvasPanel;
import com.craneding.canvas.client.ui.CanvasSystem;
import com.craneding.canvas.client.ui.CanvasSystem.ChangeListenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;

/**
 * @author 丁丁
 * 
 */
public class ToolsTemplatePanel extends HTMLTemplatePanel implements ClickHandler {

	HTML pencil; 
	HTML rectangle; 
	HTML ellipse; 
	CanvasPanel toolsColor;
	
	public ToolsTemplatePanel() {
		super("../template/toolsBoardTemplate.html");
	}

	@Override
	protected void onBefore() {
	}

	@Override
	protected void onAfter() {
		pencil = (HTML) getAutoWidget("${Pencil}");
		rectangle = (HTML) getAutoWidget("${Rectangle}");
		ellipse = (HTML) getAutoWidget("${Ellipse}");
		toolsColor = (CanvasPanel) getAutoWidget("${tools-color}");
		
		pencil.setHTML("<a href='javascript:;'>铅笔</a>");
		rectangle.setHTML("<a href='javascript:;'>矩形</a>");
		ellipse.setHTML("<a href='javascript:;'>椭圆</a>");
		
		CanvasSystem.addListener(new ChangeListenter() {
			@Override
			public void onChange(String name, String oldVal, String newVal) {
				if("rgba".equals(name)) {
					toolsColor.getContext2D().fillStyle(newVal);
					toolsColor.getContext2D().fillRect(0, 0, 102, 40);
				}
			}
		});
		
		CanvasSystem.setProperty("rgba", "rgba(255,128,128,100)");
		CanvasSystem.setProperty("canvasToolName", "Pencil");
		
		pencil.addClickHandler(this);
		rectangle.addClickHandler(this);
		ellipse.addClickHandler(this);
	}

	@Override
	public void onClick(ClickEvent event) {
		Object source =  event.getSource();

		String name = "Pencil";
		
		if(source == pencil) {
			name = "Pencil";
		} else if(source == rectangle) {
			name = "Rectangle";
		} else if(source == ellipse) {
			name = "Ellipse";
		}
		
		CanvasSystem.setProperty("canvasToolName", name);
	}

}
