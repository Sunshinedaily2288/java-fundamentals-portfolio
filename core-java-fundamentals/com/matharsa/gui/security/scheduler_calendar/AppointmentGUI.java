package com.matharsa.gui.security.scheduler_calendar;

import javax.swing.JOptionPane;

public class AppointmentGUI {
    public static void main(String[] args) {
        AppointmentManager manager = new AppointmentManager();

        // Default data to start the list
        manager.book("Sun", "May 13 @ 09:00 AM", "Dr. Java");

        while (true) {
            String menu = "--- Hospital Booking v21 ---\n" +
                    formatSchedule(manager) +
                    "\n\n1. Book New Appointment\n2. Exit System";

            String choice = JOptionPane.showInputDialog(null, menu, "Appointment Scheduler", JOptionPane.PLAIN_MESSAGE);

            if (choice == null || choice.equals("2")) break;

            if (choice.equals("1")) {
                // 1. Get Name
                String name = JOptionPane.showInputDialog("Enter Patient Name:");
                if (name == null || name.trim().isEmpty()) continue;

                // 2. Simple Date Picker (Dropdown)
                String[] dates = {"May 13", "May 14", "May 15", "May 16", "May 17", "May 18"};
                String selectedDate = (String) JOptionPane.showInputDialog(null,
                        "Select Appointment Date:", "Calendar Picker",
                        JOptionPane.QUESTION_MESSAGE, null, dates, dates[0]);

                if (selectedDate == null) continue; // User clicked cancel

                // 3. Get Time and Doctor
                String time = JOptionPane.showInputDialog("Enter Time (e.g. 10:30 AM):");
                String doctor = JOptionPane.showInputDialog("Enter Doctor Name:");

                // 4. Save to Manager
                manager.book(name, selectedDate + " @ " + time, doctor);
                JOptionPane.showMessageDialog(null, "Booking Confirmed for " + name + " on " + selectedDate);
            }
        }

        JOptionPane.showMessageDialog(null, "Closing Scheduler. All data saved to local session.");
    }

    // Helper method to display the formatted schedule
    private static String formatSchedule(AppointmentManager manager) {
        StringBuilder sb = new StringBuilder();
        for (AppointmentStructure a : manager.getSchedule()) {
            sb.append(a.toString()).append("\n");
        }
        return sb.length() == 0 ? "No appointments today." : sb.toString();
    }
}



