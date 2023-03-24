package PathFinding;

public class PathfindingTile {
    private int tileX;
    private int tileY;
    private int targetTileX;
    private int targetTileY;

    public PathfindingTile(int tileX, int tileY) {
        this.tileX = tileX;
        this.tileY = tileY;
        this.targetTileY = tileY;
        this.targetTileX = tileX;
    }
    public PathfindingTile(int tileX, int tileY, int targetTileX, int targetTileY) {
        this.tileX = tileX;
        this.tileY = tileY;
        this.targetTileX = targetTileX;
        this.targetTileY = targetTileY;
    }

    public int[][] getNeighbours(int[][] data) {
        int[][] neighbours = new int[4][2];
        neighbours[0][0] = tileX;
        neighbours[0][1] = tileY - 1;
        neighbours[1][0] = tileX + 1;
        neighbours[1][1] = tileY;
        neighbours[2][0] = tileX;
        neighbours[2][1] = tileY + 1;
        neighbours[3][0] = tileX - 1;
        neighbours[3][1] = tileY;

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
}
