package com.matharsa.enterprise.logistics.lms_mapping;

import javax.swing.*;
import java.awt.*;

public class lmsGUI extends JFrame {
    private final lmsCRM manager = new lmsCRM();
    private final DefaultListModel<Course> courseModel = new DefaultListModel<>();
    private final JList<Course> courseList = new JList<>(courseModel);
    private final JTextArea displayArea = new JTextArea(10, 30);

    public lmsGUI() {
        setTitle("LMS Enrollment System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout());
        JTextField txtTitle = new JTextField(8);
        JTextField txtCode = new JTextField(4);
        JButton btnAddCourse = new JButton("Add Course");

        topPanel.add(new JLabel("Course:")); topPanel.add(txtTitle);
        topPanel.add(new JLabel("Code:")); topPanel.add(txtCode);
        topPanel.add(btnAddCourse);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        courseList.setBorder(BorderFactory.createTitledBorder("Select Course"));
        centerPanel.add(new JScrollPane(courseList));

        JPanel studentInputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField txtStudentName = new JTextField();
        JTextField txtStudentID = new JTextField();
        JButton btnEnroll = new JButton("Enroll");

        studentInputPanel.add(new JLabel("Student:")); studentInputPanel.add(txtStudentName);
        studentInputPanel.add(new JLabel("ID:")); studentInputPanel.add(txtStudentID);
        studentInputPanel.add(new JLabel("")); studentInputPanel.add(btnEnroll);
        centerPanel.add(studentInputPanel);

        displayArea.setEditable(false);
        JScrollPane scrollBottom = new JScrollPane(displayArea);
        scrollBottom.setBorder(BorderFactory.createTitledBorder("Current Enrollments"));

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(scrollBottom, BorderLayout.SOUTH);

        btnAddCourse.addActionListener(e -> {
            if (!txtTitle.getText().isEmpty() && !txtCode.getText().isEmpty()) {
                courseModel.addElement(new Course(txtTitle.getText(), txtCode.getText()));
                txtTitle.setText(""); txtCode.setText("");
            }
        });

        btnEnroll.addActionListener(e -> {
            Course selected = courseList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select a course.");
                return;
            }
            try {
                Student s = new Student(txtStudentName.getText(), Integer.parseInt(txtStudentID.getText()));
                manager.enrollStudent(selected, s);
                refreshDisplay();
                txtStudentName.setText(""); txtStudentID.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID must be a number.");
            }
        });
    }

    private void refreshDisplay() {
        StringBuilder sb = new StringBuilder();
        manager.getEnrollmentMap().forEach((course, students) -> {
            sb.append(course).append("\n");
            students.forEach(s -> sb.append("  ↳ ").append(s).append("\n"));
        });
        displayArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new lmsGUI().setVisible(true));
    }
}
