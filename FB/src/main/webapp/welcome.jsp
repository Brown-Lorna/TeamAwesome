

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! String title = "Welcome!";%>
<%@ include file="header.jsp" %>
<!-- TODO: Add Image to database link -->
<center>
    <form action="AddImage">
        <input type="file" name="pic" accept="image/*">
        <input type="submit">
    </form>
    <!-- TODO: Show images from database -->
    <c:forEach var="image" items="${images}">
        <p>
            <a href=""></a>
        </p>
    </c:forEach>
    <!-- TODO: Share selected image to Facebook -->
    
    
</center>

<%@ include file="footer.jsp" %>
