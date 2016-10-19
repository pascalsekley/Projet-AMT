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
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    
    public UserDTO(){
        
    }
    
    public UserDTO(String name, String lastname, String username, String email, String password){
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email    = email;
    }
    /**
   * This method is used to get a user's name in a database. 
   * @return String The user's name
   */
    public String getName(){
        return name;
    }
    
    /**
   * This method is used to get a user's lastname in a database. 
   * @return String The user's lastname
   */
    public String getLastname(){
        return lastname;
    }

    
    public String getUsername(){
        return username;
    }
    public String getEmail(){
        return email;
    }

    /**
   * This method is used to set a user's name in a database.
   * @param name The user's name
   */
    public void setName(String name){
        this.name = name;
    }
    
  /**
   * This method is used to set a user's lastname in a database.
   * @param lastname The user's lastname
   */
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    public void setEmail(String email){
        this.email = email;
    }

}
