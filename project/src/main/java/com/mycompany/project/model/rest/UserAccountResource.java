/*
 -----------------------------------------------------------------------------------
 Project 	 : Projet AMT
 File     	 : UserAccountResource.java
 Author(s)       : Pascal Sekley & Rodrigue Tchuensu 
 Date            : Start: 21.09.16 - End:  
 Purpose         : The goal of this class 
 remark(s)       : n/a
 Compiler        : jdk 1.8.0_101
 -----------------------------------------------------------------------------------
 */

package com.mycompany.project.model.rest;

import com.mycompany.project.model.User;
import com.mycompany.project.model.rest.dto.UserDTO;
import com.mycompany.project.model.rest.dto.UserPostDTO;
import com.mycompany.project.services.dao.IUserManagerDAO;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 * 
 * @author Sekley Pascal <pascal.sekley@heig-vd.ch>
 */
@Stateless
@Path("/people")
public class UserAccountResource {
    
    @EJB
    private IUserManagerDAO userManager;
    
    @Context
    UriInfo uriInfo;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getUsers(@QueryParam(value = "byName") String byName){
        List<User> users = userManager.getRegisteredUsers();
        List <UserDTO> userDTO = new ArrayList<>();

        return users.stream()
                .filter(p -> byName == null || p.getUsername().equalsIgnoreCase(byName))
                .map(p -> toDTO(p))
                .collect(toList());
                
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserPostDTO userPostDTO){
        User user = fromPutOrPostDTO(userPostDTO);
        userManager.register(user);
        
        URI href = uriInfo
                .getBaseUriBuilder()
                .path(UserAccountResource.class)
                .path(UserAccountResource.class, "getUser")
                .build(user.getUsername());
             
        return Response
                .created(href)
                .build();           
      
    }
    @Path("/{username}")
    @DELETE 
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("username") String username) {
        if (userManager.deleteUser(username)) {
            return Response
                    .ok()
                    .build();
        } else {
            return Response
                    .status(Status.NOT_FOUND)
                    .build();
        }
        
    }
    
    @Path("/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUser(@PathParam("username") String username){
        User userToFind = userManager.getUser(username);
        return toDTO(userToFind);
                
    }
    
    @Path("/{username}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("username") String username, UserPostDTO userPostDTO){
        String newPassword = fromPutOrPostDTO(userPostDTO).getPassword();
        String newName     = fromPutOrPostDTO(userPostDTO).getName();
        String newLastname = fromPutOrPostDTO(userPostDTO).getLastname();
        User user = userManager.getUser(username);
        if(userManager.updateUser(user, newName, newLastname, newPassword)){
            return Response
                    .ok()
                    .build();
        } else{
            return Response
                    .status(Status.NOT_MODIFIED)
                    .build();
        }
    }
 
    
    public User fromDTO(UserDTO dto){
        User myUser = new User();
        myUser.setName(dto.getName());
        myUser.setLastname(dto.getLastname());
        myUser.setUsername(dto.getUsername());
        myUser.setUsername(dto.getEmail());
        return myUser;
    }
    
    public User fromPutOrPostDTO(UserPostDTO dto){
        return new User(dto.getName(), dto.getLastname(), dto.getUsername(), dto.getEmail(), dto.getPassword());
    }
    
    
    public UserDTO toDTO(User user){
        UserDTO dto = new UserDTO(user.getName(), user.getLastname(), user.getUsername(), user.getEmail(), user.getPassword());
        return dto;
    }

}
