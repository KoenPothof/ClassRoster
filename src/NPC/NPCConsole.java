package NPC;

import Data.Rooms.Room;
import Data.Rooms.RoomType;
import Utilities.FileConverter;
import Utilities.JsonReader;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;

public class NPCConsole {

    private ArrayList<NPC> npcs;
    private ArrayList<NPC[]> npcsByGroup;
    private FileConverter fileConverter;
    private ArrayList<Point2D> muren;
    private Room cafeteria;
    private Room teacherRoom;


    private double speed = 1;


    public NPCConsole(FileConverter fileConverter) {
        this.fileConverter = fileConverter;
        this.cafeteria = new Room("cafeteria", fileConverter.getJsonReader().getObjectX()[7], fileConverter.getJsonReader().getObjectY()[7], fileConverter.getJsonReader().getObjectWidth()[7], fileConverter.getJsonReader().getObjectHeight()[7], RoomType.CANTEEN);
        this.teacherRoom = new Room("teacherRoom", fileConverter.getJsonReader().getObjectX()[6], fileConverter.getJsonReader().getObjectY()[6], fileConverter.getJsonReader().getObjectWidth()[6], fileConverter.getJsonReader().getObjectHeight()[6], RoomType.TEACHERROOM);
        this.muren = new ArrayList<>();
        JsonReader jsonReader = new JsonReader("Walls");
        for (int i = 0; i < jsonReader.getDataArrayInt().length; i++) {
            if (jsonReader.getDataArrayInt()[i] > 0) {
                this.muren.add(new Point2D.Double(i % jsonReader.getMapWidth(), i / jsonReader.getMapWidth()));
            }
        }
        this.npcs = new ArrayList<>();
        this.npcsByGroup = new ArrayList<>();
        for (int i = 0; i < fileConverter.getGroups().length; i++) {
            npcsByGroup.add(new NPC[16]);
            for (int j = 0; j < 16; j++) {
                NPC npc = new NPC(fileConverter.getGroups()[i].toString());
                this.npcs.add(npc);
                npcsByGroup.get(i)[j] = npc;
            }
        }
        npcsByGroup.add(new NPC[fileConverter.getTeachers().length]);
        for (int j = 0; j < fileConverter.getTeachers().length; j++) {
            NPC npc = new NPC();
            this.npcs.add(new NPC());
            npcsByGroup.get(npcsByGroup.size() - 1)[j] = npc;
        }
        pathfindingUpdate();

    }

    public ArrayList<NPC> npcSort(ArrayList<NPC> npcs) {
        Collections.sort(npcs, new Comparator<NPC>() {
            @Override
            public int compare(NPC npc1, NPC npc2) {
                return Double.compare(npc1.positionY, npc2.positionY);
            }
        });
        return npcs;
    }

    public void update() {
        npcs = npcSort(npcs);
        for (NPC npc : npcs) {

            if (!npc.moving || !npc.drawn) {
                continue;
            }

            int npcX = npc.getPosition()[0];
            int npcY = npc.getPosition()[1];
            int[] nextTile = new int[2];
            try {
                nextTile = npc.getPathfinding().getNextTile(npcX, npcY);
                npc.setTarget(nextTile[0], nextTile[1], false);
            } catch (Exception ignored) {
            }


            double angleTo = Math.atan2(npc.targetY - npc.positionY, npc.targetX - npc.positionX);
            double degreesTo = 180 + Math.toDegrees(angleTo);
//            System.out.println(degreesTo);

            if (degreesTo > 60 && degreesTo <= 120) {
                npc.getWalkingDirectionController().setDirection(WalkingDirections.UP, true);
            } else if (degreesTo > 120 && degreesTo <= 240) {
                npc.getWalkingDirectionController().setDirection(WalkingDirections.RIGHT, true);
            } else if (degreesTo > 240 && degreesTo <= 300) {
                npc.getWalkingDirectionController().setDirection(WalkingDirections.DOWN, true);
            } else if (degreesTo > 300 || degreesTo <= 60) {
                npc.getWalkingDirectionController().setDirection(WalkingDirections.LEFT, true);
            }
            npc.getWalkingDirectionController().update();


            double oldPosX = npc.positionX;
            double oldPosY = npc.positionY;

            npc.positionX = (npc.positionX + speed * Math.cos(angleTo));
            npc.positionY = (npc.positionY + speed * Math.sin(angleTo));


            // als de tijd 8:30-9:20 is, foreach NPC in een groep, setTarget LB001 (voorbeeld).


            // dit is de collision
            // als we collision met muren willen dan moeten we die toevoegen aan de muren arraylist


            for (NPC otherNPC : npcs) {
                if (otherNPC == npc || !otherNPC.drawn) {
                    continue;
                }
                Point2D otherNpcPosition = new Point2D.Double(otherNPC.positionX, otherNPC.positionY);
                if (otherNpcPosition.distanceSq(new Point2D.Double(npc.positionX, npc.positionY)) < 280) {
                    npc.positionX = npc.positionX + (npc.positionX - otherNPC.positionX) * .06;
                    npc.positionY = npc.positionY + (npc.positionY - otherNPC.positionY) * .06;
                }

            }

            for (int l = 0; l < muren.size(); l++) {
                if ((int) npc.positionY / 16 == (int) muren.get(l).getY() && (int) npc.positionX / 16 == (int) muren.get(l).getX()) {
                    npc.positionX = oldPosX;
                    npc.positionY = oldPosY;


                    break;
                }
            }

            if (npcX == nextTile[0] && npcY == nextTile[1] && npc.moving) {
                npc.positionX = npc.targetX + 8;
                npc.positionY = npc.targetY - 2;
                npc.getWalkingDirectionController().setDirection(npc.getPathfinding().getWalkingDirection(), false);
//                npc.getWalkingDirectionController().update();
                npc.moving = false;
//                npc.getWalkingDirectionController().setDirection(WalkingDirections.UP);
            }

        }
    }


    public void pathfindingUpdate(int time) {
        System.out.println("pathfinding update");
        for (NPC npc : npcs) {
            npc.setPathfinding(null);
            npc.moving = true;
        }
        int cafeteriaCounter = 0;
        int teacherCounter = 0;
        String currentTime = fileConverter.getTimes()[time].toString();
        for (int j = 0; j < fileConverter.getLessons().size(); j++) {
            if (fileConverter.getLessons().get(j).getTime().toString().equals(currentTime)) {
                for (int k = 0; k < npcsByGroup.size() - 1; k++) {
                    if (npcsByGroup.get(k)[0].getGroup().equals(fileConverter.getLessons().get(j).getGroup().toString()) && npcsByGroup.get(k)[0].getPathfinding() == null) {
                        for (int l = 0; l < npcsByGroup.get(k).length; l++) {
                            npcsByGroup.get(k)[l].setPathfinding(fileConverter.getLessons().get(j).getClassroom().getPathfindings()[l]);
                        }
                    }
                }
                for (int i = 0; i < npcsByGroup.get(npcsByGroup.size() - 1).length; i++) {
                    if (npcsByGroup.get(npcsByGroup.size() - 1)[i].getPathfinding() == null) {
                        npcsByGroup.get(npcsByGroup.size() - 1)[i].setPathfinding(fileConverter.getLessons().get(j).getClassroom().getTeacherPathfinding());
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < npcsByGroup.size() - 1; i++) {
            if (npcsByGroup.get(i)[0].getPathfinding() == null) {
                for (int j = 0; j < npcsByGroup.get(i).length; j++) {
                    npcsByGroup.get(i)[j].setPathfinding(cafeteria.getPathfindings()[cafeteriaCounter]);
                    cafeteriaCounter++;
                }
            }
        }
        for (int i = 0; i < npcsByGroup.get(npcsByGroup.size() - 1).length; i++) {
            if (npcsByGroup.get(npcsByGroup.size() - 1)[i].getPathfinding() == null) {
                npcsByGroup.get(npcsByGroup.size() - 1)[i].setPathfinding(teacherRoom.getPathfindings()[teacherCounter]);
                teacherCounter++;
            }
        }
    }

    public void pathfindingUpdate() {
        System.out.println("pathfinding update");
        int cafeteriaCounter = 0;
        int teacherCounter = 0;
        for (NPC npc : npcs) {
            npc.setPathfinding(null);
            npc.moving = true;
            if (npc.group.equals("teacher")) {
                // nog te doen
                npc.setPathfinding(teacherRoom.getPathfindings()[teacherCounter]);
                teacherCounter++;
            } else {
                npc.setPathfinding(cafeteria.getPathfindings()[cafeteriaCounter]);
                cafeteriaCounter++;
            }
        }
    }


    public void draw(Graphics2D g2d) {
        for (NPC npc : npcs) {
            npc.draw(g2d);
        }
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}