import java.io.*;
import java.util.*;

public class Hangman {
    public static String wordDraw(){
        String randomWord = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Passwords.txt"));
            String line = reader.readLine();
            List<String> words = new ArrayList<String>();
            while (line != null) {
                String[] wordsLine = line.split(" ");
                words.addAll(Arrays.asList(wordsLine));
                line = reader.readLine();
            }
            Random rand = new Random(System.currentTimeMillis());
            randomWord = words.get(rand.nextInt(words.size()));
        } catch (IOException e) {
            e.getStackTrace(); //
        }
        return randomWord;
    }
    public static String hashingWord(int length){
        wordDraw();
        String hashWord = "";
        for (int i = 0; i < length; i++) {
            hashWord = hashWord + "_";
        }
        System.out.println("Twoje slowo: " + hashWord + "\nGra rozpoczyna sie, mozesz pomylic się 7 razy.");
        return hashWord;
    }
    public static void drawingHangman(int missed){
        System.out.println(" -------");
        System.out.println(" |     |");
        if (missed >= 1) {
            System.out.println(" O");
        }
        if (missed >= 2) {
            System.out.print("\\ ");
            if (missed >= 3) {
                System.out.println("/");
            }
            else {
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
            }
            else {
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    public static int guessingLetters(int length, String randomWord, String hashWord){
        wordDraw();
        int correct = 0;
        int missed = 0;
        while (correct < length && missed < 9) {
            System.out.println("Podaj literke: ");
//            drawingHangman();
            Scanner letter = new Scanner(System.in);
            char letterGuess = letter.nextLine().charAt(0);
            System.out.println(letterGuess);

            boolean found = false;
            char[] rand = randomWord.toCharArray();
            char[] hashed = hashWord.toCharArray();
            for (int i = 0; i < randomWord.length(); i++) {
                if (rand[i] == letterGuess) {
                    hashed[i] = letterGuess;
                    found = true;
                    correct++;
                }
            }
            hashWord = new String(hashed);
            if (found) {
                System.out.println("Gratulacje literka trafiona");
                System.out.println(hashWord);
            } else {
                missed++;
                System.out.println("Literka nie trafiona");
                System.out.println(hashWord);
                drawingHangman(missed);
            }
        }
        System.out.println(hashWord);
        if (correct == length) {
            System.out.println("Gratulacje slowo odganiete. Twoim slowem bylo: " + randomWord);
        } else {
            System.out.println("Tym razem się nie udało");
        }
        return missed;
    }

    public static void game(){
        //Choosing random word from our file
        String randomWord = wordDraw();
        int length = randomWord.length();
        //Showing "hashed" word to guess
        String hashWord = hashingWord(length);
        int missed = guessingLetters(length, randomWord, hashWord);
        drawingHangman(missed);
    }

    public static void addingWord(){
        System.out.println("Podaj slowo, ktore chcesz dodac do puli wyrazow.\n");
        Scanner scan = new Scanner(System.in);
        String userWord = scan.nextLine();
        System.out.println(userWord);
        try {
            File file = new File("Passwords.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            if (file.length() == 0) {
                br.write(userWord);
            } else {
                br.write("\n" + userWord);
            }
            br.close();
            fr.close();
        } catch (Exception e) {  // TODO do naprawienia na konkretne przypadki
            e.getStackTrace();
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("Witaj w grze wisielec! Co chcesz zrobic? (Wpisz numer operacji)\n "
                    + "1. Zagrac!\n 2. Dodac slowo do puli wyrazow. \n 3. Zakonczyc program.");
            Scanner in = new Scanner(System.in);
            int userChoice = Integer.parseInt(in.nextLine());
            switch (userChoice) {
                case 1: {
                    game();
                } case 2: {
                    addingWord();
                } case 3: {
                    break;
                } default:
                    throw new IllegalStateException("Unexpected value: " + userChoice);
            }
            break;
        }
    }
}
