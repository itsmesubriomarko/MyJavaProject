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

public class Client {
    private int id;
    private String firstName, lastName, contact, email, address;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getFullName() { return firstName + " " + lastName; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public static List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM clients ORDER BY created_at DESC")) {
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("first_name"));
                client.setLastName(rs.getString("last_name"));
                client.setContact(rs.getString("contact"));
                client.setEmail(rs.getString("email"));
                client.setAddress(rs.getString("address"));
                clients.add(client);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return clients;
    }

    public boolean save() {
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO clients (first_name, last_name, contact, email, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, contact);
            pstmt.setString(4, email);
            pstmt.setString(5, address);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                    return true;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean delete(int id) {
        try (Connection conn = Database.getConnection()) {
            String sql = "DELETE FROM clients WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public static Client findById(int id) {
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM clients WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("first_name"));
                client.setLastName(rs.getString("last_name"));
                client.setContact(rs.getString("contact"));
                client.setEmail(rs.getString("email"));
                client.setAddress(rs.getString("address"));
                return client;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean update() {
        try (Connection conn = Database.getConnection()) {
            String sql = "UPDATE clients SET first_name=?, last_name=?, contact=?, email=?, address=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, contact);
            pstmt.setString(4, email);
            pstmt.setString(5, address);
            pstmt.setInt(6, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}