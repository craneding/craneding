/**
 * 
 */
package com.craneding.canvas.client.template;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author crane.ding
 *
 */
public class AutoGenerateWidget {

	public static Widget generateWidget(Element elem) {
		final String tagName = elem.getTagName();
		final String type = DOM.getElementAttribute(elem, "type");
		final boolean isInput = "input".equalsIgnoreCase(tagName);
		
		// Image
		if("img".equalsIgnoreCase(tagName))
			return new AutoImage(elem);
		
		// ListBox
		if("select".equalsIgnoreCase(tagName))
			return new AutoListBox(elem);
		
		// Button
		if("button".equalsIgnoreCase(tagName))
			return new AutoButton(elem);
		
		// PasswordTextBox
		if(isInput && "PASSWORD".equalsIgnoreCase(type))
			return new AutoPasswordTextBox(elem);
		
		// RadioButton
		if(isInput && "radio".equalsIgnoreCase(type))
			return new AutoRadioButton(elem);
		
		// CheckBox
		if("checkbox".equalsIgnoreCase(tagName))
			return new AutoCheckBox(elem);
		
		// TextArea
		if("textarea".equalsIgnoreCase(tagName))
			return new AutoTextArea(elem);
		
		// TextBox
		if(isInput)
			return new AutoTextBox(elem);
		
		// HTML
		return new AutoHTML(elem);
	}

	static class AutoImage extends Image{
		public AutoImage(Element elem) {
			super(elem);
		}
	}
	
	static class AutoListBox extends ListBox{
		public AutoListBox(Element elem) {
			super(elem);
		}
	}
	
	static class AutoButton extends Button {
		public AutoButton(Element elem) {
			super(elem);
		}
	}
	
	static class AutoPasswordTextBox extends PasswordTextBox {
		public AutoPasswordTextBox(Element elem) {
			super(elem);
		}
	}
	
	static class AutoRadioButton extends RadioButton {
		public AutoRadioButton(Element elem) {
			super(elem.getAttribute("name"), elem.getAttribute("info"));
			setValue(Boolean.valueOf(elem.getAttribute("value")));
			final String styleName = elem.getAttribute("class");
			if (styleName != null)
				setStyleName(styleName);
		}
	}
	
	static class AutoCheckBox extends CheckBox {
		public AutoCheckBox(Element elem) {
			super(elem.getAttribute("info"));
			setValue(Boolean.valueOf(elem.getAttribute("value")));
			final String styleName = elem.getAttribute("class");
			if (styleName != null)
				setStyleName(styleName);
		}
	}
	
	static class AutoTextArea extends TextArea {
		public AutoTextArea(Element elem) {
			super(elem);
		}
	}
	
	static class AutoTextBox extends TextBox {
		public AutoTextBox(Element elem) {
			super(elem);
		}
	}
	
	static class AutoHTML extends HTML {
		public AutoHTML(Element elem) {
			super(elem);
		}
	}
	
}
