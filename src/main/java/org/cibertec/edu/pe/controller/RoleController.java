package org.cibertec.edu.pe.controller;

import java.util.List;

import org.cibertec.edu.pe.entity.Role;
import org.cibertec.edu.pe.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;
    
    @GetMapping({"/getRoles"})
    public List<Role> getRoles() {
    	return roleService.getRoles();
    }
    
    @GetMapping({"/getRoleByRoleName/{roleName}"})
    public Role getRoleByRoleName(@PathVariable String roleName) {
    	return roleService.getRoleByRoleName(roleName);
    }
    
    @GetMapping({"/getRolesByUserName/{userName}"})
    public List<Role> getRolesByUserName(@PathVariable String userName) {
    	return roleService.getRolesByUserName(userName);
    }
    
    @PostMapping({"/createRole"})
    public Role createRole(@RequestBody Role role){
        return roleService.createRole(role);
    }
    
    @PutMapping({"/updateRole"})
    public Role updateRole(@RequestBody Role role) {
    	return roleService.updateRole(role);
    }
    
    @DeleteMapping({"/deleteRole/{roleName}"})
    public boolean deleteRole(@PathVariable String roleName) {
    	return roleService.deleteRole(roleName);
    }
}
