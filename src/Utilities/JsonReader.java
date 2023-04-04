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

    private JsonArray objects;
    private String[] objectName;
    private int[] objectX;
    private int[] objectY;
    private int[] objectWidth;
    private int[] objectHeight;

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

        this.objects = layerArray.getJsonObject(1).getJsonArray("objects");
        this.objectName = new String[objects.size()];
        this.objectX = new int[objects.size()];
        this.objectY = new int[objects.size()];
        this.objectWidth = new int[objects.size()];
        this.objectHeight = new int[objects.size()];

        for (int i = 0; i < objects.size(); i++) {
            JsonObject object = objects.getJsonObject(i);
            objectName[i] = object.getString("name");
            objectX[i] = object.getInt("x");
            objectY[i] = object.getInt("y");
            objectWidth[i] = object.getInt("width");
            objectHeight[i] = object.getInt("height");
        }

    }

    public String[] getObjectName() {
        return objectName;
    }

    public int[] getObjectX() {
        return objectX;
    }

    public int[] getObjectY() {
        return objectY;
    }

    public int[] getObjectWidth() {
        return objectWidth;
    }

    public int[] getObjectHeight() {
        return objectHeight;
    }


    public JsonArray getLayerArray() {
        return layerArray;
    } // Array met alle layers

    public JsonArray getDataArray() {
        return dataArray;
    } // Array met getallen van de map.

    public int[] getDataArrayInt() {
        int[] data = new int[dataArray.size()];
        for (int i = 0; i < dataArray.size(); i++) {
            data[i] = dataArray.getInt(i);
        }
        return data;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public JsonArray getTileSets() {
        return tileSets;
    } // Array met tilesets

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }
}
