package PathFinding;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;

public class Pathfinding {
    private int[][] data;
    private PathfindingTile[][] pathfindingTiles;
    private Queue<PathfindingTile> queue = new LinkedList<>();


    public Pathfinding() {
        JsonReader reader = null;
        try {
            reader = Json.createReader(new FileInputStream("resources/map/project.json"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonObject root = reader.readObject();

        int mapWidth = root.getInt("width");
        int mapHeight = root.getInt("height");

        JsonObject layer = root.getJsonArray("layers").getJsonObject(0);
        data = new int[mapWidth][mapHeight];
        int count = 0;
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                data[x][y] = layer.getJsonArray("data").getInt(count);
                count++;
            }
        }
        pathfindingTiles = new PathfindingTile[data.length][data[0].length];
    }

    public int[][] getNeighbours(int x, int y) {
        int[][] neighbours = new int[4][2];
        neighbours[0][0] = x;
        neighbours[0][1] = y - 1;
        neighbours[1][0] = x + 1;
        neighbours[1][1] = y;
        neighbours[2][0] = x;
        neighbours[2][1] = y + 1;
        neighbours[3][0] = x - 1;
        neighbours[3][1] = y;

        return neighbours;
    }

    public int[][] findPath(int targetX, int targetY) {
        queue.clear();
        pathfindingTiles[targetX][targetY] = new PathfindingTile(targetX, targetY);
        queue.add(new PathfindingTile(targetX, targetY));
        int[][] path = new int[data.length][data[0].length];
        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data[0].length; y++) {
                path[x][y] = data[x][y];
            }
        }
        path[targetX][targetY] = 1;
        while (!queue.isEmpty()) {
            PathfindingTile currentTile = queue.remove();
            int x = currentTile.getTileX();
            int y = currentTile.getTileY();
            int[][] neighbours = getNeighbours(x, y);
            for (int i = 0; i < neighbours.length; i++) {
                if (path[neighbours[i][0]][neighbours[i][1]] == 0) {
                    pathfindingTiles[neighbours[i][0]][neighbours[i][1]] = new PathfindingTile(neighbours[i][0], neighbours[i][1], x, y);
                    queue.add(pathfindingTiles[neighbours[i][0]][neighbours[i][1]]);
                    path[neighbours[i][0]][neighbours[i][1]] = path[x][y] + 1;
                }
            }
        }
        return path;
    }

    public void dataCheck(int[][] data) {
        for (int y = 0; y < data[0].length; y++) {
            for (int x = 0; x < data.length; x++) {
                for (int i = 5; i > (data[x][y] + "").length(); i--) {
                    System.out.print(" ");
                }
                System.out.print(data[x][y]);
            }
            System.out.println();
        }
    }

    public int[][] getData() {
        return data;
    }
}