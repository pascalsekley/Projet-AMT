/*
 -----------------------------------------------------------------------------------
 Project 	 : Projet AMT
 File     	 : IUserManagerDAO.java
 Author(s)       : Pascal Sekley & Rodrigue Tchuensu 
 Date            : Start: 21.09.16 - End:  
 Purpose         : The goal of this file is to offer some methods to manage a user
                   This DAO interacts with a database.
 remark(s)       : n/a
 Compiler        : jdk 1.8.0_101
 -----------------------------------------------------------------------------------
 */
package com.mycompany.project.services.dao;

import com.mycompany.project.model.User;
import java.util.List;
import javax.ejb.Local;

/**
 * <h1> User Manager DAO Interface </h1>
 * This class interacts with the mysql database and manages a user
 * @author Pascal Sekley & Rodrigue Tchuensu
 * @version 1.0
 * @since 2016-10-19
 */

@Local
public interface IUserManagerDAO {
    public boolean register(User user);
    public User authenticate(String username, String password);
    public boolean registrationPossible(String username);    
    public List<User> getRegisteredUsers();
    public boolean deleteUser(String username);
    public User getUser(String username);
    public boolean updateUser(User user, String newName, String newLastname, String newPassword);
}
