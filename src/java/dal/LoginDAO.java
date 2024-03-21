/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Student;
import Model.Teacher;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import dal.DBContext;
import java.time.DayOfWeek;

/**
 *
 * @author nguye
 */
public class LoginDAO  {
    public User login(String username, String password) throws ClassNotFoundException, SQLException {
        DBContext db = new DBContext();
        String sql = "SELECT * from Account where Username = ? and Password = ?";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setString(1,username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new User(rs.getString(1), rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    public void changePassword(String password, String UserName) throws SQLException, ClassNotFoundException {
        DBContext db = new DBContext();
        try {
            String sql = "UPDATE Account\n"
                    + "SET Password = ?\n"
                    + "WHERE Username=?";

            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setString(1, password); // Thiết lập mật khẩu mới
            st.setString(2, UserName);
            int rs = st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        LoginDAO lg = new LoginDAO();
        User u = lg.login("tuanvm2@gmail.com", "123");
        TeacherDAO tc = new TeacherDAO();
        Teacher teacher = tc.TeacherInfo(u.getUsername());
        System.out.println(teacher.getName());
    }
}
