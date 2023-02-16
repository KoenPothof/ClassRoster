package Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public class Lesson implements Serializable {
    private Subject subject;
    private Teacher teacher;
    private Classroom classroom;
    private Group group;

    public Lesson(Data.Subject subject, Teacher teacher, Classroom classroom, Group group) {
        this.subject = subject;
        this.teacher = teacher;
        this.classroom = classroom;
        this.group = group;
    }

    @Override
    public String toString() {
        return this.subject.toString() + this.group + this.teacher + this.classroom;
    }
}
