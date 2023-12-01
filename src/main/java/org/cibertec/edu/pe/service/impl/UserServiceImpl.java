package org.cibertec.edu.pe.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cibertec.edu.pe.dao.RoleRepository;
import org.cibertec.edu.pe.dao.UserRepository;
import org.cibertec.edu.pe.entity.Role;
import org.cibertec.edu.pe.entity.User;
import org.cibertec.edu.pe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user){
        Role role = roleRepository.findByRoleName("User").get();

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userRepository.save(user);
    }

    @Override
    public void initRolesAndUser(){
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleRepository.save(userRole);

        User adminUser = new User();
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserName("admin");
        adminUser.setUserPassword(getEncodedPassword("password"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userRepository.save(adminUser);

    }

    @Override
    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }

	@Override
	public User getUserByUsername(String userName) {
		return userRepository.findByUserName(userName).orElse(null);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(User user) {
		if (userRepository.existsById(user.getUserName())) {
			User dbUser = getUserByUsername(user.getUserName());
			if(user.getRoles() == null) user.setRoles(dbUser.getRoles());
			if(user.getUserFirstName() == null) user.setUserFirstName(dbUser.getUserFirstName());
			if(user.getUserLastName() == null) user.setUserLastName(dbUser.getUserLastName());
			if(user.getUserPassword() == null) user.setUserPassword(dbUser.getUserPassword());
			else user.setUserPassword(getEncodedPassword(user.getUserPassword()));
			return userRepository.save(user);
        }
        return null;		
	}

	@Override
	public boolean deleteUser(String userName) {
		if (userRepository.existsById(userName)) {
			userRepository.deleteById(userName);
            return true;
        }
        return false;
	}
}
