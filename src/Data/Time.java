package Data;

public class Time {

    private String time;

    public Time(String time) {
        this.time = time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return time;
    }
}
