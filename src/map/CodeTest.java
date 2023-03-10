package map;

import Data.Classroom;

import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CodeTest {

    public static void main(String[] args) throws FileNotFoundException {
        JsonReader reader = null;
        reader = Json.createReader(new FileInputStream("resources/SchoolBuilding1.json"));
        JsonObject root = reader.readObject();
        Layer layer = new Layer("resources/SchoolBuilding1.json");

        //data arrays uitlezen
        JsonArray layers = root.getJsonArray("layers");
        JsonArray chunksArray = null;
        JsonArray tile;


        //mapwidth
        int width = root.getJsonArray("layers").getJsonObject(0).getInt("width");
//        System.out.println(width);

        //mapheight
        int height = root.getJsonArray("layers").getJsonObject(0).getInt("height");
//        System.out.println(height);


        chunksArray = layers.getJsonObject(0).getJsonArray("chunks");
        tile = chunksArray.getJsonObject(0).getJsonArray("data");
        int[] data = new int[tile.size()];
        for (int i = 0; i < tile.size(); i++) {
            data[i] = tile.getInt(i);
        }
        System.out.println(Arrays.toString(data));
//        System.out.println(tile);

        //tilesets uitlezen
        JsonArray tilesets = root.getJsonArray("tilesets");
        for (int i = 1; i < 4; i++) {
            System.out.println(tilesets.getJsonObject(i).getString("image"));
        }


        ArrayList<BufferedImage> tiles = new ArrayList<>();
        try {
            BufferedImage tilemap = ImageIO.read(new FileInputStream("resources/" + root.getJsonArray("tilesets").getJsonObject(1).getString("image")));

            int tileHeight = root.getInt("tileheight");
            int tileWidth = root.getInt("tilewidth");

            for(int y = 0; y < tilemap.getHeight(); y += tileHeight)
            {
                for(int x = 0; x < tilemap.getWidth(); x += tileWidth)
                {
                    tiles.add(tilemap.getSubimage(x, y, tileWidth, tileHeight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
