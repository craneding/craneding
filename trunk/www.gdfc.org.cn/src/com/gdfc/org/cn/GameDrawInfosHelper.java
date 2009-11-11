/**
 * 
 */
package com.gdfc.org.cn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author crane.ding
 * at drawinfos.js |iconv -c -f utf8 -t gbk |grep -E -o 'luckyNo":"[^"]+' |awk -F'"' '{print $NF}' |sed "s/\([0-9]\{2\}\)/\1 /g" 
 */
public class GameDrawInfosHelper {

	static List<GameDrawInfo> drawInfos = new ArrayList<GameDrawInfo>();
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.gdfc.org.cn/datas/drawinfos.js");
		
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		
		connection.setConnectTimeout(10000);
		connection.setReadTimeout(10000);
		connection.connect();

		try {
			if(connection.getResponseCode() == 200){
				// �ɹ�
				String string = InputStream2String(connection.getInputStream());
				System.out.println(string);
				
				torawInfos(string);
			}else {
				// ʧ��
				System.err.println(connection.getResponseCode());
			}
		} finally {
			connection.disconnect();
		}
	}
	
	private static void torawInfos(String string) {
		//var gameDrawInfos = [{"rollNext":11028021,"drawID":1578,"playTime":"��һ������ 21:54","luckyNo":"01040715242617","gameID":1,"drawName":"2009308"},{"rollNext":0,"drawID":1155,"playTime":"�ܶ������ġ����� 21:54","luckyNo":"0210141523","gameID":2,"drawName":"2009132"},{"rollNext":68350516,"drawID":2009132,"playTime":"�ܶ������ġ����� 20:45","luckyNo":"04141521233007","gameID":5,"drawName":"2009132"},{"rollNext":0,"drawID":2009308,"playTime":"��һ������ 20:30","luckyNo":" 0 3 0","gameID":6,"drawName":"2009308"},{"rollNext":4041787,"drawID":2009066,"playTime":"�ܶ������� 20:08","luckyNo":"0304081012163231","gameID":7,"drawName":"2009066"},{"rollNext":0,"drawID":1578,"playTime":"��һ������ 21:54","luckyNo":"17 �� �� ��","gameID":8,"drawName":"2009308"}];
		
		System.out.println(string.matches(".*(\\{.*\\}),(\\{.*\\}),(\\{.*\\}),(\\{.*\\}),(\\{.*\\}),(\\{.*\\}).*"));
		
		//(.*)({.*}),({.*}),({.*}),({.*}),({.*}),({.*}).*
		
	}

	private static synchronized String InputStream2String(InputStream is) throws IOException {
		String str;
		final BufferedReader breader = new BufferedReader(new InputStreamReader(is, "GBK"));
		final StringBuffer sb = new StringBuffer();

		while ((str = breader.readLine()) != null) {
			sb.append(str);
		}
		
		return sb.toString();
	}

}
