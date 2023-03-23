package PathFinding;

import map.Layer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Pathfinding {
    private int[][] data;

    public Pathfinding() {
        JsonReader reader = null;
        try {
            reader = Json.createReader(new FileInputStream("resources/project.json"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonObject root = reader.readObject();

       int mapWidth = root.getInt("height");
       int mapHeight = root.getInt("width");

        JsonObject layer = root.getJsonArray("layers").getJsonObject(0);
        data = new int[mapHeight][mapWidth];
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                data[y][x] = layer.getJsonArray("data").getInt(x);
                if (layer.getJsonArray("data").getInt(x) == 0 ){
                    // student kan er lopen.
                }
            }
        }

    }
}