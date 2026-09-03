import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q09_DfsPathSearch {
    public static List<String> dfs(Map<String, List<String>> graph, String start) {
        List<String> result = new ArrayList<>();
        if (graph == null || start == null || !graph.containsKey(start)) {
            return result;
        }
        visit(graph, start, new HashSet<>(), result);
        return result;
    }

    private static void visit(Map<String, List<String>> graph, String current,
                               Set<String> visited, List<String> result) {
        if (!visited.add(current)) {
            return;
        }
        result.add(current);
        for (String next : graph.getOrDefault(current, List.of())) {
            if (graph.containsKey(next)) {
                visit(graph, next, visited, result);
            }
        }
    }

    public static boolean reachable(Map<String, List<String>> graph, String start, String target) {
        if (graph == null || start == null || target == null
                || !graph.containsKey(start) || !graph.containsKey(target)) {
            return false;
        }
        if (start.equals(target)) {
            return true;
        }
        return visitReachable(graph, start, target, new HashSet<>());
    }

    private static boolean visitReachable(Map<String, List<String>> graph, String current,
                                           String target, Set<String> visited) {
        if (!visited.add(current)) {
            return false;
        }
        for (String next : graph.getOrDefault(current, List.of())) {
            if (!graph.containsKey(next)) {
                continue;
            }
            if (next.equals(target) || visitReachable(graph, next, target, visited)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = Map.of(
                "A", List.of("B", "C"),
                "B", List.of("D"),
                "C", List.of("D"),
                "D", List.of("A"),
                "E", List.of());

        System.out.println("dfs A=" + dfs(graph, "A"));
        System.out.println("dfs missing=" + dfs(graph, "X"));

        System.out.println("reachable A->D=" + reachable(graph, "A", "D"));
        System.out.println("reachable E->A=" + reachable(graph, "E", "A"));
        System.out.println("reachable A->A=" + reachable(graph, "A", "A"));
        System.out.println("reachable missing=" + reachable(graph, "A", "X"));
    }
}
