

<%@page import="sun.security.krb5.Confounder.bytes(int)"%>
<%@page import="sun.security.krb5.Confounder.bytes(int)"%>
<%@page import="java.io.InputStream"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- TODO: Create a way to set a title in the header.jsp. -->
<%
    session.setAttribute("indexClass", "CurrentPage");
    session.setAttribute("adminClass", "Page");
%>
<%! String title = "Greetably";%>
<%@ include file="header.jsp" %>

    <!-- TODO: Register User Form -->
    <!-- TODO: Edit Login Form -->

    
        <h1><a id="log">Login</a></h1>

        <c:if test="${incorrectCredentials}">
            <h2 id="wrongCredentials">
                Incorrect Username or Password!
            </h2>
        </c:if>

        <div id="login" class="form hidden">

            <form method="post" action="Login">
                <label for="Username">Username</label>
                <input id="Username" name="username" type="text" /> <br />

                <label for="Password">Password</label>
                <input id="Password" name="password" type="password" /> <br />

                <input type="submit" value="Login" name="login" />
            </form>
        </div>

        <form method="post" action="FaceBookLogin">
            <label for="image">Image Id</label>
            <input id="image" name="image" type="text" /> <br />

            <label for="message">Message</label>
            <textarea id="message" name="message"></textarea>

            <input type="submit" name="share" value="Share Image" />
        </form>
        

        <c:forEach var="image" items="${images}">
            
            <p>
               
                <img src="bytesToImage?id=${image.id}" title="${image.name}" alt="${image.id}">
                
            </p>
        </c:forEach>
    
    <!--<div>
            <h1><a id="reg">Register</a></h1>
            
            <div id="register" class="form hidden">
                
                <form method="post" action="RegisterUser">
                    <label for="Username2">Enter a Username</label>
                    <input id="Username2" name="username" type="text" /> <br>
    
                    <label for="Password2">Enter Password</label>
                    <input id="Password2" name="password" type="password" /> <br>
    
                    <label for="Password3">Confirm Password</label>
                    <input id="Password3" name="confirm" type="password" /> <br>
    
                    <input type="submit" value="Register" name="register" />
                </form>
            </div>
        </div>
    -->

<script>
    $("#log").click(function (event) {
        event.preventDefault();
        $("#login").toggleClass("hidden");
    });
    $("#reg").click(function (event) {
        event.preventDefault();
        $("#register").toggleClass("hidden");
    });
</script>
<%@ include file="footer.jsp" %>
