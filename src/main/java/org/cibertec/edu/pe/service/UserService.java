package org.cibertec.edu.pe.service;
import org.cibertec.edu.pe.entity.User;

public interface UserService {
    public User registerNewUser(User user);
    public void initRolesAndUser();
    public String getEncodedPassword(String password);
    public User getUserByUsername(String userName);
}
