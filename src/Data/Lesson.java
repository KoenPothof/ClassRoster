package Data;

import Data.Rooms.Room;

public class Lesson {
    private Time time;
    private Subject subject;
    private Teacher teacher;
    private Room classroom;
    private Group group;

    public Lesson(Time time, Subject subject, Teacher teacher, Room classroom, Group group) {
        this.time = time;
        this.subject = subject;
        this.teacher = teacher;
        this.classroom = classroom;
        this.group = group;
    }

    public Time getTime() {
        return time;
    }

    public Subject getSubject() {
        return subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Room getClassroom() {
        return classroom;
    }

    public Group getGroup() {
        return group;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setClassroom(Room classroom) {
        this.classroom = classroom;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "time=" + time +
                ", subject=" + subject +
                ", teacher=" + teacher +
                ", classroom=" + classroom +
                ", group=" + group;
    }
}