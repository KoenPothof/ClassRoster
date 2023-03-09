package map;

import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
    private int tileWidth;
    private int tileHeight;
    private int width;
    private int height;

    private ArrayList<BufferedImage> tiles = new ArrayList<>();
    private ArrayList<Integer> jsonArrays = new ArrayList<>();
    private ArrayList<BufferedImage> tilesets = new ArrayList<>(3);
    private JsonArray chunksArray;
    private JsonArray dataArray;
    private JsonArray layers;
    int[][] map;


    public Map(String fileName)  {
        JsonReader reader = null;
        try {
            reader = Json.createReader(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonObject root = reader.readObject();

        this.width = root.getInt("width");
        this.height = root.getInt("height");

        try {
            BufferedImage tileset1 = ImageIO.read(getClass().getResourceAsStream(root.getJsonArray("tilesets") .getJsonObject(1).getString("image")));
//            BufferedImage tileset2 = ImageIO.read(getClass().getResourceAsStream(root.getJsonArray("tilesets") .getJsonObject(2).getString("image")));
//            BufferedImage tileset3 = ImageIO.read(getClass().getResourceAsStream(root.getJsonArray("tilesets") .getJsonObject(3).getString("image")));
            tilesets.add(tileset1);
//            tilesets.add(tileset2);
//            tilesets.add(tileset3);

            tileHeight = root.getInt("tileheight");
            tileWidth = root.getInt("tilewidth");

            for (BufferedImage image:tilesets) {
                for (int y = 0; y < image.getHeight(); y += tileHeight) {
                    for (int x = 0; x < image.getWidth(); x += tileWidth) {
                        tiles.add(image.getSubimage(x, y, tileWidth, tileHeight));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        layers = root.getJsonArray("layers");

        for (int i = 0; i < layers.size(); i++) {
            chunksArray = layers.getJsonObject(i).getJsonArray("chunks");
        }

        for (int i = 0; i < chunksArray.size(); i++) {
            dataArray = chunksArray.getJsonObject(i).getJsonArray("data");
            jsonArrays.add(dataArray.getInt(i));
        }
    }


    public void draw(FXGraphics2D g2d) {
        for (int i = 0; i < jsonArrays.size(); i++) {
            g2d.drawImage(tiles.get(jsonArrays.get(i)),AffineTransform.getTranslateInstance(i* tileWidth,i * tileHeight),null);
        }
    }
}
