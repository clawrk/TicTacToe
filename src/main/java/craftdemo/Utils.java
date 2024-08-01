package craftdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Utils {
    public static Boolean playerWinsToss(String playerName) {
        Random random = new Random();
        Integer tossResult = random.nextInt(2);
        if(tossResult.equals(1)) {
            System.out.printf("%s is the first player (X)%n", playerName);
            return true;
        } else {
            System.out.println("Computer is the first player (O)");
            return false;
        }
    }

    public static String getUserName() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Reading data using readLine
        return reader.readLine();
    }

    public static GamePosition fromNumber(int number, int size) {
        int X = (number-1) / size;
        int Y = (number-1) % size;
        return new GamePosition(Y, X);
    }

    public static int fromCoordinates(int x, int y, int size) {
        return y*size + x + 1;
    }
}
