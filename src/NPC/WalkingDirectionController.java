package NPC;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class WalkingDirectionController {

    private BufferedImage image;
    private BufferedImage images[] = new BufferedImage[16];
    private WalkingDirections direction = WalkingDirections.DOWN;

    public WalkingDirectionController(String filename) {
        try {
            image = ImageIO.read(getClass().getResource("/npc/" + filename));
            for (int i = 0; i < 16; i++) {
                double spriteHeight = 24;
                double spriteWidth = 16;
                images[i] = image.getSubimage((int) (spriteWidth * (i % 4)), (int) (spriteHeight * (i / 4)), (int) spriteWidth, (int) spriteHeight);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDirection(WalkingDirections direction, boolean moving) {
        this.direction = direction;
        if (!moving)
            teller3 = 0;
        switch (direction) {
            case DOWN:
                teller2 = teller3;
                break;
            case UP:
                teller2 = 4 + teller3;
                break;
            case LEFT:
                teller2 = 8 + teller3;
                break;
            case RIGHT:
                teller2 = 12 + teller3;
                break;
        }

    }

    private int teller = 0;
    private int teller2 = 0;
    private int teller3 = 0;


    public void update() {
        teller++;
        if (teller % 10 == 0){
            teller2++;
            teller3++;
            if (teller3 > 3)
                teller3 = 0;
            switch (direction) {
                case DOWN:
                    if (teller2 > 3)
                        teller2 = 0;
                    break;
                case UP:
                    if (teller2 > 7)
                        teller2 = 4;
                    break;
                case LEFT:
                    if (teller2 > 11)
                        teller2 = 8;
                    break;
                case RIGHT:
                    if (teller2 > 15)
                        teller2 = 12;
                    break;
            }

        }

    }

    public BufferedImage getCurrentImage() {
        return images[teller2];
    }
}
