package map;

import org.jfree.fx.FXGraphics2D;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Layer {
    Tile t;

    public int[] getData() {
        return data;
    }

    int[][] tile;
    int [] data;

    public Layer(String fileName) {
        JsonReader reader = null;
        try {
            reader = Json.createReader(new FileInputStream("resources/" + fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonObject root = reader.readObject();

        t = new Tile(fileName);
        int tileWidth = t.getTileWidth();
        int tileHeight = t.getTileHeight();
        int mapWidth = root.getJsonArray("layers").getJsonObject(0).getInt("width");
        int mapHeight = root.getJsonArray("layers").getJsonObject(0).getInt("height");

        JsonArray backgroundLayer = root.getJsonArray("layers").getJsonObject(0).getJsonArray("chunks");
        JsonArray dataArray = backgroundLayer.getJsonObject(0).getJsonArray("data");
        JsonArray itemLayer = root.getJsonArray("layers").getJsonObject(1).getJsonArray("chunks");


        data = new int[dataArray.size()];
        for (int i = 0; i < dataArray.size(); i++) {
            data[i] = dataArray.getInt(i);
        }
//
//        tile = new int[mapHeight][mapWidth];
//
//        for (int y = 0; y < 27; y++) {
//            for (int x = 0; x < mapHeight; x++) {
//                tile[y][x] = backgroundLayer.getJsonObject(y).getJsonArray("data").getInt(x);
//            }
//        }
    }

    public void draw(FXGraphics2D g2d) {

    }
}
