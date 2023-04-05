package PathFinding;

import map.Layer;
import org.jfree.fx.FXGraphics2D;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;

public class Pathfinding {
    private int[][] data;
    private PathfindingTile[][] pathfindingTiles;
    private Queue<PathfindingTile> queue = new LinkedList<>();
    private Utilities.JsonReader jsonReader;


    public Pathfinding(int targetX, int targetY) {
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

        queue.clear();
        pathfindingTiles[targetX][targetY] = new PathfindingTile(targetX, targetY);
        queue.add(new PathfindingTile(targetX, targetY));
        data[targetX][targetY] = 1;
        while (!queue.isEmpty()) {
            PathfindingTile currentTile = queue.remove();
            int x = currentTile.getTileX();
            int y = currentTile.getTileY();
            int alpha[] = getNextTile(x, y);
            int[][] neighbours = currentTile.getNeighbours(alpha);
            for (int i = 0; i < neighbours.length; i++) {
                if (data[neighbours[i][0]][neighbours[i][1]] == 0) {
                    pathfindingTiles[neighbours[i][0]][neighbours[i][1]] = new PathfindingTile(neighbours[i][0], neighbours[i][1], x, y, data[x][y] + 1);
                    queue.add(pathfindingTiles[neighbours[i][0]][neighbours[i][1]]);
                    data[neighbours[i][0]][neighbours[i][1]] = data[x][y] + 1;
                }
            }
        }
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

    public void draw(FXGraphics2D g2d) {

        for (int i = 0; i < pathfindingTiles.length; i++) {
            for (int j = 0; j < pathfindingTiles[i].length; j++) {
                if (pathfindingTiles[i][j] != null) {
                    g2d.setColor(Color.ORANGE);
                    g2d.fill(new Ellipse2D.Double(16 * i + 5, 16 * j + 5, 6, 6));
                    g2d.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
                    g2d.draw(new Line2D.Double(
                            pathfindingTiles[i][j].getTileX() * 16 + 8,
                            pathfindingTiles[i][j].getTileY() * 16 + 8,
                            pathfindingTiles[i][j].getTargetTileX() * 16 + 8,
                            pathfindingTiles[i][j].getTargetTileY() * 16 + 8

                    ));

                    if (pathfindingTiles[i][j].getTargetTileX() == i && pathfindingTiles[i][j].getTargetTileY() == j) {
                        g2d.setColor(Color.RED);
                        g2d.fill(new Rectangle2D.Double(16 * i, 16 * j, 16, 16));
                    }
                }
            }
        }
    }

    public void numberDraw(FXGraphics2D g2d) {
        for (int i = 0; i < pathfindingTiles.length; i++) {
            for (int j = 0; j < pathfindingTiles[i].length; j++) {
                if (pathfindingTiles[i][j] != null) {
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(String.valueOf(pathfindingTiles[i][j].getDistance()), 16 * i, 16 * j + 10);
                }
            }
        }
    }

    public PathfindingTile[][] getPathfindingTiles() {
        return pathfindingTiles;
    }

    public int[] getNextTile(int x, int y) {
        int[] nextTile = new int[2];
        nextTile[0] = pathfindingTiles[x][y].getTargetTileX();
        nextTile[1] = pathfindingTiles[x][y].getTargetTileY();

        return nextTile;
    }


}