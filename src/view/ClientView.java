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

public class ClientView extends JPanel {
    private DefaultTableModel tableModel;
    private JTable clientTable;
    private JTextField firstNameField, lastNameField, contactField, emailField;
    private JTextArea addressArea;
    private JButton addButton, updateButton, deleteButton, clearButton, refreshButton;

    public ClientView() {
        setLayout(new BorderLayout());
        setBackground(new Color(60, 65, 75));
        initComponents();
    }

    private void initComponents() {
        // TOP PANEL - FORM
        JPanel formPanel = createFormPanel();
        
        // CENTER PANEL - TABLE
        JPanel tablePanel = createTablePanel();
        
        // BUTTON PANEL
        JPanel buttonPanel = createButtonPanel();
        
        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(tablePanel), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBackground(new Color(45, 50, 55));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Labels & Fields
        panel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        panel.add(lastNameField);

        panel.add(new JLabel("Contact:"));
        contactField = new JTextField();
        panel.add(contactField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        return panel;
    }

    private JPanel createTablePanel() {
        String[] columns = {"ID", "Name", "Contact", "Email"};
        tableModel = new DefaultTableModel(columns, 0);
        
        clientTable = new JTable(tableModel);
        clientTable.setRowHeight(30);
        clientTable.getTableHeader().setBackground(new Color(30, 30, 40));
        clientTable.getTableHeader().setForeground(Color.WHITE);
        clientTable.setBackground(new Color(60, 65, 75));
        clientTable.setForeground(Color.WHITE);
        clientTable.setSelectionBackground(new Color(0, 123, 255));
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(60, 65, 75));
        panel.add(new JScrollPane(clientTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(40, 40, 50));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        addButton = new JButton("➕ Add Client");
        updateButton = new JButton("✏️ Update");
        deleteButton = new JButton("🗑️ Delete");
        clearButton = new JButton("🧹 Clear");
        refreshButton = new JButton("🔄 Refresh");

        // Style buttons
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

    // GETTERS FOR CONTROLLER
    public DefaultTableModel getTableModel() { return tableModel; }
    public JTable getClientTable() { return clientTable; }
    public String getFirstName() { return firstNameField.getText().trim(); }
    public String getLastName() { return lastNameField.getText().trim(); }
    public String getContact() { return contactField.getText().trim(); }
    public String getEmail() { return emailField.getText().trim(); }
    public String getAddress() { return ""; } // Will add later

    // SETTERS
    public void clearForm() {
        firstNameField.setText("");
        lastNameField.setText("");
        contactField.setText("");
        emailField.setText("");
    }

    // LISTENERS
    public void addAddClientListener(java.awt.event.ActionListener listener) {
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

    public int getSelectedClientId() {
        int row = clientTable.getSelectedRow();
        if (row == -1) return -1;
        return (int) tableModel.getValueAt(row, 0);
    }
}