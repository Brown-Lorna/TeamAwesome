<%-- 
    Document   : login
    Created on : Nov 10, 2015, 8:27:09 PM
    Author     : Grant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        
        <link type="text/css" rel="stylesheet" href="styles.css" />
    </head>
    <body>
        <div>
            <h1>Login</h1>
            
            <c:if test="${incorrectCredentials}">
                <h2 id="wrongCredentials">
                    Incorrect Username or Password!
                </h2>
            </c:if>
            
            <form method="post" action="Login">
                <label for="Username">Username</label>
                <input id="Username" name="username" type="text" /> <br />
                
                <label for="Password">Password</label>
                <input id="Password" name="password" type="password" /> <br />
                
                <input type="submit" value="Submit" name="submit" />
            </form>
        </div>
    </body>
</html>
