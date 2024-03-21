/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Teacher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author minhn
 */
public class TeacherDAO {

    public Teacher TeacherInfo(String username) throws SQLException, ClassNotFoundException {
        DBContext db = new DBContext();
        try {
            String sql = "select st.ID, st.Name,st.Email,st.Phone from Teacher st inner join Account ac on (st.Email=ac.Username) where ac.Username=?";
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getString("ID"), rs.getString("Name"), rs.getString("Email"), rs.getString("Phone"));
                return teacher;
            }

        } catch (SQLException e) {
        }
        return null;
    } 

    public void changePhone(String phone, Teacher tc) throws SQLException, ClassNotFoundException {
        DBContext db = new DBContext();
        try {
            String sql = "UPDATE Teacher\n"
                    + "SET Phone = ?\n"
                    + "WHERE Email=?";

            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setString(1, phone); // Thiết lập mật khẩu mới
            st.setString(2, tc.getEmail());
            int rs = st.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
