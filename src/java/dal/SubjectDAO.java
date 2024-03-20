/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Subject;
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
public class SubjectDAO {

    public List<Subject> getSubjectbyStudentID(String Studentid) throws SQLException, ClassNotFoundException {
        DBContext db = new DBContext();
        List<Subject> list= new ArrayList<>();
        String sql = "select s.Name,s.Description from [dbo].[Subject] s \n"
                + "inner join [dbo].[Group] g on s.ID=g.SubjectID\n"
                + "inner join [dbo].[Enrollment] e on e.GroupID=g.ID \n"
                + "where e.StudentID=?";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setString(1, Studentid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getString(1), rs.getString(2)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SubjectDAO sb = new SubjectDAO();
        List<Subject> list= sb.getSubjectbyStudentID("HE160497");
        System.out.println(list.get(0).getDescription());
        System.out.println(list.get(1).getDescription());
    }
}
