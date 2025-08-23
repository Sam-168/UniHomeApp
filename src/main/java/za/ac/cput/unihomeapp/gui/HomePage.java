package za.ac.cput.unihomeapp.gui;
import javax.swing.*;
import java.awt.*;


/**
 *
 * @author jadar
 */
public class HomePage extends JFrame  {

    public  HomePage() {
        JFrame frame = new JFrame("Dashboard");
        frame.setSize(360, 640); // Mobile screen size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Absolute layout for better positioning

        // 🔹 Set the full background color (blue)
        frame.getContentPane().setBackground(new Color(5, 72, 107));

        // Top Bar (Dark Blue Background)
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(5, 72, 107));
        topPanel.setBounds(0, 0, 360, 100);
        topPanel.setLayout(null);

        // Welcome Text
        JLabel welcomeLabel = new JLabel("Welcome back, John");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(10, 10, 200, 20);
        topPanel.add(welcomeLabel);

        // Icons
        JLabel userIcon = new JLabel("👤");
        JLabel bellIcon = new JLabel("🔔");
        JLabel searchIcon = new JLabel("🔍");
        userIcon.setBounds(10, 40, 30, 30);
        bellIcon.setBounds(40, 40, 30, 30);
        searchIcon.setBounds(70, 40, 30, 30);
        topPanel.add(userIcon);
        topPanel.add(bellIcon);
        topPanel.add(searchIcon);

        // Dashboard Text
        JLabel dashboardLabel = new JLabel("Dashboard");
        dashboardLabel.setForeground(Color.WHITE);
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dashboardLabel.setBounds(250, 10, 100, 30);
        topPanel.add(dashboardLabel);

        // Accommodation Image I DON'T KNOW WHERE THIS IMAGE IS STORED
        /*
        ImageIcon imageIcon = new ImageIcon(Welcome.class.getResource("/accommodation.png")); // Replace with your image file
        Image img = imageIcon.getImage().getScaledInstance(320, 180, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBounds(20, 110, 320, 180);
        imageLabel.setLayout(null);
        */
        JLabel overlayText = new JLabel("VIEW ACCOMMODATIONS");
        overlayText.setForeground(Color.WHITE);
        overlayText.setFont(new Font("Arial", Font.BOLD, 14));
        overlayText.setBounds(10, 140, 250, 30);
        //imageLabel.add(overlayText);

        // Bottom Card (Track Application)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(new Color(230, 230, 230));
        bottomPanel.setBounds(20, 310, 320, 160);
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

        // Add all to frame
        frame.add(topPanel);
        //frame.add(imageLabel);
        frame.add(bottomPanel);

        frame.setResizable(false);
        frame.setVisible(true);
    }
}
