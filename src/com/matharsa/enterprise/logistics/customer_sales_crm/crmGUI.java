package com.matharsa.enterprise.logistics.customer_sales_crm;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class crmGUI extends JFrame {
    private final crmManager manager = new crmManager();
    private final DefaultListModel<Client> clientModel = new DefaultListModel<>();
    private final JList<Client> clientList = new JList<>(clientModel);
    private final JTextArea historyDisplay = new JTextArea();

    public crmGUI() {
        setTitle("📈 Customer Sales Interaction Services CRM 📈");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Onboard New Corporate Client"));
        JTextField txtName = new JTextField(12);
        JTextField txtEmail = new JTextField(15);
        // Initialize the button first (This fixes the red squiggly lines on lines 42, 35, 36, 37)
        JButton btnAddClient = new JButton("Add Client +");

        // Set up the click action event block
        btnAddClient.addActionListener(e -> {
            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();

            if (!name.isEmpty() && !email.isEmpty()) {
                clientModel.addElement(new Client(name, email));
                // Leaving the text fields alone so data stays inside the input boxes!
            }
        });


        topPanel.add(new JLabel("Name:")); topPanel.add(txtName);
        topPanel.add(new JLabel("Email:")); topPanel.add(txtEmail);
        topPanel.add(btnAddClient);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        clientList.setBorder(BorderFactory.createTitledBorder("Active Corporate Accounts"));
        centerPanel.add(new JScrollPane(clientList));

        JPanel interactionPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        interactionPanel.setBorder(BorderFactory.createTitledBorder("Log Client Interaction"));
        JTextField txtNote = new JTextField();
        JTextField txtDate = new JTextField(LocalDate.now().toString());

        // Locate this block inside your crmGUI constructor and update it:
        JButton btnLog = new JButton("Log Touchpoint & Schedule 🚀");

// 1. Boost contrast with a richer business blue background
        btnLog.setBackground(new Color(21, 101, 192));

// 2. Keep the text crisp white
        btnLog.setForeground(Color.WHITE);
        btnLog.setFont(new Font("Arial", Font.BOLD, 12));

// 3. FORCE the OS theme engine to custom colors!
        btnLog.setContentAreaFilled(false);
        btnLog.setOpaque(true);
        btnLog.setBorderPainted(false); // Eliminates the thick white border trace

        interactionPanel.add(new JLabel("Log Activity Note:")); interactionPanel.add(txtNote);
        interactionPanel.add(new JLabel("Follow-up Date (YYYY-MM-DD):")); interactionPanel.add(txtDate);
        interactionPanel.add(btnLog);
        centerPanel.add(interactionPanel);

        historyDisplay.setEditable(false);
        historyDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollHistory = new JScrollPane(historyDisplay);
        scrollHistory.setBorder(BorderFactory.createTitledBorder("Selected Account History Log"));
        scrollHistory.setPreferredSize(new Dimension(100, 150));

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(scrollHistory, BorderLayout.SOUTH);

        btnAddClient.addActionListener(e -> {
            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();
            if (!name.isEmpty() && !email.isEmpty()) {
                clientModel.addElement(new Client(name, email));
                txtName.setText(""); txtEmail.setText("");
            }
        });

        btnLog.addActionListener(e -> {
            Client selectedClient = clientList.getSelectedValue();
            if (selectedClient == null) {
                JOptionPane.showMessageDialog(this, "Select a client from the list first!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                LocalDate followUp = LocalDate.parse(txtDate.getText().trim());
                manager.addInteraction(selectedClient, txtNote.getText().trim(), followUp);
                updateHistoryView(selectedClient);
                txtNote.setText("");
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Use correct YYYY-MM-DD format!", "Date Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        clientList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateHistoryView(clientList.getSelectedValue());
            }
        });
    }

    private void updateHistoryView(Client client) {
        if (client == null) {
            historyDisplay.setText("");
            return;
        }
        StringBuilder sb = new StringBuilder("History Log for: " + client + "\n----------------------------------------\n");
        var encounters = manager.getClientHistory().get(client);
        if (encounters != null) {
            encounters.forEach(i -> sb.append(i).append("\n"));
        } else {
            sb.append("No recorded business touchpoints yet.");
        }
        historyDisplay.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
            new crmGUI().setVisible(true);
        });
    }
}

