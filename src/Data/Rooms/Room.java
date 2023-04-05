package Data.Rooms;

import NPC.WalkingDirections;
import PathFinding.Pathfinding;

public class Room {
    private String room;
    private Pathfinding[] pathfindings;
    private Pathfinding teacherPathfinding;
    private int x;
    private int y;
    private int width;
    private int height;

    public Room(String room, int X, int Y, int width, int height, RoomType roomType) {
        this.x = X / 16;
        this.y = Y / 16;
        this.width = width / 16;
        this.height = height / 16;
        this.room = room;


        switch (roomType) {
            case CLASSROOM:
                teacherPathfinding = new Pathfinding(x , y , WalkingDirections.DOWN);
                pathfindings = new Pathfinding[this.width * this.height];
                for (int i = 0; i < this.height / 2; i++) {
                    pathfindings[i * 4] = new Pathfinding(x, y + i * 2 + 1, WalkingDirections.UP);
                    pathfindings[(i * 4) + 1] = new Pathfinding(x + 2, y + i * 2 + 1, WalkingDirections.UP);
                    pathfindings[(i * 4) + 2] = new Pathfinding(x + 6, y + i * 2 + 1 , WalkingDirections.UP);
                    pathfindings[(i * 4) + 3] = new Pathfinding(x + 8, y + i * 2 + 1, WalkingDirections.UP);
                }
                break;
            case RESTROOM:
                pathfindings = new Pathfinding[this.width];
                for (int i = 0; i < this.width; i++) {
                    pathfindings[i] = new Pathfinding(x + i, y, WalkingDirections.DOWN);
                }
                break;
            case CANTEEN:
                pathfindings = new Pathfinding[8 * 14];
                int count = 0;
                for (int i = 0; i < this.width; i += 25) {
                    for (int j = 0; j < this.height; j += 13) {
                        for (int k = 0; k < 13; k++) {
                            pathfindings[count] = new Pathfinding(x + i + k, y + j, WalkingDirections.DOWN);
                            pathfindings[count + 1] = new Pathfinding(x + i + k, y + j + 6, WalkingDirections.UP);
                            count += 2;
                        }
                    }
                }
                break;
            case TEACHERROOM:
                pathfindings = new Pathfinding[6];
                pathfindings[0] = new Pathfinding(x, y + 2, WalkingDirections.RIGHT);
                pathfindings[1] = new Pathfinding(x, y + 5, WalkingDirections.RIGHT);
                pathfindings[2] = new Pathfinding(x + 4, y - 1, WalkingDirections.UP);
                pathfindings[3] = new Pathfinding(x + 6, y - 1, WalkingDirections.UP);
                pathfindings[4] = new Pathfinding(x + 4, y - 4, WalkingDirections.DOWN);
                pathfindings[5] = new Pathfinding(x + 6, y - 4, WalkingDirections.DOWN);
                break;
        }

    }

    @Override
    public String toString() {
        return room;
    }

    public Pathfinding[] getPathfindings() {
        return pathfindings;
    }

    public Pathfinding getTeacherPathfinding() {
        return teacherPathfinding;
    }
}
