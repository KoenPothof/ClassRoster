package map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Tile {
    private int tileWidth;
    private int tileHeight;
    private int[][] tiles;
    private int mapWidth;
    private int mapHeight;

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public Tile(String fileName) {
        JsonReader reader = null;
        try {
            reader = Json.createReader(new FileInputStream("resources/" + fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonObject root = reader.readObject();

        this.tileHeight = root.getInt("tileheight");
        this.tileWidth = root.getInt("tilewidth");
        mapWidth = root.getJsonArray("layers").getJsonObject(0).getInt("width");
        mapHeight = root.getJsonArray("layers").getJsonObject(0).getInt("height");

    }

    public int[][] saveTile(JsonArray layer) {
        tiles = new int[mapHeight][mapWidth];

        for (int y = 0; y < layer.size(); y++) {
            for (int x = 0; x < 112; x++) {
                tiles[y][x] = layer.getJsonObject(y).getJsonArray("data").getInt(x);
            }
        }
        return tiles;
    }
}
