/*
 -----------------------------------------------------------------------------------
 Project 	 : Projet AMT
 File     	 : UserDTO.java
 Author(s)       : Pascal Sekley & Rodrigue Tchuensu 
 Date            : Start: 21.09.16 - End:  
 Purpose         : The goal of this class is to define a user and what his
                   caracteristics are in a DTO format.
 remark(s)       : n/a
 Compiler        : jdk 1.8.0_101
 -----------------------------------------------------------------------------------
 */

package com.mycompany.project.model.rest.dto;

/**
 * <h1> User </h1>
 * The goal of this class is to define a user in a DTO format.
 * @author Pascal Sekley & Rodrigue Tchuensu
 * @version 1.0
 * @since 2016-10-19
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

    /**
   * This method is used to get a user's username in a database. 
   * @return String The user's username
   */
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
    
   /**
   * This method is used to set a user's username in a database.
   * @param username The user's lastname
   */
    public void setUsername(String username){
        this.username = username;
    }
    
   /**
   * This method is used to set a user's email in a database.
   * @param email The user's email
   */
    public void setEmail(String email){
        this.email = email;
    }

}
