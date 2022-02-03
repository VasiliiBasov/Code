package cryptography;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BrutForce {
    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюяABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz .,:;-–—«»!?%";

    public BrutForce() {
    }

    private static String reverseAlphabet = new StringBuilder(ALPHABET).reverse().toString();

    public static String brutForce(String file) throws IOException {
        Path path = Path.of(file);
        List<String> list = Files.readAllLines(path);
        int key = 0;
        int answerCount = 0;
        for (int j = 0; j < list.size();) {
            StringBuilder stringBuilder = new StringBuilder(list.get(j));
            if (stringBuilder.length() < 10) {
                j++;
                continue;
            }
            for (int i = 0; i < stringBuilder.length(); i++) {
                char ch1 = stringBuilder.charAt(i);
                char ch2 = reverseAlphabet.charAt((reverseAlphabet.indexOf(ch1) + key)%reverseAlphabet.length());
                stringBuilder.setCharAt(i, ch2);
            }
            if (check(stringBuilder.toString())) {
                while (true) {
                    System.out.println("Ключ - " + key);
                    System.out.println();
                    System.out.println(stringBuilder.toString());
                    System.out.println("Есть ли ошибки в тексте? Введите yes/no");
                    Scanner console = new Scanner(System.in);
                    String answer = console.nextLine();
                    if (answer.equals("yes")) {
                        key++;
                        answerCount = 0;
                        break;
                    }
                    else if (answer.equals("no")) {
                        answerCount++;
                        if (answerCount == 3) {
                            return Decryption.decryption(file, key);
                        }
                        j++;
                        break;
                    } else continue;
                }
            }
            else {
                if (key == reverseAlphabet.length()-1) {
                    j++;
                    key = 0;
                }
                key++;
            }

        }
        System.out.println("Не удалось подобрать ключ");
        return null;
    }

    private static boolean check(String text) {
        StringBuilder stringCheck = new StringBuilder(text);
        int countSpaces = 1;
        for (int i = 0; i < stringCheck.length()-1; i++) {
            if (stringCheck.charAt(i) == '.' || stringCheck.charAt(i) == ',' || stringCheck.charAt(i) == ':' || stringCheck.charAt(i) == ';' || stringCheck.charAt(i) == '?' || stringCheck.charAt(i) == '!') {
                if (stringCheck.charAt(i+1) != ' ' || !(i==stringCheck.length()-2)) {
                    return false;
                }
            }
            if (stringCheck.charAt(i) == ' ') countSpaces++;
        }
        if (stringCheck.length()/countSpaces > 10) {
            return false;
        }

        return true;
    }
}
