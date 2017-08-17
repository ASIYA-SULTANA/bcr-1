package org.accion.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.accion.dao.IUserDao;
import org.accion.entity.User;

@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDao userDao;

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public User getUserByUserName(String userName) {
		User obj = userDao.getUserByUserName(userName);
		return obj;
	}

	@Override
	public synchronized boolean createUser(User user) {
		if (userDao.userExists(user.getUserName(), user.getPassword())) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);

	}

	@Override
	public void deleteUser(String userName) {
		userDao.deleteUser(userName);
	}

}
