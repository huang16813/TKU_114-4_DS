import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MetroTransferPath {
    static List<String> shortestPath(Map<String, List<String>> lines, String start, String target) {
        if (lines == null || !lines.containsKey(start) || !lines.containsKey(target)) {
            return List.of();
        }
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> previous = new HashMap<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(target)) {
                break;
            }
            for (String next : lines.getOrDefault(current, List.of())) {
                if (lines.containsKey(next) && visited.add(next)) {
                    previous.put(next, current);
                    queue.offer(next);
                }
            }
        }
        if (!visited.contains(target)) {
            return List.of();
        }
        List<String> path = new ArrayList<>();
        for (String at = target; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    static void report(Map<String, List<String>> lines, String start, String target) {
        List<String> path = shortestPath(lines, start, target);
        if (path.isEmpty()) {
            System.out.println(start + " -> " + target + " : no path");
        } else {
            System.out.println(start + " -> " + target + " : " + path
                    + " edges=" + (path.size() - 1));
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> lines = Map.of(
                "Central", List.of("North", "East"),
                "North", List.of("Central", "Airport"),
                "East", List.of("Central", "South"),
                "South", List.of("East"),
                "Airport", List.of("North"),
                "Island", List.of());

        report(lines, "South", "Airport");
        report(lines, "Central", "Central");
        report(lines, "South", "Island");
    }
}
