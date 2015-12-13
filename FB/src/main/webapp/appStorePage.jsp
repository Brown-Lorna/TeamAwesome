<%-- 
    Document   : adminStorePage
    Created on : Dec 12, 2015, 5:38:50 PM
    Author     : TeamAwesome
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    String title = "Greetably - Admin Access";
    boolean loggedStore = false;
    int userType = -1;
%>
<%
    session.setAttribute("indexClass", "Page");
    session.setAttribute("adminClass", "Page");
    if (session.getAttribute("logged") == null || !(Boolean) session.getAttribute("logged")) {
        loggedStore = false;
    } else {
        loggedStore = true;
    }

    if (session.getAttribute("userType") == null) {
        userType = -1;
    } else {
        userType = (Integer) session.getAttribute("userType");
    }
%>
<%@ include file="header.jsp" %>
<div id="MainDiv">
    <header id="banner"> 
    </header>
    <div id="app"><image src="img/greetably-logo.png" alt="Greetably Logo"  style="width:200px;height:200px;float:left;">
        <a href="http://java-gshawm.rhcloud.com/FB-1.0-SNAPSHOT">Try Greetably Now!</a>
    </div>
    <div id="appInfo">
        <p>Category: Photo Sharing</p>
    </div>

    <div class="tabs">
        <div class="tab">
            <input type="radio" id="tab-1" name="tab-group-1" checked>
            <label for="tab-1">What is it?</label>

            <div class="content">
                <h3>Share holiday images and greetings to your Facebook page.</h3>
                <div style="margin-left: 200px;">
                    <ul style="list-style-type: circle; list-style-position: outside;">
                        <li>Free</li>
                        <li>Suitable for Everyone</li>
                        <li>No need to download anything</li>
                        <li>Easy place to find beautiful holiday greetings</li>
                        <li>Easy to share with your friends</li>
                    </ul>
                </div>
            </div> 
        </div>

        <div class="tab">
            <input type="radio" id="tab-2" name="tab-group-1">
            <label for="tab-2">Usage</label>

            <div class="content">
                <h1>How To Use Greetably</h1>
                Greetably makes it easy to post images and greetings to your Facebook page.</br>
                <ol>
                    <li>Navigate to the home page.</li>
                    <li>Click on an image to share on your Facebook page.</li>
                    <li>You may optionally write a message in the text box provided.</li>
                    <li>Click on the "Share Image to Facebook" button.</li>
                    <li>If you are not logged into Facebook, it will prompt you to log in now.</li>
                    <li>You are done! Your image and message are now shared.</li>
                    <li>If you wish, check your Facebook page to see the image!</li>
                </ol>
            </div> 
        </div>

        <div class="tab">
            <input type="radio" id="tab-3" name="tab-group-1">
            <label for="tab-3">Tab Three</label>

            <div class="content">
                stuff
            </div> 
        </div>
    </div
</div>
<%@ include file="footer.jsp" %>