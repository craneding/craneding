/**
 * 
 */
package com.gdfc.org.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crane.ding
 * 
 */
public class GameDrawInfo {
	/*
		编号:5 期号:2009132 开奖号码:04141521233007, -双色球
		编号:6 期号:2009308 开奖号码: 0 3 0,  -3D
		编号:1 期号:2009308 开奖号码:01040715242617, -36选7
		编号:8 期号:2009308 开奖号码:17 龙 夏 东 -好彩1
	
		编号:2 期号:2009132 开奖号码:0210141523, -26选5
	
		编号:7 期号:2009066 开奖号码:0304081012163231, -深圳风采
	*/
	static enum GameName {
		_双色球("5", "双色球"),
		_3D("6", "3D"),
		_36选7("1", "36选7"),
		_好彩1("8", "好彩1"),
		_26选5("2", "26选5"),
		_深圳风采("7", "深圳风采"),
		;
		private String gameID;
		private String gameName;

		private GameName(String gameID, String gameName) {
			this.gameID = gameID;
			this.gameName = gameName;
		}

		public String getGameID() {
			return gameID;
		}

		public String getGameName() {
			return gameName;
		}
		
		public static GameName getGameName(String gameID){
			if(_双色球.gameID.equals(gameID))
				return _双色球;
			if(_3D.gameID.equals(gameID))
				return _3D;
			if(_36选7.gameID.equals(gameID))
				return _36选7;
			if(_好彩1.gameID.equals(gameID))
				return _好彩1;
			if(_26选5.gameID.equals(gameID))
				return _26选5;
			if(_好彩1.gameID.equals(gameID))
				return _好彩1;
			if(_深圳风采.gameID.equals(gameID))
				return _深圳风采;
			return null;
		}
	}
	
	/*
	 * "rollNext":11028021, "drawID":1578, "playTime":"周一至周日 21:54",
	 * "luckyNo":"01040715242617", "gameID":1, "drawName":"2009308"
	 */

	/**
	 * 滚入下期
	 */
	private String rollNext;
	/**
	 * 期号
	 */
	private String drawID;
	/**
	 * 开奖日
	 */
	private String playTime;
	/**
	 * 开奖号码
	 */
	private String luckyNo;
	/**
	 * 编号
	 */
	private String gameID;
	/**
	 * 期名
	 */
	private String drawName;

	public String getRollNext() {
		return rollNext;
	}

	public void setRollNext(String rollNext) {
		this.rollNext = rollNext;
	}

	public String getDrawID() {
		return drawID;
	}

	public void setDrawID(String drawID) {
		this.drawID = drawID;
	}

	public String getPlayTime() {
		return playTime;
	}

	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}

	public String getLuckyNo() {
		return luckyNo;
	}

	public void setLuckyNo(String luckyNo) {
		this.luckyNo = luckyNo;
	}

	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	public String getDrawName() {
		return drawName;
	}

	public void setDrawName(String drawName) {
		this.drawName = drawName;
	}

	@Override
	public String toString() {
		final String nextLine = System.getProperty("line.separator");
		final StringBuffer buff = new StringBuffer()
			.append(nextLine).append("-------------------------")
			.append(nextLine).append(" 彩票编号:").append(gameID)
			.append(nextLine).append(" 彩票玩法:").append(GameName.getGameName(gameID).getGameName())
			.append(nextLine).append(" 彩票期号:").append(drawID)
			.append(nextLine).append(" 彩票期名:").append(drawName)
			.append(nextLine).append(" 开奖号码:").append(LuckeyNoArrays())
			.append(nextLine).append(" 开奖周期:").append(playTime)
			.append(nextLine).append(" 滚入下期:").append(rollNext)
			.append(nextLine).append("-------------------------")
			;
		
		return buff.toString();
	}
	
	/**
	 * 开奖号码信息
	 */
	public List<String> LuckeyNoArrays() {
		int endIndex = 0;
		final List<String> arrays = new ArrayList<String>();
		final GameName gameName = GameName.getGameName(gameID);

		switch (gameName) {
		case _3D:
			endIndex = 6;
			break;
		case _好彩1:
			endIndex = 8;
			break;
		case _26选5:
			endIndex = 10;
			break;
		case _双色球:
		case _36选7:
			endIndex = 14;
			break;
		case _深圳风采:
			endIndex = 16;
			break;
		}

		for (int i = 0; i < endIndex; i = i + 2) {
			arrays.add(luckyNo.substring(i, i + 2));
		}

		return arrays;
	}
}
