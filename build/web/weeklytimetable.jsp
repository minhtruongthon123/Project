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
        <link rel="stylesheet" href="css/styles.css"/>
        <title>Weekly  Timetable</title>       
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
            <c:set var="page" value="${requestScope.page}"/>
            <div  class="page col-md-1">
                <c:forEach begin="1" end="${requestScope.numpage}" var="i">
                    <a class="${i==page?"active":""} " href="weeklytime?page=${i}">Week ${i}</a>
                </c:forEach>
            </div>
            <div class="container col-md-11">
                <table >
                    <thead>
                        <tr>
                            <th></th>
                            <th>Date</th>
                            <th>Time Start</th>
                            <th>Time End</th>
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
