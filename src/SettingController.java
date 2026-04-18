/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

/**
 *
 * @author Marko
 */

import model.User;
import view.SettingView;
import view.DashboardView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.SwingUtilities;

public class SettingController {
    private SettingView view;
    private DashboardView dashboard;

    public SettingController(SettingView view) {
        this.view = view;
        this.dashboard = (DashboardView) SwingUtilities.getWindowAncestor(view);
        initController();
        loadUsers();
    }

    private void initController() {
        view.addCreateUserListener(e -> createUser());
        view.addDeleteUserListener(e -> deleteUser());
        view.addRefreshUsersListener(e -> loadUsers());
        view.addChangePasswordListener(e -> changePassword());
    }

    public void loadUsers() {
        DefaultTableModel model = view.getUserTableModel();
        model.setRowCount(0);
        
        List<User> users = User.getAll();
        for (User u : users) {
            model.addRow(new Object[]{
                u.getId(),
                u.getUsername(),
                u.getRole()
            });
        }
    }
    
    private void createUser() {
        String username = view.getNewUsername();
        String password = view.getNewPassword();
        String role = view.getRole();
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Username and password required!", 
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (password.length() < 4) {
            JOptionPane.showMessageDialog(view, "Password must be 4+ characters!", 
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        
        if (user.save()) {
            JOptionPane.showMessageDialog(view, "✅ User created successfully!");
            view.clearUserForm();
            loadUsers();
        } else {
            JOptionPane.showMessageDialog(view, "❌ Username already exists!", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteUser() {
        int id = view.getSelectedUserId();
        if (id == -1) {
            JOptionPane.showMessageDialog(view, "Select a user to delete!");
            return;
        }
        
        int choice = JOptionPane.showConfirmDialog(view, "Delete this user?", 
            "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (choice == JOptionPane.YES_OPTION) {
            if (User.delete(id)) {
                JOptionPane.showMessageDialog(view, "✅ User deleted!");
                loadUsers();
            } else {
                JOptionPane.showMessageDialog(view, "❌ Cannot delete admin!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void changePassword() {
        String username = view.getTargetUsername();
        String currentPassword = view.getCurrentPassword();
        String newPassword = view.getNewPasswordChange();
        
        if (username.isEmpty() || currentPassword.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields required!", 
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (newPassword.length() < 4) {
            JOptionPane.showMessageDialog(view, "New password too short!", 
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        User user = User.authenticate(username, currentPassword);
        if (user == null) {
            JOptionPane.showMessageDialog(view, "❌ Wrong username/password!", 
                "Auth Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (User.updatePassword(username, newPassword)) {
            JOptionPane.showMessageDialog(view, "✅ Password changed!");
            view.clearPasswordForm();
        } else {
            JOptionPane.showMessageDialog(view, "❌ Password change failed!", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
