/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Schedule;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Date;

/**
 *
 * @author minhn
 */
public class ScheduleDAO {

    public List<Schedule> getAllScheduleforStudent(String studentID) throws SQLException, ClassNotFoundException {
        List<Schedule> list = new ArrayList<>();
        String sql = "SELECT DATENAME(dw, sch.Date) AS DayOfWeek,FORMAT(CONVERT(DATE, sch.Date, 23), 'dd-MM-yyyy') AS Date,\n"
                + "       sch.TimeStart, sch.TimeEnd, sch.RoomID, g.Name AS ClassName, \n"
                + "       sb.Name AS SubjectName\n"
                + "FROM [dbo].[Enrollment] e\n"
                + "INNER JOIN [dbo].[Schedule] sch ON e.GroupID = sch.GroupID\n"
                + "INNER JOIN [dbo].[Group] g ON e.GroupID = g.ID\n"
                + "INNER JOIN [dbo].[Subject] sb ON g.SubjectID = sb.ID\n"
                + "WHERE e.StudentID=?";

        try {
            DBContext db = new DBContext();
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setString(1, studentID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                //Date sqldate = rs.getDate("Date");
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                String date = dateFormat.format(sqldate);
                String date = rs.getString("Date");
                String dayOfWeek = rs.getString("DayOfWeek");
                String timeStart = rs.getString("TimeStart");
                String timeEnd = rs.getString("TimeEnd");
                String roomID = rs.getString("RoomID");
                String className = rs.getString("ClassName");
                String subjectName = rs.getString("SubjectName");

                list.add(new Schedule(dayOfWeek, date, timeStart, timeEnd, roomID, className, subjectName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<List<Schedule>> getSchedulebyWeek(List<Schedule> list) {
        List<List<Schedule>> weeklyLists = new ArrayList<>();
        List<Schedule> currentWeek = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Schedule ls : list) {
            LocalDate localDate = LocalDate.parse(ls.getDate(), formatter);

            if (currentWeek.isEmpty() || isInSameWeek(localDate, LocalDate.parse(currentWeek.get(0).getDate(), formatter))) {
                currentWeek.add(ls);
            } else {
                weeklyLists.add(new ArrayList<>(currentWeek));
                currentWeek.clear();
                currentWeek.add(ls);
            }
        }

        if (!currentWeek.isEmpty()) {
            weeklyLists.add(currentWeek);
        }
        return weeklyLists;
    }

    public boolean isInSameWeek(LocalDate date1, LocalDate date2) {
        return date1.getYear() == date2.getYear()
                && date1.get(WeekFields.ISO.weekOfWeekBasedYear()) == date2.get(WeekFields.ISO.weekOfWeekBasedYear());
    }

    public List<Schedule> getScheduleforStudentbySubject(List<String> subjectname, String studentid) throws ClassNotFoundException {
        List<Schedule> list = new ArrayList<>();
        String sql = "SELECT DATENAME(dw, sch.Date) AS DayOfWeek,FORMAT(CONVERT(DATE, sch.Date, 23), 'dd-MM-yyyy') AS Date,\n"
                + "       sch.TimeStart, sch.TimeEnd, sch.RoomID, g.Name AS ClassName, \n"
                + "       sb.Name AS SubjectName\n"
                + "FROM [dbo].[Enrollment] e\n"
                + "INNER JOIN [dbo].[Schedule] sch ON e.GroupID = sch.GroupID\n"
                + "INNER JOIN [dbo].[Group] g ON e.GroupID = g.ID\n"
                + "INNER JOIN [dbo].[Subject] sb ON g.SubjectID = sb.ID\n"
                + "WHERE e.StudentID=? ";
        if (subjectname != null) {
            sql += "and sb.Name in (";
            for (int i = 0; i < subjectname.size(); i++) {
                sql += "'" + subjectname.get(i) + "',";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
                sql += ")";
            }
        }
        try {
            DBContext db = new DBContext();
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setString(1, studentid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                //Date sqldate = rs.getDate("Date");
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                String date = dateFormat.format(sqldate);
                String date = rs.getString("Date");
                String dayOfWeek = rs.getString("DayOfWeek");
                String timeStart = rs.getString("TimeStart");
                String timeEnd = rs.getString("TimeEnd");
                String roomID = rs.getString("RoomID");
                String className = rs.getString("ClassName");
                String subjectName = rs.getString("SubjectName");

                list.add(new Schedule(dayOfWeek, date, timeStart, timeEnd, roomID, className, subjectName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Schedule> getAllScheduleforTeacher(String TeacherID) throws SQLException, ClassNotFoundException {
        List<Schedule> list = new ArrayList<>();
        String sql = "SELECT DATENAME(dw, sch.Date) AS DayOfWeek,FORMAT(CONVERT(DATE, sch.Date, 23), 'dd-MM-yyyy') AS Date,\n"
                + "sch.TimeStart, sch.TimeEnd, sch.RoomID, g.Name AS ClassName,\n"
                + "sb.Name AS SubjectName\n"
                + "FROM [dbo].[Schedule] sch\n"
                + "INNER JOIN [dbo].[Enrollment] e ON e.GroupID = sch.GroupID\n"
                + "INNER JOIN [dbo].[Group] g ON e.GroupID = g.ID\n"
                + "INNER JOIN [dbo].[Subject] sb ON g.SubjectID = sb.ID\n"
                + "WHERE g.TeacherID=? group by sch.Date,sch.TimeStart, sch.TimeEnd, sch.RoomID, g.Name, sb.Name ";

        try {
            DBContext db = new DBContext();
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setString(1, TeacherID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                //Date sqldate = rs.getDate("Date");
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                String date = dateFormat.format(sqldate);
                String date = rs.getString("Date");
                String dayOfWeek = rs.getString("DayOfWeek");
                String timeStart = rs.getString("TimeStart");
                String timeEnd = rs.getString("TimeEnd");
                String roomID = rs.getString("RoomID");
                String className = rs.getString("ClassName");
                String subjectName = rs.getString("SubjectName");

                list.add(new Schedule(dayOfWeek, date, timeStart, timeEnd, roomID, className, subjectName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getListSubject(String id) throws ClassNotFoundException {
        List<String> subjectlist = new ArrayList<>();
        String sql = "select s.Name from [dbo].[Group] g inner join [dbo].[Enrollment] e on e.GroupID=g.ID\n"
                + " inner join [dbo].[Subject] s on s.ID=g.SubjectID where e.StudentID =?";

        try {
            DBContext db = new DBContext();
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                //Date sqldate = rs.getDate("Date");
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                String date = dateFormat.format(sqldate);
                subjectlist.add(rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectlist;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        
        try {
            ScheduleDAO sch = new ScheduleDAO();
            //List<Schedule> schedules = sch.getScheduleforStudentbySubject("PRF192", "HE160497");
            List<Schedule> schedules = sch.getAllScheduleforTeacher("thangnt44");
            Collections.sort(schedules, new Comparator<Schedule>() {
            @Override
            public int compare(Schedule fs1, Schedule fs2) {
                // Đầu tiên so sánh ngày
                int dateComparison = fs1.getDate().compareTo(fs2.getDate());
                if (dateComparison != 0) {
                    return dateComparison;
                }
                // Nếu ngày giống nhau, thì so sánh thời gian bắt đầu
                return fs1.getTimeStart().compareTo(fs2.getTimeStart());
            }
        });
            if (!schedules.isEmpty()) {
                System.out.println("Danh sách lịch ban đầu:");
                for (Schedule schedule : schedules) {
                    System.out.print(schedule.getDayofWeek());
                    System.out.print(" ," + schedule.getDate());
                    System.out.print(" ," + schedule.getTimeStart());
                    System.out.print(" ," + schedule.getTimeEnd());
                    System.out.print(" ," + schedule.getRoomID());
                    System.out.print(" ," + schedule.getClassName());
                    System.out.println(" ," + schedule.getSubjectName());
                }

                List<List<Schedule>> weeklyLists = sch.getSchedulebyWeek(schedules);

                System.out.println("\nDanh sách lịch theo tuần:");
                for (int i = 0; i < weeklyLists.size(); i++) {
                    for (int j = 0; j < weeklyLists.get(i).size(); j++) {
                        System.out.println("Tuần " + (i + 1) + ": " + weeklyLists.get(i).get(j).getDate());
                    }
                }
            } else {
                System.out.println("Không tìm thấy lịch cho sinh viên cụ thể.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ScheduleDAO sch = new ScheduleDAO();
            List<String> subject = sch.getListSubject("HE160497");
            List<Schedule> schedules = sch.getScheduleforStudentbySubject(subject, "HE160497");
            for (Schedule schedule : schedules) {
                System.out.print(schedule.getClassName());
                System.out.print(" , " + schedule.getDate());
                System.out.print(" , " + schedule.getDayofWeek());
                System.out.println(" , " + schedule.getSubjectName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
