/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Student;
import Model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author minhn
 */
public class StudentDAO {

    public Student StudentInfo(String username) throws SQLException, ClassNotFoundException {
        DBContext db = new DBContext();
        try {
            String sql = "select st.ID, st.Name,st.Dob,st.Email,st.Gender,st.Phone from Student st inner join Account ac on (st.Email=ac.Username) where ac.Username=?";

            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Student student = new Student(rs.getString("ID"), rs.getString("Name"), rs.getString("Dob"), rs.getString("Email"), rs.getBoolean("Gender"), rs.getString("Phone"));
                return student;
            }

        } catch (SQLException e) {
        }
        return null;
    }
    
    public List<String> getAllStudentinClass(int GroupID) throws SQLException, ClassNotFoundException {
        DBContext db = new DBContext();
        String sql = "select s.Name from Student s \n"
                    + "inner join [dbo].Enrollment e on e.StudentID=s.ID where e.GroupID=?";
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setInt(1, GroupID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {                
                list.add(rs.getString(1));
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }
    public List<String> getAllStudentID(int GroupID) throws SQLException, ClassNotFoundException {
        DBContext db = new DBContext();
        String sql = "select s.ID from Student s \n"
                    + "inner join [dbo].Enrollment e on e.StudentID=s.ID where e.GroupID=?";
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setInt(1, GroupID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {                
                list.add(rs.getString(1));
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }

public static void main(String[] args) {
        // Gọi hàm StudentInfo để lấy thông tin sinh viên từ cơ sở dữ liệu
        List<String> list = new ArrayList<>();
        try {
            StudentDAO studentDAO = new StudentDAO();
            String usernameToSearch = "minhndhe160497@gmail.com"; // Replace with the actual username
            Student student = studentDAO.StudentInfo(usernameToSearch);
            list=studentDAO.getAllStudentID(11);
            for (String string : list) {
                System.out.println(string);
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Log or handle the exception
        }
    }
}
