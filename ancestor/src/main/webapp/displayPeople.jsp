<%-- 
    Document   : displayPeople
    Created on : Nov 28, 2015, 1:35:27 PM
    Author     : Theriault
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    <c:forEach var="person" items="${people}">
        <section>
            <aside>${person.first_name} - ${person.last_name}</aside>
            <aside>${person.birthay}</aside>
        </section>
    </c:forEach>

</body>
</html>
