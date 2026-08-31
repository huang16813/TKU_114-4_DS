import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IterativeDfsTrace {
    static void traceDfs(Map<String, List<String>> graph, String start) {
        if (graph == null || start == null || !graph.containsKey(start)) {
            System.out.println("no traversal: invalid graph or start");
            return;
        }
        ArrayDeque<String> stack = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        stack.push(start);
        System.out.println("push " + start + " -> stack=" + stack + " visited=" + visited);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            System.out.println("pop " + current + " -> stack=" + stack + " visited=" + visited);
            if (!visited.add(current)) {
                continue;
            }

            List<String> neighbors = graph.getOrDefault(current, List.of());
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                String next = neighbors.get(i);
                if (graph.containsKey(next) && !visited.contains(next)) {
                    stack.push(next);
                    System.out.println("push " + next + " -> stack=" + stack + " visited=" + visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("D"));
        graph.put("C", List.of("D"));
        graph.put("D", List.of("A"));

        traceDfs(graph, "A");
        System.out.println("---");
        traceDfs(graph, "X");
    }
}
