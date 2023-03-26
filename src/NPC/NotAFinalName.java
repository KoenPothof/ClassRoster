package NPC;

import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NotAFinalName {

    private BufferedImage image;
    private BufferedImage images[] = new BufferedImage[16];
    private int teller = 0;
    private WalkingDirection direction = WalkingDirection.DOWN;

    public NotAFinalName() {

        try {
            image = ImageIO.read(getClass().getResource("/npc/sprite.png"));
            for (int i = 0; i < 16; i++) {
                double spriteHeight = 24;
                double spriteWidth = 16;
                images[i] = image.getSubimage((int) (spriteWidth * (i % 4)), (int) (spriteHeight * (i / 4)), (int) spriteWidth, (int) spriteHeight);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDirection(WalkingDirection direction) {
        this.direction = direction;
        switch (direction) {
            case UP:
                teller2 = 0;
                break;
            case DOWN:
                teller2 = 4;
                break;
            case LEFT:
                teller2 = 8;
                break;
            case RIGHT:
                teller2 = 12;
                break;
        }

//        if (rotation == 0) {
//            allFalse();
//            walkDown = true;
//            teller2 = 0;
//        } else if (rotation == 1) {
//            allFalse();
//            walkUp = true;
//            teller2 = 4;
//        } else if (rotation == 2) {
//            allFalse();
//            walkLeft = true;
//            teller2 = 8;
//        } else if (rotation == 3) {
//            allFalse();
//            walkRight = true;
//            teller2 = 12;
//        }
    }

//    public void allFalse() {
//        walkDown = false;
//        walkUp = false;
//        walkLeft = false;
//        walkRight = false;
//    }

    private int teller2 = 0;


    public void update() {
        switch (direction) {
            case UP:
                if (teller2 > 3)
                    teller2 = 0;

                break;
            case DOWN:
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

//        if (walkDown) {
//            y += 1;
//            if (teller2 > 3) {
//                teller2 = 0;
//            }
//        } else if (walkUp) {
//            y -= 1;
//            if (teller2 > 7) {
//                teller2 = 4;
//            }
//        } else if (walkLeft) {
//            x -= 1;
//            if (teller2 > 11) {
//                teller2 = 8;
//            }
//        } else if (walkRight) {
//            x += 1;
//            if (teller2 > 15) {
//                teller2 = 12;
//            }
//        }
    }

    public BufferedImage getCurrentImage() {
        return images[teller2];
    }
}
