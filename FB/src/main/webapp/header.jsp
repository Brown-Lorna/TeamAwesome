<%-- 
    Document   : header
    Created on : Nov 20, 2015, 8:50:15 PM
    Author     : dan
--%>

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
        <title><!-- TODO: Dynamically add title --></title>
        <link rel="stylesheet" type="text/css" href="css/styles.css" />
    </head>
    <body>
    <center>
        <!-- TODO: index.jsp/welcome.jsp (Only if user is logged in) link -->
        <!-- TODO: logout.jsp link (Only appear if user is logged in) -->
        <h2>Testing Layout</h2>
        <p>This site has been visited <%= pageCount%> times.</p>
    </center>
    <br/><br/>