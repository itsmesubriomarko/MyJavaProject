/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Marko
 */

import java.sql.*;
import java.util.*;

public class User {
    private int id;
    private String username, password, role;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = hashPassword(password); }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    private static String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return password; // fallback
        }
    }

    private static boolean verifyPassword(String password, String hashedPassword) {
        return hashPassword(password).equals(hashedPassword);
    }

    public static List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return users;
    }

    public boolean save() {
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE role = VALUES(role)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) id = rs.getInt(1);
                return true;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean delete(int id) {
        try (Connection conn = Database.getConnection()) {
            String sql = "DELETE FROM users WHERE id = ? AND username != 'admin'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static User authenticate(String username, String password) {
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                if (verifyPassword(password, rs.getString("password"))) {
                    return user;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean updatePassword(String username, String newPassword) {
        try (Connection conn = Database.getConnection()) {
            String sql = "UPDATE users SET password = ? WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, hashPassword(newPassword));
            pstmt.setString(2, username);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
