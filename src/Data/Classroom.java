package Data;

public class Classroom {
    private String classroom;

    public Classroom(String classroom) {
        this.classroom = classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return classroom;
    }
}
