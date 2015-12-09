

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title><%=title%></title>
        <link rel="stylesheet" type="text/css" href="css/styles.css" />
        <script type="text/javascript" src="js/jquery-1.11.3.min.js" ></script>
    </head>
    <body>
        <nav id="NavBar">
            <ul>                        
                <li class="<%= session.getAttribute("indexClass")%>"><a href="index.jsp">HOME</a></li>
                <li class="<%= session.getAttribute("adminClass")%>"><a href="admin.jsp">ADMIN</a></li>
                <c:if test="<%= (Boolean)session.getAttribute("logged")%>">
                    <li><a href="Logout">LOGOUT</a></li>
                </c:if>
            </ul>
        </nav>
        <br/><br/>