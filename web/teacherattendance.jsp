<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="stylesheet" href="css/bootstrap-grid.min.css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/styles.css"/>
        <title>Teacher Attendance</title>
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
                padding: 60px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.3);
                text-align: center;
                display: flex;
                justify-content: center;
                flex-direction: column;
                margin: 0;
            }
            h2 {
                color: #F37022;
                font-size: 60px;
                font-weight: 700;
                margin-bottom: 20px;
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
            .student-info {
                background-color: rgba(252,89,13,0.6);
                flex: 1;
                display: grid;
            }

            .student-info img {
                width: 100px;
                border-radius: 50%;
                margin-right: 20px;
                text-align: center;
            }

            .student-info h4 {
                color: #444444;
                text-align: center;
                margin: auto;
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
                transition: background-color 0.3s;
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
        <div class="content ">
            <div class="page col-md-1">
                <table>
                    <c:forEach var="i" items="${requestScope.listGroup}">
                        <tr><td><a style="text-decoration: none;color: black;" class="" href="teacherattendace?group=${i.getID()}">${i.getName()}</a></td></tr>
                            </c:forEach>
                </table>
            </div>

            <div class="container col-md-3">
                <table>
                    <thead>
                        <tr>
                            <th>No</th>                        
                            <th>Date</th>
                            <th>Time Start</th>
                            <th>Time End</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${requestScope.listAttendance}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${c.getDate()}</td>
                                <td>${c.getTimeStart()}</td>
                                <td>${c.getTimeEnd()}</td>
                                <td><a style="color: black;" class="" href="teacherattendace?group=${requestScope.group}&scheduleID=${c.getId()}">GO</a></td>
                            </tr>
                        </c:forEach>
                        </form>
                    </tbody>
                </table>
            </div>
            <div class="container col-md-8">
                <c:set var="listID" value="${requestScope.listID}"></c:set>
                <c:set var="listStudent" value="${requestScope.listStudent}"></c:set>
                <c:set var="size" value="${listStudent.size()}"></c:set>
                <c:choose>
                    <c:when test="${size > 0}">
                        <form action="teacherattendace" method="post">      
                            <table>
                                <thead>
                                    <tr>
                                        <th>No</th>                        
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Present</th>
                                        <th>Comment</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach begin="0" end="${size - 1}" var="index">
                                        <tr>
                                            <td>${index + 1}</td>
                                            <td><input type="hidden" name="listID${index}" value="${listID[index]}" />${listID[index]}</td>
                                            <td>${listStudent[index]}</td>
                                            <td>
                                                <input type="radio" name="present${index}" value="1" />Present
                                                <input type="radio" name="present${index}" value="0" />Absent
                                            </td>
                                            <td><input type="text" name="comment${index}" value="" /></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <input type="hidden" name="group" value="${requestScope.group}" />   
                            <input type="hidden" name="size" value="${size}" /> 
                            <input type="hidden" name="scheduleID" value="${requestScope.scheduleID}" />        
                            <input type="submit" value="SAVE" />
                        </form>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
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

        <script>
            function sendFormOnChange() {
                document.getElementById("attendanceForm").submit();
            }
        </script>

    </body>
</html>
