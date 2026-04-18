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

public class Event {
    private int id, clientId, numGuests;
    private String eventType, eventDate, eventTime, venue, paymentStatus;

    // Simplified getters/setters for now
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }
    public String getEventTime() { return eventTime; }
    public void setEventTime(String eventTime) { this.eventTime = eventTime; }
    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public static List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT e.*, c.first_name, c.last_name " +
                                            "FROM events e JOIN clients c ON e.client_id = c.id " +
                                            "ORDER BY e.event_date DESC")) {
            while (rs.next()) {
                Event event = new Event();
                event.setId(rs.getInt("id"));
                event.setClientId(rs.getInt("client_id"));
                event.setEventType(rs.getString("event_type"));
                event.setEventDate(rs.getString("event_date"));
                event.setEventTime(rs.getString("event_time"));
                event.setVenue(rs.getString("venue"));
                event.setPaymentStatus(rs.getString("payment_status"));
                events.add(event);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return events;
    }
}