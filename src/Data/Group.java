package Data;

import java.util.ArrayList;

public class Group {
    private final ArrayList<String> groups;

    public Group() {
        groups = new ArrayList<>();
    }

    public void add(String group) {
        this.groups.add(group);
    }

    public ArrayList<String> get() {
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
