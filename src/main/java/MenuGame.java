import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MenuGame {
    public static void drawingHangman(int missed) {
        System.out.println(" -------");
        System.out.println(" |     |");
        if (missed >= 1) {
            System.out.println(" O");
        }
        if (missed >= 2) {
            System.out.print("\\ ");
            if (missed >= 3) {
                System.out.println("/");
            } else {
                System.out.println("");
            }
        }
        if (missed >= 4) {
            System.out.println(" |");
        }
        if (missed >= 5) {
            System.out.print("/ ");
            if (missed >= 6) {
                System.out.println("\\");
            } else {
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    public static int guessingLetters(String randomWord, String hashWord) {
        PasswordService.wordDraw();
        int missed = 0;
        String randomized = randomWord.replaceAll("\\s", "");  // Deleting whitespaces
        Set<String> result = new HashSet<>(Arrays.asList(randomized.split("")));  // Creating an array
        // of letters in word without repeatition
        while (result.size() > 0 && missed < 6) {
            char letterGuess;
            Scanner letter = new Scanner(System.in);
            // Checking if char is letter
            do {
                System.out.println("Podaj literke: ");
                letterGuess = letter.nextLine().charAt(0);
            } while (!PasswordService.isLetter(letterGuess));

            boolean found = false;
            char[] rand = randomWord.toCharArray();
            char[] hashed = hashWord.toCharArray();
            for (int i = 0; i < randomWord.length(); i++) {
                if (rand[i] == letterGuess) {  // Is letter in our word?
                    hashed[i] = letterGuess;
                    found = true;
                }
            }
            hashWord = new String(hashed);

            if (found && result.contains(String.valueOf(letterGuess))) {
                result.remove(String.valueOf(letterGuess));
                System.out.println("Gratulacje literka trafiona");
                System.out.println("Twoje slowo: " + hashWord);
            } else {
                missed++;
                System.out.println("Literka nie trafiona");
                System.out.println("Twoje slowo: " + hashWord);
            }
            drawingHangman(missed);
        }
        System.out.println(hashWord);
        if (result.size() == 0) {
            System.out.println("Gratulacje slowo odganiete. Twoim slowem bylo: " + randomWord);
        } else {
            System.out.println("Tym razem sie nie udalo. Powodzenia nastepnym razem");
        }
        return missed;
    }
}
