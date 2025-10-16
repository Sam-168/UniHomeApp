
package za.ac.cput.unihomeapp.gui;
import javax.swing.*;
import java.awt.*;

public class WelcomeGUI extends JFrame {

    public WelcomeGUI() {
        // Frame setup
        JFrame frame = new JFrame("UniHome Login");
        frame.setSize(380, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(5, 72, 107)); // Dark blue background

        // UniHome Logo Image
        ImageIcon logoIcon = new ImageIcon(Welcome.class.getResource("/Logo.png"));
        Image scaledLogo = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledLogo);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(140, 50, 80, 80);
        frame.add(logoLabel);

        // Welcome Text
        JLabel welcomeLabel = new JLabel("Welcome !");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(125, 160, 200, 30);
        frame.add(welcomeLabel);

        // Log in button
        JButton loginButton = new JButton("Log in");
        loginButton.setBounds(100, 280, 160, 35);
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        frame.add(loginButton);

        // Landlord Login button
        JButton landlordButton = new JButton("Landlord Login");
        landlordButton.setBounds(100, 400, 160, 35);
        landlordButton.setBackground(Color.WHITE);
        landlordButton.setForeground(Color.BLACK);
        landlordButton.setFocusPainted(false);
        landlordButton.setFont(new Font("Arial", Font.BOLD, 14));
        landlordButton.setBorder(BorderFactory.createEmptyBorder());
        frame.add(landlordButton);

        // Create account button
        JButton createButton = new JButton("Sign up");
        createButton.setBounds(100, 340, 160, 35);
        createButton.setBackground(Color.WHITE);
        createButton.setForeground(Color.BLACK);
        createButton.setFocusPainted(false);
        createButton.setFont(new Font("Arial", Font.BOLD, 14));
        createButton.setBorder(BorderFactory.createEmptyBorder());
        frame.add(createButton);

        // Decorative Circles (Top left and bottom right)
        CirclePanel topCircle = new CirclePanel(new Color(95, 239, 241));
        topCircle.setBounds(-60, -60, 150, 150);
        frame.add(topCircle);

        CirclePanel bottomCircle = new CirclePanel(new Color(95, 239, 241));
        bottomCircle.setBounds(250, 480, 150, 150);
        frame.add(bottomCircle);

        frame.setResizable(false);
        frame.setVisible(true);
    }
}