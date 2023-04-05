package Data;

import PathFinding.Pathfinding;
import Utilities.FileConverter;

public class Classroom {
    private String classroom;
    private Pathfinding[] pathfindings = new Pathfinding[16];
    private int x;
    private int y;
    private int width;
    private int height;

    public Classroom(String classroom, int X, int Y, int width, int height) {
        this.x = X / 16;
        this.y = Y / 16;
        this.width = width / 16;
        this.height = height / 16;
        this.classroom = classroom;
        for (int i = 0; i < 16; i++) {
            pathfindings[i] = new Pathfinding((int) (x + Math.random() * this.width), (int) (y + Math.random() * this.height));
        }
    }

    @Override
    public String toString() {
        return classroom;
    }

    public Pathfinding[] getPathfindings() {
        return pathfindings;
    }
}
