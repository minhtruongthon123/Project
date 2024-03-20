/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Group;
import Model.Subject;
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
public class GroupDAO {

    public List<Group> getAllGroupbyTeacherID(String teacherID) throws SQLException, ClassNotFoundException {
        DBContext db = new DBContext();
        List<Group> list = new ArrayList<>();
        String sql = "select g.ID,g.Name,s.Name from [Dbo].[Group] g \n"
                + "inner join [dbo].[Subject] s on g.SubjectID=s.ID where TeacherID=?";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setString(1, teacherID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Group(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
