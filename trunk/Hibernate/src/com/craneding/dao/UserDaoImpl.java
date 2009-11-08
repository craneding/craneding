/**
 * 
 */
package com.craneding.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Example;

import com.craneding.domain.User;
import com.craneding.util.HibernateUtil;

/**
 * @author crane.ding
 * 
 */
public class UserDaoImpl implements IUserDao {

	@Override
	public void deleteUser(User user) {
		final Session session = HibernateUtil.openSession();

		try {
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	@Override
	public User queryUser(Integer id) {
		final Session session = HibernateUtil.openSession();

		try {
			return (User) session.get(User.class, id);
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> queryUser(User user) {
		final Session session = HibernateUtil.openSession();

		try {
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Example.create(user).excludeNone().excludeZeroes());
			return criteria.list();
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public void saveUser(User user) {
		final Session session = HibernateUtil.openSession();

		try {
			session.beginTransaction();
			session.save(user);
			session.flush();
			session.getTransaction().commit();
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public void updateUser(User user) {
		final Session session = HibernateUtil.openSession();

		try {
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

}
