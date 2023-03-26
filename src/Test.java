import Data.*;
import PathFinding.Pathfinding;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        Pathfinding pathfinding = new Pathfinding();
        pathfinding.dataCheck(pathfinding.getData());
        pathfinding.dataCheck(pathfinding.findPath(58,50));


    }

}
