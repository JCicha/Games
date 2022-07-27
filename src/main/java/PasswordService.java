import java.io.*;
import java.util.*;

public class PasswordService {
    public static boolean isLetter(char c) {
        //Checking if char is a letter by using an ascii array
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static boolean allLetters(String word){
        return word.chars()
                .allMatch(c -> isLetter((char) c));
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
    public static void addingWord(){
        System.out.println("Podaj slowo, ktore chcesz dodac do puli wyrazow.\n");
        Scanner scan = new Scanner(System.in);
        String userWord = scan.nextLine();
        if(allLetters(userWord)){
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
        else{
            System.out.println("Slowo zawieralo niedozwolony znak. Sprobuj ponownie.\n");
        }

    }
}
