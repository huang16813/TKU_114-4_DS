import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseTagReport {
    public static void main(String[] args) {
        String[] tagInput = {"Java", "Tree", "Java", "Graph", "Tree", "Queue"};

        List<String> original = new ArrayList<>();
        Set<String> unique = new LinkedHashSet<>();
        Map<String, Integer> counts = new HashMap<>();

        for (String tag : tagInput) {
            original.add(tag);
            unique.add(tag);
            counts.merge(tag, 1, Integer::sum);
        }

        System.out.println("原始順序：" + original);
        System.out.println("不重複標籤：" + unique);
        System.out.println("次數統計：" + counts);

        System.out.println("List 用途：保留輸入順序與重複次數，適合記錄使用歷程");
        System.out.println("Set 用途：快速確認標籤是否存在，並自動去除重複");
        System.out.println("Map 用途：以標籤查詢出現次數，適合建立排行或篩選條件");
    }
}
