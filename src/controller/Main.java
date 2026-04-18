/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;  // Change to your main package

import view.LoginView;
import view.DashboardView;
import controller.LoginController;
import controller.ClientController;
import controller.EventController;
import controller.SettingController;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set Nice Look & Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            // LOGIN SCREEN
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
            
            // DASHBOARD WITH ALL CONTROLLERS
            loginController.setSuccessCallback(() -> {
                loginView.dispose();
                DashboardView dashboard = new DashboardView(
                    loginController.getCurrentUser().getUsername(),
                    loginController.getCurrentRole()
                );
                dashboard.initControllers();  // Initialize all controllers
                dashboard.setVisible(true);
            });
            
            loginView.setVisible(true);
        });
    }
}