

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
        <link rel="stylesheet" type="text/css" href="http://www.w3schools.com/lib/w3.css" />
        <link rel="stylesheet" type="text/css" href="css/image-picker.css" />
        <link rel="stylesheet" type="text/css" href="css/styles.css" />
        <script type="text/javascript" src="js/jquery-1.11.3.min.js" ></script>
        <script type="text/javascript" src="js/image-picker.min.js" ></script>
    </head>
    <body>
        <img src="img/christmas-bow.png" style="position: absolute; top: 0; left: 0; width: 200px;" />
        <nav id="NavBar">
            <h1 style="font-family: 'Colonna MT', serif; font-weight: normal;">
                <a href="index.jsp"><span style='font-size: 50px;'>G</span>REETABLY</a></h1>
            <ul>                        
                <li class="<%= session.getAttribute("indexClass")%>"><a href="index.jsp">HOME</a></li>
                <li class="<%= session.getAttribute("adminClass")%>"><a href="admin.jsp">ADMIN</a></li>
                <c:if test="<%= loggedHeader%>">
                    <li><a href="Logout">LOGOUT</a></li>
                </c:if>
            </ul>
        </nav>
        <br/><br/>