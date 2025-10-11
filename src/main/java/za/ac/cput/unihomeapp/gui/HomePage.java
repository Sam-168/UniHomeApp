package za.ac.cput.unihomeapp.gui;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author jadar
 */
public class HomePage extends JFrame  {

    public HomePage() {
        // Frame setup
        setTitle("Dashboard");
        setSize(380, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(5, 72, 107));
        setResizable(false);

        // ===== Top Panel =====
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(5, 72, 107));
        topPanel.setBounds(0, 0, 380, 100);
        topPanel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome back, John");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setBounds(20, 10, 250, 25);
        topPanel.add(welcomeLabel);

        JLabel dashboardLabel = new JLabel("Dashboard");
        dashboardLabel.setForeground(Color.WHITE);
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dashboardLabel.setBounds(20, 50, 200, 30);
        topPanel.add(dashboardLabel);

        add(topPanel);

        // ===== Accommodation Image =====
        ImageIcon imgIcon = new ImageIcon(Welcome.class.getResource("/accommodation.png"));
        Image img = imgIcon.getImage().getScaledInstance(320, 180, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBounds(30, 110, 320, 180);
        imageLabel.setLayout(null);

        JLabel overlayText = new JLabel("VIEW ACCOMMODATIONS");
        overlayText.setForeground(Color.WHITE);
        overlayText.setFont(new Font("Arial", Font.BOLD, 14));
        overlayText.setBounds(10, 140, 250, 30);
        imageLabel.add(overlayText);

        add(imageLabel);

        // ===== Bottom Card =====
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(new Color(230, 230, 230));
        bottomPanel.setBounds(30, 310, 320, 160);
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));

        JLabel instructionText = new JLabel("<html><center>Check your application progress<br>and make sure your documents are approved</center></html>");
        instructionText.setHorizontalAlignment(SwingConstants.CENTER);
        instructionText.setBounds(20, 10, 280, 40);
        bottomPanel.add(instructionText);

        JButton trackButton = new JButton("Track Application");
        trackButton.setBounds(80, 100, 160, 30);
        trackButton.setBackground(new Color(0, 153, 204));
        trackButton.setForeground(Color.WHITE);
        trackButton.setFocusPainted(false);
        bottomPanel.add(trackButton);

        add(bottomPanel);

        // ===== Bottom Navigation Bar =====
        JPanel navBar = new JPanel();
        navBar.setBackground(Color.WHITE);
        navBar.setBounds(0, 550, 380, 60);
        navBar.setLayout(new GridLayout(1, 3));

        // Buttons with icons
        JButton userButton = createIconButton("/home.png");
        JButton bellButton = createIconButton("/bell.jpg");
        JButton searchButton = createIconButton("/search.jpg");

        navBar.add(userButton);
        navBar.add(bellButton);
        navBar.add(searchButton);

        add(navBar);

        setVisible(true);
    }

    private JButton createIconButton(String iconPath) {
        ImageIcon icon = new ImageIcon(Welcome.class.getResource(iconPath));
        Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JButton btn = new JButton(new ImageIcon(img));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}