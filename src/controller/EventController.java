/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Marko
 */
import model.Event;
import view.EventView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EventController {
    private EventView view;

    public EventController(EventView view) {
        this.view = view;
        initController();
        loadEvents();
    }

    private void initController() {
        view.addAddEventListener(e -> addEvent());
        view.addUpdateListener(e -> updateEvent());
        view.addDeleteListener(e -> deleteEvent());
        view.addClearListener(e -> view.clearForm());
        view.addRefreshListener(e -> loadEvents());
    }

    private void loadEvents() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);

        List<Event> events = Event.getAll();
        for (Event e : events) {
            model.addRow(new Object[]{
                e.getId(),
                "Client " + e.getClientId(),
                e.getEventType(),
                e.getEventDate(),
                e.getEventTime(),
                e.getVenue(),
                e.getPaymentStatus()
            });
        }
    }

    private void addEvent() {
        // SIMPLIFIED - Full implementation later
        JOptionPane.showMessageDialog(view, "Event feature coming soon!\n" +
            "Data: " + view.getEventType() + " on " + view.getEventDate(),
            "Add Event", JOptionPane.INFORMATION_MESSAGE);
        loadEvents();
    }

    private void updateEvent() {
        int id = view.getSelectedEventId();
        if (id == -1) {
            JOptionPane.showMessageDialog(view, "Select an event to update!");
            return;
        }
        JOptionPane.showMessageDialog(view, "Update event ID: " + id);
    }

    private void deleteEvent() {
        int id = view.getSelectedEventId();
        if (id == -1) {
            JOptionPane.showMessageDialog(view, "Select an event to delete!");
            return;
        }
        JOptionPane.showMessageDialog(view, "Delete event ID: " + id);
    }
}
