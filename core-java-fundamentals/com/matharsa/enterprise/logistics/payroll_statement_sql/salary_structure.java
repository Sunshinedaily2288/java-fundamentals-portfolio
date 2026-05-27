package com.matharsa.enterprise.logistics.payroll_statement_sql;
/**
 * Data Model layer defining structural fields matching corporate com.matharsa.enterprise.logistics.payroll columns,
 * including a dedicated tracking field for transaction timestamps.
 */
public class salary_structure {
    public int employeeId;
    public String employeeName;
    public double grossSalary;
    public double taxDeduction;
    public double netPayable;
    public String transactionDate; // Holds our persistent date record

    public salary_structure(int id, String name, double gross, String date) {
        this.employeeId = id;
        this.employeeName = name;
        this.grossSalary = gross;
        this.transactionDate = date;
        // Apply standard enterprise 15% structural deduction allocation (e.g., CPF)
        this.taxDeduction = gross * 0.15;
        this.netPayable = gross - this.taxDeduction;
    }
}
