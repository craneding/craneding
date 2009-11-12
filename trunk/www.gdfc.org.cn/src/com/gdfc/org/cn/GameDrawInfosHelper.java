/**
 * 
 */
package com.gdfc.org.cn;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

/**
 * 福彩开奖号码工具类
 * @author crane.ding
 * at drawinfos.js |iconv -c -f utf8 -t gbk |grep -E -o 'luckyNo":"[^"]+' |awk -F'"' '{print $NF}' |sed "s/\([0-9]\{2\}\)/\1 /g" 
 */
public class GameDrawInfosHelper {

	private static String spec = "http://www.gdfc.org.cn/datas/drawinfos.js";
	private static int connectTimeout = 10000;
	private static int readTimeOut = 10000;
	
	static List<GameDrawInfo> drawInfos = new ArrayList<GameDrawInfo>();
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// 返回信息
		final String response = doGet();
		
		// 解析成GameDrawInfo对象
		List<GameDrawInfo> gameDrawInfos = toDrawInfos(response);
		
		String results = gameDrawInfos.toString();
		
		// 打印
		System.out.println(results);
		
		OutputStream stream = new FileOutputStream("彩票开奖信息.txt");
		stream.write(results.getBytes());
		stream.close();
		
	}
	
	private static String doGet() throws IOException{
		final URL url = new URL(spec);
		final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		
		connection.setConnectTimeout(connectTimeout);
		connection.setReadTimeout(readTimeOut);
		connection.connect();

		try {
			if(connection.getResponseCode() == 200){
				// 成功
				String str;
				final BufferedReader breader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
				final StringBuffer sb = new StringBuffer();

				while ((str = breader.readLine()) != null) {
					sb.append(str);
				}
				
				return sb.toString();
			}else {
				// 失败
				System.err.println(connection.getResponseCode());
				return null;
			}
		} finally {
			connection.disconnect();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private static List<GameDrawInfo> toDrawInfos(String message) {
		message = message.substring(message.indexOf("["), message.lastIndexOf("]")+1);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(GameDrawInfo.class);
		Object object = JSONSerializer.toJava(JSONSerializer.toJSON(message),jsonConfig);
		
		return (List<GameDrawInfo>) object;
	}

}
