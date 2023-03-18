package Data;

public class Group {
    private String group;

    public Group(String group) {
        this.group = group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return group;
    }
}
