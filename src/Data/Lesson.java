package Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public class Lesson implements Serializable {
    private Subject subject;
    private Teacher teacher;
    private Classroom classroom;
    private Group group;
    private LocalTime startTime;
    private LocalTime endTime;

    public Lesson(Subject subject, Teacher teacher, Classroom classroom, Group group, LocalTime startTime, LocalTime endTime) {
        this.subject = subject;
        this.teacher = teacher;
        this.classroom = classroom;
        this.group = group;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
