/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.project.services.dao;

import com.mycompany.project.model.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sekley Pascal <pascal.sekley@heig-vd.ch>
 */

@Local
public interface IUserManagerDAO {
    public boolean register(User user);
    public User authenticate(String username, String password);
    public boolean registrationPossible(String username);    
    public List<User> getRegisteredUsers();
    public boolean deleteUser(String username);
    public User getUser(String username);
    public boolean updateUser(User user);
}
