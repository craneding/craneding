/**
 * 
 */
package com.webxml;


import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.webxml.qqonline.QqOnlineWebService;
import com.webxml.qqonline.QqOnlineWebServiceSoap;
import com.webxml.traditionalsimplified.TraditionalSimplifiedWebService;
import com.webxml.traditionalsimplified.TraditionalSimplifiedWebServiceSoap;
import com.webxml.translator.ArrayOfString;
import com.webxml.translator.TranslatorWebService;
import com.webxml.translator.TranslatorWebServiceSoap;

/**
 * @author crane.ding
 *
 */
public class TestWebService {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * �����ѶQQ����״̬
	 */
//	@Test
	public void testQqOnline(){
		final QqOnlineWebService webService = new QqOnlineWebService();
		final QqOnlineWebServiceSoap soap = webService.getQqOnlineWebServiceSoap();

		String no = "360888719";
		final String status = soap.qqCheckOnline(no);

		System.out.print(" QQ: " + no);
		System.out.print(" Status: " + toString(status));
		System.out.print("\n");
	}
	
	
	/**
	 * ����<->Ӣ��˫���� WEB ����
	 */
	@Test
	public void testTranslator(){
		TranslatorWebService webService = new TranslatorWebService();	
		TranslatorWebServiceSoap soap = webService.getTranslatorWebServiceSoap();
		ArrayOfString arrayOfString = soap.getEnCnTwoWayTranslator("��");
		
		//String(0) ����Ϊ[ƴ��][������ ���� �ʻ� ��˳]��Ӣ��Ϊ[����]��
		//String(1) ���� �����Ŀ�м��� | ������Ӣ�Ļ�������������
		List<String> result = arrayOfString.getString();
		
		System.out.println(result.get(0));
		System.out.println(result.get(1));
	}
	
	/**
	 * ������<->������ ˫���� WEB ����
	 */
	@Test
	public void testTraditionalSimpliFied(){
		TraditionalSimplifiedWebService webService = new TraditionalSimplifiedWebService();	
		TraditionalSimplifiedWebServiceSoap soap = webService.getTraditionalSimplifiedWebServiceSoap();
		String chinese = soap.toSimplifiedChinese("�J");
		String simpleChinese = soap.toTraditionalChinese("��");
		
		System.out.println(chinese);
		System.out.println(simpleChinese);
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
