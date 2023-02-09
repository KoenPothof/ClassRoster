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

    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<Classroom> classrooms = new ArrayList<>();
    private ArrayList<Group> groups = new ArrayList<>();

    public Lesson(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
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
        return "Lesson: " + startTime + ", " + endTime;
    }
}
