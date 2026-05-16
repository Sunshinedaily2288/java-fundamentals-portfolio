package com.matharsa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class InventoryGUI extends JFrame {
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private JLabel summaryLabel;

    public InventoryGUI() {
        setTitle("Restaurant Supply & Logistics Ledger");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 1. Header Panel: Search Controls
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        controlPanel.setBackground(new Color(24, 43, 73));

        JLabel searchLabel = new JLabel("Live Filter Search: ");
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("Arial", Font.BOLD, 13));

        JTextField searchField = new JTextField(25);
        searchField.setFont(new Font("Arial", Font.PLAIN, 13));

        controlPanel.add(searchLabel);
        controlPanel.add(searchField);
        add(controlPanel, BorderLayout.NORTH);

        // 2. Center Panel: Core UI Grid Table Matrix
        String[] columnHeaders = {"ID", "Storage Area", "Item Description", "Base Unit", "Case Price ($)", "Sub-Unit", "Each Price ($)"};
        tableModel = new DefaultTableModel(columnHeaders, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        JTable dataTable = new JTable(tableModel);
        dataTable.setRowHeight(24);
        dataTable.setFont(new Font("Arial", Font.PLAIN, 12));
        dataTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        rowSorter = new TableRowSorter<>(tableModel);
        dataTable.setRowSorter(rowSorter);

        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);

        // 2b. Configure Enterprise Grid Column Width Profiles
        dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        dataTable.getColumnModel().getColumn(0).setPreferredWidth(40);   // ID
        dataTable.getColumnModel().getColumn(1).setPreferredWidth(120);  // Storage Area
        dataTable.getColumnModel().getColumn(2).setPreferredWidth(320);  // Item Description
        dataTable.getColumnModel().getColumn(3).setPreferredWidth(80);   // Base Unit
        dataTable.getColumnModel().getColumn(4).setPreferredWidth(100);  // Case Price
        dataTable.getColumnModel().getColumn(5).setPreferredWidth(80);   // Sub-Unit
        dataTable.getColumnModel().getColumn(6).setPreferredWidth(100);  // Each Price

        // 3. Footer Panel: Live Operational Summary Ledger
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        footerPanel.setBackground(new Color(240, 244, 248));
        summaryLabel = new JLabel("Calculating portfolio summaries...");
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 13));
        summaryLabel.setForeground(new Color(24, 43, 73));
        footerPanel.add(summaryLabel);
        add(footerPanel, BorderLayout.SOUTH);

        // Register Event Listeners for Instant Live Filtering
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = searchField.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        // Hydrate the GUI Interface and compute financial metrics
        loadTableData();
        calculateMetrics();

        setLocationRelativeTo(null);
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        String querySQL = "SELECT id, storage_area, item_description, base_unit, case_price, sub_unit, unit_price FROM restaurant_inventory";

        try (Connection conn = DriverManager.getConnection(Main.DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(querySQL)) {

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("storage_area"),
                        rs.getString("item_description"),
                        rs.getString("base_unit"),
                        rs.getDouble("case_price"),
                        rs.getString("sub_unit"),
                        rs.getDouble("unit_price")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateMetrics() {
        String scalarSQL = "SELECT COUNT(*), SUM(case_price), AVG(unit_price) FROM restaurant_inventory";
        try (Connection conn = DriverManager.getConnection(Main.DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(scalarSQL)) {

            if (rs.next()) {
                int totalItems = rs.getInt(1);
                double totalCaseValue = rs.getDouble(2);
                double avgUnitPrice = rs.getDouble(3);

                summaryLabel.setText(String.format("Total Catalogued Line Items: %d   |   Total Combined Case Value: $%,.2f   |   Avg Sub-Unit Price: $%.2f",
                        totalItems, totalCaseValue, avgUnitPrice));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


