/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.project.services;

import com.mycompany.project.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Singleton;

/**
 *
 * @author Pascal Sekley
 */
@Singleton
public class UserManager implements IUserManager {

    private final Map<String, User> registeredUsers = new HashMap<>();

    @Override
    public boolean register(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();

        if (registrationPossible(username)) {
            registeredUsers.put(username, user);
            return true;

        } else {
            return false;
        }
    }

    @Override
    public boolean deleteUser(String usernameToDelete) {
        return (registeredUsers.remove(usernameToDelete) != null);
    }

    @Override
    public boolean registrationPossible(String username) {
        return !registeredUsers.containsKey(username);
    }

    @Override
    public List<User> getRegisteredUsers() {
        return new ArrayList(registeredUsers.values());
    }

    @Override
    public User getUser(String username) {
        User newUser = registeredUsers.get(username);
        if (newUser != null) {
            return newUser;
        } else {
            return null;
        }

    }

    @Override
    public User authenticate(String username, String password) {
        User user = registeredUsers.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateUser(User user) {
        String username = user.getUsername();
        String newPassword = user.getPassword();

        if (registeredUsers.get(username) != null) {
            registeredUsers.get(username).setPassword(newPassword);
            return true;
        } else {
            return false;
        }
    }

}
