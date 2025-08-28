package za.ac.cput.unihomeapp.gui;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ayren
 */
public class LandLordForm extends JFrame {

    public LandLordForm() {
        // Frame setup
        JFrame frame = new JFrame("Create New Account");
        frame.setSize(380, 640);
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
        titleLabel.setBounds(90, 30, 200, 60);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        frame.add(titleLabel);

        // Subtitle
        JLabel subLabel = new JLabel("Already Registered? Log in here.");
        subLabel.setBounds(90, 60, 300, 50);
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

    }
}