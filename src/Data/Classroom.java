package Data;

import PathFinding.Pathfinding;

public class Classroom {
    private String classroom;
    private Pathfinding[] pathfindings = new Pathfinding[16] ;

    public Classroom(String classroom) {
        this.classroom = classroom;
        for (int i = 0; i < 16; i++) {
//            pathfindings[i] = new Pathfinding();
        }
    }

    @Override
    public String toString() {
        return classroom;
    }
}
