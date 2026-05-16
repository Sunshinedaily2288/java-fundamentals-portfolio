package com.matharsa;

import javax.swing.*;
import java.sql.*;

public class Main {
    public static final String DB_URL = "jdbc:sqlite:inventory.db";

    public static void main(String[] args) {
        System.out.println("Starting Restaurant Logistics System...");

        // 1. Initialize and Seed the Backend Database Matrix
        initializeDatabase();

        // 2. Launch the Separated Interface Layer
        SwingUtilities.invokeLater(() -> {
            InventoryGUI gui = new InventoryGUI();
            gui.setVisible(true);
        });
    }

    private static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS restaurant_inventory ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "storage_area TEXT NOT NULL,"
                        + "item_description TEXT NOT NULL,"
                        + "base_unit TEXT,"
                        + "case_price REAL,"
                        + "sub_unit TEXT,"
                        + "unit_price REAL"
                        + ");";

                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(createTableSQL);
                    stmt.execute("DELETE FROM restaurant_inventory;"); // Clear table for a fresh reload
                }

                String insertSQL = "INSERT INTO restaurant_inventory(storage_area, item_description, base_unit, case_price, sub_unit, unit_price) VALUES(?,?,?,?,?,?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    Object[][] excelData = {
                            {"Bread", "12 Grain Bread (12 ct)", "Pkg", 1.10, "Each", 0.07},
                            {"Bread", "3x5's 6 ct Bread Rolls", "Pkg", 1.25, "Each", 0.21},
                            {"Beverages", "AQUAFINA WATER - 20 OZ - 24/CASE", "Case", 9.25, "Btl", 0.39},
                            {"Paper", "AU BOB PAN 12 OZ. 20/60 ct", "Case", 50.54, "Slv", 2.53},
                            {"Paper", "AU BOB PAN 16 OZ. 20/25 ct", "Case", 29.43, "Slv", 1.47},
                            {"Freezer", "Bacon Sliced Bulk 18/22 Ct", "Case", 33.35, "Lb", 2.22},
                            {"Beverages", "BAG IN THE BOX COKE SYRUP", "Gal", 8.28, "N/A", 0.00},
                            {"Cooler", "BASE CHICKEN 6/CASE ENGINE", "Case", 29.94, "Lb", 4.99},
                            {"Cooler", "Bratwurst Pauxent 10lb/cs 4/1", "Case", 24.95, "Lb", 2.50},
                            {"Desserts", "Brownie Walnut Trays", "Case", 42.00, "Tray", 5.25},
                            {"Bread", "Buns Onion Premium", "Pkg", 2.00, "Each", 0.25},
                            {"Bread", "Buns Sesame 12 ct", "Pkg", 1.10, "Each", 0.09},
                            {"Cooler Dairy", "CHEESE SWISS 4/5 Lb 160 slice", "Case", 39.07, "Lb", 1.95},
                            {"Beverages", "COFFEE DECAF 36/CASE PACKS", "Case", 39.70, "Bag", 1.10},
                            {"Beverages", "COFFEE REGULAR 36/CASE PACKS", "Case", 77.28, "Bag", 2.15},
                            {"Beverages", "COKE 20 OZ - 24/CASE BOTTLES", "Case", 10.80, "Btl", 0.45},
                            {"Freezer", "Corn IQF 6/4 Lb Ice Bags", "Case", 16.02, "Lb", 0.67},
                            {"Cooler Dairy", "EGGS WHOLE MEDIUM BOX", "Case", 11.19, "Each", 0.06},
                            {"Freezer", "Fries Straight Cut Bulk", "Case", 14.19, "Bag", 2.37},
                            {"Freezer", "Fries Wedge Fries - 30 Lb Box", "Case", 21.00, "N/A", 0.00},
                            {"Cooler Produce", "Garlic Chopped 6/48 oz Jars", "Case", 23.41, "Each", 3.90},
                            {"Cooler Dairy", "Heavy Cream 6/64z Cartons", "Case", 36.48, "Each", 6.08}
                    };

                    for (Object[] row : excelData) {
                        pstmt.setString(1, (String) row[0]);
                        pstmt.setString(2, (String) row[1]);
                        pstmt.setString(3, (String) row[2]);
                        pstmt.setDouble(4, (Double) row[3]);
                        pstmt.setString(5, (String) row[4]);
                        pstmt.setDouble(6, (Double) row[5]);
                        pstmt.addBatch();
                    }
                    pstmt.executeBatch();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
