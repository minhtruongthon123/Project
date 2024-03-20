package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
public class DBContext {

    protected Connection connection;

    public DBContext() throws SQLException, ClassNotFoundException {
        // Edit URL , username, password to authenticate with your MS SQL Server
        String url = "jdbc:sqlserver://DESKTOP-9K7RTAK\\SQLEXPRESS:1433;databaseName=FAP;encrypt=false";
        String username = "sa";
        String password = "123";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connection = DriverManager.getConnection(url, "sa", "123");

    }
}
