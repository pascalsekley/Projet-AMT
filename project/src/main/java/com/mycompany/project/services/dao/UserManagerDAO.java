/*
 -----------------------------------------------------------------------------------
 Project 	 : Projet AMT
 File     	 : IUserManagerDAO.java
 Author(s)       : Pascal Sekley & Rodrigue Tchuensu 
 Date            : Start: 21.09.16 - End:  
 Purpose         : The goal of this file is to implement some methods to manage a user
                   This DAO interacts with a database.
 remark(s)       : n/a
 Compiler        : jdk 1.8.0_101
 -----------------------------------------------------------------------------------
 */
package com.mycompany.project.services.dao;

import com.mycompany.project.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 * <h1> User Manager DAO </h1>
 * This class implement all the methods to manage a user
 * @author Pascal Sekley & Rodrigue Tchuensu
 * @version 1.0
 * @since 2016-10-19
 */

@Stateless
public class UserManagerDAO implements IUserManagerDAO {
    
    @Resource(lookup = "java:/jdbc/amtusers")
    private DataSource dataSource;
    
   /**
   * This method is used to register a user in a database. 
   * @param user This is the user object to be registered
   * @return boolean This returns true is the user has been registered and false if not.
   */

    @Override
    public boolean register(User user) {
        String name = user.getName();
        String lastname = user.getLastname();
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        
        if (registrationPossible(username)) {
            try {
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?)");
                pstmt.setString(1, name);
                pstmt.setString(2, lastname);
                pstmt.setString(3, username);
                pstmt.setString(4, email);
                pstmt.setString(5, password);
                int ex = pstmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            return true;
        } else {
            return false;
        }
    }
    
    /**
   * This method is used authenticate a user in a database. 
   * @param username The user's username
   * @param password The user's password
   * @return User The authenticated user
   */

    @Override
    public User authenticate(String username, String password) {

        ResultSet rs = null;
        User user = null;
        String nameDb;
        String lastnameDb;
        String usernameDb;
        String emailDb;
        String passwordDb = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                nameDb = rs.getString("name");
                lastnameDb = rs.getString("lastname");
                usernameDb = rs.getString("username");
                emailDb = rs.getString("email");
                passwordDb = rs.getString("password");
                user = new User(nameDb, lastnameDb, usernameDb, emailDb, passwordDb);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (user != null && passwordDb.equals(password)) {
            return user;
        } else {
            return null;
        }
    }
    
   /**
   * This method is used to check if a user could be regitered in a database. 
   * @param username The user's username
   * @return boolean True is the action could be perform and false if not.
   */

    @Override
    public boolean registrationPossible(String username) {
        boolean isPresent = false;
            try {
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();
                
                isPresent = !rs.next();
            } catch (SQLException ex) {
                Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return isPresent;

    }
    
  /**
   * This method is used to find all the users in a database. 
   * @return List All the user in a database
   */

    @Override
    public List<User> getRegisteredUsers() {
        List<User> users = new ArrayList<>();
        User user = new User();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                users.add(new User(name, lastname, username, email, password));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

     /**
   * This method is used to delete a user in a database. 
   * @param username The user's username
   * @return boolean True is the action could be perform and false if not.
   */
    
    @Override
    public boolean deleteUser(String username) {
        int status = 0;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM users WHERE username = ?");
            pstmt.setString(1, username);
            status = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status == 1;
    }
    
     /**
   * This method is used to find a specific user in a database. 
   * @param username The user's username
   * @exception SQLException 
   * @see SQLException
   * @return User The user in the database or null if he's not in
   */    

    @Override
    public User getUser(String username) {
        ResultSet rs = null;
        User user = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            while(rs.next()){
            String nameDb = rs.getString("name");
            String lastnameDb = rs.getString("lastname");
            String usernameDb = rs.getString("username");
            String emailDb = rs.getString("email");
            String passwordDb = rs.getString("password");
            user = new User(nameDb, lastnameDb, usernameDb, emailDb, passwordDb);

        }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
        
    }

     /**
   * This method is used to update a specific user information in a database.
   * He we decided to update his password
   * @param user The user object
   * @param newName The user's new name
   * @param newLastname The user's new lastname
   * @param newPassword The new password to set
   * @exception SQLException
   * @see SQLException
   * @return boolean If the action could be perform or not
   */  
    
    @Override
    public boolean updateUser(User user, String newName, String newLastname, String newPassword) {
        int status = 0;
        try {
            String username = user.getUsername();
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE users SET password = ?, name = ?, lastname = ?"+"WHERE username = ?");
            pstmt.setString(1, newPassword);
            pstmt.setString(2, newName);
            pstmt.setString(3, newLastname);
            pstmt.setString(4, username);
            status = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status == 1;
    }

}
