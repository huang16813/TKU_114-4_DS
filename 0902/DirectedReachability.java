import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DirectedReachability {
    record Query(String from, String to) {}

    static boolean reachable(Map<String, List<String>> graph, String from, String to) {
        if (graph == null || from == null || to == null
                || !graph.containsKey(from) || !graph.containsKey(to)) {
            return false;
        }
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.offer(from);
        visited.add(from);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(to)) {
                return true;
            }
            for (String next : graph.getOrDefault(current, List.of())) {
                if (graph.containsKey(next) && visited.add(next)) {
                    queue.offer(next);
                }
            }
        }
        return false;
    }

    static void runQueries(Map<String, List<String>> graph, List<Query> queries) {
        for (Query query : queries) {
            System.out.println(query.from() + "->" + query.to()
                    + " reachable=" + reachable(graph, query.from(), query.to()));
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = Map.of(
                "A", List.of("B"),
                "B", List.of("C"),
                "C", List.of(),
                "D", List.of("A"),
                "E", List.of());

        List<Query> queries = List.of(
                new Query("A", "C"),
                new Query("C", "A"),
                new Query("D", "C"),
                new Query("E", "A"),
                new Query("A", "X"),
                new Query("A", "A"));

        runQueries(graph, queries);
    }
}
