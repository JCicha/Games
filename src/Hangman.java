import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {
        System.out.println("Witaj w grze wisielec! Co chcesz zrobic? (Wpisz numer operacji)\n " +
                "1. Zagrac!\n 2. Dodac slowo do puli wyrazow.");
        Scanner in = new Scanner(System.in);
        String userChoice = in.nextLine();
        System.out.println(userChoice);
    }
}
