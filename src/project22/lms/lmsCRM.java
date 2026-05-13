package project22.lms;

import java.util.*;

// student records placed right inside the management file!
record Student(String name, int id) {
    @Override
    public String toString() { return name + " (ID: " + id + ")"; }
}
//course
record Course(String title, String code) {
    @Override
    public String toString() { return title + " [" + code + "]"; }
}

public class lmsCRM {
    private final Map<Course, List<Student>> enrollmentMap = new HashMap<>();

    public void enrollStudent(Course course, Student student) {
        enrollmentMap.computeIfAbsent(course, k -> new ArrayList<>()).add(student);
    }

    public Map<Course, List<Student>> getEnrollmentMap() {
        return enrollmentMap;
    }
}
