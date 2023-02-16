import Data.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Schedule schedule = new Schedule();
        OutputFile output = new OutputFile();
        output.save("data.txt", schedule.getlessons());
    }

}
