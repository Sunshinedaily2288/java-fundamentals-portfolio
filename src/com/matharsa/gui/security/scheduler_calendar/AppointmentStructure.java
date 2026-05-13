package scheduler_calendar;

public class AppointmentStructure {
    String patientName;
    String timeSlot;
    String doctorName;

    public AppointmentStructure(String patientName, String timeSlot, String doctorName) {
        this.patientName = patientName;
        this.timeSlot = timeSlot;
        this.doctorName = doctorName;
    }

    @Override
    public String toString() {
        return String.format("[%s] Patient: %s | Dr: %s", timeSlot, patientName, doctorName);
    }
}

