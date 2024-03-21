<%-- 
    Document   : studentattended
    Created on : Feb 19, 2024, 7:29:41 AM
    Author     : minhn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
                <li class="navbar__left__link col-md-2"><a href="/project1/studenthome.jsp">Home</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/weeklytime">Weekly Timetable</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/subjecttime">Subject Timetable</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/attendance">Attended Report</a></li>
                <li class="navbar__left__link col-md-2"><a href="#contact">Exam Schedule</a></li>
                <li class="navbar__left__link col-md-2"><a href="/project1/studentabout.jsp">About</a></li>
            </ul>
        </div>
        <br> <br>
        <div class="col-md-12">
            <table>
                <tbody>
                    <tr>
                        <td class="col-md-4" valign="top"><h3>Select course ...</h3>
                            <table summary="Select a course">
                                <thead>
                                    <tr>
                                        <th scope="col">Campus/program</th>
                                        <th scope="col">Term</th>
                                        <th scope="col">Course</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td valign="top">
                                            <div>
                                                <table>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <b>FU-HL</b>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </td>
                                        <td valign="top">
                                            <table>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            Spring2022
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            Summer2022
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            Fall2022
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            Spring2023
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            Summer2023
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            Fall2023
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            Spring2024
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </td>
                                        <td valign="top">                                            
                                            <table>
                                                <tbody>
                                                    <c:forEach var="i" items="${requestScope.listsubject}">
                                                        <tr><a style="text-decoration: none;color: black;" class="" href="attendance?subject=${i.getName()}">${i.getName()}(${i.getDescription()})</a></tr><br/>
                                                    </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
</tbody>
</table>

<td class="col-md-8" valign="top"> <h3 style="text-align: center;">... then see report</h3>
    <table>
        <thead>
            <tr>
                <th>No</th>                        
                <th>Date</th>
                <th>Time Start</th>
                <th>Time End</th>
                <th>Room</th>
                <th>LECTURER</th>
                <th>GROUP NAME</th>
                <th>Present</th>
                <th>LECTURER'S COMMENT</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${requestScope.list}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${c.getDate()}</td>
                    <td>${c.getTimeStart()}</td>
                    <td>${c.getTimeEnd()}</td>
                    <td>${c.getRoom()}</td>
                    <td>${c.getInstructor()}</td>
                    <td>${c.getGroupname()}</td>
                    <td><c:choose>
                            <c:when test="${c.isIsPresent() == 1}">
                                Presented
                            </c:when>
                            <c:when test="${c.isIsPresent() == 0}">
                                Absent
                            </c:when>
                            <c:otherwise>
                                Future
                            </c:otherwise>
                        </c:choose></td>
                    <td>${c.getComment()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</td>
</tr>
</tbody>
</table>
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
