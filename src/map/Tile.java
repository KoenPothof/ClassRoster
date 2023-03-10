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
    private int[] tiles;
    private ArrayList<int[]> dataArrays = new ArrayList<>();
    private JsonArray data;

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

    public ArrayList<int[]> saveTile(JsonArray layer,int index) {
        data = layer.getJsonObject(index).getJsonArray("data");
        tiles = new int[data.size()];
        for (int x = 0; x < data.size(); x++) {
            tiles[x] = data.getInt(dataArrays.size());
            dataArrays.add(tiles);
        }
        return dataArrays;
    }
}
