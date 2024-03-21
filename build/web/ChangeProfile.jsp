<%-- 
    Document   : ChangeProfile
    Created on : Mar 21, 2024, 2:42:12 PM
    Author     : minhn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Change Profile</title>
    </head>
    <body>


        <c:set var="type" value="${param.type}" />

        <c:choose>
            <c:when test="${type eq 'password'}">
                <h2>Change Password</h2>
                <form action="change" method="post">
                    <label for="currentPassword">Current Password:</label><br>
                    <input type="password" id="currentPassword" name="currentPassword" required><br>
                    <label for="newPassword">New Password:</label><br>
                    <input type="password" id="newPassword" name="newPassword" required><br>
                    <input type="hidden" name="type" value="${type}">
                    <input type="submit" value="Change Password">
                </form>
                ${error}
            </c:when>
            <c:when test="${type eq 'phone'}">
                <h2>Change Phone Number</h2>
                <form action="change" method="post">
                    <label for="phone">Phone:</label><br>
                    <input type="text" id="phone" name="phone" required pattern="[0-9]{10,11}"><br>            
                    <input type="hidden" name="type" value="${type}">
                    <input type="submit" value="Submit">
                </form>
                ${error}
            </c:when>
            <c:otherwise>
                <p>Invalid type.</p>
            </c:otherwise>
        </c:choose>

    </body>
</body>
</html>
