package Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private final List<String> groups;

    public Group() {
        groups = new ArrayList<>();
    }

    public void add(String group) {
        this.groups.add(group);
    }

    public List<String> get() {
        return groups;
    }

    @Override
    public String toString() {
        String returnString = "";
        for (String group : groups) {
            returnString = returnString + group + " ";
        }
        return returnString;
    }
}
