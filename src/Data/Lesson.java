package Data;

public class Lesson {
    private Time time;
    private Subject subject;
    private Teacher teacher;
    private Classroom classroom;
    private Group group;

    public Lesson(Time time, Subject subject, Teacher teacher, Classroom classroom, Group group) {
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

    public Classroom getClassroom() {
        return classroom;
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "time=" + time +
                ", subject=" + subject +
                ", teacher=" + teacher +
                ", classroom=" + classroom +
                ", group=" + group +
                '}';
    }
}