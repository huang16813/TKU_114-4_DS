import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordIndexSystem {
    public static void main(String[] args) {
        String text = "Tree graph tree Queue queue Stack tree";
        String[] words = text.toLowerCase().split("\\s+");

        Map<String, Integer> counts = new HashMap<>();
        Set<String> uniqueWords = new HashSet<>();

        for (String word : words) {
            uniqueWords.add(word);
            counts.merge(word, 1, Integer::sum);
        }

        System.out.println("不重複單字：" + uniqueWords);
        System.out.println("次數統計：" + counts);

        System.out.println("出現至少兩次：");
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() >= 2) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
