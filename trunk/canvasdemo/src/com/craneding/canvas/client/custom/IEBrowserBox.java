/**
 * 
 */
package com.craneding.canvas.client.custom;

import com.google.gwt.user.client.ui.PopupPanel;

/**
 * @author 丁丁
 *
 */
public class IEBrowserBox extends PopupPanel {

	public IEBrowserBox() {
		setAnimationEnabled(true);
		setWidget(new IEBrowserTemplatePanel());
		
		setSize("300px", "300px");
	}
	
}
