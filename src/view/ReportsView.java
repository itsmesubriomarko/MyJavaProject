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

public class ReportsView extends JDialog {
    private JComboBox<String> reportTypeCombo;
    private JButton generateBtn, pdfBtn, excelBtn;
    private JTable reportTable;
    private DefaultTableModel reportModel;

    public ReportsView(JFrame parent) {
        super(parent, "📊 Reports Generator", true);
        setSize(1000, 700);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        JPanel headerPanel = createHeaderPanel();
        JScrollPane tableScroll = createTablePanel();
        JPanel exportPanel = createExportPanel();

        add(headerPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(exportPanel, BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 25, 35));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Generate Report", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        reportTypeCombo = new JComboBox<>(new String[]{
            "All Events", "Client Summary", "Bookings", "Revenue Report", "Payment Status"
        });

        generateBtn = new JButton("🔄 GENERATE REPORT");
        generateBtn.setBackground(new Color(255, 152, 0));
        generateBtn.setForeground(Color.WHITE);
        generateBtn.setFont(new Font("Arial", Font.BOLD, 14));
        generateBtn.setFocusPainted(false);

        panel.add(title);
        panel.add(new JLabel("Report Type:"));
        panel.add(reportTypeCombo);
        panel.add(generateBtn);

        return panel;
    }

    private JScrollPane createTablePanel() {
        String[] columns = {"ID", "Name", "Date", "Status", "Amount"};
        reportModel = new DefaultTableModel(columns, 0);
        reportTable = new JTable(reportModel);
        reportTable.setRowHeight(30);
        reportTable.setBackground(new Color(60, 65, 75));
        reportTable.setForeground(Color.WHITE);
        reportTable.getTableHeader().setBackground(new Color(30, 30, 40));
        reportTable.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(reportTable);
        scroll.setBackground(new Color(60, 65, 75));
        scroll.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));
        return scroll;
    }

    private JPanel createExportPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 25, 35));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pdfBtn = new JButton("📄 Export PDF");
        excelBtn = new JButton("📊 Export Excel");
        
        pdfBtn.setBackground(new Color(220, 53, 69));
        excelBtn.setBackground(new Color(40, 167, 69));
        pdfBtn.setForeground(Color.WHITE);
        excelBtn.setForeground(Color.WHITE);
        pdfBtn.setFont(new Font("Arial", Font.BOLD, 14));
        excelBtn.setFont(new Font("Arial", Font.BOLD, 14));

        panel.add(pdfBtn);
        panel.add(excelBtn);
        return panel;
    }

    // GETTERS
    public String getSelectedReportType() { return (String) reportTypeCombo.getSelectedItem(); }
    public DefaultTableModel getReportModel() { return reportModel; }

    // LISTENERS
    public void addGenerateReportListener(java.awt.event.ActionListener listener) {
        generateBtn.addActionListener(listener);
    }
    public void addExportPDFListener(java.awt.event.ActionListener listener) {
        pdfBtn.addActionListener(listener);
    }
    public void addExportExcelListener(java.awt.event.ActionListener listener) {
        excelBtn.addActionListener(listener);
    }
}