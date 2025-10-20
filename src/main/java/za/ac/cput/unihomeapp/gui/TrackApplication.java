/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.unihomeapp.gui;

/**
 *
 * @author samuk
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import za.ac.cput.unihomeapp.dao.AccommodationDAO;
import za.ac.cput.unihomeapp.dao.ApplicationDAO;
import za.ac.cput.unihomeapp.domain.Accommodation;
import za.ac.cput.unihomeapp.domain.Application;

/**
 *
 * @author ayren
 */
public class TrackApplication extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private int studentId;
    private ApplicationDAO dao;
    private Application app;

    public TrackApplication(int studentId) {

        setTitle("Track Application");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        dao = new ApplicationDAO();
        app = dao.getApplicationByStudentId(studentId);
        showApplicationStatus(app);

        // Main background panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0, 80, 130));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Title
        JLabel title = new JLabel("Track application");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Tabs - now clickable
        JPanel tabPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        tabPanel.setBackground(new Color(0, 80, 130));
        String[] tabs = {"All", "Accepted", "Rejected", "Pending"};
        for (String tabName : tabs) {
            JLabel tab = createClickableTab(tabName);
            tabPanel.add(tab);
        }
        mainPanel.add(tabPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Content area with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(new Color(0, 80, 130));

        // Pages
        contentPanel.add(makeAllPanel(), "All");
        contentPanel.add(makeAcceptedPanel(), "Accepted");
        contentPanel.add(makeRejectedPanel(), "Rejected");
        contentPanel.add(makePendingPanel(), "Pending");
        contentPanel.add(makeRoomDetailsPanel(), "RoomDetails"); // hidden until button is pressed

        mainPanel.add(contentPanel);

        add(mainPanel);
        setVisible(true);

        // Show All by default (or whichever you prefer)
        cardLayout.show(contentPanel, "All");
    }

    // Create clickable tab with mouse listener
    private JLabel createClickableTab(String tabName) {
        JLabel tab = new JLabel(tabName);
        tab.setForeground(Color.WHITE);
        tab.setFont(new Font("Arial", Font.PLAIN, 14));
        tab.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Show hand cursor on hover
        tab.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Add mouse listener to handle clicks
        tab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Switch to the corresponding card when tab is clicked
                cardLayout.show(contentPanel, tabName);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                tab.setForeground(new Color(200, 230, 255)); // Light blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tab.setForeground(Color.WHITE); // Back to white when not hovering
            }
        });

        return tab;
    }

    // Accepted page
    private JPanel makeAcceptedPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 80, 130));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel card = makeCard(new Color(0, 140, 180));

        JLabel congrats = new JLabel("Congratulations!!");
        congrats.setFont(new Font("Arial", Font.BOLD, 18));
        congrats.setForeground(Color.WHITE);
        congrats.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(congrats);
        card.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel appName = makeHeaderLabel("New Market Junction");
        card.add(appName);
        card.add(Box.createRigidArea(new Dimension(0, 8)));

        card.add(makeInfoLabel("Applied at:     10 May 2024"));
        card.add(makeInfoLabel("Room type:     Sharing"));
        card.add(makeInfoLabel("Ref number:   #234952"));
        card.add(makeInfoLabel("Status:           ACCEPTED!"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonPanel.setBackground(new Color(0, 140, 180));

        // Make the "View room details" button functional
        JButton viewRoomBtn = makeRoundedButton("View room details", new Color(0, 180, 200));
        viewRoomBtn.addActionListener(e -> cardLayout.show(contentPanel, "RoomDetails"));
        buttonPanel.add(viewRoomBtn);

        buttonPanel.add(makeRoundedButton("Cancel Application", new Color(200, 70, 70)));
        card.add(buttonPanel);

        panel.add(card);
        return panel;
    }

    // Room Details (separate page, only shown when button pressed later)
    private JPanel makeRoomDetailsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 80, 130));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel roomCard = makeCard(new Color(0, 180, 200));
        roomCard.add(makeHeaderLabel("Room Details"));
        roomCard.add(Box.createRigidArea(new Dimension(0, 8)));
        roomCard.add(makeInfoLabel("Residence:       NMJ"));
        roomCard.add(makeInfoLabel("Room no:         A420"));
        roomCard.add(makeInfoLabel("Res Period:      (Feb–Nov)"));
        roomCard.add(Box.createRigidArea(new Dimension(0, 10)));
        roomCard.add(makeInfoLabel("Be sure to receive your key from"));
        roomCard.add(makeInfoLabel("the admin office at NMJ!"));

        // Add a back button to return to Accepted tab
        JButton backButton = makeRoundedButton("Back to Application", new Color(0, 140, 180));
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "Accepted"));
        roomCard.add(Box.createRigidArea(new Dimension(0, 10)));
        roomCard.add(backButton);

        panel.add(roomCard);

        return panel;
    }

    // Rejected page
    private JPanel makeRejectedPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 80, 130));
        JLabel msg = new JLabel("Sorry, your application was REJECTED.");
        msg.setFont(new Font("Arial", Font.BOLD, 16));
        msg.setForeground(Color.RED);
        panel.add(msg);
        return panel;
    }

    // Pending page
    private JPanel makePendingPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 80, 130));
        JLabel msg = new JLabel("Your application is still PENDING...");
        msg.setFont(new Font("Arial", Font.BOLD, 16));
        msg.setForeground(Color.YELLOW);
        panel.add(msg);
        return panel;
    }

    // All page
    private JPanel makeAllPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 80, 130));
        JLabel msg = new JLabel("Showing ALL applications here...");
        msg.setFont(new Font("Arial", Font.BOLD, 16));
        msg.setForeground(Color.WHITE);
        panel.add(msg);
        return panel;
    }

    // Utility methods
    private JPanel makeCard(Color bgColor) {
        JPanel card = new JPanel();
        card.setBackground(bgColor);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return card;
    }

    private JLabel makeHeaderLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JLabel makeInfoLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        return label;
    }

    private void populateAcceptedPanel(Application app) {
    // Clear the previous Accepted panel and create a new one dynamically
    JPanel acceptedPanel = makeAcceptedPanel(); // base panel with card layout structure

    // Fetch accommodation for this student
    AccommodationDAO accDao = new AccommodationDAO();
    Accommodation acc = accDao.getAccommodationByStudentId(app.getStudentId());

    // Update the labels in the acceptedPanel
    acceptedPanel.removeAll(); // Clear any existing components

    JPanel card = makeCard(new Color(0, 140, 180));

    JLabel congrats = new JLabel("Congratulations!!");
    congrats.setFont(new Font("Arial", Font.BOLD, 18));
    congrats.setForeground(Color.WHITE);
    congrats.setAlignmentX(Component.CENTER_ALIGNMENT);
    card.add(congrats);
    card.add(Box.createRigidArea(new Dimension(0, 10)));

    // Application info
    JLabel appName = makeHeaderLabel(acc != null ? acc.getRoom_type() + " - Residence " + acc.getResidence_id() : "Residence Info Pending");
    card.add(appName);
    card.add(Box.createRigidArea(new Dimension(0, 8)));

    if (acc != null) {
        card.add(makeInfoLabel("Applied at:     " + java.time.LocalDate.now()));
        card.add(makeInfoLabel("Room type:     " + acc.getRoom_type()));
        card.add(makeInfoLabel("Price:             R" + acc.getPrice()));
        card.add(makeInfoLabel("Lease:           " + acc.getLease_duration()));
        card.add(makeInfoLabel("Status:           ACCEPTED!"));
    } else {
        card.add(makeInfoLabel("Accommodation details are not yet available."));
    }

    // Buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    buttonPanel.setBackground(new Color(0, 140, 180));

        JButton viewRoomBtn = new JButton("View room details");
        viewRoomBtn.setBackground(new Color(0, 180, 200));
        viewRoomBtn.setForeground(Color.WHITE);
        viewRoomBtn.setFocusPainted(false);
        viewRoomBtn.addActionListener(e -> {
            // Show the RoomDetails card
            cardLayout.show(contentPanel, "RoomDetails");
        });
        buttonPanel.add(viewRoomBtn);

        // Create the "Cancel Application" button
        JButton cancelBtn = new JButton("Cancel Application");
        cancelBtn.setBackground(new Color(200, 70, 70));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);
        buttonPanel.add(cancelBtn);

    card.add(buttonPanel);
    acceptedPanel.add(card);

    // Replace old Accepted panel with the new one
    contentPanel.add(acceptedPanel, "Accepted");
}


// Dynamic population for Rejected panel
    private void populateRejectedPanel(Application app) {
        JPanel rejectedPanel = (JPanel) contentPanel.getComponent(2); // Rejected index
        rejectedPanel.removeAll();

        JLabel msg = new JLabel("Sorry, your application was REJECTED.");
        msg.setFont(new Font("Arial", Font.BOLD, 16));
        msg.setForeground(Color.RED);

        JLabel ref = makeInfoLabel("Ref number: #" + app.getApplicationId());

        rejectedPanel.add(msg);
        rejectedPanel.add(ref);
        rejectedPanel.revalidate();
        rejectedPanel.repaint();
    }

// Dynamic population for Pending panel
    private void populatePendingPanel(Application app) {
        JPanel pendingPanel = (JPanel) contentPanel.getComponent(3); // Pending index
        pendingPanel.removeAll();

        JLabel msg = new JLabel("Your application is still PENDING...");
        msg.setFont(new Font("Arial", Font.BOLD, 16));
        msg.setForeground(Color.YELLOW);

        JLabel ref = makeInfoLabel("Ref number: #" + app.getApplicationId());

        pendingPanel.add(msg);
        pendingPanel.add(ref);
        pendingPanel.revalidate();
        pendingPanel.repaint();
    }

    private void showApplicationStatus(Application app) {
        if (app == null) {
            cardLayout.show(contentPanel, "All"); // or a "No Application" card
        } else {
            switch (app.getStatus()) {
                case "Accepted":
                    populateAcceptedPanel(app); // dynamically fill labels
                    cardLayout.show(contentPanel, "Accepted");
                    break;
                case "Rejected":
                    populateRejectedPanel(app);
                    cardLayout.show(contentPanel, "Rejected");
                    break;
                case "Pending":
                    populatePendingPanel(app);
                    cardLayout.show(contentPanel, "Pending");
                    break;
                default:
                    cardLayout.show(contentPanel, "All");
                    break;
            }
        }
    }
    private JButton makeRoundedButton(String text, Color bgColor) {
    JButton button = new JButton(text) {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            super.paintComponent(g2);
            g2.dispose();
        }
    };
    button.setContentAreaFilled(false);
    button.setFocusPainted(false);
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Arial", Font.BOLD, 12));
    button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    return button;
}


    public static void main(String[] args) {
        new TrackApplication(12345);
    }
}
