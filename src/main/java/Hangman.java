import java.awt.*;
import java.io.*;
import java.util.*;

public class Hangman {
    public static void game() {
        //Choosing random word from our file
        String randomWord = PasswordService.wordDraw();
        int length = randomWord.length();
        //Showing "hashed" word to guess
        String hashWord = PasswordService.hashingWord(randomWord);
        int missed = MenuGame.guessingLetters(randomWord, hashWord);
        MenuGame.drawingHangman(missed);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("Witaj w grze wisielec! Co chcesz zrobic? (Wpisz numer operacji)\n "
                    + "1. Zagrac!\n 2. Dodac slowo do puli wyrazow. \n 3. Zakonczyc program.");
            Scanner in = new Scanner(System.in);
            String userChoice = in.nextLine().strip();
            switch (userChoice) {
                case "1" -> {
                    game();
                }
                case "2" -> {
                    PasswordService.addingWord();
                }
                case "3" -> {
                    return;
                }
                default -> throw new IllegalStateException("Unexpected value: " + userChoice);
            }
        }
    }
}
