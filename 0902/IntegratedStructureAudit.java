import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class IntegratedStructureAudit {
    private static int passCount = 0;
    private static int failCount = 0;

    static void check(String description, boolean condition) {
        if (condition) {
            passCount++;
            System.out.println("PASS - " + description);
        } else {
            failCount++;
            System.out.println("FAIL - " + description);
        }
    }

    static void auditList() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(1, 15);
        check("List keeps insertion/index order", list.equals(List.of(10, 15, 20)));
        check("List index access within bounds", list.get(0) == 10 && list.get(2) == 20);
    }

    static void auditQueue() {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        check("Queue is FIFO", queue.poll() == 1 && queue.poll() == 2 && queue.poll() == 3);
        check("Queue empty poll returns null", queue.poll() == null);
    }

    static void auditBst() {
        Map<Integer, String> tree = new TreeMap<>();
        tree.put(50, "root");
        tree.put(30, "left");
        tree.put(70, "right");
        List<Integer> keysInOrder = new ArrayList<>(tree.keySet());
        check("BST-like TreeMap keeps sorted order", keysInOrder.equals(List.of(30, 50, 70)));
    }

    static void auditHeap() {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.offer(40);
        heap.offer(10);
        heap.offer(30);
        check("Heap peek is the minimum", heap.peek() == 10);
        check("Heap poll order is ascending",
                heap.poll() == 10 && heap.poll() == 30 && heap.poll() == 40);
    }

    static void auditHashTable() {
        Map<String, Integer> table = new HashMap<>();
        table.put("a", 1);
        table.put("b", 2);
        table.put("a", 3);
        check("HashMap put with existing key updates value", table.get("a") == 3);
        check("HashMap size does not grow on key update", table.size() == 2);
    }

    static void auditGraph() {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", List.of("B"));
        graph.put("B", List.of("A", "C"));
        graph.put("C", List.of("B"));

        boolean symmetric = graph.get("A").contains("B") && graph.get("B").contains("A")
                && graph.get("B").contains("C") && graph.get("C").contains("B");
        check("Undirected graph adjacency is symmetric", symmetric);
        check("Graph missing vertex returns empty neighbor list",
                graph.getOrDefault("X", List.of()).isEmpty());
    }

    public static void main(String[] args) {
        auditList();
        auditQueue();
        auditBst();
        auditHeap();
        auditHashTable();
        auditGraph();

        System.out.println("total pass=" + passCount + " fail=" + failCount);
    }
}
