/*
 -----------------------------------------------------------------------------------
 Project 	 : Projet AMT
 File     	 : RESTApplication.java
 Author(s)       : Pascal Sekley & Rodrigue Tchuensu 
 Date            : Start: 21.09.16 - End:  
 Purpose         : The goal of this class is to define 
                   caracteristics are.
 remark(s)       : n/a
 Compiler        : jdk 1.8.0_101
 -----------------------------------------------------------------------------------
 */

package com.mycompany.project.model.rest;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * <h1> REST Application </h1>
 * This class implement the configuration of the application
 * @author Pascal Sekley & Rodrigue Tchuensu
 * @version 1.0
 * @since 2016-10-19
 */
@ApplicationPath("/api")
public class RESTApplication extends Application{
    
    @Override
    public Map<String, Object> getProperties(){
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.disableMoxyJson", true);
        return properties;
    }
}
