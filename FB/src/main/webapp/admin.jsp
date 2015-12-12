

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    String title = "Greetably - Admin Access";
    boolean loggedAdmin = false;
    int userType = -1;
%>
<%
    session.setAttribute("indexClass", "Page");
    session.setAttribute("adminClass", "CurrentPage");
    if (session.getAttribute("logged") == null || !(Boolean) session.getAttribute("logged")) {
        loggedAdmin = false;
    } else {
        loggedAdmin = true;
    }

    if (session.getAttribute("userType") == null) {
        userType = -1;
    } else {
        userType = (Integer)session.getAttribute("userType");
    }
%>
<%@ include file="header.jsp" %>
    <div id="MainDiv">
        <c:if test="${errorExists}">
            <h3 class="ErrorMessage">${error}</h3>
        </c:if>
        <c:choose>
            <c:when test="<%= loggedAdmin%>">
                <c:if test="${imageAdded}">
                    <h3 class="Success">Image was uploaded successfully!</h3>
                </c:if>
                <c:if test="${addedError}">
                    <h3 class="ErrorMessage">Error in uploading image!</h3>
                </c:if>
                <h1>Add Image</h1>
                <form name="addImageForm" action="AddImage" method="post" enctype="multipart/form-data">
                    <label for="title">Title of Image</label>
                    <input type="text" name="title" id="title" /><br />
                    
                    <label for="image">Choose an image</label>
                    <input type="file" name="image" accept="image/*" /><br />

                    <input name="submit" type="submit" value="Add Image">
                </form>
                <c:if test="<%= userType == 0%>">
                    <h1>Delete Image</h1>
                    <form name="deleteImageForm" action="" method="post">
                        
                    </form>
                </c:if>
            </c:when>
            <c:otherwise>
                <h1>Login</h1>
                <form name="logForm" action="Login" method="post">
                    <label for="username">Username</label>
                    <input name="username" id="username" type="text" /><br />
                    <label for="password">Password</label>
                    <input name="password" id="password" type="password" /><br />

                    <input name="submit" type="submit" value="Login" />
                </form>
            </c:otherwise>
        </c:choose>
    </div>

<%@ include file="footer.jsp" %>
