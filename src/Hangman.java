import java.io.*;
import java.util.*;

public class Hangman {
    public static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') ||  //Checking if char is a letter by using an ascii array
                (c >= 'A' && c <= 'Z');
    }
    public static String wordDraw(){
        String randomWord = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Passwords.txt"));
            String line = reader.readLine();
            List<String> words = new ArrayList<String>();
            while (line != null) {
                String[] wordsLine = line.split(" ");
                words.addAll(Arrays.asList(line));
                line = reader.readLine();
            }  // reading through a whole file
            Random rand = new Random(System.currentTimeMillis()); // drawing a word
            randomWord = words.get(rand.nextInt(words.size()));
        } catch (IOException e) {
            e.getStackTrace(); //
        }

        return randomWord;

    }
    public static String hashingWord(String randomWord){
        wordDraw();
        String hashWord = "";
        for (int i = 0; i < randomWord.length(); i++) {
            if (randomWord.charAt(i) == ' '){  // checking if char in word is a space or not
                hashWord += " ";
            }
            else {
                hashWord += "_";
            }
        }
        System.out.println("Twoje slowo to: " + hashWord + "\nGra rozpoczyna sie, mozesz pomylic sie 7 razy.");
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

    public static int guessingLetters(String randomWord, String hashWord){
        wordDraw();
        int missed = 0;
        String randomized = randomWord.replaceAll("\\s","");  // Deleting whitespaces
        Set<String> result = new HashSet<>(Arrays.asList(randomized.split("")));  // Creating an array
        // of letters in word without repeatition
        while (result.size() > 0 && missed < 6) {
            char letterGuess;
            Scanner letter = new Scanner(System.in);
            while (true) {
                System.out.println("Podaj literke: ");
                letterGuess = letter.nextLine().charAt(0);
                if (isLetter(letterGuess)) {  // Checking if char is letter
                    break;
                }
            }

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
                System.out.println("Twoje slowo: "+hashWord);
            } else {
                missed++;
                System.out.println("Literka nie trafiona");
                System.out.println("Twoje slowo: "+hashWord);
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

    public static void game(){
        //Choosing random word from our file
        String randomWord = wordDraw();
        int length = randomWord.length();
        //Showing "hashed" word to guess
        String hashWord = hashingWord(randomWord);
        int missed = guessingLetters(randomWord, hashWord);
        drawingHangman(missed);
    }

    public static void addingWord(){
        System.out.println("Podaj slowo, ktore chcesz dodac do puli wyrazow.\n");
        Scanner scan = new Scanner(System.in);
        String userWord = scan.nextLine();
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
        } catch (Exception e) {
            e.getStackTrace();
        }
        System.out.println("Slowo zostalo dodane do puli wyrazow.\n");
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
                    break;
                } case 2: {
                    addingWord();
                    break;
                } case 3: {
                    return;
                } default:
                    throw new IllegalStateException("Unexpected value: " + userChoice);
            }
        }
    }
}
