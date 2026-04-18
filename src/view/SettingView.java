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
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class SettingView extends JPanel {
    private JTable userTable;
    private DefaultTableModel userTableModel;
    private JTextField newUsernameField, newPasswordField, targetUsernameField, currentPasswordField, newPasswordChangeField;
    private JComboBox<String> roleCombo;
    private JButton createUserBtn, deleteUserBtn, refreshUsersBtn, changePasswordBtn;

    public SettingView() {
        setLayout(new BorderLayout());
        setBackground(new Color(60, 65, 75));
        initComponents();
    }

    private void initComponents() {
        JPanel leftPanel = createUserForm();
        JPanel rightPanel = createUserTable();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setResizeWeight(0.4);
        splitPane.setBackground(new Color(60, 65, 75));

        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel createUserForm() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBackground(new Color(45, 50, 55));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // NEW USER FORM
        panel.add(new JLabel("New Username:"));
        newUsernameField = new JTextField();
        panel.add(newUsernameField);

        panel.add(new JLabel("New Password:"));
        newPasswordField = new JTextField();
        panel.add(newPasswordField);

        panel.add(new JLabel("Role:"));
        roleCombo = new JComboBox<>(new String[]{"Admin", "Client", "EventPlanner", "Staff"});
        panel.add(roleCombo);

        createUserBtn = new JButton("➕ Create User");
        createUserBtn.setBackground(new Color(40, 167, 69));
        createUserBtn.setForeground(Color.WHITE);
        panel.add(new JLabel(""));
        panel.add(createUserBtn);

        // CHANGE PASSWORD FORM
        panel.add(new JLabel("Target Username:"));
        targetUsernameField = new JTextField();
        panel.add(targetUsernameField);

        panel.add(new JLabel("Current Password:"));
        currentPasswordField = new JTextField();
        panel.add(currentPasswordField);

        panel.add(new JLabel("New Password:"));
        newPasswordChangeField = new JTextField();
        panel.add(newPasswordChangeField);

        changePasswordBtn = new JButton("🔐 Change Password");
        changePasswordBtn.setBackground(new Color(0, 123, 255));
        changePasswordBtn.setForeground(Color.WHITE);
        panel.add(new JLabel(""));
        panel.add(changePasswordBtn);

        return panel;
    }

    private JPanel createUserTable() {
        String[] columns = {"ID", "Username", "Role"};
        userTableModel = new DefaultTableModel(columns, 0);
        userTable = new JTable(userTableModel);
        userTable.setRowHeight(30);
        userTable.setBackground(new Color(60, 65, 75));
        userTable.setForeground(Color.WHITE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(60, 65, 75));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        refreshUsersBtn = new JButton("🔄 Refresh Users");
        refreshUsersBtn.setBackground(new Color(255, 152, 0));
        refreshUsersBtn.setForeground(Color.WHITE);
        panel.add(refreshUsersBtn, BorderLayout.NORTH);

        deleteUserBtn = new JButton("🗑️ Delete Selected User");
        deleteUserBtn.setBackground(new Color(220, 53, 69));
        deleteUserBtn.setForeground(Color.WHITE);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(60, 65, 75));
        buttonPanel.add(deleteUserBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(new JScrollPane(userTable), BorderLayout.CENTER);
        return panel;
    }

    // GETTERS
    public DefaultTableModel getUserTableModel() { return userTableModel; }
    public String getNewUsername() { return newUsernameField.getText().trim(); }
    public String getNewPassword() { return newPasswordField.getText().trim(); }
    public String getRole() { return (String) roleCombo.getSelectedItem(); }
    public String getTargetUsername() { return targetUsernameField.getText().trim(); }
    public String getCurrentPassword() { return currentPasswordField.getText().trim(); }
    public String getNewPasswordChange() { return newPasswordChangeField.getText().trim(); }

    public void clearUserForm() {
        newUsernameField.setText("");
        newPasswordField.setText("");
    }

    public void clearPasswordForm() {
        targetUsernameField.setText("");
        currentPasswordField.setText("");
        newPasswordChangeField.setText("");
    }

    // LISTENERS
    public void addCreateUserListener(java.awt.event.ActionListener listener) {
        createUserBtn.addActionListener(listener);
    }
    public void addDeleteUserListener(java.awt.event.ActionListener listener) {
        deleteUserBtn.addActionListener(listener);
    }
    public void addRefreshUsersListener(java.awt.event.ActionListener listener) {
        refreshUsersBtn.addActionListener(listener);
    }
    public void addChangePasswordListener(java.awt.event.ActionListener listener) {
        changePasswordBtn.addActionListener(listener);
    }

    public int getSelectedUserId() {
        int row = userTable.getSelectedRow();
        if (row == -1) return -1;
        return (int) userTableModel.getValueAt(row, 0);
    }
}