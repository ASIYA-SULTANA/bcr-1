package org.accion.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.accion.entity.User;
@Transactional
@Repository
public class UserDao implements IUserDao {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public User getUserByUserName(String userName) {
		return entityManager.find(User.class, userName);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		String hql = "SELECT * FROM USER";
		return (List<User>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void createUser(User user) {
		entityManager.persist(user);
	}
	@Override
	public void updateUser(User user) {
		User usr = getUserByUserName(user.getUserName());
		usr.setPassword(user.getPassword());
		entityManager.flush();
	}
	@Override
	public void deleteUser(String userName) {
		entityManager.remove(getUserByUserName(userName));
	}
	@Override
	public boolean userExists(String userName, String password) {
		String hql = "FROM User as usr WHERE usr.userName = ? and usr.fullName = ?";
		int count = entityManager.createQuery(hql).setParameter(1,userName )
		              .setParameter(2, password).getResultList().size();
		return count > 0 ? true : false;
	}
}
