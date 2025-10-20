package za.ac.cput.unihomeapp.gui;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import za.ac.cput.unihomeapp.dao.AdminDAO;
import za.ac.cput.unihomeapp.dao.StudentsDAO;
import za.ac.cput.unihomeapp.domain.Admin;
import za.ac.cput.unihomeapp.domain.Students;

/**
 *
 * @author jadar
 */
public class LogInInput extends JFrame {

    StudentsDAO dao = new StudentsDAO();

    public LogInInput() {
        // Frame setup
        JFrame frame = new JFrame("Login");
        frame.setSize(360, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(5, 72, 107)); // Dark blue background

        // Top "Login" title
        JLabel loginTitle = new JLabel("Login");
        loginTitle.setFont(new Font("Arial", Font.BOLD, 28));
        loginTitle.setForeground(Color.WHITE);
        loginTitle.setBounds(130, 30, 200, 40);
        frame.add(loginTitle);

        // Subtext
        JLabel subText = new JLabel("Sign in to continue.");
        subText.setFont(new Font("Arial", Font.PLAIN, 12));
        subText.setForeground(Color.WHITE);
        subText.setBounds(120, 65, 200, 20);
        frame.add(subText);

        // White Login Card
        JPanel loginCard = new JPanel();
        loginCard.setBackground(Color.WHITE);
        loginCard.setBounds(30, 110, 300, 320);
        loginCard.setLayout(null);
        loginCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0, true));

        // Email Label
        JLabel emailLabel = new JLabel("ENTER EMAIL");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        emailLabel.setForeground(new Color(130, 130, 130));
        emailLabel.setBounds(20, 20, 200, 15);
        loginCard.add(emailLabel);

        // Email Field
        JTextField emailField = new JTextField("johndoel123@gmail.com");
        emailField.setBounds(20, 40, 260, 35);
        emailField.setBackground(new Color(220, 220, 220));
        emailField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        emailField.setFont(new Font("Arial", Font.PLAIN, 12));
        loginCard.add(emailField);

        // Password Label
        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        passwordLabel.setForeground(new Color(130, 130, 130));
        passwordLabel.setBounds(20, 90, 200, 15);
        loginCard.add(passwordLabel);

        // Password Field
        JPasswordField passwordField = new JPasswordField("******");
        passwordField.setBounds(20, 110, 260, 35);
        passwordField.setBackground(new Color(220, 220, 220));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
        loginCard.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Log in");
        loginButton.setBounds(20, 160, 260, 35);
        loginButton.setBackground(new Color(0, 153, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        loginButton.setFocusPainted(false);
        loginCard.add(loginButton);

        // Forgot Password
        JLabel forgotLabel = new JLabel("Forgot Password?");
        forgotLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        forgotLabel.setForeground(new Color(100, 100, 100));
        forgotLabel.setBounds(95, 210, 150, 20);
        loginCard.add(forgotLabel);

        // Signup
        JLabel signupLabel = new JLabel("Back");
        signupLabel.setFont(new Font("Arial", Font.BOLD, 11));
        signupLabel.setForeground(new Color(80, 80, 80));
        signupLabel.setBounds(120, 240, 100, 20);
        loginCard.add(signupLabel);

        // Add login card
        frame.add(loginCard);

        // Background decorative circles
        CirclePanel topCircle = new CirclePanel(new Color(95, 239, 241));
        topCircle.setBounds(-60, 60, 150, 150);
        frame.add(topCircle);

        CirclePanel bottomCircle = new CirclePanel(new Color(95, 239, 241));
        bottomCircle.setBounds(240, 460, 160, 160);
        frame.add(bottomCircle);

        frame.setResizable(false);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
            String emailInput = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (emailInput.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both email and password.");
                return;
            }

            
            Students student = new Students(emailInput, password);
            Students loggedInStudent = dao.signIn(student);

            if (loggedInStudent != null) {
                JOptionPane.showMessageDialog(null,
                        "Student login successful! Welcome " + loggedInStudent.getFirst_name());
                new HomePage(); 
                frame.dispose();
                return;
            }

            // --- Check Admin ---
            AdminDAO adminDAO = new AdminDAO();
            Admin admin = new Admin(emailInput, password); // use email field as username
            Admin loggedInAdmin = adminDAO.signIn(admin);

            if (loggedInAdmin != null) {
                JOptionPane.showMessageDialog(null,
                        "Admin login successful! Welcome " + loggedInAdmin.getUsername());
                new AdminDashboard(); // Admin GUI
                frame.dispose();
                return;
            }

            // If neither found
            JOptionPane.showMessageDialog(null, "Invalid email or password.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Unexpected Error: " + ex.getMessage());
            ex.printStackTrace();
        }
            }

        });
    }

}


