/**
 * 
 */
package com.craneding.h2;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.h2.tools.Server;

import com.craneding.h2.jdbc.easy.EasyBean;
import com.craneding.h2.jdbc.easy.EasyTransaction;

/**
 * H2µÄÁ·Ï°
 * 
 * @author crane.ding
 * 
 */
public class H2Demo {
	private static final Logger LOGGER = Logger.getLogger(H2Demo.class.getName()); 
	
	static {
		LOGGER.setLevel(Level.INFO);
		
		try {
			final FileHandler handler = new FileHandler("H2.log", 0, 1, true);
			handler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(handler);
		} catch (SecurityException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		Server server = null;
		
		final File path = new File("database");
		if(path.exists()){
			path.mkdirs();
		}
		final String url = "jdbc:h2:" + path.getAbsolutePath() + File.separator +"h2demo";
		
		try {
			server = Server.createTcpServer(args);
			
			Class.forName("org.h2.Driver");
			
			// "jdbc:h2:~/h2demo"
			EasyBean bean = EasyBean.newInstance(url, "craneding", "123456").open();
			
			List<User> users = bean.select("select * from user", User.class);
			System.out.println(users);
			
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			if(server != null)
				server.stop();
		}

	}

	protected static void createTable(EasyBean bean) throws SQLException {
		bean.transaction(new EasyTransaction() {
			@Override
			public void execute(EasyBean easyBean) throws SQLException {
				easyBean.execute("drop table user if exists")
					    .execute("create table user(`id` number not null, `username` varchar2(25) not null)")
					    ;
			}
		});
	}
	
	protected static void insertData(EasyBean bean) throws SQLException {
		bean.transaction(new EasyTransaction() {
			@Override
			public void execute(EasyBean easyBean) throws SQLException {
				easyBean.execute("insert into user values(1,'crane.ding@gmail.com')")
					    .execute("insert into user values(2,'crane.ding@163.com')")
					    .execute("insert into user values(3,'crane.ding@qq.com')")
					    ;
			}
		});
	}

}
