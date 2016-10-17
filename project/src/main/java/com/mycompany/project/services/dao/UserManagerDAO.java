///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.mycompany.project.services.dao;
//
//import com.mycompany.project.model.User;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.Resource;
//import javax.ejb.Stateless;
//import javax.sql.DataSource;
//
///**
// * 
// * @author Sekley Pascal <pascal.sekley@heig-vd.ch>
// */
//
//@Stateless
//public class UserManagerDAO implements IUserManagerDAO {
//    
//    @Resource(lookup = "java:/jdbc/amtusers")
//    private DataSource dataSource;
//
//    @Override
//    public boolean register(User user) {
//        String username = user.getUsername();
//        String email = user.getEmail();
//        String password = user.getPassword();
//        
//        if (registrationPossible(username)) {
//            try {
//                Connection connection = dataSource.getConnection();
//                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users VALUES(username, email, password)");
//                ResultSet rs = pstmt.executeQuery();
//            } catch (SQLException ex) {
//                Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public User authenticate(String username, String password) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean registrationPossible(String username) {
//        boolean isPresent = false;
//            try {
//                Connection connection = dataSource.getConnection();
//                PreparedStatement pstmt = connection.prepareStatement("IF EXISTS SELECT * FROM users WHERE username = username");
//                ResultSet rs = pstmt.executeQuery();
//                
//                isPresent = !rs.next();
//            } catch (SQLException ex) {
//                Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return isPresent;
//
//    }
//
//    @Override
//    public List<User> getRegisteredUsers() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean deleteUser(String username) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public User getUser(String username) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean updateUser(User user) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//}
