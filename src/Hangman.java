import java.io.FileWriter;
import java.util.Scanner;

//Nie dzialające na ten moment: wychodzenie z programu, wpisywanie słowa do puli.
public class Hangman {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Witaj w grze wisielec! Co chcesz zrobic? (Wpisz numer operacji)\n " +
                    "1. Zagrac!\n 2. Dodac slowo do puli wyrazow. \n 3. Zakonczyc program.");
            Scanner in = new Scanner(System.in);
            int userChoice = Integer.parseInt(in.nextLine());
            System.out.println(userChoice);
            switch(userChoice) {
                case 2: {
                    System.out.println("Podaj słowo, które chcesz dodać do puli wyrazów.\n");
                    Scanner scan = new Scanner(System.in);
                    String userWord = scan.nextLine();
                    System.out.println(userWord);
                    try {
                        FileWriter output = new FileWriter("Passwords.txt");
                        output.write(userWord);
                        output.close();
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
                case 3: {
                    break;
                }
                default: {
                    System.out.println("Niepoprawnie wybrana opcja");
                }
            }
        }
    }
}
