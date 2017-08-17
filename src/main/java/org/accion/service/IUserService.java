package org.accion.service;
import java.util.List;
import org.accion.entity.User;
public interface IUserService {
	List<User> getAllUsers();
    User getUserByUserName(String userName);
    boolean createUser(User user);
    void updateUser(User user);
    void deleteUser(String userName);
	 
}


 