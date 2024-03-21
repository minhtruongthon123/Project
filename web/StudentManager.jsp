<%-- 
    Document   : StudentManager
    Created on : Mar 21, 2024, 7:10:02 AM
    Author     : minhn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="stylesheet" href="css/bootstrap-grid.min.css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/styleSMS.css"/>
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

                bottom:0;
                width:100%;
                height:60px;   /* Height of the footer */
                text-align: center;
            }
        </style>
        <title>JSP Page</title>
    </head>
    <body id="body">
        <div class="header" >
            <ul class="navbar__left">
                <li class="navbar__left__link col-md-2"><a href="/project1/teacherhome.jsp">Home</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/teaching">Teaching schedule</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/teacherattendace">Attended</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/report">Attended Report</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/manager">Student</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/studentabout.jsp">About</a></li>
            </ul>
        </div>
        <div class="maincontainer">             
            <div class="container1">
                <h1>--Student Management System--</h1>
                <form action="manager" method="get">
                    Student Name:
                    <input type="text"  name="search" value="">
                    <input type="submit" value="Search" />
                </form>
                <div id="content">
                    <h2>Student Report</h2>
                    <table id="attendanceTable">
                        <thead>
                        <th>Student ID</th>
                        <th>Student Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Action</th>
                        </thead>
                        <tbody>
                        <tbody>
                            <c:forEach var="manager" items="${requestScope.listManager}">
                                <tr>
                                    <td>${manager.student.getId()}</td>
                                    <td>${manager.student.getName()}</td>
                                    <td>${manager.student.getEmail()}</td>
                                    <td>${manager.student.getPhone()}</td>
                                    <td>
                                        <table>
                                            <c:set var="ID" value="${manager.student.getId()}"/>
                                            <form action="remove" method="post" > 
                                                <c:forEach var="group" items="${manager.group}">
                                                    <input type="hidden" name="groupID" value="${group.getID()}" />
                                                    <input type="hidden" name="studentID" value="${ID}" />
                                                    
                                                    <tr>
                                                        <td>${group.getName()}</td>
                                                        <td>${group.getGroupName()}</td>
                                                        <td><input type="submit" value="REMOVE" /></td>
                                                    </tr>
                                                    
                                                </c:forEach>
                                            </form>
                                        </table>
                                    </td>
                                </tr>
                            </c:forEach>


                        </tbody>

                        </tbody>
                    </table>
                </div>
            </div>
        </div><br>
        <div class="footer">
            <p>
                Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: dichvusinhvien@fe.edu.vn. Điện thoại: (024)7308.13.13
            </p>
            <p>
                © Powered by FPT University |  CMS |  library |  books24x7
            </p>
        </div>
    </body>
</html>
