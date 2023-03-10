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


    private ArrayList<Integer> jsonArrays = new ArrayList<>();
    private ArrayList<BufferedImage> tilesets = new ArrayList<>(3);
    private ArrayList<BufferedImage> slicedTiles = new ArrayList<>();
    private JsonArray chunksArray;
    private JsonArray dataArray;
    private JsonArray layers;
    private Layer layer;
    int[][] map;
    int[] tile;
    private BufferedImage image;

    Tile t;

    public Map(String fileName) {
        JsonReader reader = null;
        try {
            reader = Json.createReader(new FileInputStream("resources/" + fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonObject root = reader.readObject();
        t = new Tile(fileName);

        this.width = root.getInt("width");
        this.height = root.getInt("height");

        try {
            for (int i = 1; i < 4; i++) {
                image = ImageIO.read(new FileInputStream("resources/" + root.getJsonArray("tilesets").getJsonObject(i).getString("image")));
                tilesets.add(image);
            }

            tileHeight = t.getTileHeight();
            tileWidth = t.getTileWidth();

            for (BufferedImage image:tilesets) {
                for (int y = 0; y < image.getHeight(); y += tileHeight) {
                    for (int x = 0; x < image.getWidth(); x += tileWidth) {
                        slicedTiles.add(image.getSubimage(x, y, tileWidth, tileHeight));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        layers = root.getJsonArray("layers");

        chunksArray = layers.getJsonObject(0).getJsonArray("chunks");


        for (int i = 0; i < chunksArray.size(); i++) {
            dataArray = chunksArray.getJsonObject(i).getJsonArray("data");
            tile[i] = (dataArray.getInt(i));
        }
    }

    public void draw(FXGraphics2D g2d) {
        for (int i = 0; i < dataArray.size(); i++) {
            g2d.drawImage(slicedTiles.get(tile[i]), AffineTransform.getTranslateInstance(i * tileWidth, i * tileHeight), null);

        }

    }
}
