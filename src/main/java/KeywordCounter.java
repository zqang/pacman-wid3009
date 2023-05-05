import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class KeywordCounter {

    public static Map<String, Integer> getTopKeywords(String filePath) throws IOException {
        if (!filePath.toLowerCase().endsWith(".txt")) {
            throw new IllegalArgumentException("Invalid file format. Only .txt files are allowed.");
        }

        Map<String, Integer> keywordMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.toLowerCase();
                    if (!keywordMap.containsKey(word)) {
                        keywordMap.put(word, 1);
                    } else {
                        keywordMap.put(word, keywordMap.get(word) + 1);
                    }
                }
            }
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(keywordMap.entrySet());
        entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        Map<String, Integer> topKeywords = new LinkedHashMap<>();
        for (int i = 0; i < Math.min(entries.size(), 10); i++) {
            Map.Entry<String, Integer> entry = entries.get(i);
            topKeywords.put(entry.getKey(), entry.getValue());
        }

        return topKeywords;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\Desktop\\Pacman-WID3009\\WID3009-Assignment1-main\\WID3009-Assignment1-main\\src\\main\\resources\\example.txt"; // Replace with your file path
        Map<String, Integer> topKeywords = getTopKeywords(filePath);
        System.out.println("Top 10 Keywords:");
        for (Map.Entry<String, Integer> entry : topKeywords.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}