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
    private Tile tile;
    private int width;
    private int height;
    private int[][] map;

    private ArrayList<BufferedImage> tiles = new ArrayList<>();
    private ArrayList<Integer> jsonArrays = new ArrayList<>();
    private BufferedImage image;


    public Map(String filename) throws FileNotFoundException {
        JsonReader reader = null;
        reader = Json.createReader(new FileInputStream(filename));
        JsonObject root = reader.readObject();

        JsonArray tilesets = root.getJsonArray("tilesets");

        try {
            tileHeight = root.getInt("tileheight");
            tileWidth = root.getInt("tilewidth");

            for (int i = 0; i < tilesets.size(); i++) {
                image = ImageIO.read(getClass().getResourceAsStream(root.getJsonArray("tilesets") .getJsonObject(i).getString("image")));

                for (int y = 0; y < image.getHeight(); y += tileHeight) {
                    for (int x = 0; x < image.getWidth(); x += tileWidth) {
                        tiles.add(image.getSubimage(x, y, tileWidth, tileHeight));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonArray layers = root.getJsonArray("layers");
        JsonArray chunksArray = null;
        JsonArray dataArray;

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
