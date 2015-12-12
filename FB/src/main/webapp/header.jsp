

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
    boolean loggedHeader = false;
%>
<%
    if (session.getAttribute("logged") == null || !(Boolean)session.getAttribute("logged")) {
        loggedHeader = false;
    } else {
        loggedHeader = true;
    }
%>
<html>
    <head>
        <title><%=title%></title>
        <link rel="stylesheet" type="text/css" href="css/image-picker.css" />
        <link rel="stylesheet" type="text/css" href="css/styles.css" />
        <script type="text/javascript" src="js/jquery-1.11.3.min.js" ></script>
        <script type="text/javascript" src="js/image-picker.min.js" ></script>
    </head>
    <body>
        <nav id="NavBar">
            <h1>GREETABLY</h1>
            <ul>                        
                <li class="<%= session.getAttribute("indexClass")%>"><a href="index.jsp">HOME</a></li>
                <li class="<%= session.getAttribute("adminClass")%>"><a href="admin.jsp">ADMIN</a></li>
                <c:if test="<%= loggedHeader%>">
                    <li><a href="Logout">LOGOUT</a></li>
                </c:if>
            </ul>
        </nav>
        <br/><br/>