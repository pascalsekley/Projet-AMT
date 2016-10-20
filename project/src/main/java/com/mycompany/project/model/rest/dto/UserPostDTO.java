/*
 -----------------------------------------------------------------------------------
 Project 	 : Projet AMT
 File     	 : UserPostDTO.java
 Author(s)       : Pascal Sekley & Rodrigue Tchuensu 
 Date            : Start: 21.09.16 - End:  
 Purpose         : The goal of this class is the same as UerDTO
 remark(s)       : n/a
 Compiler        : jdk 1.8.0_101
 -----------------------------------------------------------------------------------
 */

package com.mycompany.project.model.rest.dto;

/**
 * 
 * @author Sekley Pascal <pascal.sekley@heig-vd.ch>
 */
public class UserPostDTO {
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    
    public UserPostDTO(){
        
    }
    
    /**
     * Class constructor
     * @param name     User's name
     * @param lastname User's lastname
     * @param username User's username
     * @param email    User's email
     * @param password User's password
     */
    public UserPostDTO(String name, String lastname, String username, String email, String password){
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email    = email;
        this.password = password;
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
   * @return String The user's lastname
   */
    public String getUsername(){
        return username;
    }
    
   /**
   * This method is used to get a user's email in a database. 
   * @return String The user's lastname
   */
    public String getEmail(){
        return email;
    }
    
   /**
   * This method is used to get a user's password in a database. 
   * @return String The user's lastname
   */
    public String getPassword(){
        return password;
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
   * @param username The user's username
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
    
   /**
   * This method is used to set a user's password in a database.
   * @param password The user's password
   */
    public void setPassword(String password){
        this.password = password;
    }

}
