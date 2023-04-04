package PathFinding;

public class PathfindingTile {
    private int tileX;
    private int tileY;
    private int targetTileX;
    private int targetTileY;
    private int distance;

    public PathfindingTile(int tileX, int tileY) {
        this.tileX = tileX;
        this.tileY = tileY;
        this.targetTileY = tileY;
        this.targetTileX = tileX;
        this.distance = 1;
    }

    public PathfindingTile(int tileX, int tileY, int targetTileX, int targetTileY, int distance) {
        this.tileX = tileX;
        this.tileY = tileY;
        this.targetTileX = targetTileX;
        this.targetTileY = targetTileY;
        this.distance = distance;
    }

    public int[][] getNeighbours(int[] data) {
        PathfindingEnum direction = PathfindingEnum.NORTH;
        if (tileX > data[0]) {
            direction = PathfindingEnum.EAST;
        } else if (tileX < data[0]) {
            direction = PathfindingEnum.WEST;
        } else if (tileY > data[1]) {
            direction = PathfindingEnum.NORTH;
        } else if (tileY < data[1]) {
            direction = PathfindingEnum.SOUTH;
        }
        double aaa = 4d;

        int[][] neighbours = new int[3][2];
        switch (direction) {
            case NORTH:
                neighbours[0][0] = tileX + 1;
                neighbours[0][1] = tileY;
                neighbours[1][0] = tileX - 1;
                neighbours[1][1] = tileY;
                neighbours[2][0] = tileX;
                neighbours[2][1] = tileY + 1;
                break;
            case EAST:
                neighbours[0][0] = tileX;
                neighbours[0][1] = tileY + 1;
                neighbours[1][0] = tileX;
                neighbours[1][1] = tileY - 1;
                neighbours[2][0] = tileX + 1;
                neighbours[2][1] = tileY;
                break;
            case SOUTH:
                neighbours[0][0] = tileX + 1;
                neighbours[0][1] = tileY;
                neighbours[1][0] = tileX - 1;
                neighbours[1][1] = tileY;
                neighbours[2][0] = tileX;
                neighbours[2][1] = tileY - 1;
                break;
            case WEST:
                neighbours[0][0] = tileX;
                neighbours[0][1] = tileY + 1;
                neighbours[1][0] = tileX;
                neighbours[1][1] = tileY - 1;
                neighbours[2][0] = tileX - 1;
                neighbours[2][1] = tileY;
                break;
        }
        return neighbours;
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public int getTargetTileX() {
        return targetTileX;
    }

    public int getTargetTileY() {
        return targetTileY;
    }

    public int getDistance() {
        return distance;
    }
}
