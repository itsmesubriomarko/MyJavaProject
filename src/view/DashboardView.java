/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Marko
 */
import controller.ClientController;
import controller.EventController;
import controller.SettingController;
import controller.ReportsController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DashboardView extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel statsPanel, quickActionsPanel;
    private ClientView clientView;
    private EventView eventView;
    private SettingView settingView;
    private ReportsView reportsView;

    // COLORS 🎨
    private static final Color DARK_BG = new Color(25, 25, 35);
    private static final Color LIGHT_BG = new Color(40, 40, 50);
    private static final Color CARD_BG = new Color(60, 65, 75);
    private static final Color TEXT_WHITE = Color.WHITE;
    private static final Color BLUE = new Color(0, 123, 255);
    private static final Color GREEN = new Color(40, 167, 69);
    private static final Color ORANGE = new Color(255, 152, 0);
    private static final Color PURPLE = new Color(128, 0, 128);

    public DashboardView(String username, String role) {
        setTitle("Event System Dashboard - " + username + " (" + role + ")");
        setSize(1400, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initDashboard();
    }

    private void initDashboard() {
        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(DARK_BG);

        // HEADER
        JPanel header = createHeader();
        mainPanel.add(header, BorderLayout.NORTH);

        // STATS + QUICK ACTIONS
        statsPanel = createStatsPanel();
        quickActionsPanel = createQuickActions();
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(DARK_BG);
        topPanel.add(statsPanel, BorderLayout.NORTH);
        topPanel.add(quickActionsPanel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // TABBED PANE
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(LIGHT_BG);
        tabbedPane.setForeground(TEXT_WHITE);
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        // ADD VIEWS
        clientView = new ClientView();
        eventView = new EventView();
        settingView = new SettingView();

        tabbedPane.addTab("👥 Clients", clientView);
        tabbedPane.addTab("🎉 Events", eventView);
        tabbedPane.addTab("⚙️ Settings", settingView);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);

        applyTheme();
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBackground(new Color(15, 15, 25));
        header.setPreferredSize(new Dimension(0, 60));
        header.setLayout(new BorderLayout());
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        JLabel title = new JLabel("Event Management System", SwingConstants.CENTER);
        title.setForeground(TEXT_WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        header.add(title, BorderLayout.CENTER);

        return header;
    }

    private JPanel createStatsPanel() {
        JPanel stats = new JPanel(new GridLayout(1, 4, 15, 15));
        stats.setBackground(DARK_BG);
        stats.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        stats.add(createStatCard("📊 Total Events\n45", BLUE));
        stats.add(createStatCard("⏳ Pending\n12", ORANGE));
        stats.add(createStatCard("💰 Fully Paid\n33", GREEN));
        stats.add(createStatCard("💵 Revenue\n₱245,000", PURPLE));

        return stats;
    }

    private JPanel createStatCard(String text, Color color) {
        JPanel card = new JPanel();
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createLineBorder(color, 2));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel value = new JLabel(text.split("\n")[0]);
        value.setForeground(color);
        value.setFont(new Font("Arial", Font.BOLD, 22));
        value.setAlignmentX(CENTER_ALIGNMENT);

        JLabel label = new JLabel(text.split("\n")[1]);
        label.setForeground(TEXT_WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setAlignmentX(CENTER_ALIGNMENT);

        card.add(value);
        card.add(Box.createVerticalStrut(5));
        card.add(label);
        return card;
    }

    private JPanel createQuickActions() {
        JPanel actions = new JPanel(new GridLayout(1, 4, 10, 10));
        actions.setBackground(LIGHT_BG);
        actions.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        JButton[] buttons = {
            createActionButton("➕ New Client", GREEN),
            createActionButton("➕ New Event", BLUE),
            createActionButton("📊 Reports", ORANGE),
            createActionButton("🔧 Settings", PURPLE)
        };

        for (JButton btn : buttons) actions.add(btn);
        return actions;
    }

    private JButton createActionButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(TEXT_WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        return btn;
    }

    private void applyTheme() {
        UIManager.put("TabbedPane.contentOpaque", true);
        UIManager.put("TabbedPane.background", LIGHT_BG);
        UIManager.put("TabbedPane.foreground", TEXT_WHITE);
        UIManager.put("TabbedPane.selected", BLUE);
        UIManager.put("Button.background", LIGHT_BG);
        UIManager.put("Button.foreground", TEXT_WHITE);
        UIManager.put("Table.background", CARD_BG);
        UIManager.put("Table.foreground", TEXT_WHITE);
        UIManager.put("Table.headerBackground", DARK_BG);
    }

    // CONTROLLERS
    public void initControllers() {
        new ClientController(clientView);
        new EventController(eventView);
        new SettingController(settingView);
       
    }

    // GETTERS FOR CONTROLLERS
    public ClientView getClientView() { return clientView; }
    public EventView getEventView() { return eventView; }
    public SettingView getSettingView() { return settingView; }
}