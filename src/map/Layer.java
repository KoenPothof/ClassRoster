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
import java.util.Arrays;

public class Layer {
    private Tile t;
    private int[][] layer;
    private int [] data;
    private BufferedImage image;
    private ArrayList<BufferedImage> tilesets = new ArrayList<>();
    private ArrayList<BufferedImage> slicedTiles = new ArrayList<>();

    public Layer(String fileName) {
        JsonReader reader = null;
        try {
            reader = Json.createReader(new FileInputStream("resources/" + fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonObject root = reader.readObject();

        t = new Tile(fileName);

        JsonArray backgroundLayer = root.getJsonArray("layers").getJsonObject(0).getJsonArray("chunks");
        JsonArray itemLayer = root.getJsonArray("layers").getJsonObject(1).getJsonArray("chunks");

        this.layer = t.saveTile(itemLayer);

    }
    public int[][] getLayer() {
        return layer;
    }
    public int[] getData() {
        return data;
    }

    public void draw(FXGraphics2D g2d) {
//        for(int y = 0; y < 64; y++)
//        {
//            for(int x = 0; x < 112; x++)
//            {
//                if(layer[y][x] < 0)
//                    continue;
//
//                g2d.drawImage(
//                        slicedTiles.get(layer[y][x]),
//                        AffineTransform.getTranslateInstance(x*112, y*64),
//                        null);
//            }
//        }
    }
}
