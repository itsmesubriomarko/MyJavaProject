/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
/**
 *
 * @author Marko
 */

import model.Client;
import view.ClientView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ClientController {
    private ClientView view;

    public ClientController(ClientView view) {
        this.view = view;
        initController();
        loadClients();
    }

    private void initController() {
        view.addAddClientListener(e -> addClient());
        view.addUpdateListener(e -> updateClient());
        view.addDeleteListener(e -> deleteClient());
        view.addClearListener(e -> view.clearForm());
        view.addRefreshListener(e -> loadClients());
    }

    private void loadClients() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);

        List<Client> clients = Client.getAll();
        for (Client c : clients) {
            model.addRow(new Object[]{
                c.getId(),
                c.getFullName(),
                c.getContact(),
                c.getEmail()
            });
        }
    }

    private void addClient() {
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        String contact = view.getContact();
        String email = view.getEmail();

        if (firstName.isEmpty() || lastName.isEmpty()) {
            JOptionPane.showMessageDialog(view, "First name and last name are required!", 
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setContact(contact);
        client.setEmail(email);

        if (client.save()) {
            JOptionPane.showMessageDialog(view, "Client added successfully!");
            view.clearForm();
            loadClients();
        } else {
            JOptionPane.showMessageDialog(view, "Error adding client!", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateClient() {
        int id = view.getSelectedClientId();
        if (id == -1) {
            JOptionPane.showMessageDialog(view, "Please select a client to update!", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Client client = Client.findById(id);
        if (client != null) {
            client.setFirstName(view.getFirstName());
            client.setLastName(view.getLastName());
            client.setContact(view.getContact());
            client.setEmail(view.getEmail());

            if (client.update()) {
                JOptionPane.showMessageDialog(view, "Client updated successfully!");
                view.clearForm();
                loadClients();
            } else {
                JOptionPane.showMessageDialog(view, "Error updating client!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void deleteClient() {
        int id = view.getSelectedClientId();
        if (id == -1) {
            JOptionPane.showMessageDialog(view, "Please select a client to delete!", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(view, 
            "Are you sure you want to delete this client?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (Client.delete(id)) {
                JOptionPane.showMessageDialog(view, "Client deleted successfully!");
                loadClients();
            } else {
                JOptionPane.showMessageDialog(view, "Error deleting client!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}