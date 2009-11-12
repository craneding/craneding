/**
 * 
 */
package com.gdfc.org.cn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		// 打印
		System.out.println(gameDrawInfos);
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
	
	private static List<GameDrawInfo> toDrawInfos(String message) {
		// var gameDrawInfos = [{"rollNext":11028021,"drawID":1578,"playTime":"周一至周日 21:54","luckyNo":"01040715242617","gameID":1,"drawName":"2009308"},{"rollNext":0,"drawID":1155,"playTime":"周二、周四、周日 21:54","luckyNo":"0210141523","gameID":2,"drawName":"2009132"},{"rollNext":68350516,"drawID":2009132,"playTime":"周二、周四、周日 20:45","luckyNo":"04141521233007","gameID":5,"drawName":"2009132"},{"rollNext":0,"drawID":2009308,"playTime":"周一至周日 20:30","luckyNo":" 0 3 0","gameID":6,"drawName":"2009308"},{"rollNext":4041787,"drawID":2009066,"playTime":"周二、周五 20:08","luckyNo":"0304081012163231","gameID":7,"drawName":"2009066"},{"rollNext":0,"drawID":1578,"playTime":"周一至周日 21:54","luckyNo":"17 龙 夏 东","gameID":8,"drawName":"2009308"}];
		final List<GameDrawInfo> gameDrawInfos = new ArrayList<GameDrawInfo>();
		final Pattern pattern = Pattern.compile(".*(\\{.*\\}),(\\{.*\\}),(\\{.*\\}),(\\{.*\\}),(\\{.*\\}),(\\{.*\\}).*");
		final Matcher matcher = pattern.matcher(message);

		// 匹配不成功
		if(!matcher.matches())
			return gameDrawInfos;
		
		final int groupCount = matcher.groupCount();
		for (int i = 1; i <= groupCount; i++) {
			String record = matcher.group(i);
			// 清除中括号([])和双引号("")
			record = record.substring(1, record.length() - 1).replace("\"", "");
			// rollNext:0,drawID:1578,playTime:周一至周日 21:54,luckyNo:17 龙 夏 东,gameID:8,drawName:2009308
			String[] fields = record.split("\\,");
			
			final GameDrawInfo drawInfo = new GameDrawInfo();
			for (String fs : fields) {
				final String[] field = fs.split("\\:");
				if(field[0].equals("rollNext")){
					drawInfo.setRollNext(field[1]);
				} else if(field[0].equals("drawID")){
					drawInfo.setDrawID(field[1]);
				} else if(field[0].equals("playTime")){
					drawInfo.setPlayTime(field[1]);
				} else if(field[0].equals("luckyNo")){
					drawInfo.setLuckyNo(field[1]);
				} else if(field[0].equals("gameID")){
					drawInfo.setGameID(field[1]);
				} else if(field[0].equals("drawName")){
					drawInfo.setDrawName(field[1]);
				} 
			}
			
			gameDrawInfos.add(drawInfo);
		}
		
		return gameDrawInfos;
	}

}
