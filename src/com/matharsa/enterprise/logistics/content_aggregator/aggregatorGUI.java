package project23.news.headline.feed.services;

import javax.swing.*;
import java.awt.*;

/**
 * The Visual Presentation Layer.
 * Inherits from JFrame to construct graphical desktop feed window workspace.
 */
public class aggregatorGUI extends JFrame {
    // Establishes a direct connection link to processing and logic engine instance
    private final aggregatorManager manager = new aggregatorManager();

    // Swing list components used to dynamically render underlying data collection array
    private final DefaultListModel<Article> feedModel = new DefaultListModel<>();
    private final JList<Article> feedList = new JList<>(feedModel);

    public aggregatorGUI() {
        // Core Window Setup: Titles, Dimensions, Layout configurations
        setTitle("🚀 Live Content Aggregator Feed 🚀");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        // Layout Panel Construction: Groups input fields cleanly using GridBagLayout parameters
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Publish Next Headline"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Input Fields: Title string receiver and a dropdown picker box for classifications
        JTextField txtTitle = new JTextField(20);
        String[] categories = {"Tech", "Business", "Sports", "Gaming", "Campus News"};
        JComboBox<String> comboCategory = new JComboBox<>(categories);

        // Button Customization: High-visibility coloring styles
        JButton btnPublish = new JButton("Publish to Feed! 🔥");
        btnPublish.setBackground(new Color(46, 204, 113));
        btnPublish.setForeground(Color.WHITE);
        btnPublish.setFont(new Font("Arial", Font.BOLD, 12));

        // Grid Mapping: Arranges visual label nodes and components neatly on a vertical grid matrix
        gbc.gridx = 0; gbc.gridy = 0; inputPanel.add(new JLabel("Article Title:"), gbc);
        gbc.gridx = 1; inputPanel.add(txtTitle, gbc);
        gbc.gridx = 0; gbc.gridy = 1; inputPanel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1; inputPanel.add(comboCategory, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; inputPanel.add(btnPublish, gbc);

        // Center Panel Area: Configures a scrollable list view pane for our timeline stream elements
        feedList.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(feedList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Live Feed Updates (Sorted Newest First)"));

        // Border Placement mapping layout locations
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Action Handler: Fires immediately whenever someone hits the Publish button control
        btnPublish.addActionListener(e -> {
            String title = txtTitle.getText().trim();
            String category = (String) comboCategory.getSelectedItem();

            // Guard Clause validation: Prevents blanks from entering the sorting systems
            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title cannot be empty!", "Oops!", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Processing Pipeline: Hands data values down to the logic management matrix layer
            manager.addArticle(title, category);

            // UI Synch: Wipes out the current list rendering view and paints the new sorted state
            refreshFeedList();

            // Input resets: Empties out input text entry fields ready for next entry
            txtTitle.setText("");
        });
    }

    /**
     * Synchronization loop.
     * Clears old list graphics views and pulls fresh chronologically ordered items from memory.
     */
    private void refreshFeedList() {
        feedModel.clear();
        for (Article article : manager.getFeed()) {
            feedModel.addElement(article);
        }
    }

    // Application Launch Engine entry target point
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Adapts application border windows to match local operating system styling look and feel
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
            new aggregatorGUI().setVisible(true);
        });
    }
}

