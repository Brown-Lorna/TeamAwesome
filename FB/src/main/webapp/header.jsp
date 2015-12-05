

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
    int pageCount = 0;
    
    void addCount() {
        pageCount++;
    }
%>
<% addCount();%>
<html>
    <head>
        <title><%=title %></title>
        <link rel="stylesheet" type="text/css" href="css/styles.css" />
        <script type="text/javascript" src="js/jquery-1.11.3.min.js" ></script>
    </head>
    <body>
        <div id="header">
            <h1>Greetably</h1>
            <div>
                
            </div>
        </div>
    <center>
        <!-- TODO: index.jsp/welcome.jsp (Only if user is logged in) link -->
        <!-- TODO: logout.jsp link (Only appear if user is logged in) -->
        <h2>Testing Layout</h2>
        <p>This site has been visited <%= pageCount%> times.</p>
    </center>
    <br/><br/>