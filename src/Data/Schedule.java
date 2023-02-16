package Data;

import java.awt.*;
import java.io.*;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Schedule implements Serializable {
    private ArrayList<Group> groups;
    private ArrayList<Classroom> classrooms;
    private ArrayList<Subject> subjects;
    private ArrayList<Teacher> teachers;
    private ArrayList<Lesson> lessons;

    public Schedule() {
    }

    public ArrayList<Classroom> getClassrooms() {
        int room = 100;
        for (int i = 0; i < 7; i++) {
            this.classrooms.add(new Classroom(Integer.toString(room)));
            room++;
        }
        return this.classrooms;
    }

    public ArrayList<Group> getGroups() {
        int group = 0;
        for (int i = 0; i < 5; i++) {
            this.groups.add(new Group("A" + group));
        }
        return groups;
    }

    public ArrayList<Subject> getSubjects() {

        this.subjects.add((Subject.Wiskunde));
        this.subjects.add((Subject.Nederlands));
        this.subjects.add((Subject.Engels));
        this.subjects.add((Subject.Duits));
        this.subjects.add((Subject.Frans));
        this.subjects.add((Subject.Economie));
        this.subjects.add((Subject.Natuurkunde));
        this.subjects.add((Subject.Scheikunde));
        this.subjects.add((Subject.Muziek));

        return subjects;
    }

    public ArrayList<Teacher> getTeachers() {
        this.teachers.add(new Teacher("Johan Shortboom"));
        this.teachers.add(new Teacher("Joly Plusdijk"));
        this.teachers.add(new Teacher("Hans van der Lindeboom"));
        this.teachers.add(new Teacher("Peter Herreiger"));
        this.teachers.add(new Teacher("Robin Schaaldier"));
        this.teachers.add(new Teacher("Pieter Schop Jansen"));
        this.teachers.add(new Teacher("Maurice Draden"));
        this.teachers.add(new Teacher("Wouter van Ooievaar"));
        this.teachers.add(new Teacher("Sanne Slopsepa"));

        return teachers;
    }
}
