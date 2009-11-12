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
		���:5 �ں�:2009132 ��������:04141521233007, -˫ɫ��
		���:6 �ں�:2009308 ��������: 0 3 0,  -3D
		���:1 �ں�:2009308 ��������:01040715242617, -36ѡ7
		���:8 �ں�:2009308 ��������:17 �� �� �� -�ò�1
	
		���:2 �ں�:2009132 ��������:0210141523, -26ѡ5
	
		���:7 �ں�:2009066 ��������:0304081012163231, -���ڷ��
	*/
	static enum GameName {
		_˫ɫ��("5", "˫ɫ��"),
		_3D("6", "3D"),
		_36ѡ7("1", "36ѡ7"),
		_�ò�1("8", "�ò�1"),
		_26ѡ5("2", "26ѡ5"),
		_���ڷ��("7", "���ڷ��"),
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
			if(_˫ɫ��.gameID.equals(gameID))
				return _˫ɫ��;
			if(_3D.gameID.equals(gameID))
				return _3D;
			if(_36ѡ7.gameID.equals(gameID))
				return _36ѡ7;
			if(_�ò�1.gameID.equals(gameID))
				return _�ò�1;
			if(_26ѡ5.gameID.equals(gameID))
				return _26ѡ5;
			if(_�ò�1.gameID.equals(gameID))
				return _�ò�1;
			if(_���ڷ��.gameID.equals(gameID))
				return _���ڷ��;
			return null;
		}
	}
	
	/*
	 * "rollNext":11028021, "drawID":1578, "playTime":"��һ������ 21:54",
	 * "luckyNo":"01040715242617", "gameID":1, "drawName":"2009308"
	 */

	/**
	 * ��������
	 */
	private String rollNext;
	/**
	 * �ں�
	 */
	private String drawID;
	/**
	 * ������
	 */
	private String playTime;
	/**
	 * ��������
	 */
	private String luckyNo;
	/**
	 * ���
	 */
	private String gameID;
	/**
	 * ����
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
			.append(nextLine).append(" ��Ʊ���:").append(gameID)
			.append(nextLine).append(" ��Ʊ�淨:").append(GameName.getGameName(gameID).getGameName())
			.append(nextLine).append(" ��Ʊ�ں�:").append(drawID)
			.append(nextLine).append(" ��Ʊ����:").append(drawName)
			.append(nextLine).append(" ��������:").append(LuckeyNoArrays())
			.append(nextLine).append(" ��������:").append(playTime)
			.append(nextLine).append(" ��������:").append(rollNext)
			.append(nextLine).append("-------------------------")
			;
		
		return buff.toString();
	}
	
	/**
	 * ����������Ϣ
	 */
	public List<String> LuckeyNoArrays() {
		int endIndex = 0;
		final List<String> arrays = new ArrayList<String>();
		final GameName gameName = GameName.getGameName(gameID);

		switch (gameName) {
		case _3D:
			endIndex = 6;
			break;
		case _�ò�1:
			endIndex = 8;
			break;
		case _26ѡ5:
			endIndex = 10;
			break;
		case _˫ɫ��:
		case _36ѡ7:
			endIndex = 14;
			break;
		case _���ڷ��:
			endIndex = 16;
			break;
		}

		for (int i = 0; i < endIndex; i = i + 2) {
			arrays.add(luckyNo.substring(i, i + 2));
		}

		return arrays;
	}
}
