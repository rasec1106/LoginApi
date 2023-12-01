package org.cibertec.edu.pe.controller;

import org.cibertec.edu.pe.dto.AddRoleRequest;
import org.cibertec.edu.pe.entity.User;
import org.cibertec.edu.pe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.PostConstruct;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUser();
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accesible to the user";
    }
    
    
    @GetMapping({"/getUsers"})
    public List<User> getUsers() {
    	return userService.getUsers();
    }
    
    @GetMapping({"/getUserByUsername/{userName}"})
    public User getUserByUsername(@PathVariable String userName) {
    	return userService.getUserByUsername(userName);
    }
    
    @PostMapping({"/createUser"})
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    
    @PutMapping({"/updateUser"})
    public User updateRole(@RequestBody User user) {
    	return userService.updateUser(user);
    }
    
    @DeleteMapping({"/deleteUser/{userName}"})
    public boolean deleteRole(@PathVariable String userName) {
    	return userService.deleteUser(userName);
    }
    
    @PostMapping({"/addRoleToUser"})
    public User addRoleToUser(@RequestBody AddRoleRequest request){
        return userService.addRoleToUser(request.getUserName(), request.getRoleName());
    }
}
