package Data;

import java.time.LocalTime;
import java.util.ArrayList;

public class Lesson {
    private Subject subject;
    private Teacher teacher;
    private Classroom classroom;
    private Group Group;
    private LocalTime startTime;
    private LocalTime endTime;

    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<Classroom> classrooms = new ArrayList<>();
    private ArrayList<Group> groups = new ArrayList<>();

    public Lesson(Subject subject, Teacher teacher, Classroom classroom, Group group, LocalTime startTime, LocalTime endTime) {
        this.subject = subject;
        this.teacher = teacher;
        this.classroom = classroom;
        this.Group = group;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void add(){

    }
    public void printTeacher(Teacher teacher) {
        System.out.println(teacher.toString());
    }
}
