/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginView() {
        setTitle(" Event System Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(30, 30, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Special Event System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(title, gbc);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1; 
        panel.add(userLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(usernameField, gbc);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(passLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(passwordField, gbc);

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        add(panel);
    }

    public String getUsername() { return usernameField.getText().trim(); }
    public String getPassword() { return new String(passwordField.getPassword()).trim(); }
    public void addLoginListener(java.awt.event.ActionListener listener) {
        loginButton.addActionListener(listener);
    }
    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }
}