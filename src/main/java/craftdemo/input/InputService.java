package craftdemo.input;

import com.google.common.annotations.VisibleForTesting;

import java.util.Scanner;

public class InputService {
    @VisibleForTesting
    public static final Scanner inputScanner = new Scanner(System.in);
    public static int getUserInput(int size) {
        int ret = -1;
        while (true) {
            try {
                ret = Integer.parseInt(inputScanner.nextLine());
            } catch (NumberFormatException e) {
            }
            if (ret < 1 | ret > size * size)
                System.out.printf("\nInvalid input. Please pick between 1 - %s " , size*size);
            else break;
        }
        return ret;
    }
}
