<%-- 
    Document   : StudentAbout
    Created on : Feb 29, 2024, 3:56:20 PM
    Author     : minhn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <style>
        body {
            background-color: white;
            font-family: Arial, Helvetica, sans-serif;
            line-height: 1.8;
            font-size: 15px;
            color: #444444;
        }
        .header{
            background-color: #f36f21;
        }
        input[type=submit]:hover {
            background-color: #ff6600;
        }
        ul {
            display: block;
            list-style: none;
        }
        .header{
            margin: 0;
            padding: 0;
        }
        .header a:hover {
            text-decoration: none;
            color: black;
        }
        .navbar__left{
            list-style: none;
            display: flex;
            justify-content: center;
            width: 100%;
            height: 50px;
            margin: 0px;
            padding: 0px;
        }
        .navbar__left__link{
            padding: 8px;
            border: 1px solid #ddd;
            text-align: center;
        }
        .navbar__left__link a{
            color: black;
            text-decoration: none;
        }
        .navbar__left__link:hover {
            background-color: #f5f5f5;
        }
        .footer {
            color: black;
            text-align: center;
        }
        .user-info-container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
        }

        .user-avatar {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            overflow: hidden;
            margin-bottom: 15px;
        }
        .user-avatar img{
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .user-name {
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .user-details {
            font-size: 16px;
            color: #555;
        }
    </style>
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link rel="stylesheet" href="css/bootstrap-grid.min.css"/>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/styles.css"/>
    <title>JSP Page</title>

</head>
<body>
    <div class="header" >
        <ul class="navbar__left">
            <li class="navbar__left__link col-md-2"><a href="/project1/student">Home</a></li>
            <li class="navbar__left__link col-md-2"><a href="/project1/weeklytime">Weekly Timetable</a></li>
            <li class="navbar__left__link col-md-2"><a href="/project1/subjecttime">Subject Timetable</a></li>
            <li class="navbar__left__link col-md-2"><a href="/project1/attendance">Attended Report</a></li>
            <li class="navbar__left__link col-md-2"><a href="#contact">Exam Schedule</a></li>
            <li class="navbar__left__link col-md-2"><a href="#">About</a></li>
        </ul>
    </div>
    <div class="user-info-container">
        <div class="user-avatar">
            <!-- Đặt URL của ảnh người dùng trong thuộc tính src -->
            <img src="img/avatar.jpg" alt="User Avatar">
        </div>
        <div class="user-name">${sessionScope.student.getName()}</div>
        <div class="user-details">
            <p>Email: ${sessionScope.student.getEmail()}</p>
            <p>ID: ${sessionScope.student.getId()}</p>
            <p>Password:******** <a href="change?type=password">Change password</a></p>
            <p>Phone: ${sessionScope.student.getPhone()}<a href="change?type=phone">Change phone</a></p>            
            <p><a href="/project1/logout">Log Out</a></p>
        </div>
    </div>
</body>
</html>
