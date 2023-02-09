package Data;

public class Classroom {
    private int classRoom;

    public Classroom(int classRoom) {
        this.classRoom = classRoom;
    }

    @Override
    public String toString() {
        return Integer.toString(classRoom) ;
    }
}
