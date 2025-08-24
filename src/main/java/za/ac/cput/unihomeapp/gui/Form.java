package za.ac.cput.unihomeapp.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import za.ac.cput.unihomeapp.dao.StudentsDAO;
import za.ac.cput.unihomeapp.domain.Students;

/**
 *
 * @author ayren
 */
public class Form extends JFrame {

    StudentsDAO dao = new StudentsDAO();

    public Form() {
        // Frame setup
        JFrame frame = new JFrame("Create New Account");
        frame.setSize(360, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(5, 72, 107)); // dark blue background

        // Main white panel
        JPanel panel = new JPanel();
        panel.setBounds(20, 120, 320, 400);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frame.add(panel);

        // Title
        JLabel titleLabel = new JLabel("Create new Account");
        titleLabel.setBounds(30, 10, 260, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        frame.add(titleLabel);

        // Subtitle
        JLabel subLabel = new JLabel("Already Registered? Log in here.");
        subLabel.setBounds(30, 50, 300, 20);
        subLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subLabel.setForeground(Color.WHITE);
        frame.add(subLabel);

        // Name field
        JLabel nameLabel = new JLabel("NAME");
        nameLabel.setBounds(20, 20, 100, 20);
        panel.add(nameLabel);

        JTextField nameField = new JTextField("John Doe");
        nameField.setBounds(20, 40, 280, 30);
        nameField.setBackground(new Color(230, 230, 230));
        nameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(nameField);

        // Email field
        JLabel emailLabel = new JLabel("EMAIL");
        emailLabel.setBounds(20, 80, 100, 20);
        panel.add(emailLabel);

        JTextField emailField = new JTextField("JohnDoe123@gmail.com");
        emailField.setBounds(20, 100, 280, 30);
        emailField.setBackground(new Color(230, 230, 230));
        emailField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(emailField);

        // Student Number
        JLabel studentLabel = new JLabel("STUDENT NUMBER");
        studentLabel.setBounds(20, 140, 150, 20);
        panel.add(studentLabel);

        JTextField studentField = new JTextField("123456789");
        studentField.setBounds(20, 160, 280, 30);
        studentField.setBackground(new Color(230, 230, 230));
        studentField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(studentField);

        // Contact Number
        JLabel contactLabel = new JLabel("CONTACT NUMBER");
        contactLabel.setBounds(20, 200, 150, 20);
        panel.add(contactLabel);

        JTextField contactField = new JTextField("987654321");
        contactField.setBounds(20, 220, 280, 30);
        contactField.setBackground(new Color(230, 230, 230));
        contactField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(contactField);

        // Password
        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setBounds(20, 260, 100, 20);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField("*");
        passwordField.setBounds(20, 280, 280, 30);
        passwordField.setBackground(new Color(230, 230, 230));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(passwordField);

        // Sign up button
        JButton signUpBtn = new JButton("Sign up");
        signUpBtn.setBounds(20, 330, 280, 40);
        signUpBtn.setBackground(new Color(0, 153, 204)); // teal-ish
        signUpBtn.setForeground(Color.WHITE);
        signUpBtn.setFocusPainted(false);
        panel.add(signUpBtn);

        // Background decorative circles
        CirclePanel topCircle = new CirclePanel(new Color(95, 239, 241));
        topCircle.setBounds(-60, 60, 150, 150);
        frame.add(topCircle);

        CirclePanel bottomCircle = new CirclePanel(new Color(95, 239, 241));
        bottomCircle.setBounds(240, 460, 160, 160);
        frame.add(bottomCircle);

        frame.setResizable(false);
        frame.setVisible(true);

        signUpBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    // Get input values
                    String name = nameField.getText().trim();
                    String email = emailField.getText().trim();
                    String studentNumber = studentField.getText().trim();
                    String contact = contactField.getText().trim();
                    String password = new String(passwordField.getPassword()).trim();

                    // 1. Check for empty fields
                    if (name.isEmpty() || email.isEmpty() || studentNumber.isEmpty() || contact.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                        return;
                    }

                    // 2. Validate Student ID is numeric
                    int student_ID;
                    try {
                        student_ID = Integer.parseInt(studentNumber);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Student ID must be a number.");
                        return;
                    }

                    // 3. Validate email format
                    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        JOptionPane.showMessageDialog(null, "Invalid email format.");
                        return;
                    }

                    // 4. Validate phone number (digits only, 10-15 characters)
                    if (!contact.matches("\\d{10,15}")) {
                        JOptionPane.showMessageDialog(null, "Invalid phone number. Must be 10-15 digits.");
                        return;
                    }

                    // 5. Validate password strength (at least 6 characters, one uppercase, one number)
                    if (!password.matches("^(?=.*[A-Z])(?=.*\\d).{6,}$")) {
                        JOptionPane.showMessageDialog(null, "Password must be at least 6 characters, include one uppercase letter and one number.");
                        return;
                    }

                    // Optional: Check if student ID or email already exists in DB
                    if (dao.isStudentIDExists(student_ID)) {
                        JOptionPane.showMessageDialog(null, "Student ID already exists.");
                        return;
                    }
                    if (dao.isEmailExists(email)) {
                        JOptionPane.showMessageDialog(null, "Email already registered.");
                        return;
                    }

                    // Create student object
                    Students student = new Students(student_ID, name, email, contact, password);

                    // Debug print (optional)
                    System.out.println("Student Info -> ID: " + student.getStudent_ID()
                            + ", Name: " + student.getFirst_name()
                            + ", Email: " + student.getEmail()
                            + ", Phone: " + student.getPhone()
                            + ", Password: " + student.getPassword());

                    // Call DAO to sign up
                    dao.signUp(student);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unexpected Error: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }

        });

    }
}
