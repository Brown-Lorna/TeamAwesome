
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- TODO: Create a way to set a title in the header.jsp. -->
<%
    session.setAttribute("indexClass", "CurrentPage");
    session.setAttribute("adminClass", "Page");
%>
<%! String title = "Greetably";%>
<%@ include file="header.jsp" %>
    <c:if test="${empty redirect}">
        <script>
            location.href = "LoadImages";
        </script>
    </c:if>
    <!-- TODO: Register User Form -->
    <!-- TODO: Edit Login Form -->


    <div id="MainDiv">
        <c:if test="${imageShared}">
            <h3 class="Success">Image was successfully shared to your Facebook timeline!</h3>
        </c:if>
        <c:if test="${errorExists}">
            <h3 class="ErrorMessage">${error}</h3>
        </c:if>
            
        <h1><a id="log">Share an Image to Facebook!</a></h1>
            
        <form method="post" action="FaceBookLogin">
            <input id="imageId" type="hidden" name="imageId" value="null" />
            <select id="images">
                <%! int count; %>
                <% count = 1; %>
                <c:forEach var="image" items="${images}">
                    <option value="${image.id}" data-img-src="BytesToImage?id=${image.id}">Image <%= count%> - ${image.name}</option>
                    <% count++; %>
                </c:forEach>
            </select>

            <script>
                var currentImage = null;
                $("#images").imagepicker({
                    show_label: true,
                    initialized: function() {
                        currentImage = $("input#imageId").first();
                        currentImage.attr("value", this.select[0][0].attributes.value.value);
                        console.log(this);
                    },
                    selected: function(option) {
                        console.log(option);
                        currentImage.attr("value", option.option[0].attributes.value.value);
                    }
                });
            </script>
            
            <label for="message">Message</label><br />
            <textarea rows="10" cols="40" id="message" name="message"></textarea><br />

            <input type="submit" name="share" value="Share Image" />
        </form>

        <script>
            $("#log").click(function (event) {
                event.preventDefault();
                $("#login").toggleClass("hidden");
            });
            $("#reg").click(function (event) {
                event.preventDefault();
                $("#register").toggleClass("hidden");
            });
        </script>
    </div>
<%@ include file="footer.jsp" %>
