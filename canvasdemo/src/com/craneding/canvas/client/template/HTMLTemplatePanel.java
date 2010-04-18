/**
 * 
 */
package com.craneding.canvas.client.template;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * HTML 模板
 * 
 * @author crane.ding
 */
public abstract class HTMLTemplatePanel extends HTMLPanel {

	private Map<String, CustomUI> customUIs = new HashMap<String, CustomUI>();
	
	public HTMLTemplatePanel(String url, String id) {
		super("loading...");
		
		loadHTML(url, id);
	}
	
	public HTMLTemplatePanel(String url) {
		this(url, null);
	}
	
	public boolean putWidget(String gwtid, Widget widget){
		final CustomUI customUI = customUIs.get(gwtid);
		if(customUI != null){
			replaceWidget(customUI.customElement, customUI.custoomWidget = widget);
			return true;
		} else
			return false;
	}
	
	public Widget getAutoWidget(String gwtid){
		CustomUI customUI = customUIs.get(gwtid);
		return customUI != null ? customUI.custoomWidget : null;
	}

	protected abstract void onAfter();

	protected abstract void onBefore();

	private void loadHTML(String url, final String id) {
		onBefore();
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);
		builder.setCallback(new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				DOM.setInnerHTML(getElement(), getBodyHTML(response.getText(), id));
				findPageWidgets(getElement());
				onAfter();
			}
			@Override
			public void onError(Request request, Throwable exception) {
				exception.printStackTrace();
				findPageWidgets(getElement());
				onAfter();
			}
		});
		try {
			builder.send();
		} catch (RequestException e) {
			DOM.setInnerHTML(getElement(), e.getMessage());
		}
	}

	protected static String getBodyHTML(String text, String id) {
		text = text.replaceAll("\r\n", "")
				   .replaceAll("\n", "")
				   .replaceAll(".*<[bB][oO][dD][yY]>(.*?)</[bB][oO][dD][yY]>.*", "$1")
				   ;
		
		final Element tmpElem = DOM.createDiv();
		tmpElem.setInnerHTML(text);
		final int count = DOM.getChildCount(tmpElem);
		for (int i = 0; i < count; i++) {
			Element child = DOM.getChild(tmpElem, i);
			if(id.equals(child.getId())){
				text = child.getString();
				return text;
			}
		}
		
		return text;
	}

	/**
	 * find all the element,which have a gwtid attribte.
	 * @param child element in the page
	 */
	protected void findPageWidgets(Element child) {
		String gwtid = child.getAttribute("gwtid");
		if (gwtid != null && !gwtid.trim().equals("")) {
			CustomUI custom = new CustomUI(child);
			custom.custoomWidget = AutoGenerateWidget.generateWidget(child);
			replaceWidget(child, custom.custoomWidget);
			
			customUIs.put(gwtid, custom);
		} else {
			int count = DOM.getChildCount(child);
			for (int i = 0; i < count; i++) {
				findPageWidgets(DOM.getChild(child, i));
			}
		}
	}

	protected void replaceWidget(Element child, Widget widget) {
		Element newChild = DOM.createSpan();
		Element parent = DOM.getParent(child);
		parent.replaceChild(newChild, child);
		// 这样处理是保证自建的Element具有事件处理的能力
		super.add(widget, newChild);
		// 删除多于的span 保持原有的Element
		parent.replaceChild(widget.getElement(), newChild);
	}
	
	public static class CustomUI {
		public CustomUI(Element el) {
			customElement = el;
		}
		Element customElement;
		Widget custoomWidget;
	}
}
