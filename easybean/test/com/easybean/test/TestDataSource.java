/**
 * 
 */
package com.easybean.test;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author ¶¡¶¡
 *
 */
public class TestDataSource implements DataSource {
	
	private String url;
	private String username;
	private String password;
	private int loginTimeout;
	private PrintWriter logWriter;
	private boolean wrapperFor;
	private Class<?> iface;
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return logWriter;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return loginTimeout;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		this.logWriter = out;
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		this.loginTimeout = seconds; 
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		this.iface = iface;
		
		return wrapperFor;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		if(this.iface == iface)
			try {
				return iface.newInstance();
			} catch (InstantiationException e) {
				throw new SQLException(e);
			} catch (IllegalAccessException e) {
				throw new SQLException(e);
			}
		
		return null;
	}

}
