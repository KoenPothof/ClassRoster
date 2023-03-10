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
    }

    public int[][] saveTile(JsonArray layer) {
        tiles = new int[layer.size()][tileWidth*tileHeight];

        for (int y = 0; y < layer.size(); y++) {
            for (int x = 0; x < tileWidth*tileHeight; x++) {
                tiles[y][x] = layer.getJsonObject(y).getJsonArray("data").getInt(x);
            }
        }
        return tiles;
    }
}
