<%-- 
    Document   : index
    Created on : Nov 20, 2015, 8:55:05 PM
    Author     : dan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- TODO: Create a way to set a title in the header.jsp. -->
<%@ include file="header.jsp" %>
<center>
    <!-- TODO: Register User Form -->
    <!-- TODO: Edit Login Form -->
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
</center>
<%@ include file="footer.jsp" %>
