package com.example.demo.services;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    User getUser(String username);
    public void addRoleToUser(String rolename ,String username);
    List<User> getUsers();
}
