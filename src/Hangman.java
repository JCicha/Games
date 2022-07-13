import java.io.*;
import java.util.Scanner;

//Nie dzialające na ten moment: -
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
                    break;
                }
                case 2: {
                    System.out.println("Podaj slowo, ktore chcesz dodac do puli wyrazow.\n");
                    Scanner scan = new Scanner(System.in);
                    String userWord = scan.nextLine();
                    System.out.println(userWord);
                    try {
                        File file = new File("Passwords.txt");
                        FileWriter fr = new FileWriter(file, true);
                        BufferedWriter br = new BufferedWriter(fr);
                        if (File.length().equals(0)) {
                            br.write(userWord);
                        } else {
                            br.write("\n" + userWord);
                        }
                        br.close();
                        fr.close();
                    }

                    catch (Exception e) {
                        e.getStackTrace();
                    }

                }case 3: {
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + userChoice);
            }
            break;
        }
    }
}

