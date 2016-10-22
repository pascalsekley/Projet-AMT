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
 * This class will be hosted at the URI path "/people"
 * @author Sekley Pascal & Rodrigue Tchuensu
 */
@Stateless
@Path("/people")
public class UserAccountResource {
    
    @EJB
    private IUserManagerDAO userManager;
    
    @Context
    UriInfo uriInfo;
    
    /**
     * The Java method will process HTTP GET requests produce content identified by the MIME Media
     * @param byName Name of a specific user to find
     * @return List of all the users in the database
     */
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
    
    /**
     * The Java method will process HTTP POST requests and consume a resource sent.
     * @param userPostDTO A PDO user to be created
     * @return Response message to the client
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserPostDTO userPostDTO){
        boolean result;
        User user = fromPutOrPostDTO(userPostDTO);
        result = userManager.register(user);
        if (result) {
            URI href = uriInfo
                    .getBaseUriBuilder()
                    .path(UserAccountResource.class)
                    .path(UserAccountResource.class, "getUser")
                    .build(user.getUsername());

            return Response
                    .created(href)
                    .entity("User created")
                    .build();
        }
        else{
            return Response.status(Response.Status.CONFLICT)
                    .entity("User NOT created")
                    .build();
        }
    }
    
    /**
     * The java method will process HTTP DELETE to delete a stored user
     * @param username of the user that'll help to finh him in the database
     * @return Response message to the client
     */
    @Path("/{username}")
    @DELETE 
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("username") String username) {
        if (userManager.deleteUser(username)) {
            return Response
                    .ok()
                    .entity("User deleted")
                    .build();
        } else {
            return Response
                    .status(Status.NOT_FOUND)
                    .entity("User NOT found")
                    .build();
        }
        
    }
    
    /**
     * The java method will proceed HTTP GET to get a stored user
     * @param username pathParam that identify the specific user to fetch
     * @return The user in a DTO format
     */
    @Path("/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username){
        User userToFind = userManager.getUser(username);
        if (userToFind != null) {
            return Response.ok(toDTO(userToFind))
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        
                
    }
    
    /**
     * The java method will proceed HTTP PUT to modify a specfic stored user
     * Everything can be modified a part from his username which is the unique id
     * @param username The username as pathParam of the user to modify
     * @param userPostDTO The user in a DTO format
     * @return Response message to the client
     */
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
                    .entity("User has been modified successfully")
                    .build();
        } else{
            return Response
                    .status(Status.NOT_MODIFIED)
                    .entity("Failed to modify the user: Unidentified user")
                    .build();
        }
    }
 
    /**
     * This method is used to create a user 
     * @param dto The user in a DTO format
     * @return The user
     */
    public User fromDTO(UserDTO dto){
        User myUser = new User();
        myUser.setName(dto.getName());
        myUser.setLastname(dto.getLastname());
        myUser.setUsername(dto.getUsername());
        myUser.setUsername(dto.getEmail());
        return myUser;
    }
    
    /**
     * This method is used to create a user using information from an HTTP PUT or POST
     * @param dto The dto sent in the request
     * @return The created user 
     */
    public User fromPutOrPostDTO(UserPostDTO dto){
        return new User(dto.getName(), dto.getLastname(), dto.getUsername(), dto.getEmail(), dto.getPassword());
    }
    
    /**
     * This method is used to create a DTO from a user
     * @param user The user to be in a DTO format
     * @return The user in a DTO format
     */
    public UserDTO toDTO(User user){
        UserDTO dto = new UserDTO(user.getName(), user.getLastname(), user.getUsername(), user.getEmail(), user.getPassword());
        return dto;
    }

}
