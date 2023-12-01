package org.cibertec.edu.pe.service;
import java.util.List;

import org.cibertec.edu.pe.entity.User;

public interface UserService {
    public void initRolesAndUser();
    public String getEncodedPassword(String password);
    public List<User> getUsers();
    public User getUserByUsername(String userName);
    public User createUser(User user);
    public User updateUser(User user);
    public boolean deleteUser(String userName);
}
