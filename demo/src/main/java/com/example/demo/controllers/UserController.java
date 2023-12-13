package com.example.demo.controllers;

import com.example.demo.entities.Role;
import com.example.demo.entities.RoleToUserForm;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers(){

        return ResponseEntity.ok().body(userService.getUsers());

    }
    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("role/save")
   public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }


    public ResponseEntity<?> addRoletoUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getRoleName(), form.getUserName());
        return ResponseEntity.ok().build();
    }
}
