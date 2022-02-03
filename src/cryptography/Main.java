package cryptography;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        menu();

    }
    public static void menu() throws IOException {
        while (true) {
            Scanner console = new Scanner(System.in);
            System.out.println("Привет!");
            System.out.println("1. Encription");
            System.out.println("2. Decription");
            System.out.println("3. Brut-force");
            System.out.println("4. Analyze");
            System.out.println("5. Exit");

            int i = console.nextInt();

            switch (i) {
                case 1 : {
                    System.out.println("Введите путь к файлу");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String fileName = reader.readLine();
                    System.out.println("Введите ключ");
                    int key = Integer.parseInt(reader.readLine());
                    System.out.println(Encryption.encryption(fileName, key));
                    break;
                }
                case 2 : {
                    System.out.println("Введите путь к файлу");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String fileName = reader.readLine();
                    System.out.println("Введите ключ");
                    int key = Integer.parseInt(reader.readLine());
                    System.out.println(Decryption.decryption(fileName, key));
                    System.out.println();
                    break;
                }
                case 3 : {
                    System.out.println("Введите путь к файлу");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String fileName = reader.readLine();
                    System.out.println(BrutForce.brutForce(fileName));
                    break;
                }
                case 4 : {
                    System.out.println("analyze");
                    break;
                }
                case 5 : {
                    System.exit(0);
                }
                default:
                    System.out.println("wrong number!");
            }
        }
    }
}
