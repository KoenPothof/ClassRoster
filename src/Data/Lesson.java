package Data;

import java.time.LocalTime;
import java.util.ArrayList;

public class Lesson {
    private Subject subject;
    private Teacher teacher;
    private Classroom classroom;
    private Group group;
    private LocalTime startTime;
    private LocalTime endTime;

    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<Classroom> classrooms = new ArrayList<>();
    private ArrayList<Group> groups = new ArrayList<>();

    public Lesson(Subject subject, Teacher teacher, Classroom classroom, Group group, LocalTime startTime, LocalTime endTime) {
        this.subject = subject;
        this.teacher = teacher;
        this.classroom = classroom;
        this.group = group;
        this.startTime = startTime;
        this.endTime = endTime;

        this.teachers.add(this.teacher);
        this.classrooms.add(this.classroom);
        this.groups.add(this.group);
    }

    public void printTeachers(){
        System.out.println(this.teachers);
    }

    public void printClassrooms(){
        System.out.println(this.classrooms);
    }

    public void printGroups(){
        System.out.println(this.groups);
    }

    public void printTeacher(Teacher teacher) {
        System.out.println(teacher.toString());
    }

    @Override
    public String toString() {
        return  "Lesson 1: " + subject + ", "+teacher + ", " + classroom + ", " + group + ", " + startTime + ", " + endTime;
    }
}
