package NPC;

import PathFinding.Pathfinding;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class NPC {
    double positionX;
    double positionY;
    double targetX;
    double targetY;
    boolean moving = false;

    private final int tileWidth = 16;
    private final int tileOffset = 8;


    private BufferedImage image;
    private SpriteDirection notAFinalName;
    private Pathfinding pathfinding;
    private String group;

    // note ons poppetje mag alleen maar 90 graden hoeken draaien dus goed invoegen bij de mapping


    public NPC(String group) {
        this.group = group;
        this.positionX = 55 * tileWidth - tileOffset;
        this.positionY = 8 * tileWidth - tileOffset;
        this.targetX = this.positionX;
        this.targetY = this.positionY;
        this.notAFinalName = new SpriteDirection();
        this.image = notAFinalName.getCurrentImage();
        this.pathfinding = null;
    }



    public NPC(int x, int y) {
        this.positionX = x * tileWidth - tileOffset;
        this.positionY = y * tileWidth - tileOffset;
        this.targetX = this.positionX;
        this.targetY = this.positionY;
        this.notAFinalName = new SpriteDirection();
        image = notAFinalName.getCurrentImage();
        this.pathfinding = null;
    }

    public void draw(Graphics2D g) {
        AffineTransform tx = new AffineTransform();
        this.image = notAFinalName.getCurrentImage();

        tx.translate(positionX, positionY);
        tx.scale(1.5, 2.3);
        tx.translate(-image.getWidth() / 2d, -image.getHeight() / 1.2d);
        g.drawImage(image, tx, null);

//        g.setColor(Color.ORANGE);
//        g.draw(new Ellipse2D.Double(positionX - 10, positionY - 10, 20, 20));

    }

    // plek waar je naar toe moet
    public void setTarget(int x, int y) {
        this.targetX = x * tileWidth + tileOffset;
        this.targetY = y * tileWidth + tileOffset;
//        this.target = new Point2D.Double(x * tileWidth - tileOffset, y * tileWidth - tileOffset);
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setPathfinding(Pathfinding pathfinding) {
        this.pathfinding = pathfinding;
    }

    // plek van poppetje
    public int[] getPosition() {
        int[] position = new int[2];
        position[0] = (int) (positionX / tileWidth);
        position[1] = (int) (positionY / tileWidth);
        return position;
    }

    public int[] getTarget() {
        int[] position = new int[2];
        position[0] = (int) (targetX / tileWidth);
        position[1] = (int) (targetY / tileWidth);
        return position;
    }

    public SpriteDirection getNotAFinalName() {
        return notAFinalName;
    }

    public Pathfinding getPathfinding() {
        return pathfinding;
    }

    public String getGroup(){return group;}
}
