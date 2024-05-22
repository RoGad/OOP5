package com.example.oop5;

import java.io.*;
import java.sql.*;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/Delete")
public class Delete extends HttpServlet{
    private final static String URL = "jdbc:mysql://127.0.0.1:3306/users";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private Connection connection;

    //private static final String filePath = "students.json";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PreparedStatement ps = null;
        String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
        int lastPersonId = 0;
        String SELECT_LAST_ID_QUERY = "SELECT id FROM users ORDER BY id DESC LIMIT 1";

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement selectPs = connection.prepareStatement(SELECT_LAST_ID_QUERY);
            ResultSet resultSet = selectPs.executeQuery();

            if (resultSet.next()) {
                lastPersonId = resultSet.getInt("id");
                System.out.println(lastPersonId);
            }
            ps = connection.prepareStatement(DELETE_QUERY);
            ps.setInt(1, lastPersonId);
            ps.executeUpdate();
            System.out.println("Delete operation successful");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        response.sendRedirect("/lab5_war_exploded");
    }
}
