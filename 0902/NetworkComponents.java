import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class NetworkComponents {
    static List<List<String>> components(Map<String, List<String>> graph) {
        List<List<String>> result = new ArrayList<>();
        if (graph == null) {
            return result;
        }
        Set<String> visited = new HashSet<>();
        for (String start : graph.keySet()) {
            if (visited.contains(start)) {
                continue;
            }
            List<String> component = new ArrayList<>();
            Queue<String> queue = new ArrayDeque<>();
            queue.offer(start);
            visited.add(start);
            while (!queue.isEmpty()) {
                String current = queue.poll();
                component.add(current);
                for (String next : graph.getOrDefault(current, List.of())) {
                    if (graph.containsKey(next) && visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
            result.add(component);
        }
        return result;
    }

    static List<String> largestComponent(List<List<String>> allComponents) {
        List<String> largest = List.of();
        for (List<String> component : allComponents) {
            if (component.size() > largest.size()) {
                largest = component;
            }
        }
        return largest;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("A"));
        graph.put("C", List.of("A"));
        graph.put("D", List.of("E"));
        graph.put("E", List.of("D"));
        graph.put("F", List.of());

        List<List<String>> allComponents = components(graph);
        System.out.println("components=" + allComponents);
        System.out.println("componentCount=" + allComponents.size());
        System.out.println("largestComponent=" + largestComponent(allComponents));

        System.out.println("empty graph components=" + components(Map.of()));
        System.out.println("null graph components=" + components(null));
    }
}
