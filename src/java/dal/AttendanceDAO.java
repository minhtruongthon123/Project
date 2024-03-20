/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Attendance;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author minhn
 */
public class AttendanceDAO {

    public List<Attendance> getAttendancebyStudentandSubject(String StudentID, String subjectName) throws SQLException, ClassNotFoundException {
        DBContext db = new DBContext();
        List<Attendance> list = new ArrayList<>();
        String sql = "Select sch.ID,FORMAT(CONVERT(DATE, sch.Date, 23), 'dd-MM-yyyy') AS Date,sch.TimeStart,sch.TimeEnd,sch.RoomID,g.Name as GroupName,t.ID as Intructor,a.IsPresent,a.comment \n"
                + " from Attendance a inner join Schedule sch on sch.ID=a.ScheduleID \n"
                + "inner join[dbo].[Group] g on g.ID = sch.GroupID \n"
                + "inner join [dbo].[Subject] s on g.SubjectID = s.ID \n"
                + "inner join [dbo].[Teacher] t on t.ID=g.TeacherID\n"
                + "WHERE a.StudentID=? and s.Name=?";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setString(1, StudentID);
            st.setString(2, subjectName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String comment = rs.getString("comment");
                if (comment == null) {
                    comment = "";
                }
                list.add(new Attendance(rs.getInt("ID"), rs.getString("Date"), rs.getString("TimeStart"), rs.getString("TimeEnd"), rs.getString("RoomID"), rs.getString("Intructor"), rs.getString("GroupName"), rs.getInt("IsPresent"), comment));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Attendance> getAttendancebyTeacher(String TeacherID, int GroupID) throws SQLException, ClassNotFoundException {
        DBContext db = new DBContext();
        List<Attendance> list = new ArrayList<>();
        String sql = "Select sch.ID,FORMAT(CONVERT(DATE, sch.Date, 23), 'dd-MM-yyyy') AS Date,sch.TimeStart,sch.TimeEnd,sch.RoomID,t.ID as Intructor\n"
                + " from Attendance a inner join Schedule sch on sch.ID=a.ScheduleID \n"
                + "inner join[dbo].[Group] g on g.ID = sch.GroupID \n"
                + "inner join [dbo].[Subject] s on g.SubjectID = s.ID \n"
                + "inner join [dbo].[Teacher] t on t.ID=g.TeacherID\n"
                + "WHERE t.ID=? and g.ID=? group by sch.ID,FORMAT(CONVERT(DATE, sch.Date, 23), 'dd-MM-yyyy'),sch.TimeStart,sch.TimeEnd,sch.RoomID,t.ID";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setString(1, TeacherID);
            st.setInt(2, GroupID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Attendance(rs.getInt("ID"), rs.getString("Date"), rs.getString("TimeStart"), rs.getString("TimeEnd"), rs.getString("RoomID"), rs.getString("Intructor")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Attendance> getListAttendancebyScheduleID(int scheduleID) throws SQLException, ClassNotFoundException {
        DBContext db = new DBContext();
        List<Attendance> list = new ArrayList<>();
        String sql = "select* from Attendance where ScheduleID=?";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setInt(1, scheduleID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Attendance(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Integer> getAttendancebyStudentID(String StudentID, int groupID) throws SQLException, ClassNotFoundException {
        DBContext db = new DBContext();
        List<Integer> list = new ArrayList<>();
        String sql = "select sch.ID,a.IsPresent from Attendance a\n"
                + "inner join Schedule sch on sch.ID=a.ScheduleID \n"
                + "inner join[dbo].[Group] g on g.ID = sch.GroupID\n"
                + "inner join [dbo].[Teacher] t on t.ID=g.TeacherID\n"
                + "inner join[dbo].[Enrollment] e on g.ID=e.GroupID\n"
                + "inner join [dbo].[Student] s on e.StudentID=s.ID\n"
                + "where a.StudentID=? and g.ID=? group by sch.ID,a.IsPresent order by sch.ID ASC ";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setString(1, StudentID);
            st.setInt(2, groupID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(2));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void takeAttendance(int scheduleID, String ID, int present, String comment) throws SQLException, ClassNotFoundException {
        DBContext db = new DBContext();
        String sql = "Update Attendance \n"
                + "set IsPresent=?,Comment=?\n"
                + "where ScheduleID=? and StudentID=?";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setInt(1, present);
            st.setString(2, comment);
            st.setInt(3, scheduleID);
            st.setString(4, ID);
            int rs = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AttendanceDAO atten = new AttendanceDAO();
        List<Attendance> list = atten.getAttendancebyTeacher("thangnt44", 11);
        System.out.println(list.get(0).getTimeEnd());
    }
}
