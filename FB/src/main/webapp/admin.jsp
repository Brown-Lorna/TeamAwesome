

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
        userType = (Integer) session.getAttribute("userType");
    }
%>
<%@ include file="header.jsp" %>
<c:if test="${empty redirect}">
    <script> location.href = "LoadImages?dest=admin.jsp";</script>
</c:if>
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
            <c:if test="${imageDeleted}">
                <h3 class="Success">Image was deleted from database!</h3>
            </c:if>
            <c:if test="${deletedError}">
                <h3 class="ErrorMessage">Cannot delete image. Permissions are incorrect.</h3>
            </c:if>
            <div id="upload">
                <h1>Upload an Image to the Database</h1>
                <form name="addImageForm" action="AddImage" method="post" enctype="multipart/form-data">

                    <label for="image">Choose an image</label>
                    <input type="file" name="image" accept="image/*" /><br />

                    <label for="title">Give the Image a Title</label>
                    <input type="text" name="title" id="title" /><br />

                    <input name="submit" type="submit" value="Upload Image">
                </form>
            </div>
            <c:if test="<%= userType == 0%>">
                <h1>Delete an Image to the Database</h1>
                <form name="deleteImageForm" action="DeleteImage" method="post">
                    <input id="imageId" type="hidden" name="imageId" value="null" />
                    <select id="images">
                        <%! int count;%>
                        <% count = 1;%>
                        <c:forEach var="image" items="${images}">
                            <option value="${image.id}" data-img-src="BytesToImage?id=${image.id}">Image <%= count%> - ${image.name}</option>
                            <% count++;%>
                        </c:forEach>
                    </select>

                    <script>
                        var currentImage = null;
                        $("#images").imagepicker({
                            show_label: true,
                            initialized: function () {
                                currentImage = $("input#imageId").first();
                                currentImage.attr("value", this.select[0][0].attributes.value.value);
                            },
                            selected: function (option) {
                                currentImage.attr("value", option.option[0].attributes.value.value);
                            }
                        });
                    </script>

                    <input type="submit" name="delete" value="Delete Image" />
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
