package org.cibertec.edu.pe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.cibertec.edu.pe.dao.RoleRepository;
import org.cibertec.edu.pe.entity.Role;
import org.cibertec.edu.pe.entity.User;
import org.cibertec.edu.pe.service.RoleService;
import org.cibertec.edu.pe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
    private RoleRepository roleRepository;
	@Autowired
	private UserService userService;

	@Override
    public Role createRole(Role role){
        return roleRepository.save(role);
    }
    
    @Override
    public List<Role> getRoles(){
    	return roleRepository.findAll();
    }
    
    @Override
    public Role getRoleByRoleName(String roleName) {
    	return roleRepository.findByRoleName(roleName).orElse(null);
    }

	@Override
	public Role updateRole(Role role) {
		if (roleRepository.existsById(role.getRoleName())) {
            return roleRepository.save(role);
        }
        return null;
	}

	@Override
	public boolean deleteRole(String roleName) {
		if (roleRepository.existsById(roleName)) {
			roleRepository.deleteById(roleName);
            return true;
        }
        return false;
	}

	@Override
	public List<Role> getRolesByUserName(String userName) {
		User user = userService.getUserByUsername(userName);
		return new ArrayList<>(user.getRoles());
	}
}
