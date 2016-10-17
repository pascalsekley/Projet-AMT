/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * 
 * @author Sekley Pascal <pascal.sekley@heig-vd.ch>
 */

@Stateless
public class UserManagerDAO implements IUserManagerDAO {
    
    @Resource(lookup = "java:/jdbc/amtusers")
    private DataSource dataSource;

    @Override
    public boolean register(User user) {
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        
        if (registrationPossible(username)) {
            try {
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users VALUES(?,?,?)");
                pstmt.setString(1, username);
                pstmt.setString(2, email);
                pstmt.setString(3, password);
                int ex = pstmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public User authenticate(String username, String password) {

        ResultSet rs = null;
        User user = null;
        String usernameDb;
        String emailDb;
        String passwordDb = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                usernameDb = rs.getString("username");
                emailDb = rs.getString("email");
                passwordDb = rs.getString("password");
                user = new User(usernameDb, emailDb, passwordDb);

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

    @Override
    public List<User> getRegisteredUsers() {
        List<User> users = new ArrayList<>();
        User user = new User();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                users.add(new User(username, email, password));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

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
            String usernameDb = rs.getString("username");
            String emailDb = rs.getString("email");
            String passwordDb = rs.getString("password");
            user = new User(usernameDb, emailDb, passwordDb);

        }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
        
    }

    @Override
    public boolean updateUser(User user, String newPassword) {
        int status = 0;
        try {
            String username = user.getUsername();
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE users SET password = ?"+"WHERE username = ?");
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            status = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status == 1;
    }

}
