/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.project.model.rest.dto;

/**
 * 
 * @author Sekley Pascal <pascal.sekley@heig-vd.ch>
 */
public class UserDTO {
    private String username;
    private String email;
    private String password;
    
    public UserDTO(){
        
    }
    
    public UserDTO(String username, String email, String password){
        this.username = username;
        this.email    = email;
    }
    
    public String getUsername(){
        return username;
    }
    public String getEmail(){
        return email;
    }

    
    public void setUsername(String username){
        this.username = username;
    }
    public void setEmail(String email){
        this.email = email;
    }

}
