package NPC;

import PathFinding.Pathfinding;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class NPC {
    String group;
    double positionX;
    double positionY;
    double targetX;
    double targetY;
    boolean moving;
    boolean drawn;

    private final int tileWidth = 16;
    private final int tileOffset = 8;


    private BufferedImage image;
    private WalkingDirectionController notAFinalName;
    private Pathfinding pathfinding;


    // note ons poppetje mag alleen maar 90 graden hoeken draaien dus goed invoegen bij de mapping


    public NPC(String group) {
        this.group = group;
        this.positionX = 53 * tileWidth - tileOffset + Math.random() - 0.5;
        this.positionY = 4 * tileWidth - tileOffset + Math.random() - 0.5;
        this.targetX = this.positionX;
        this.targetY = this.positionY;
        this.notAFinalName = new WalkingDirectionController("sprite.png");
        this.image = notAFinalName.getCurrentImage();
        this.pathfinding = null;
        this.moving = false;
        this.drawn = true;
    }


    public NPC() {
        this.group = "teacher";
        this.positionX = 53 * tileWidth - tileOffset + Math.random() - 0.5;
        this.positionY = 4 * tileWidth - tileOffset + Math.random() - 0.5;
        this.targetX = this.positionX;
        this.targetY = this.positionY;
        this.notAFinalName = new WalkingDirectionController("sprite2.png");
        this.image = notAFinalName.getCurrentImage();
        this.pathfinding = null;
        this.moving = false;
        this.drawn = true;
    }

    public void draw(Graphics2D g) {
        if (drawn) {
            AffineTransform tx = new AffineTransform();
            this.image = getWalkingDirectionController().getCurrentImage();

            tx.translate(positionX, positionY);
            tx.scale(1.3, 2.3);
            tx.translate(-image.getWidth() / 2d, -image.getHeight() / 1.2d);
            g.drawImage(image, tx, null);

//        g.setColor(Color.ORANGE);
//        g.draw(new Ellipse2D.Double(positionX - 10, positionY - 10, 20, 20));
        }
    }

    // plek waar je naar toe moet
    public void setTarget(int x, int y, boolean raw) {
        if (raw) {
            this.targetX = x;
            this.targetY = y;
        } else {
            this.targetX = x * tileWidth + tileOffset;
            this.targetY = y * tileWidth + tileOffset;
        }
//        this.targetX = x * tileWidth + tileOffset;
//        this.targetY = y * tileWidth + tileOffset;
//        this.target = new Point2D.Double(x * tileWidth - tileOffset, y * tileWidth - tileOffset);
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

    public WalkingDirectionController getWalkingDirectionController() {
        return notAFinalName;
    }

    public Pathfinding getPathfinding() {
        return pathfinding;
    }

    public String getGroup() {
        return group;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }
}
