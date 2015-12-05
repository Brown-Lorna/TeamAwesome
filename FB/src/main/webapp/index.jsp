

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- TODO: Create a way to set a title in the header.jsp. -->
<%! String title = "Greetably";%>
<%@ include file="header.jsp" %>
<center>
    <!-- TODO: Register User Form -->
    <!-- TODO: Edit Login Form -->

    <div>
        <h1>Login</h1>
        <div id="login">
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

                <input type="submit" value="Login" name="login" />
            </form>
        </div>
    </div>
    <div>
        <h1>Register</h1>
        <div id="register">
        <form method="post" action="Register">
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
</center>
<%@ include file="footer.jsp" %>
