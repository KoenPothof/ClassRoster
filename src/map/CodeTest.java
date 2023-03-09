package map;

import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CodeTest {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<JsonArray> jsonArrays = new ArrayList<>();
        JsonReader reader = null;
        reader = Json.createReader(new FileInputStream("SchoolBuilding1.json"));
        JsonObject root = reader.readObject();

        //data arrays uitlezen
        JsonArray layers = root.getJsonArray("layers");
        JsonArray chunksArray = null;
        JsonArray dataArray;

        for (int i = 0; i < layers.size(); i++) {
            chunksArray = layers.getJsonObject(i).getJsonArray("chunks");
        }

        for (int i = 0; i < chunksArray.size(); i++) {
            dataArray = chunksArray.getJsonObject(i).getJsonArray("data");

            jsonArrays.add(dataArray);
        }

        //mapwidth
        int width = root.getInt("width");
        //mapheight
        int height = root.getInt("height");

        //tilesets uitlezen
        JsonArray tilesets = root.getJsonArray("tilesets");
        for (int i = 1; i < 4; i++) {
            System.out.println(tilesets.getJsonObject(i).getString("image"));
        }


//        try {
//            BufferedImage weirdTileset = ImageIO.read(CodeTest.class.getResourceAsStream(root.getJsonArray("tilesets").getString(2)));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }



    }
}
