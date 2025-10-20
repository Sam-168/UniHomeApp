package za.ac.cput.unihomeapp.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import za.ac.cput.unihomeapp.dao.ApplicationDAO;
import za.ac.cput.unihomeapp.domain.Application;

/**
 *
 * @author jadar
 */
public class AdminDash extends JFrame {

    private JTable table;

    public AdminDash() {
        // Frame setup
        JFrame frame = new JFrame("ADMIN DASHBOARD");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(5, 72, 107)); // dark blue background
// Header label
        JLabel headerLabel = new JLabel("Admin Dashboard");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 26));
        headerLabel.setBounds(190, 20, 300, 40);
        frame.add(headerLabel);

        // Table columns
        String[] columns = {"Student Name", "Student Number", "Average", "Campus", "Status"};
        Object[][] data = {
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""}
        };

        // Table model and table setup
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setBackground(Color.WHITE);
        table.setGridColor(new Color(200, 200, 200));

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 500, 200);
        frame.add(scrollPane);

        // Buttons
        JButton approveBtn = new JButton("Approve");
        approveBtn.setBounds(100, 340, 120, 35);
        approveBtn.setBackground(new Color(0, 153, 204));
        approveBtn.setForeground(Color.WHITE);
        approveBtn.setFont(new Font("Arial", Font.BOLD, 14));
        approveBtn.setFocusPainted(false);
        approveBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int applicationId = getApplicationIdFromRow(selectedRow); // helper method
                ApplicationDAO dao = new ApplicationDAO();
                if (dao.updateApplicationStatus(applicationId, "Accepted")) {
                    loadApplicationsToTable();
                    JOptionPane.showMessageDialog(this, "Application approved!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update status.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select an application to approve.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        frame.add(approveBtn);

        JButton rejectBtn = new JButton("Reject");
        rejectBtn.setBounds(250, 340, 120, 35);
        rejectBtn.setBackground(new Color(0, 153, 204));
        rejectBtn.setForeground(Color.WHITE);
        rejectBtn.setFont(new Font("Arial", Font.BOLD, 14));
        rejectBtn.setFocusPainted(false);
        rejectBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int applicationId = getApplicationIdFromRow(selectedRow);
                ApplicationDAO dao = new ApplicationDAO();
                if (dao.updateApplicationStatus(applicationId, "Rejected")) {
                   loadApplicationsToTable();
                    JOptionPane.showMessageDialog(this, "Application rejected!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update status.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select an application to reject.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        frame.add(rejectBtn);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(400, 340, 120, 35);
        refreshBtn.setBackground(new Color(0, 153, 204));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFont(new Font("Arial", Font.BOLD, 14));
        refreshBtn.addActionListener(e ->loadApplicationsToTable());

        refreshBtn.setFocusPainted(false);
        frame.add(refreshBtn);
        // Background decorative circles
        CirclePanel topCircle = new CirclePanel(new Color(95, 239, 241));
        topCircle.setBounds(-60, 60, 150, 150);
        frame.add(topCircle);

        CirclePanel bottomCircle = new CirclePanel(new Color(95, 239, 241));
        bottomCircle.setBounds(440, 320, 160, 160);
        frame.add(bottomCircle);
        //load data from db
        loadApplicationsToTable();
        // Final frame setup
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private int getApplicationIdFromRow(int row) {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    // Even if the column is hidden, the model still holds it
    return (int) model.getValueAt(row, 0);
}

    private void loadApplicationsToTable() {
    ApplicationDAO dao = new ApplicationDAO();
    ArrayList<Application> applications = dao.getAllApplications();

    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0); // clear existing rows first

    for (Application app : applications) {
        // Add app_id as hidden data, not displayed in visible columns
        model.addRow(new Object[]{
            app.getApplicationId(), // Hidden, you’ll see below we hide it
            app.getStudentId(),
            app.getAverage(),
            app.getCampus(),
            app.getStatus()
        });
    }

    // Hide column 0 (application_id) so it doesn’t show in the table
    if (table.getColumnCount() > 5) return; // prevent duplicate hiding
    table.removeColumn(table.getColumnModel().getColumn(0));
}

    public static void main(String[] args) {
        new AdminDash();
    }

}
