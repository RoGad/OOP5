package com.example.oop5;

import java.io.*;
import java.sql.*;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/Update")
public class Update extends HttpServlet {
    private final static String URL = "jdbc:mysql://127.0.0.1:3306/users";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private Connection connection;

    //private static final String filePath = "students.json";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String ageParam = request.getParameter("age");
        String telegram = request.getParameter("telegram");
        String mobileParam = request.getParameter("mobile");

        int age = ageParam != null && !ageParam.isEmpty() ? Integer.parseInt(ageParam) : 0;
        int mobile = mobileParam != null && !mobileParam.isEmpty() ? Integer.parseInt(mobileParam) : 0;
        PreparedStatement ps = null;
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

            String UPDATE_QUERY = "UPDATE users SET name = ?, lastname = ?, age = ?, email = ?, telegram = ?, mobile = ? WHERE id = ?";
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = connection.prepareStatement(UPDATE_QUERY);
            ps.setString(1, name);
            ps.setString(2, lastname);
            ps.setInt(3, age);
            ps.setString(4, email);
            ps.setString(5, telegram);
            ps.setInt(6, mobile);
            ps.setInt(7, lastPersonId);
            System.out.println(ps);
            ps.executeUpdate();
            System.out.println("execute is successful");
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
