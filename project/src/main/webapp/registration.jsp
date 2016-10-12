<!--
        Author: W3layouts
        Author URL: http://w3layouts.com
        License: Creative Commons Attribution 3.0 Unported
        License URL: http://creativecommons.org/licenses/by/3.0/
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        <title>Simple Login Form a Flat Responsive Widget Template :: w3layouts</title>
        <link rel="stylesheet" href="inc/css/style.css">

        <link href='//fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

        <!-- For-Mobile-Apps-and-Meta-Tags -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Simple Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- //For-Mobile-Apps-and-Meta-Tags -->

    </head>

    <body>
        <h1> Projet AMT</h1>
        <div class="container w3">
            <h2>Create your account Now</h2>
            <form action="RegistrationServlet" method="post">
                <div class="username">
                    <span class="username">Username:</span>
                    <input type="text" name="username" class="name" placeholder="" required="">
                    <div class="clear"></div>
                </div>
                <div class="username">
                    <span class="username">E-mail:</span>
                    <input type="email" name="email" class="name" placeholder="" required="">
                    <div class="clear"></div>
                </div>
                <div class="password-agileits">
                    <span class="username">Password:</span>
                    <input type="password" name="password" class="password" placeholder="" required="">
                    <div class="clear"></div>
                </div>
                    
                    <div class = "erreur">
                        <h2><font color="red"> ${errorMessage} </font> <h3> <br><br>
                    </div>
                
                <div class="login-w3">
                    <input type="submit" class="login" value="Sign Up">
                </div>
                
                <div class="clear"></div>
                
            </form>
        </div>

    </body>
</html>


