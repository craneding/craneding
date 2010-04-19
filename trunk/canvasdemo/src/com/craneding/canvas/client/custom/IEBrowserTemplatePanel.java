/**
 * 
 */
package com.craneding.canvas.client.custom;

import com.craneding.canvas.client.template.HTMLTemplatePanel;

/**
 * IE浏览器显示界面
 * 
 * @author 丁丁
 * 
 */
public class IEBrowserTemplatePanel extends HTMLTemplatePanel {

	public IEBrowserTemplatePanel() {
		super("../template/ieBrowserTemplate.html");
	}

	@Override
	protected void onAfter() {
	}

	@Override
	protected void onBefore() {
	}

}
