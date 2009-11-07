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
	 * 获得腾讯QQ在线状态
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
	 * 中文<->英文双向翻译 WEB 服务
	 */
	@Test
	public void testTranslator(){
		TranslatorWebService webService = new TranslatorWebService();	
		TranslatorWebServiceSoap soap = webService.getTranslatorWebServiceSoap();
		ArrayOfString arrayOfString = soap.getEnCnTwoWayTranslator("考");
		
		//String(0) 中文为[拼音][国标码 部首 笔画 笔顺]，英文为[音标]；
		//String(1) 译文 多个条目中间用 | 隔开，英文还包括单词属性
		List<String> result = arrayOfString.getString();
		
		System.out.println(result.get(0));
		System.out.println(result.get(1));
	}
	
	/**
	 * 繁体字<->简体字 双向翻译 WEB 服务
	 */
	@Test
	public void testTraditionalSimpliFied(){
		TraditionalSimplifiedWebService webService = new TraditionalSimplifiedWebService();	
		TraditionalSimplifiedWebServiceSoap soap = webService.getTraditionalSimplifiedWebServiceSoap();
		String chinese = soap.toSimplifiedChinese("認");
		String simpleChinese = soap.toTraditionalChinese("鸟");
		
		System.out.println(chinese);
		System.out.println(simpleChinese);
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
