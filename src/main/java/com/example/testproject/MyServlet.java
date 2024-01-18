package com.example.testproject;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.sql.*;


@RestController
public class MyServlet {

    @RequestMapping("/none")
    @ResponseBody
    public String doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
// Настройки подключения к базе данных Postgres

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "admin";
        Class.forName("org.postgresql.Driver");
        String a="ID ";
        try {
// Соединение с базой данныхlij

            Connection con = DriverManager.getConnection(url, user, password);

// Выполнение SQL запроса
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

// Подготовка шаблона HTML страницы
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println("<html><body>");

// Обработка результата запроса

            while (rs.next()) {
                a = a+rs.getString("full_name");

            }

            pw.println("</body></html>");


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }
}