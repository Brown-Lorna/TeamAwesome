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
        <title>Team Awesome Facebook Thing</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css" />
    </head>
    <body>
    <center>
        <h2>Testing Layout</h2>
        <p>This site has been visited <%= pageCount%> times.</p>
    </center>
    <br/><br/>