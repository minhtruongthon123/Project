<%-- 
    Document   : TeachingSchedule
    Created on : Mar 18, 2024, 2:07:07 PM
    Author     : minhn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="stylesheet" href="css/bootstrap-grid.min.css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/stylesheet.scss"/>
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
            .page{
                align-items: center;
                flex-direction: row;
                display: flex;
                text-align: center;

            }
            .page a {
                display: inline-block;
                text-decoration: none;
                color: black;
                width: 100%;
                background-color: white;
                padding-bottom: 5px;
                padding-top: 5px;

            }
            .page a.active{
                background-color: #4CAF50;
                color: white;
            }
            .page a:hover:not(.active){
                background-color: chocolate;
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
        </div>
        <c:set var="page" value="${requestScope.page}"/>
        <div  class="page col-md-1">Week
            <c:forEach begin="1" end="${requestScope.numpage}" var="i">
                <a class="${i==page?"active":""} " href="teaching?page=${i}">${i}</a>
            </c:forEach>
        </div>
        <section class="timetable">
            <ul class="week">
                <li>
                    <h3 class="day-title">MONDAY</h3>
                    <div class="activities-container">
                        <c:forEach var="i" items="${requestScope.list}">
                            <c:choose>
                                <c:when test="${i.getDayofWeek() == 'Monday'}">
                                    <article class="activity">
                                        <p>${i.getDate()}</p>
                                        <p>${i.getTimeStart()}-${i.getTimeEnd()}</p>
                                        <p>${i.getRoomID()}-${i.getClassName()}</p>
                                        <p>${i.getSubjectName()}</p>
                                    </article>
                                </c:when> 
                            </c:choose>
                        </c:forEach>
                    </div>
                </li>
                <li>
                    <h3 class="day-title">TUESDAY</h3>
                    <div class="activities-container">
                        <c:forEach var="i" items="${requestScope.list}">
                            <c:choose>
                                <c:when test="${i.getDayofWeek() == 'Tuesday'}">
                                    <article class="activity">
                                        <p>${i.getDate()}</p>
                                        <p>${i.getTimeStart()}-${i.getTimeEnd()}</p>
                                        <p>${i.getRoomID()}-${i.getClassName()}</p>
                                        <p>${i.getSubjectName()}</p>
                                    </article>
                                </c:when> 
                            </c:choose>
                        </c:forEach>
                    </div>
                </li>
                <li>
                    <h3 class="day-title">WEDNESDAY</h3>
                    <div class="activities-container">
                        <c:forEach var="i" items="${requestScope.list}">
                            <c:choose>
                                <c:when test="${i.getDayofWeek() == 'Wednessday'}">
                                    <article class="activity">
                                        <p>${i.getDate()}</p>
                                        <p>${i.getTimeStart()}-${i.getTimeEnd()}</p>
                                        <p>${i.getRoomID()}-${i.getClassName()}</p>
                                        <p>${i.getSubjectName()}</p>
                                    </article>
                                </c:when> 
                            </c:choose>
                        </c:forEach>
                    </div>
                </li>
                <li>
                    <h3 class="day-title">THURSDAY</h3>
                    <div class="activities-container">
                        <c:forEach var="i" items="${requestScope.list}">
                            <c:choose>
                                <c:when test="${i.getDayofWeek() == 'Thursday'}">
                                    <article class="activity">
                                        <p>${i.getDate()}</p>
                                        <p>${i.getTimeStart()}-${i.getTimeEnd()}</p>
                                        <p>${i.getRoomID()}-${i.getClassName()}</p>
                                        <p>${i.getSubjectName()}</p>
                                    </article>
                                </c:when> 
                            </c:choose>
                        </c:forEach>
                    </div>
                </li>
                <li>
                    <h3 class="day-title">FRIDAY</h3>
                    <div class="activities-container">
                        <c:forEach var="i" items="${requestScope.list}">
                            <c:choose>
                                <c:when test="${i.getDayofWeek() == 'Friday'}">
                                    <article class="activity">
                                        <p>${i.getDate()}</p>
                                        <p>${i.getTimeStart()}-${i.getTimeEnd()}</p>
                                        <p>${i.getRoomID()}-${i.getClassName()}</p>
                                        <p>${i.getSubjectName()}</p>
                                    </article>
                                </c:when> 
                            </c:choose>
                        </c:forEach>
                    </div>
                </li>
                <li>
                    <h3 class="day-title">SATURDAY</h3>
                    <div class="activities-container">
                        <c:forEach var="i" items="${requestScope.list}">
                            <c:choose>
                                <c:when test="${i.getDayofWeek() == 'Saturday'}">
                                    <article class="activity">
                                        <p>${i.getDate()}</p>
                                        <p>${i.getTimeStart()}-${i.getTimeEnd()}</p>
                                        <p>${i.getRoomID()}-${i.getClassName()}</p>
                                        <p>${i.getSubjectName()}</p>
                                    </article>
                                </c:when> 
                            </c:choose>
                        </c:forEach>
                    </div>
                </li>
                <li>
                    <h3 class="day-title">SUNDAY</h3>
                    <div class="activities-container">
                        <c:forEach var="i" items="${requestScope.list}">
                            <c:choose>
                                <c:when test="${i.getDayofWeek() == 'Sunday'}">
                                    <article class="activity">
                                        <p>${i.getDate()}</p>
                                        <p>${i.getTimeStart()}-${i.getTimeEnd()}</p>
                                        <p>${i.getRoomID()}-${i.getClassName()}</p>
                                        <p>${i.getSubjectName()}</p>
                                    </article>
                                </c:when> 
                            </c:choose>
                        </c:forEach>
                    </div>
                </li>
            </ul>
        </section>
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
