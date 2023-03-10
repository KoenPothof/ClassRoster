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
    private int mapWidth;
    private int mapHeight;
    private ArrayList<BufferedImage> tilesets = new ArrayList<>(3);
    private ArrayList<BufferedImage> slicedTiles = new ArrayList<>();
    private Layer layer;
    private  int[][] map;
    private BufferedImage image;
    private Tile t;
    int[] data;
    JsonArray chunksArray;


    public Map(String fileName) {
        JsonReader reader = null;
        try {
            reader = Json.createReader(new FileInputStream("resources/" + fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonObject root = reader.readObject();
        layer = new Layer(fileName);
        t = new Tile(fileName);

        mapWidth = root.getJsonArray("layers").getJsonObject(0).getInt("width");
        mapHeight = root.getJsonArray("layers").getJsonObject(0).getInt("height");

        try {
            tilesets.add(ImageIO.read(new FileInputStream("resources/dark-wood.png")));
            tilesets.add(ImageIO.read(new FileInputStream("resources/Game Boy Advance - Pokemon Emerald - Interior Tilesets.png")));
            tilesets.add(ImageIO.read(new FileInputStream("resources/New Piskel.png")));
            tilesets.add(ImageIO.read(new FileInputStream("resources/74257b9030d47b8adc5eacd20f23050b.png")));
            tilesets.add(ImageIO.read(new FileInputStream("resources/ddpy71z-41398be7-7d93-453f-a8b6-dd7312fcabeb.png")));
            tilesets.add(ImageIO.read(new FileInputStream("resources/PC Computer - Stardew Valley - Town Interior.png")));
            tilesets.add(ImageIO.read(new FileInputStream("resources/Slates v.2 [32x32px orthogonal tileset by Ivan Voirol].png")));


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

        map = this.layer.getLayer();

        JsonArray layers = root.getJsonArray("layers");
        chunksArray = layers.getJsonObject(1).getJsonArray("chunks");
        JsonArray tile = chunksArray.getJsonObject(1).getJsonArray("data");
        data = new int[tile.size()];
        for (int i = 0; i < tile.size(); i++) {
            data[i] = tile.getInt(i);
        }
    }

    public void draw(FXGraphics2D g2d) {
        AffineTransform tx = new AffineTransform();
//        tx.scale(2,2);
//        layer.draw(g2d);
//        for (int i = 0; i < data.length; i++) {
//            if (data[i] < 0){
//                continue;
//            }
//            g2d.drawImage(slicedTiles.get(data[i]),tx,null);
//        }


        for(int y = 0; y < 24; y++)
        {
            for(int x = 0; x < 256; x++)
            {
                if(map[y][x] < 0)
                    continue;

                g2d.drawImage(
                        slicedTiles.get(map[y][x]),
                        AffineTransform.getTranslateInstance(x*tileWidth, y*tileHeight),
                        null);
            }
        }

    }
}
