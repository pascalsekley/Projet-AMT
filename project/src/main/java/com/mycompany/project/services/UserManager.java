/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.project.services;

import com.mycompany.project.model.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Pascal Sekley
 */
public class UserManager {
    
    private User user;
    private ArrayList tabUsers = new ArrayList();
    private static  Map<String,String> registeredUsersCredentials = new HashMap<String, String>();
    private static List<User> RegisteredUsers = new LinkedList<User>();
    private String erreur = "error field works fine";
    
   
    public static final String USER_NAME_FIELD = "username";
    public static final String PASSWORD_FIELD = "password";
    public static final String EMAIL_FIELD = "email";

//    public UserManager(String username, String email, String password/*, HttpServletResponse response*/) throws IOException {
//        user.setUsername(username);
//        user.setEmail(email);
//        user.setPassword(password);
//        
//        this.addUser();
//        
//    }
    
    
    public boolean register(HttpServletRequest request){
        
        String username = request.getParameter(USER_NAME_FIELD);
        String password = request.getParameter(PASSWORD_FIELD);
        String email = request.getParameter(EMAIL_FIELD);
        
        
        if(registrationPossible(username)){
            User user = new  User(username, email, password);
            RegisteredUsers.add(user);
            registeredUsersCredentials.put(user.getUsername(), user.getPassword());
            //erreur = null;
            return true;
  
        }else{
            erreur = "username already in use";
            return false;
        }
        
    }
    
    public  User ConnectUser(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Ici on doit vérifier la validation des champs avant--- TO DO
        if(registeredUsersCredentials.containsKey(username) && (password.equals(registeredUsersCredentials.get(username))) ){
            erreur = null;
            return new User(username, email, password);
        }

        erreur = "wrong username or password";
        return null;
    }
    
    
    private void addUser(){
        tabUsers.add(user);
        
    }
    
    
    public boolean registrationPossible(String username){
       return !registeredUsersCredentials.containsKey(username);
    }

    public String getErreur() {
        return erreur;
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
    }
    
    

}
