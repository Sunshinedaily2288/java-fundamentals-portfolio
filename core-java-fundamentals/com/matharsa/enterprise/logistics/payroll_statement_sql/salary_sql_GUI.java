package com.matharsa.enterprise.logistics.payroll_statement_sql;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Corporate User Interface Layer (UIL) connecting text fields with
 * a relational simulator backend and a localized printing pipeline.
 */
public class salary_sql_GUI extends JFrame {
    private final JTextField idField = new JTextField(5);
    private final JTextField nameField = new JTextField(15);
    private final JTextField grossField = new JTextField(10);

    private final JTextArea displayArea = new JTextArea(8, 40);
    private final JButton processButton = new JButton("Process & Post to SQL Database");
    private final JButton printButton = new JButton("Print Employee Slip");

    private salary_structure lastProcessedSlip = null;

    public salary_sql_GUI() {
        salary_database_sql.initializeDatabase();
        buildDashboardLayout();
        registerEventPipelines();
    }

    private void buildDashboardLayout() {
        setTitle("ABC Enterprise SQL Payroll Terminal");
        setSize(580, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder(" Enterprise SQL Data Field Constraints "));

        inputPanel.add(new JLabel("  Employee ID (PRIMARY KEY - Numeric):"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("  Full Employee Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("  Gross Monthly Salary ($):"));
        inputPanel.add(grossField);
        add(inputPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder(" Real-Time SQL Query Output Stream "));
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        centerPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        bottomPanel.add(processButton);
        printButton.setEnabled(false);
        bottomPanel.add(printButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void registerEventPipelines() {
        processButton.addActionListener(e -> executeSQLPayrollTransmission());
        printButton.addActionListener(e -> executeSlipPrinting());
    }

    private void executeSQLPayrollTransmission() {
        try {
            int empId = Integer.parseInt(idField.getText().trim());
            String empName = nameField.getText().trim();
            double grossSalary = Double.parseDouble(grossField.getText().trim());

            if (empName.isEmpty()) throw new IllegalArgumentException("Employee name cannot be blank.");
            if (grossSalary <= 0) throw new IllegalArgumentException("Gross monthly evaluation must be positive.");

            // Capture the full current system timestamp
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestampStr = now.format(formatter);

            // Fixes the red line error by passing exactly 4 arguments matching the structure constructor
            salary_structure processedSlip = new salary_structure(empId, empName, grossSalary, timestampStr);
            boolean success = salary_database_sql.commitPayrollToSQL(processedSlip);

            if (success) {
                lastProcessedSlip = processedSlip;
                printButton.setEnabled(true);

                displayArea.setText("");
                displayArea.append("======= RELATIONAL SQL INSERT TRANSACTION SUCCESS =======\n");
                displayArea.append(" [SQL Status]      : ROW COMMITTED IN PAYROLL_RECORDS\n");
                displayArea.append(" PK Employee ID    : " + processedSlip.employeeId + "\n");
                displayArea.append(" Staff Member Name : " + processedSlip.employeeName + "\n");
                displayArea.append(" Gross Base Pay    : $" + String.format("%.2f", processedSlip.grossSalary) + "\n");
                displayArea.append(" CPF Deductions    : $" + String.format("%.2f", processedSlip.taxDeduction) + "\n");
                displayArea.append(" Net Liquid Payout : $" + String.format("%.2f", processedSlip.netPayable) + "\n");
                displayArea.append(" Transaction Date  : " + processedSlip.transactionDate + "\n");
                displayArea.append("=========================================================");

                idField.setText("");
                nameField.setText("");
                grossField.setText("");
            } else {
                throw new IllegalStateException("SQL database driver dropped validation payload. Ensure Primary Key uniqueness.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Data parsing exception. Verify numeric constraints.", "Input Error", JOptionPane.WARNING_MESSAGE);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Database Transaction Conflict", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void executeSlipPrinting() {
        if (lastProcessedSlip == null) return;

        String printFileName = "receipt_emp_" + lastProcessedSlip.employeeId + ".txt";

        try (PrintWriter printer = new PrintWriter(new FileWriter(printFileName))) {
            printer.println("========================================================");
            printer.println("          ABC ENTERPRISE PAYROLL RECEIPT          ");
            printer.println("========================================================");
            printer.println(" Issue Full Date   : " + lastProcessedSlip.transactionDate);
            printer.println("--------------------------------------------------------");
            printer.println(" Employee Reference : ID #" + lastProcessedSlip.employeeId);
            printer.println(" Recipient Name     : " + lastProcessedSlip.employeeName);
            printer.println("--------------------------------------------------------");
            printer.println(" Financial Breakdown:");
            printer.println("   (+) Gross Monthly Allocation : $" + String.format("%.2f", lastProcessedSlip.grossSalary));
            printer.println("   (-) Statutory CPF Deductions : $" + String.format("%.2f", lastProcessedSlip.taxDeduction));
            printer.println("--------------------------------------------------------");
            printer.println(" TOTAL NET DISTRIBUTION AMOUNT   : $" + String.format("%.2f", lastProcessedSlip.netPayable));
            printer.println("========================================================");
            printer.println("        Status: TRANSACTION SETTLED VIA BANK CORE       ");
            printer.println("========================================================");

            JOptionPane.showMessageDialog(this, "Payslip document successfully printed to file:\n" + printFileName, "Print Job Completed", JOptionPane.INFORMATION_MESSAGE);

            printButton.setEnabled(false);
            lastProcessedSlip = null;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Print hardware stream pipeline failed: " + e.getMessage(), "System IO Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new salary_sql_GUI().setVisible(true));
    }
}
