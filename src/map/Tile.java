package map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tile {
    private int tileWidth;
    private int tileHeight;
    private int[][] tiles;
    private int mapWidth;
    private int mapHeight;
    private int dataArray;

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

    public void saveTile(JsonArray layer) {
        tiles = new int[tileHeight][tileWidth];

        for (int x = 0; x < layer.size(); x++) {
//            tiles[x] = layer.getJsonObject(x).getJsonArray("data").getInt(x);
        }
    }

}
