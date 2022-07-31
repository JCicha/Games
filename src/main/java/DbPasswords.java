import java.util.Random;
import java.util.Scanner;

public class DbPasswords {

    public static boolean allLetters(String word){
        return word.chars()
                .allMatch(c -> isLetter((char) c));
    }
    public static boolean isLetter(char c) {
        //Checking if char is a letter by using an ascii array
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static String getPasswords() {
        return DaoPasswords.getPasswordsFromTheDb();
    }

    public static String getRandom() {
        String[] passwords = getPasswords().split("\n");
        int index = new Random().nextInt(passwords.length);
        return passwords[index].strip();
    }

    public static String hashingWord(String randomWord){
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
        DbConnection.getDBConnection();
        System.out.println("Podaj slowo, ktore chcesz dodac do puli wyrazow.\n");
        Scanner scan = new Scanner(System.in);
        String userWord = scan.nextLine();
        if(allLetters(userWord)){
            try {
                DaoPasswords.addingPasswordToDb(userWord);
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
