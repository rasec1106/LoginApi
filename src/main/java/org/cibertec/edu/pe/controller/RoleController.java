package org.cibertec.edu.pe.controller;

import java.util.List;

import org.cibertec.edu.pe.entity.Role;
import org.cibertec.edu.pe.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role){
        return roleService.createNewRole(role);
    }
    
    @GetMapping({"/getRoles"})
    public List<Role> getRoles() {
    	return roleService.getRoles();
    }
    
    @GetMapping({"/getRoleByRoleName/{roleName}"})
    public Role getRoleByRoleName(@PathVariable String roleName) {
    	return roleService.getRoleByRoleName(roleName);
    }
    
}
