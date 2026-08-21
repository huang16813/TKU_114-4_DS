import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class CollectionChoiceReport {
    public static void main(String[] args) {
        System.out.println("需求一：保持插入順序且不允許重複");
        System.out.println("interface=Set, implementation=LinkedHashSet");
        Set<String> orderedTags = new LinkedHashSet<>();
        orderedTags.add("VIP");
        orderedTags.add("Online");
        orderedTags.add("VIP");
        orderedTags.add("Urgent");
        System.out.println("結果：" + orderedTags);

        System.out.println();
        System.out.println("需求二：保存不重複學員編號");
        System.out.println("interface=Set, implementation=HashSet");
        Set<String> studentIds = new HashSet<>();
        studentIds.add("S001");
        studentIds.add("S002");
        System.out.println("重複加入 S001：" + studentIds.add("S001"));
        System.out.println("結果數量：" + studentIds.size());

        System.out.println();
        System.out.println("需求三：以學號查詢成績");
        System.out.println("interface=Map, implementation=HashMap");
        Map<String, Integer> scores = new HashMap<>();
        scores.put("S001", 88);
        scores.put("S002", 92);
        System.out.println("查詢 S001：" + scores.get("S001"));

        System.out.println();
        System.out.println("需求四：依到達順序處理待辦工作");
        System.out.println("interface=Deque, implementation=ArrayDeque（當 Queue 使用）");
        Deque<String> todoQueue = new ArrayDeque<>();
        todoQueue.offerLast("清點庫存");
        todoQueue.offerLast("回覆客訴");
        System.out.println("下一項：" + todoQueue.pollFirst());
        System.out.println("剩餘：" + todoQueue);

        System.out.println();
        System.out.println("需求五：復原最近操作");
        System.out.println("interface=Deque, implementation=ArrayDeque（當 Stack 使用）");
        Deque<String> actionHistory = new ArrayDeque<>();
        actionHistory.push("新增訂單");
        actionHistory.push("修改地址");
        System.out.println("復原：" + actionHistory.pop());
        System.out.println("剩餘：" + actionHistory);
    }
}
