import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//Nie dzialające na ten moment: - case 2 - length
public class Hangman {
    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("Witaj w grze wisielec! Co chcesz zrobic? (Wpisz numer operacji)\n " +
                    "1. Zagrac!\n 2. Dodac slowo do puli wyrazow. \n 3. Zakonczyc program.");
            Scanner in = new Scanner(System.in);
            int userChoice = Integer.parseInt(in.nextLine());
            System.out.println(userChoice);
            switch (userChoice) {
                case 1: {
                    //Choosing random word from our file
                    System.out.println("I'm in");
                    try{
                        System.out.println("Here");
                        BufferedReader reader = new BufferedReader(new FileReader("Passwords.txt"));
                        String line = reader.readLine();
                        List<String> words = new ArrayList<String>();
                        while(line != null) {
                            String[] wordsLine = line.split(" ");
                            for(String word : wordsLine) {
                                words.add(word);
                            }
                            line = reader.readLine();
                        }
                        Random rand = new Random(System.currentTimeMillis());
                        String randomWord = words.get(rand.nextInt(words.size()));
                        System.out.println(randomWord);
                    } catch (Exception e) {
                        // Handle this
                    }
                }
//                case 2: {
//                    System.out.println("Podaj slowo, ktore chcesz dodac do puli wyrazow.\n");
//                    Scanner scan = new Scanner(System.in);
//                    String userWord = scan.nextLine();
//                    System.out.println(userWord);
//                    try {
//                        File file = new File("Passwords.txt");
//                        FileWriter fr = new FileWriter(file, true);
//                        BufferedWriter br = new BufferedWriter(fr);
//                        if (File.length().equals(0)) {
//                            br.write(userWord);
//                        } else {
//                            br.write("\n" + userWord);
//                        }
//                        br.close();
//                        fr.close();
//                    }
//
//                    catch (Exception e) {
//                        e.getStackTrace();
//                    }
//
//                }case 3: {
//                    break;
//                }
//                default:
//                    throw new IllegalStateException("Unexpected value: " + userChoice);
            }
            //break;
        }
    }
}

