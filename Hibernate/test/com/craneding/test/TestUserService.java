/**
 * 
 */
package com.craneding.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.craneding.dao.IUserDao;
import com.craneding.dao.UserDaoImpl;
import com.craneding.domain.User;

/**
 * @author crane.ding
 *
 */
public class TestUserService {

	private IUserDao userDao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		userDao = new UserDaoImpl();
	}

	/**
	 * Test method for {@link com.craneding.dao.UserDaoImpl#deleteUser(com.craneding.domain.User)}.
	 */
	@Test
	public void testDeleteUser() {
		final User user = userDao.queryUser(2);
		
		userDao.deleteUser(user);
	}

	/**
	 * Test method for {@link com.craneding.dao.UserDaoImpl#queryUser(java.lang.Integer)}.
	 */
	@Test
	public void testQueryUserInteger() {
		final User user = userDao.queryUser(1);
		
		assertNotNull(user);
		
		System.out.println(user.getName());
	}

	/**
	 * Test method for {@link com.craneding.dao.UserDaoImpl#queryUser(com.craneding.domain.User)}.
	 */
	@Test
	public void testQueryUserUser() {
		final User user = new User();
		user.setPassword("11111111");
		
		final List<User> list = userDao.queryUser(user);
		
		assertNotNull(list);
		
		for (User u : list) {
			System.out.println(u.getName());
		}
	}

	/**
	 * Test method for {@link com.craneding.dao.UserDaoImpl#saveUser(com.craneding.domain.User)}.
	 */
	@Test
	public void testSaveUser() {
		final User user = new User();
		user.setName("crane.ding");
		user.setEmail("crane.ding@163.com");
		user.setPassword("11111111");
		user.setQq("360888719");
		user.setTelephone("15818160426");
		user.setAddress("ÌÄÑÅÔ·");
		
		userDao.saveUser(user);
	}

	/**
	 * Test method for {@link com.craneding.dao.UserDaoImpl#updateUser(com.craneding.domain.User)}.
	 */
	@Test
	public void testUpdateUser() {
		final User user = userDao.queryUser(1);
		
		user.setName("¶¡¶¡");
		
		userDao.updateUser(user);
	}

}
