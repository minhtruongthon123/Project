<%-- 
    Document   : studenthome
    Created on : Jan 8, 2024, 2:25:38 PM
    Author     : minhn
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="stylesheet" href="css/bootstrap-grid.min.css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <title>Subject  Timetable</title>
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
                margin: 0 auto 20px; /* Updated line to use auto for horizontal centering */
                padding: 60px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.3);
                text-align: center;
                display: flex;
                justify-content: center;
                flex-direction: column;
            }
            input[type=submit]:hover {
                background-color: #ff6600;
            }
            .navbar__left__link{
                padding: 20px;
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
            .checkboxSubject{
                align-items: center;
                flex-direction: column;
                display: flex;
            }
            .checkboxSubject input[type=checkbox] {
                background-color: gray;
            }

            .checkboxSubject input[type=checkbox]:checked {
                background-color: green;
            }

        </style>
    </head>
    <body>
        <div class="header" >
            <ul class="navbar__left">
                <li class="navbar__left__link col-md-2"><a href="/project1/student">Home</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/weeklytime">Weekly Timetable</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/subjecttime">Subject Timetable</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/attendance">Attended Report</a></li>
                <li class="navbar__left__link col-md-2"><a href="#contact">Exam Schedule</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/studentabout.jsp">About</a></li>
            </ul>
        </div>
        <div class="content">
            <div class="checkboxSubject col-md-1">
                <c:set var="check" value="${requestScope.check}"></c:set>
                <c:set var="listsubject" value="${requestScope.listsubject}"></c:set>
                    <form action="subjecttime" method="get">
                    <c:forEach begin="0" end="${listsubject.size()-1}" var="i">
                        <input type="checkbox" name="subjectSelected" value="${listsubject.get(i).getName()}" ${check[i]?"checked":""} onclick="this.form.submit()">
                        ${listsubject.get(i).getName()}<br/>
                    </c:forEach>
                </form>
            </div>
            <div class="container col-md-11">
                <table >
                    <thead>
                        <tr>
                            <th></th>
                            <th>Date</th>
                            <th>TimeStart</th>
                            <th>TimeEnd</th>
                            <th>Room</th>
                            <th>Class</th>
                            <th>Subject</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${requestScope.list}">
                            <tr>
                                <td>${c.getDayofWeek()}</td>
                                <td>${c.getDate()}</td>
                                <td>${c.getTimeStart()}</td>
                                <td>${c.getTimeEnd()}</td>
                                <td>${c.getRoomID()}</td>
                                <td>${c.getClassName()}</td>
                                <td>${c.getSubjectName()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>

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
