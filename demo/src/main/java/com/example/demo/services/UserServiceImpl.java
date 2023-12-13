package com.example.demo.services;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService , UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepo.findByUsername(username);
        if(user==null){
            log.error("the user not found in data base");
            throw new UsernameNotFoundException("user not found in database");
        }else {
             log.info("user found in tha database: {}",username);

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));});
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);


    }
    public User saveUser(User user) {
        log.info("saving new user {} to the data base",user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user) ;
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving new role {} to the data base",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public User getUser(String username) {
        log.info("fetching user {} ",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String rolename, String username) {
        log.info("adding role {} to user {}",rolename,username);
        User user =userRepo.findByUsername(username);
        Role role =roleRepo.findByName(rolename);
        user.getRoles().add(role);
    }

    @Override
    public List<User> getUsers() {
        log.info("fetching all users {} ");

        return userRepo.findAll();
    }
}
