package map;

import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
    private int tileWidth;
    private int tileHeight;
    private int mapWidth;
    private int mapHeight;
    private ArrayList<BufferedImage> slicedTiles = new ArrayList<>();
    private ArrayList<Layer> layers = new ArrayList<>();

    public Map(String fileName) {
        JsonReader reader = null;
        try {
            reader = Json.createReader(new FileInputStream("resources/" + fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonObject root = reader.readObject();

        mapWidth = root.getInt("width");
        mapHeight = root.getInt("height");

        try {

            JsonArray tileSets = root.getJsonArray("tilesets");

            for (int i = 0; i < tileSets.size(); i++) {
                JsonObject tileset = tileSets.getJsonObject(i);
                System.out.println(tileset.getString("image"));
                BufferedImage image = ImageIO.read(new FileInputStream("resources/" + tileset.getString("image")));
                int gid = tileset.getInt("firstgid");
                int tileHeight = tileset.getInt("tileheight");
                int tileWidth = tileset.getInt("tilewidth");
                for (int y = 0; y < image.getHeight(); y += tileHeight) {
                    for (int x = 0; x < image.getWidth(); x += tileWidth) {
                        slicedTiles.add(image.getSubimage(x, y, tileWidth, tileHeight)); // zorg ervoor dat image.getSubImgae op positie gid komt te staan in de slicedTiles array
                        gid++;
                    }
                }
            }
            tileHeight = root.getInt("tileheight");
            tileWidth = root.getInt("tilewidth");


        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < root.getJsonArray("layers").size(); i++) {
            JsonObject layer = root.getJsonArray("layers").getJsonObject(i);
            int[] data = new int[layer.getJsonArray("data").size()];
            for (int j = 0; j < data.length; j++) {
                data[j] = layer.getJsonArray("data").getInt(j);
            }
            layers.add(new Layer(data));
        }
    }

    public void draw(FXGraphics2D g2d) {

        for (Layer layer : layers) {
            layer.draw(g2d,this);
        }
    }

    public int getWidth() {
        return this.mapWidth;
    }

    public int getHeight(){
        return this.mapHeight;
    }

    public BufferedImage getSlicedImage(int tileId) {
        return slicedTiles.get(tileId);
    }
}
