<%-- 
    Document   : ReportAttendance
    Created on : Mar 20, 2024, 9:45:24 PM
    Author     : minhn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="stylesheet" href="css/bootstrap-grid.min.css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/styles.css"/>
        <title>Report Attendance</title>
        <style>
            body {
                background-color: #e2e2e2;
                font-family: Arial, Helvetica, sans-serif;
                line-height: 1.8;
                font-size: 15px;
                color: #444444;
            }
            .header{
                background-color: rgba(26, 160, 218, 1);
            }
            .container {
                background-color: #ffffff;
                padding: 60px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.3);
                text-align: center;
                display: flex;
                justify-content: center;
                flex-direction: column;
                margin: 0;
                text-align: center;
            }
            input[type=submit]:hover {
                background-color: #ff6600;
            }
            .navbar__left__link{
                padding: 20px;
                width: calc(((100% / 12) * 6));
            }
            .header_row{
                padding: 0px;
                text-align: left;
            }
            ul {
                display: block;
                list-style: none;
                margin-block-start: 1em;
                margin-block-end: 1em;
                margin-inline-start: 0px;
                margin-inline-end: 0px;
                padding-inline-start: 40px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
            }

            th:hover, td:hover {
                background-color: #f5f5f5;
            }
            .sloganheader{
                margin-left: 10px;
            }
            .header{
                margin: 0;
                padding: 0;
            }
            .navbar__left{
                list-style: none;
                display: flex;
                justify-content: center;
                width: 100%;
                height: 50px;
                background-color: #f36f21;
                margin: 0px;
                padding: 0px;
            }
            .navbar__left__link{
                padding: 8px;
                width: calc((100%/12)*7);
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
            .content{
                display: flex;
                justify-content: space-between;
            }
            .content table{
                justify-content: center;
                align-items: center;
            }
            .table-container {
                display: flex;
                justify-content: space-between;
            }
            .page{
                
                align-items: center;
                margin: 0 auto 20px;
                flex-direction: column;
                display: flex;
            }
            .page a {
                display: inline-block;
                padding: 8px 12px;
                margin: 4px;
                border: 1px solid #ccc;
                text-decoration: none;
                background-color:  rgba(255, 172, 142, 0.9);
                color: black;
                border-radius: 4px;
            }
            .content{
                background-color: white;
            }
        </style>
    </head>
    <body>
        <div class="header" >
            <ul class="navbar__left">
                <li class="navbar__left__link col-md-2"><a href="/project1/teacherhome.jsp">Home</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/teaching">Teaching schedule</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/teacherattendace">Attended</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/report">Attended Report</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/manager">Student</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/teacherabout.jsp">About</a></li>
            </ul>
            <div class="content">
                <div class="page col-md-1">
                    <table>
                        <c:forEach var="i" items="${requestScope.listGroup}">
                            <tr><td><a style="text-decoration: none;color: black;" class="" href="report?group=${i.getID()}">${i.getName()}</a></td></tr>
                                </c:forEach>
                    </table>
                </div>
                <div class="container col-md-11">
                    <c:set var="listID" value="${requestScope.listID}"></c:set>
                    <c:set var="listStudent" value="${requestScope.listStudent}"></c:set>
                    <c:set var="size" value="${listStudent.size()}"></c:set>
                    <c:set var="size1" value="${listAttendance.size()}"></c:set>
                    <c:set var="listPresent" value="${requestScope.listPresent}"></c:set>
                    <c:set var="size2" value="${listPresent.size()}"></c:set>

                    <c:choose>
                        <c:when test="${size > 0}">    
                            <table>
                                <thead>
                                    <tr>
                                        <th>No</th>                        
                                        <th>ID</th>
                                        <th>Name</th>
                                            <c:forEach begin="0" end="${size1 - 1}" var="i">
                                            <th>${listAttendance.get(i).getDate()}</th>
                                            </c:forEach>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach begin="0" end="${size - 1}" var="index">
                                        <tr>
                                            <td>${index + 1}</td>
                                            <td>${listID[index]}</td>
                                            <td>${listStudent[index]}</td>
                                            <c:forEach  var="i" items="${listPresent[index]}">
                                                <td><c:choose>
                                                        <c:when test="${i == 1}">
                                                            P
                                                        </c:when>
                                                        <c:when test="${i == 0}">
                                                            ABS
                                                        </c:when>
                                                        <c:otherwise>
                                                            F
                                                        </c:otherwise>
                                                    </c:choose></td>
                                                </c:forEach>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

    </body>
</html>
