/**
 * 
 */
package com.craneding.dao;

import java.util.List;

import com.craneding.domain.User;

/**
 * @author crane.ding
 * 
 */
public interface IUserDao {

	void saveUser(User user);

	void updateUser(User user);

	void deleteUser(User user);

	User queryUser(Integer id);

	List<User> queryUser(User user);
}
