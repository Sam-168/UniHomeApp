package za.ac.cput.unihomeapp.gui;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ayren
 */
public class TrackApplication extends JFrame {
  private CardLayout cardLayout;
    private JPanel contentPanel;
    public TrackApplication() {
        setTitle("Track Application");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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

        // Tabs
        JPanel tabPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        tabPanel.setBackground(new Color(0, 80, 130));
        String[] tabs = {"All", "Accepted", "Rejected", "Pending"};
        for (String t : tabs) {
            JLabel tab = new JLabel(t);
            tab.setForeground(Color.WHITE);
            tab.setFont(new Font("Arial", Font.PLAIN, 14));
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

        // Show Accepted by default
        cardLayout.show(contentPanel, "Accepted");
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
        buttonPanel.add(makeRoundedButton("View room details", new Color(0, 180, 200)));
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
}


