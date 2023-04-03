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

    public int[][] getNeighbours() {
        int[][] neighbours = new int[8][2];

        neighbours[0][0] = tileX;
        neighbours[0][1] = tileY - 1;
        neighbours[1][0] = tileX + 1;
        neighbours[1][1] = tileY;
        neighbours[2][0] = tileX;
        neighbours[2][1] = tileY + 1;
        neighbours[3][0] = tileX - 1;
        neighbours[3][1] = tileY;
//        neighbours[4][0] = tileX + 1;
//        neighbours[4][1] = tileY + 1;
//        neighbours[5][0] = tileX + 1;
//        neighbours[5][1] = tileY - 1;
//        neighbours[6][0] = tileX - 1;
//        neighbours[6][1] = tileY - 1;
//        neighbours[7][0] = tileX - 1;
//        neighbours[7][1] = tileY + 1;

        
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
