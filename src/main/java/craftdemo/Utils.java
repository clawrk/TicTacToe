package craftdemo;

import java.util.Random;

public class Utils {
    public static Boolean playerWinsToss() {
        Random random = new Random();
        Integer tossResult = random.nextInt(2);
        if(tossResult.equals(1)) {
            System.out.println("Player won the toss");
            return true;
        } else {
            System.out.println("Computer won the toss");
            return false;
        }
    }
}
