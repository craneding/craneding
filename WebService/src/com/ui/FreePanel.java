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
	private final JButton searchButton = new JButton("����");
	private final JTextArea resultMessage = new JTextArea(14, 51);
	
	private TranslatorWebService translatorWebService;
	private QqOnlineWebService qqOnlineWebService;
	private TraditionalSimplifiedWebService traditionalSimplifiedWebService;
	
	public FreePanel() {
		typeSelect.addItem("��ѶQQ����״̬");
		typeSelect.addItem("������<->������");
		typeSelect.addItem("����<->Ӣ��");
		
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
			return "QQ�������";
		
	    final QqOnlineWebServiceSoap soap = qqOnlineWebService().getQqOnlineWebServiceSoap();
		final String status = soap.qqCheckOnline(no);

		final StringBuffer buff = new StringBuffer()
			.append("QQ����: ").append(no).append("\r\n")
			.append("״̬: ").append(toString(status)).append("\r\n")
		;
		return buff.toString();
	}

	protected String doTraditionalSimpliFied() {
		final TraditionalSimplifiedWebServiceSoap soap = traditionalSimplifiedWebService().getTraditionalSimplifiedWebServiceSoap();
		final String text = searchText.getText();
		final String chinese = soap.toSimplifiedChinese(text);
		final String simpleChinese = soap.toTraditionalChinese(text);
		
		final StringBuffer buff = new StringBuffer()
			.append("����: ").append(chinese).append("\r\n")
			.append("����: ").append(simpleChinese).append("\r\n")
			;
		
		return buff.toString();
	}

	protected String doTranslator() {
		final TranslatorWebServiceSoap soap = translatorWebService().getTranslatorWebServiceSoap();
		final ArrayOfString arrayOfString = soap.getEnCnTwoWayTranslator(searchText.getText());
		
		//String(0) ����Ϊ[ƴ��][������ ���� �ʻ� ��˳]��Ӣ��Ϊ[����]��
		//String(1) ���� �����Ŀ�м��� | ������Ӣ�Ļ�������������
		final List<String> result = arrayOfString.getString();
		
		final StringBuffer buff = new StringBuffer()
			.append(result.get(0)).append("\r\n")
			.append(result.get(1).replaceAll("\\|", "")).append("\r\n")
			;
		
		final String str = buff.toString();
		
		//�ܱ�Ǹ������һ���������Ժ����ԣ�����ϵ���� http;//www.webxml.com.cn��QQ 8409035
		if (str.indexOf("http") != -1 || str.matches(".*[qQ]{2}.*"))
			return "ϵͳ��æ,���Ժ�����";
		
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
			return "����";
		if ("N".equals(status))
			return "����";
		if ("E".equals(status))
			return "QQ�������";
		if ("A".equals(status))
			return "��ҵ�û���֤ʧ��";
		if ("V".equals(status))
			return "����û���������";
		return "δ֪״̬";
	}
}
