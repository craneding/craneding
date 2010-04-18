/**
 * 
 */
package com.craneding.canvas.client.custom;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;

/**
 * 工具栏
 * 
 * @author 丁丁
 * 
 */
public class ToolsBox extends DialogBox implements ClickHandler {

	public ToolsBox() {
		super(false, false);

		setText("工具栏");
		setWidget(new ToolsTemplatePanel());
	}

	@Override
	public void onClick(ClickEvent event) {
		
	}

}
