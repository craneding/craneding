/**
 * 
 */
package com.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.webxml.qqonline.QqOnlineWebService;
import com.webxml.qqonline.QqOnlineWebServiceSoap;
import com.webxml.traditionalsimplified.TraditionalSimplifiedWebService;
import com.webxml.traditionalsimplified.TraditionalSimplifiedWebServiceSoap;
import com.webxml.translator.ArrayOfString;
import com.webxml.translator.TranslatorWebService;
import com.webxml.translator.TranslatorWebServiceSoap;

/**
 * 
 * 
 * @author crane.ding
 * 
 */
public class FreePanel extends JPanel {
	private static final long serialVersionUID = 338753665192219307L;
	
	private final JComboBox typeSelect = new JComboBox();
	private final JTextField searchText = new JTextField(37);
	private final JButton searchButton = new JButton("搜索");
	private final JTextArea resultMessage = new JTextArea(14, 51);
	
	private TranslatorWebService translatorWebService;
	private QqOnlineWebService qqOnlineWebService;
	private TraditionalSimplifiedWebService traditionalSimplifiedWebService;
	
	public FreePanel() {
		typeSelect.addItem("腾讯QQ在线状态");
		typeSelect.addItem("简体字<->繁体字");
		typeSelect.addItem("中文<->英文");
		
		final JPanel jPanel = new JPanel();
		jPanel.setLayout(new FlowLayout());
		jPanel.add(typeSelect);
		jPanel.add(searchText);
		jPanel.add(searchButton);
		
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = null;
				switch (typeSelect.getSelectedIndex()) {
				case 0:
					result = doQqOnline();
					break;
				case 1:
					result = doTraditionalSimpliFied();
					break;
				case 2:
					result = doTranslator();
					break;
				default:
					result = "";
					break;
				}
				resultMessage.setText(result);
			}
		});
		resultMessage.setEditable(false);
		resultMessage.setBackground(new Color(255, 255, 255));
		resultMessage.setBorder(new LineBorder(Color.gray));
		
		add(jPanel);
		add(resultMessage);
	}
	
	protected String doQqOnline() {
		final String no = searchText.getText();
		
		if(!no.matches("\\d*"))
			return "QQ号码错误";
		
	    final QqOnlineWebServiceSoap soap = qqOnlineWebService().getQqOnlineWebServiceSoap();
		final String status = soap.qqCheckOnline(no);

		final StringBuffer buff = new StringBuffer()
			.append("QQ号码: ").append(no).append("\r\n")
			.append("状态: ").append(toString(status)).append("\r\n")
		;
		return buff.toString();
	}

	protected String doTraditionalSimpliFied() {
		final TraditionalSimplifiedWebServiceSoap soap = traditionalSimplifiedWebService().getTraditionalSimplifiedWebServiceSoap();
		final String text = searchText.getText();
		final String chinese = soap.toSimplifiedChinese(text);
		final String simpleChinese = soap.toTraditionalChinese(text);
		
		final StringBuffer buff = new StringBuffer()
			.append("简体: ").append(chinese).append("\r\n")
			.append("繁体: ").append(simpleChinese).append("\r\n")
			;
		
		return buff.toString();
	}

	protected String doTranslator() {
		final TranslatorWebServiceSoap soap = translatorWebService().getTranslatorWebServiceSoap();
		final ArrayOfString arrayOfString = soap.getEnCnTwoWayTranslator(searchText.getText());
		
		//String(0) 中文为[拼音][国标码 部首 笔画 笔顺]，英文为[音标]；
		//String(1) 译文 多个条目中间用 | 隔开，英文还包括单词属性
		final List<String> result = arrayOfString.getString();
		
		final StringBuffer buff = new StringBuffer()
			.append(result.get(0)).append("\r\n")
			.append(result.get(1).replaceAll("\\|", "")).append("\r\n")
			;
		
		final String str = buff.toString();
		
		//很抱歉，发现一个错误，请稍后再试！请联系我们 http;//www.webxml.com.cn，QQ 8409035
		if (str.indexOf("http") != -1 || str.matches(".*[qQ]{2}.*"))
			return "系统繁忙,请稍后再试";
		
		return str;
	}
	
	TranslatorWebService translatorWebService(){
		return translatorWebService != null ? translatorWebService : (translatorWebService = new TranslatorWebService());
	}
	
	QqOnlineWebService qqOnlineWebService(){
		return qqOnlineWebService != null ? qqOnlineWebService : (qqOnlineWebService = new QqOnlineWebService());
	}
	
	TraditionalSimplifiedWebService traditionalSimplifiedWebService(){
		return traditionalSimplifiedWebService != null ? traditionalSimplifiedWebService : (traditionalSimplifiedWebService = new TraditionalSimplifiedWebService());
	}
	
	String toString(String status) {
		if ("Y".equals(status))
			return "在线";
		if ("N".equals(status))
			return "离线";
		if ("E".equals(status))
			return "QQ号码错误";
		if ("A".equals(status))
			return "商业用户验证失败";
		if ("V".equals(status))
			return "免费用户超过数量";
		return "未知状态";
	}
}
