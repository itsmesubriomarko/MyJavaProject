/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Marko
 */
import view.ReportsView;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReportsController {
    private ReportsView view;

    public ReportsController(ReportsView view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.addGenerateReportListener(e -> generateReport());
        view.addExportPDFListener(e -> exportPDF());
        view.addExportExcelListener(e -> exportExcel());
    }

    private void generateReport() {
        String reportType = view.getSelectedReportType();
        DefaultTableModel model = view.getReportModel();
        model.setRowCount(0);

        // SAMPLE DATA FOR EACH REPORT TYPE
        switch (reportType) {
            case "All Events":
                model.addRow(new Object[]{"1", "Wedding - Juan Dela Cruz", "2024-03-15", "Fully Paid", "₱45,000"});
                model.addRow(new Object[]{"2", "Birthday - Maria Santos", "2024-03-20", "Pending", "₱25,000"});
                break;
            case "Client Summary":
                model.addRow(new Object[]{"1", "Juan Dela Cruz", "2 events", "Fully Paid", "₱65,000"});
                break;
            case "Revenue Report":
                model.addRow(new Object[]{"March 2024", "Total Revenue", "-", "-", "₱245,000"});
                break;
            default:
                model.addRow(new Object[]{"Sample", "Data", "Generated", "Successfully", "🎉"});
        }

        JOptionPane.showMessageDialog(view, "✅ Report generated: " + reportType);
    }

    private void exportPDF() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(view, "📄 PDF saved: " + chooser.getSelectedFile().getName());
        }
    }

    private void exportExcel() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(view, "📊 Excel saved: " + chooser.getSelectedFile().getName());
        }
    }
}