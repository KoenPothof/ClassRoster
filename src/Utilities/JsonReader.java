package Utilities;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonString;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JsonReader {
    private JsonArray layerArray;
    private JsonArray dataArray;
    private int mapWidth;
    private int mapHeight;
    private int tileHeight;
    private int tileWidth;



    private JsonArray tileSets;

    public JsonReader(String layer) {
        javax.json.JsonReader reader = null;
        try {
            reader = Json.createReader(new FileInputStream("resources/map/project.json"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonObject root = reader.readObject();

        this.mapHeight = root.getInt("height");
        this.mapWidth = root.getInt("width");
        this.tileHeight = root.getInt("tileheight");
        this.tileWidth = root.getInt("tilewidth");

        this.layerArray = root.getJsonArray("layers");
        this.tileSets = root.getJsonArray("tilesets");

        for (int i = 0; i < layerArray.size(); i++) {
            if (layerArray.getJsonObject(i).getString("name").equals(layer)) {
                dataArray = layerArray.getJsonObject(i).getJsonArray("data");
            }
        }
    }

    public JsonReader() {
        javax.json.JsonReader reader = null;
        try {
            reader = Json.createReader(new FileInputStream("resources/map/project.json"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonObject root = reader.readObject();

        this.mapHeight = root.getInt("height");
        this.mapWidth = root.getInt("width");

        this.layerArray = root.getJsonArray("layers");
        this.tileSets = root.getJsonArray("tilesets");
    }

    public JsonArray getLayerArray() {
        return layerArray;
    }

    public JsonArray getDataArray() {
        return dataArray;
    }
    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public JsonArray getTileSets() {
        return tileSets;
    }
    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }
}
