/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Marko
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EventView extends JPanel {
    private DefaultTableModel tableModel;
    private JTable eventTable;
    private JComboBox<String> clientCombo, eventTypeCombo, paymentStatusCombo;
    private JTextField dateField, timeField, venueField, guestsField;
    private JButton addButton, updateButton, deleteButton, refreshButton, clearButton;

    public EventView() {
        setLayout(new BorderLayout());
        setBackground(new Color(60, 65, 75));
        initComponents();
    }

    private void initComponents() {
        JPanel formPanel = createFormPanel();
        JPanel tablePanel = createTablePanel();
        JPanel buttonPanel = createButtonPanel();

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(tablePanel), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 3, 10, 10));
        panel.setBackground(new Color(45, 50, 55));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Client:"));
        clientCombo = new JComboBox<>(new String[]{"Client 1", "Client 2", "Select Client"});
        panel.add(clientCombo);

        panel.add(new JLabel("Event Type:"));
        eventTypeCombo = new JComboBox<>(new String[]{
            "Wedding", "Birthday", "Family Reunion", "Debut", "Anniversary", "Other"
        });
        panel.add(eventTypeCombo);

        panel.add(new JLabel("Date:"));
        dateField = new JTextField("YYYY-MM-DD");
        panel.add(dateField);

        panel.add(new JLabel("Time:"));
        timeField = new JTextField("HH:MM");
        panel.add(timeField);

        panel.add(new JLabel("Venue:"));
        venueField = new JTextField();
        panel.add(venueField);

        panel.add(new JLabel("Guests:"));
        guestsField = new JTextField("0");
        panel.add(guestsField);

        JPanel statusPanel = new JPanel(new FlowLayout());
        statusPanel.setBackground(new Color(45, 50, 55));
        statusPanel.add(new JLabel("Status:"));
        paymentStatusCombo = new JComboBox<>(new String[]{
            "Pending", "Down Payment", "Fully Paid"
        });
        statusPanel.add(paymentStatusCombo);
        panel.add(statusPanel);

        return panel;
    }

    private JPanel createTablePanel() {
        String[] columns = {"ID", "Client", "Event Type", "Date", "Time", "Venue", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        
        eventTable = new JTable(tableModel);
        eventTable.setRowHeight(30);
        eventTable.getTableHeader().setBackground(new Color(30, 30, 40));
        eventTable.getTableHeader().setForeground(Color.WHITE);
        eventTable.setBackground(new Color(60, 65, 75));
        eventTable.setForeground(Color.WHITE);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(60, 65, 75));
        panel.add(new JScrollPane(eventTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(40, 40, 50));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        addButton = new JButton("➕ Add Event");
        updateButton = new JButton("✏️ Update");
        deleteButton = new JButton("🗑️ Delete");
        clearButton = new JButton("🧹 Clear");
        refreshButton = new JButton("🔄 Refresh");

        Color[] colors = {new Color(40, 167, 69), new Color(0, 123, 255), 
                         new Color(220, 53, 69), new Color(128, 128, 128), new Color(255, 152, 0)};
        
        JButton[] buttons = {addButton, updateButton, deleteButton, clearButton, refreshButton};
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(colors[i]);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusPainted(false);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 12));
        }

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(clearButton);
        panel.add(refreshButton);

        return panel;
    }

    // GETTERS
    public DefaultTableModel getTableModel() { return tableModel; }
    public JTable getEventTable() { return eventTable; }
    public String getSelectedClient() { return (String) clientCombo.getSelectedItem(); }
    public String getEventType() { return (String) eventTypeCombo.getSelectedItem(); }
    public String getEventDate() { return dateField.getText(); }
    public String getEventTime() { return timeField.getText(); }
    public String getVenue() { return venueField.getText(); }
    public String getPaymentStatus() { return (String) paymentStatusCombo.getSelectedItem(); }

    public void clearForm() {
        clientCombo.setSelectedIndex(0);
        dateField.setText("YYYY-MM-DD");
        timeField.setText("HH:MM");
        venueField.setText("");
        guestsField.setText("0");
        paymentStatusCombo.setSelectedIndex(0);
    }

    // LISTENERS
    public void addAddEventListener(java.awt.event.ActionListener listener) {
        addButton.addActionListener(listener);
    }
    public void addUpdateListener(java.awt.event.ActionListener listener) {
        updateButton.addActionListener(listener);
    }
    public void addDeleteListener(java.awt.event.ActionListener listener) {
        deleteButton.addActionListener(listener);
    }
    public void addClearListener(java.awt.event.ActionListener listener) {
        clearButton.addActionListener(listener);
    }
    public void addRefreshListener(java.awt.event.ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    public int getSelectedEventId() {
        int row = eventTable.getSelectedRow();
        if (row == -1) return -1;
        return (int) tableModel.getValueAt(row, 0);
    }
}