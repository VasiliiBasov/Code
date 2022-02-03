package cryptography;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Decryption {
    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюяABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz .,:;-–—«»!?%";
    private static String reverseAlphabet = new StringBuilder(ALPHABET).reverse().toString();

    public static String decryption(String file, int key) throws IOException {
        Path path = Path.of(file);
        List<String> list = Files.readAllLines(path);
        List<String> decryptionList = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            StringBuilder stringBuilder = new StringBuilder(list.get(j));
            for (int i = 0; i < stringBuilder.length(); i++) {
                char ch1 = stringBuilder.charAt(i);
                char ch2 = reverseAlphabet.charAt((reverseAlphabet.indexOf(ch1) + key) % reverseAlphabet.length());
                stringBuilder.setCharAt(i, ch2);
            }
            decryptionList.add(j, stringBuilder.toString());

        }
        if (Files.exists(Path.of(path.getParent() + "\\decryptedText.txt"))) {
            Files.delete(Path.of(path.getParent() + "\\decryptedText.txt"));
        }
        Path decryptedText = Files.createFile(Path.of(path.getParent() + "\\decryptedText.txt"));
        Files.write(decryptedText, decryptionList, StandardOpenOption.CREATE);

        return (decryptedText.getParent() + "\\" + decryptedText.getFileName()).toString();
    }
}
