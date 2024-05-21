package GUI.message_page;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code message} class represents a GUI for displaying messages and contacts.
 */
public class message {
    private JFrame frame;
    private JList<String> systemMessagesList;
    private JList<String> contactsList;

    /**
     * Constructs a {@code message} object and initializes the GUI components.
     */
    public message() {
        frame = new JFrame("Messages and Contacts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the system messages list
        systemMessagesList = new JList<>(new String[]{"[Security Warning] Illegal transaction...", "[System] Welcome back!"});
        JScrollPane systemMessagesScrollPane = new JScrollPane(systemMessagesList);
        JPanel systemMessagesPanel = createSectionPanel("System Messages", systemMessagesScrollPane);

        // Create the contacts list
        contactsList = new JList<>(new String[]{"System message", "Parent", "New user"});
        JScrollPane contactsScrollPane = new JScrollPane(contactsList);
        JPanel contactsPanel = createSectionPanel("Contacts", contactsScrollPane);

        // Add the two section panels to the main panel
        mainPanel.add(systemMessagesPanel, BorderLayout.WEST);
        mainPanel.add(contactsPanel, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
        frame.setSize(new Dimension(600, 400)); // Set the initial size of the window
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    /**
     * Creates a panel with a title and a scroll pane.
     *
     * @param title      the title of the panel
     * @param scrollPane the scroll pane to be added to the panel
     * @return the created panel
     */
    private JPanel createSectionPanel(String title, JScrollPane scrollPane) {
        JPanel sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // Set the preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(100, 400)); // Adjust width and height appropriately

        sectionPanel.add(titleLabel, BorderLayout.NORTH);
        sectionPanel.add(scrollPane, BorderLayout.CENTER);

        return sectionPanel;
    }

    /**
     * Main method to create and display the message GUI.
     *
     * @param args the command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new message());
    }
}
