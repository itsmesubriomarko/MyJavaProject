/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.User;
import view.LoginView;
import view.DashboardView;
import javax.swing.*;

public class LoginController {
    private LoginView view;
    private User currentUser;
    private String currentRole;
    private Runnable successCallback;


    public LoginController(LoginView view) {
        this.view = view;
        initController();
    }
    public void setSuccessCallback(Runnable callback){
   this.successCallback= callback;
}

    private void initController() {
        view.addLoginListener(e -> attemptLogin());
    }

    private void attemptLogin() {
        String username = view.getUsername();
        String password = view.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter username and password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = User.authenticate(username, password);
        if (user != null) {
            currentUser = user;
            currentRole = user.getRole();
            JOptionPane.showMessageDialog(view, "Login successful! Welcome " + username);
            showDashboard();
        } else {
            JOptionPane.showMessageDialog(view, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            view.clearFields();
        }
    }

    private void showDashboard() {
        view.dispose();
        DashboardView dashboard = new DashboardView(currentUser.getUsername(), currentRole);
        dashboard.setVisible(true);
    }

    public User getCurrentUser() { return currentUser; }
    public String getCurrentRole() { return currentRole; }
}
    