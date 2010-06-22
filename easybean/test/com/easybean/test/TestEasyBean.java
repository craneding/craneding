/**
 * 
 */
package com.easybean.test;

import java.util.List;
import java.util.Map;

import com.easybean.EasyBean;


/**
 * @author 供供
 *
 */
public class TestEasyBean {
	static final TestDataSource dataSource = new TestDataSource();
	
	static {
		dataSource.setUrl("jdbc:h2:database/h2demo");
		dataSource.setUsername("craneding");
		dataSource.setPassword("123456");
	}
	
	static final EasyBean easyBean = EasyBean.newInstance(dataSource);
	
	public static void main(String[] args) {
		org.h2.Driver.load();
		
		for (int i = 0; i < 10; i++) {
			new Thread(){
				@Override
				public void run() {
					test3();
				}
			}.start();
		}
		
		test3();
	}

	protected static void test3() {
		try {
			easyBean.beginTransaction();
			
			int maxCount = 5;
			
			long count = (Long)easyBean
				.sql("select count(*) as C from user")
				.findUniqueMap()
				.get("C");
			System.out.println(count);
			
			long page = (count % maxCount == 0) ? (count / maxCount) : (count / maxCount + 1);
			
			int startIndex = 1;
			for (int i = 1; i <= page; i++) {
				List<User> findList = easyBean
					.sql("select * from user u order by u.id")
					.findList(User.class, startIndex, maxCount);
				System.out.println(findList);
				
				startIndex += maxCount;
			}
			
			easyBean.commitTransaction();
		} finally {
			easyBean.endTransaction();
		}
	}

	protected static void test2(TestDataSource dataSource) {
		EasyBean easyBean = new EasyBean(dataSource);
		
		easyBean.beginTransaction();
		
		try {
			Object key = easyBean
					.sql("select max(u.id) + 1 as key from user u")
					.findUniqueMap()
					.get("KEY");
			System.out.println(key);

			easyBean.sql("insert into user (id, username) values(?, ?)")
					.addParameters(key, "供供@hotye.com")
					.save();
			
			easyBean.commitTransaction();
		} finally {
			easyBean.endTransaction();
		}
	}

	protected static void test1(TestDataSource dataSource) {
		EasyBean easyBean = new EasyBean(dataSource);
		
		List<Map<String, Object>> result = easyBean
			.sql("select max(u.id) + 1 as key from user u")
			.findListMap();
		System.out.println(result);
		
		easyBean
			.sql("insert into user (id, username) values(?, ?)")
			.addParameters(result.get(0).get("KEY") ,"crane.ding@hotye.com")
			.save();
		
		easyBean
			.sql("update user u set u.username = ? where u.id = ?")
			.addParameters("crane.ding@gmail.com", 1)
			.update();
		
		User user = easyBean
		 	.sql("select id, username from user u where u.id = ?")
		 	.addParameters(1)
		 	.findUnique(User.class);
		System.out.println(user);
		
		List<User> users = easyBean
		 	.sql("select id, username from user u")
		 	.findList(User.class);
		System.out.println(users);
	}

}
