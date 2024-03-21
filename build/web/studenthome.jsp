<%-- 
    Document   : studenthome
    Created on : Jan 8, 2024, 2:25:38 PM
    Author     : minhn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <style>
        body {
            background-color: #e2e2e2;
            font-family: Arial, Helvetica, sans-serif;
            line-height: 1.8;
            font-size: 15px;
            color: #444444;
        }
        .header{
            background-color: #f36f21;
        }
        .container {
            background-color: #ffffff;
            margin: 10px auto auto ;
            padding: 60px;
            width: 500px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.3);
            text-align: center;
        }
        input[type=submit]:hover {
            background-color: #ff6600;
        }
        .navbar__left__link{
            padding: 20px;
        }
        .header_row{
            padding: 0px;
            text-align: left;
        }
        ul {
            display: block;
            list-style: none;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        th:hover, td:hover {
            background-color: #f5f5f5;
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
        .navbar__left__link a:hover{
            color: black;
            text-decoration: none;
        }
        .navbar__left__link:hover {
            background-color: #f5f5f5;
        }

        .footer {
            position:absolute;
            bottom:0;
            width:100%;
            height:60px;   /* Height of the footer */
            text-align: center;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="stylesheet" href="css/bootstrap-grid.min.css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <title>Home</title>

    </head>
    <body>
        <div class="header" >
            <ul class="navbar__left">
                <li class="navbar__left__link col-md-2"><a href="/project1/studenthome.jsp">Home</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/weeklytime">Weekly Timetable</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/subjecttime">Subject Timetable</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/attendance">Attended Report</a></li>
                <li class="navbar__left__link col-md-2"><a href="#contact">Exam Schedule</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/studentabout.jsp">About</a></li>
            </ul>
        </div>
        <div class="container">
            <h1>Hello: ${sessionScope.student.getName()}</h1>

        </div>
        <div class="footer">
            <p>
                Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: dichvusinhvien@fe.edu.vn. Điện thoại: (024)7308.13.13
            </p>
            <p>
                © Powered by FPT University |  CMS |  library |  books24x7
            </p>

        </div>

</html>
