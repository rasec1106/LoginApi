package org.cibertec.edu.pe.service;

import org.cibertec.edu.pe.dao.RoleRepository;
import org.cibertec.edu.pe.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role createNewRole(Role role){
        return roleRepository.save(role);
    }
}
