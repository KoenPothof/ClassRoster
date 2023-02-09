package Data;

import java.awt.*;
import java.io.*;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Schedule implements Serializable {
    private ArrayList<Lesson> lessons;
    private ArrayList<Teacher> teachers;
    private ArrayList<Classroom> classrooms;
    private ArrayList<Group> groups;

    public Schedule() {
    }

    public ArrayList<Lesson> saveLessons(String filename) {

        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson(LocalTime.of(8, 30), LocalTime.of(9, 20)));
        lessons.add(new Lesson(LocalTime.of(9, 20), LocalTime.of(10, 10)));
        lessons.add(new Lesson(LocalTime.of(10, 10), LocalTime.of(11, 0)));
        lessons.add(new Lesson(LocalTime.of(11, 15), LocalTime.of(12, 5)));
        lessons.add(new Lesson(LocalTime.of(12, 5), LocalTime.of(12, 55)));
        lessons.add(new Lesson(LocalTime.of(13, 30), LocalTime.of(14, 20)));
        lessons.add(new Lesson(LocalTime.of(14, 20), LocalTime.of(15, 10)));
        lessons.add(new Lesson(LocalTime.of(15, 10), LocalTime.of(16, 0)));

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Lesson lesson : lessons) {
                output.writeObject(lesson);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lessons;
    }

    public ArrayList<Teacher> teacherList() {
        this.teachers.add(new Teacher("Johan Shortstruik"));
        this.teachers.add(new Teacher("Joly van Plusdam"));
        this.teachers.add(new Teacher("Edwin van der Poen"));
        this.teachers.add(new Teacher("Sanne Slopsepa"));
        this.teachers.add(new Teacher("Wouter van Ooijen"));
        this.teachers.add(new Teacher("Maurice Draden"));
        this.teachers.add(new Teacher("Pieter Hoofd Jansen"));
        this.teachers.add(new Teacher("Peter Steltloper"));

        return this.teachers;
    }

    public ArrayList<Classroom> classroomList() {

        for (int i = 100; i < 111; i++) {
            this.classrooms.add(new Classroom(i));
        }
        return this.classrooms;
    }

    public ArrayList<Group> groupList() {
        for (int i = 0; i < 7; i++) {
            this.groups.add(new Group("A" + i));
        }
        return this.groups;
    }


}
