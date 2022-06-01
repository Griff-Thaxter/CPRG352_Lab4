<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello World Form</title>
    </head>
    <body>
        <!-- This is an HTML comment. It is not rendered by the browser, but it is visible in the source code. -->
        <%-- THis is a JSP comment. It is not rendered by the browser, nor is it visible in the source code. --%>
        <h1>Hello World!</h1>
        <form method="POST" action="hello">
            <label>First Name:</label>
            <input type="text" name="first_name" value="${firstName}">
            <br>
            <label>Last Name:</label>
            <input type="text" name="last_name" value="${lastName}">
            <br><br>
            <input type="submit" value="Say Hello!">
        </form>
        <c:if test="${error == true}">
        <p>Please enter both a first and last name.</p>
        </c:if>
    </body>
</html>
