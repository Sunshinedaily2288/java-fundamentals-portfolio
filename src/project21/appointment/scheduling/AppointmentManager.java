package project21.appointment.scheduling;

import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {
    private List<AppointmentStructure> schedule = new ArrayList<>();

    public void book(String name, String time, String doctor) {
        schedule.add(new AppointmentStructure(name, time, doctor));
    }

    public List<AppointmentStructure> getSchedule() {
        return schedule;
    }
}

