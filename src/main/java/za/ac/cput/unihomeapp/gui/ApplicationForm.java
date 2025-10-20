package za.ac.cput.unihomeapp.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import za.ac.cput.unihomeapp.dao.ApplicationDAO;
import za.ac.cput.unihomeapp.domain.Application;
/**
 *
 * @author ayren
 */

public class ApplicationForm extends JFrame {

    public ApplicationForm() {
        // Frame setup
        JFrame frame = new JFrame("Apply for Accommodation");
        frame.setSize(450, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(5, 72, 107)); // dark blue background
        // --- Header label ---
        JLabel headerLabel = new JLabel("Apply for Accommodation");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setBounds(90, 30, 300, 40);
        frame.add(headerLabel);

        // --- White container panel ---
        JPanel panel = new JPanel();
        panel.setBounds(40, 100, 350, 580);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        frame.add(panel);
        // --- Add background decorative circles FIRST so they stay behind everything ---
        CirclePanel topCircle = new CirclePanel(new Color(95, 239, 241));
        topCircle.setBounds(-60, 20, 150, 150); // moved slightly higher
        frame.add(topCircle);

        CirclePanel bottomCircle = new CirclePanel(new Color(95, 239, 241));
        bottomCircle.setBounds(280, 560, 160, 160); // adjusted for better spacing
        frame.add(bottomCircle);

        // --- Student Information Section ---
        JLabel studentInfoLabel = new JLabel("Student Information");
        studentInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        studentInfoLabel.setBounds(20, 10, 200, 25);
        panel.add(studentInfoLabel);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(20, 40, 120, 20);
        panel.add(nameLabel);

        JTextField nameField = new JTextField("John Doe");
        nameField.setBounds(20, 60, 300, 30);
        styleField(nameField);
        panel.add(nameField);

        JLabel studentNumLabel = new JLabel("Student Number:");
        studentNumLabel.setBounds(20, 100, 150, 20);
        panel.add(studentNumLabel);

        JTextField studentNumField = new JTextField("123456789");
        studentNumField.setBounds(20, 120, 300, 30);
        styleField(studentNumField);
        panel.add(studentNumField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(20, 160, 100, 20);
        panel.add(genderLabel);

        String[] genders = {"Male", "Female", "Other"};
        JComboBox<String> genderCombo = new JComboBox<>(genders);
        genderCombo.setBounds(20, 180, 300, 30);
        panel.add(genderCombo);

        JLabel campusLabel = new JLabel("Campus:");
        campusLabel.setBounds(20, 220, 100, 20);
        panel.add(campusLabel);

        String[] campuses = {"District Six", "Bellville", "Mowbray", "Wellington", "Granger Bay"};
        JComboBox<String> campusCombo = new JComboBox<>(campuses);
        campusCombo.setBounds(20, 240, 300, 30);
        panel.add(campusCombo);

        // --- Academic Information Section ---
        JLabel academicInfoLabel = new JLabel("Academic Information");
        academicInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        academicInfoLabel.setBounds(20, 280, 250, 25);
        panel.add(academicInfoLabel);

        String[] columns = {"Subject Name", "Mark"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable subjectTable = new JTable(tableModel);
        subjectTable.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subjectTable.setRowHeight(25);
        subjectTable.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(subjectTable);
        scrollPane.setBounds(20, 310, 300, 120);
        panel.add(scrollPane);

        JButton addBtn = new JButton("Add Subject");
        addBtn.setBounds(20, 440, 130, 30);
        addBtn.setBackground(new Color(0, 153, 204));
        addBtn.setFont(new Font("Arial", Font.BOLD, 13));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        panel.add(addBtn);

        JButton removeBtn = new JButton("Remove Subject");
        removeBtn.setBounds(190, 440, 130, 30);
        removeBtn.setBackground(new Color(0, 153, 204));
        removeBtn.setFont(new Font("Arial", Font.BOLD, 11));
        removeBtn.setForeground(Color.WHITE);
        removeBtn.setFocusPainted(false);
        panel.add(removeBtn);

        JButton applyBtn = new JButton("Apply Now");
        applyBtn.setBounds(100, 500, 150, 35);
        applyBtn.setBackground(new Color(0, 153, 204));
        applyBtn.setForeground(Color.WHITE);
        applyBtn.setFont(new Font("Arial", Font.BOLD, 15));
        applyBtn.setFocusPainted(false);
        panel.add(applyBtn);
        
        addBtn.addActionListener(e -> {
            String subject = JOptionPane.showInputDialog(frame, "Enter Subject Name:");
            if (subject == null || subject.trim().isEmpty()) {
                return;
            }
            String markStr = JOptionPane.showInputDialog(frame, "Enter Mark:");
            try {
                double mark = Double.parseDouble(markStr);
                tableModel.addRow(new Object[]{subject, mark});
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid mark entered!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        removeBtn.addActionListener(e -> {
            int selectedRow = subjectTable.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Select a subject to remove", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        
        applyBtn.addActionListener(e -> {
            try {
                String fullName = nameField.getText().trim();
                String studentNum = studentNumField.getText().trim();
                String gender = (String) genderCombo.getSelectedItem();
                String campus = (String) campusCombo.getSelectedItem();

                if (fullName.isEmpty() || studentNum.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Full Name and Student Number are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Collect subjects
                ArrayList<String> subjects = new ArrayList<>();
                ArrayList<Double> marks = new ArrayList<>();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    subjects.add((String) tableModel.getValueAt(i, 0));
                    marks.add((Double) tableModel.getValueAt(i, 1));
                }
                int studentId =Integer.parseInt(studentNum);
                // Create application object
                Application app = new Application(studentId, fullName, gender, campus, subjects, marks);

                // Save to DB
                ApplicationDAO dao = new ApplicationDAO();
                boolean success = dao.saveApplication(app);
                
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Application submitted successfully!");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to submit application!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        
       

        // --- Final setup ---
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void styleField(JTextField field) {
        field.setBackground(new Color(230, 230, 230));
        field.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    static class CirclePanel extends JPanel {
        private final Color color;

        public CirclePanel(Color color) {
            this.color = color;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            g.fillOval(0, 0, getWidth(), getHeight());
        }
    }
    public static void main(String[] args) {
        new ApplicationForm();
    }
}
