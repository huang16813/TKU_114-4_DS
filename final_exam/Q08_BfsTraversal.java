import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q08_BfsTraversal {
    public static List<String> bfs(Map<String, List<String>> graph, String start) {
        List<String> order = new ArrayList<>();
        if (graph == null || start == null || !graph.containsKey(start)) {
            return order;
        }
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new LinkedHashSet<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            order.add(current);
            for (String next : graph.getOrDefault(current, List.of())) {
                if (graph.containsKey(next) && visited.add(next)) {
                    queue.offer(next);
                }
            }
        }
        return order;
    }

    public static Map<String, Integer> distanceFrom(Map<String, List<String>> graph, String start) {
        Map<String, Integer> distances = new LinkedHashMap<>();
        if (graph == null || start == null || !graph.containsKey(start)) {
            return distances;
        }
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        distances.put(start, 0);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            int nextDistance = distances.get(current) + 1;
            for (String next : graph.getOrDefault(current, List.of())) {
                if (graph.containsKey(next) && !distances.containsKey(next)) {
                    distances.put(next, nextDistance);
                    queue.offer(next);
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("A", "D"));
        graph.put("C", List.of("A", "D"));
        graph.put("D", List.of("B", "C"));
        graph.put("E", List.of());

        System.out.println("bfs A=" + bfs(graph, "A"));
        System.out.println("bfs E=" + bfs(graph, "E"));
        System.out.println("bfs missing=" + bfs(graph, "X"));
        System.out.println("bfs null graph=" + bfs(null, "A"));

        System.out.println("distance A=" + distanceFrom(graph, "A"));
        System.out.println("distance missing=" + distanceFrom(graph, "X"));
    }
}
