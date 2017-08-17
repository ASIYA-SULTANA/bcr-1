package org.accion.dao;

import java.util.List;
import org.accion.entity.User;

public interface IUserDao {
	List<User> getAllUsers();
    User getUserByUserName(String userName);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(String userName);
    boolean userExists(String userName,String password);
}
