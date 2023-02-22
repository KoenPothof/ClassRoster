package Data;

import java.awt.*;
import java.io.*;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Schedule {
    private Subject subject;
    private Teacher teacher;
    private Classroom classroom;
    private Group group;
    private Lesson lesson;

    public Schedule(Subject subject, Teacher teacher, Classroom classroom, Group group, Lesson lesson) {
        this.subject = subject;
        this.teacher = teacher;
        this.classroom = classroom;
        this.group = group;
        this.lesson = lesson;
    }
}
