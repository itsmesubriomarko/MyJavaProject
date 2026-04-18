/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/event_system?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";  // CHANGE IF NEEDED
    private static final String PASSWORD = "";  // CHANGE IF NEEDED

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found");
        }
    }}