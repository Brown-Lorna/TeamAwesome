<%-- 
    Document   : adminStorePage
    Created on : Dec 12, 2015, 5:38:50 PM
    Author     : TeamAwesome
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    String title = "Greetably - App Store Page";
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
    <div class="tabs">
        <div class="tab">
            <input type="radio" id="tab-1" name="tab-group-1" checked>
            <label for="tab-1">What is it?</label>

            <div class="content">
                <image src="img/greetably-logo.png" alt="Greetably Logo"  style="width:200px;height:200px;float:left;">
                <h3>Want to share Christmas spirit with your Facebook friends? Use Greetably! 
                    Share holiday images and greetings to your Facebook page and let your friends
                    in on the holiday spirit!</h3>
                <div style="margin-left: 20px;">
                    <ul style="list-style-type: circle;">
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
                    <li>Make sure that you are logged out of your Facebook.</li>
                    <li>Navigate to the home page.</li>
                    <li>Click on an image to share on your Facebook page.</li>
                    <img src="img/home.png" alt="Home" style="height:200px;" />
                    <li>You may optionally write a message in the text box provided.</li>
                    <li>Click on the "Share Image to Facebook" button.</li>
                    <img src="img/shareImage.png" alt="Share Image" style="height:200px;" />
                    <li>Log into the test user Facebook using the credentials provided on the lesson 13.2-Weekly Developer Forum.</li>
                    <img src="img/facebookLogin.png" alt="Share Image" style="height:200px;" />
                    <li>You are done! Your image and message are now shared.</li>
                    <li>If you wish, check your Facebook page to see the image!</li>
                </ol>
            </div> 
        </div>

        <div class="tab">
            <input type="radio" id="tab-3" name="tab-group-1">
            <label for="tab-3">Admin Instructions</label>

            <div class="content">
                <h1>How To Use Greetably As An Admin</h1>
                CS 313 students have additional privileges:
                <ol>
                    <li>Navigate to the admin page.</li>
                    <li>Login using the credentials given on the lesson 13.2-Weekly Developer Forum.</li>
                    <img src="img/login.png" alt="Share Image" style="height:200px;" />
                    <li>Now you may add an image to the database!</li>
                    <li>Click the button to browse for an image.</li>
                    <li>Type the title of the image.</li>
                    <li>Click "Upload Image" to upload the image to the database!</li>
                    <img src="img/addImage.png" alt="Share Image" style="height:200px;" />
                </ol>
            </div> 
        </div>
    </div>
    <h1><a href="http://java-gshawm.rhcloud.com/FB-1.0-SNAPSHOT">Click here to try Greetably now!</a></h1>
</div>
<%@ include file="footer.jsp" %>