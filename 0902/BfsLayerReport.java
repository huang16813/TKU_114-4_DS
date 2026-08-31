import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BfsLayerReport {
    static Map<String, Integer> layerDistances(Map<String, List<String>> graph, String start) {
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
        graph.put("D", List.of("B", "C", "E"));
        graph.put("E", List.of("D"));
        graph.put("F", List.of());

        System.out.println("from A: " + layerDistances(graph, "A"));
        System.out.println("from F: " + layerDistances(graph, "F"));
        System.out.println("missing start: " + layerDistances(graph, "X"));
        System.out.println("null graph: " + layerDistances(null, "A"));
    }
}
