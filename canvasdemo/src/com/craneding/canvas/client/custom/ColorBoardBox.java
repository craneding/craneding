/**
 * 
 */
package com.craneding.canvas.client.custom;

import com.google.gwt.user.client.ui.DialogBox;

/**
 * 调色板
 * 
 * @author 丁丁
 */
public class ColorBoardBox extends DialogBox {

	private ColorBoardTemplatePanel templatePanel = new ColorBoardTemplatePanel();
	
	public ColorBoardBox() {
		super(false, false);
		
		setAnimationEnabled(true);
		
		setWidget(templatePanel);
		
		setStyleName("ColorBoardBox");
	}

}
