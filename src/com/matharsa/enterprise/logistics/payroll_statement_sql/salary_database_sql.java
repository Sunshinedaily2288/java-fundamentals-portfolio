package com.matharsa.enterprise.logistics.payroll_statement_sql;

import java.io.*;

/**
 * Enterprise Relational Data Access Layer (DAL) Simulator.
 * Tracks primary keys and transaction timestamps natively without external drivers.
 */
public class salary_database_sql {
    private static final String STORAGE_FILE = "corporate_payroll.csv";

    public static void initializeDatabase() {
        File file = new File(STORAGE_FILE);
        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println("employee_id,employee_name,gross_salary,deductions,net_payable,transaction_date");
                System.out.println("[SQL Engine] Relational payroll_records table validated successfully.");
            } catch (IOException e) {
                System.err.println("[SQL Setup Error] Failed to prepare relational tables: " + e.getMessage());
            }
        }
    }

    /**
     * Inserts an employee salary row record after checking for explicit PRIMARY KEY violations.
     */
    public static boolean commitPayrollToSQL(salary_structure record) {
        if (isPrimaryKeyViolated(record.employeeId)) {
            System.err.println("[SQL Query Error] PRIMARY KEY constraint violation. ID " + record.employeeId + " already exists.");
            return false;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(STORAGE_FILE, true))) {
            writer.println(record.employeeId + "," +
                    record.employeeName + "," +
                    String.format("%.2f", record.grossSalary) + "," +
                    String.format("%.2f", record.taxDeduction) + "," +
                    String.format("%.2f", record.netPayable) + "," +
                    record.transactionDate); // Appends the timestamp string securely
            return true;
        } catch (IOException e) {
            System.err.println("[SQL Query Error] Failed to post record to data engine: " + e.getMessage());
            return false;
        }
    }

    private static boolean isPrimaryKeyViolated(int targetId) {
        File file = new File(STORAGE_FILE);
        if (!file.exists()) return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine(); // Skip header row
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    try {
                        int existingId = Integer.parseInt(tokens[0].trim());
                        if (existingId == targetId) {
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        // Skip corrupted rows
                    }
                }
            }
        } catch (IOException e) {
            // Assume no violation if unreadable
        }
        return false;
    }
}
