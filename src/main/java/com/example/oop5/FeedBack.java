package com.example.oop5;

import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/FeedBack")
public class FeedBack extends HttpServlet{
    private final static String URL = "jdbc:mysql://127.0.0.1:3306/users";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private Connection connection;
    // private static final String filePath = "users.json";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String telegram = request.getParameter("telegram");
        String mobile = request.getParameter("mobile");

        PreparedStatement ps = null;
        String INSERT_NEW = "INSERT INTO users (name, lastname, age, email, telegram, mobile) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ps = connection.prepareStatement(INSERT_NEW);
            ps.setString(1, name);
            ps.setString(2, lastname);
            ps.setInt(3, age);
            ps.setString(4, email);
            ps.setString(5, telegram);
            ps.setString(6, mobile);
            System.out.println(ps);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/lab5_war_exploded");

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBWoker worker = new DBWoker();

        String query = "Select * from users";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Таблица</title><link href=\"css/bootstrap.min.css\" rel=\"stylesheet\"></head><body style=\"background-color: #000000\"><div><table class=\"table\"><thead><tr><th scope=\"col\">Имя</th><th scope=\"col\">Фамилия</th><th scope=\"col\">Возраст</th><th scope=\"col\">Почта</th><th scope=\"col\">Телеграм</th><th scope=\"col\">Номер телефона</th></tr></thead>");
        try{
            Statement statement = worker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                out.println("<tbody> <tr><td>" + resultSet.getString("name") + "</td><td>" + resultSet.getString("lastname") + "</td><td>" + resultSet.getInt("age") + "</td><td>" + resultSet.getString("email") + "</td><td>" + resultSet.getString("telegram") + "</td><td>" + resultSet.getString("mobile") + "</td></tr>");
                Users users = new Users(resultSet.getString("name"), resultSet.getString("lastname"), resultSet.getInt("age"), resultSet.getString("email"), resultSet.getString("telegram"), resultSet.getString("mobile"));
                System.out.println(users.toString());
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        out.println("</tbody></table></div ><script src =\"js/bootstrap.bundle.min.js \"></script ></body ></html >");
    }


}