package cryptography;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatAnalyze {

    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюяABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz .,:;-–—«»!?%";
    public StatAnalyze() {
    }

    public static String statAnalyze(String file, String file2) throws IOException {
        Path path = Path.of(file);
        Path path2 = Path.of(file2);
        List<String> list = Files.readAllLines(path);
        List<String> list2 = Files.readAllLines(path2);
        return null;
    }

    private static Map letterCounter(List list) {
        List<String> listStr = list;
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder letters = new StringBuilder(ALPHABET);
        for (int i = 0; i < letters.length(); i++) {
            map.put(letters.charAt(i), 0);
        }
        for (int a = 0; a < listStr.size(); a++) {
            StringBuilder strBuilder = new StringBuilder(listStr.get(a));
            for (int i = 0; i < strBuilder.length(); i++) {
                for (int j = 0; j < letters.length(); j++) {
                    if (strBuilder.charAt(i) == letters.charAt(j)) {
                        map.put(letters.charAt(j), map.get(letters.charAt(j) + 1));
                    }
                }

            }
        }

        return map;
    }
}
