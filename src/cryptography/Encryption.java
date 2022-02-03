package cryptography;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Encryption {
    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюяABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz .,:;-–—«»!?%";

    public Encryption() {
    }
    public static String encryption(String file, int key) throws IOException {
        Path path = Path.of(file);
        List<String> list = Files.readAllLines(path);
        List<String> encryptionList = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            StringBuilder stringBuilder = new StringBuilder(list.get(j));
            for (int i = 0; i < stringBuilder.length(); i++) {
                char ch1 = stringBuilder.charAt(i);
                char ch2 = ALPHABET.charAt((ALPHABET.indexOf(ch1) + key) % ALPHABET.length());
                stringBuilder.setCharAt(i, ch2);
            }
            encryptionList.add(j, stringBuilder.toString());

        }
        if (Files.exists(Path.of(path.getParent() + "\\encryptedText.txt"))) {
            Files.delete(Path.of(path.getParent() + "\\encryptedText.txt"));
        }
        Path encryptedText = Files.createFile(Path.of(path.getParent() + "\\encryptedText.txt"));
        Files.write(encryptedText, encryptionList, StandardOpenOption.CREATE);

        return (encryptedText.getParent() + "\\" + encryptedText.getFileName()).toString();
    }
}
