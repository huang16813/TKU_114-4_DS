import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CampusNavigationSystem {
    private final Map<String, String> locations = new LinkedHashMap<>();
    private final Map<String, Set<String>> routes = new LinkedHashMap<>();

    public boolean addLocation(String code, String name) {
        if (code == null || code.isBlank() || name == null || name.isBlank()) {
            return false;
        }
        if (locations.containsKey(code)) {
            return false;
        }
        locations.put(code, name);
        routes.put(code, new LinkedHashSet<>());
        return true;
    }

    public boolean addRoute(String from, String to) {
        if (!locations.containsKey(from) || !locations.containsKey(to) || from.equals(to)) {
            return false;
        }
        boolean changed = routes.get(from).add(to);
        routes.get(to).add(from);
        return changed;
    }

    public String nameOf(String code) {
        return locations.get(code);
    }

    public List<String> shortestPath(String start, String target) {
        if (!locations.containsKey(start) || !locations.containsKey(target)) {
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
            for (String next : routes.getOrDefault(current, Set.of())) {
                if (visited.add(next)) {
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

    public static void main(String[] args) {
        CampusNavigationSystem campus = new CampusNavigationSystem();
        campus.addLocation("LIB", "Library");
        campus.addLocation("GYM", "Gym");
        campus.addLocation("CAF", "Cafeteria");
        campus.addLocation("DORM", "Dormitory");
        campus.addLocation("LAB", "Science Lab");

        campus.addRoute("LIB", "GYM");
        campus.addRoute("LIB", "CAF");
        campus.addRoute("CAF", "DORM");
        campus.addRoute("GYM", "DORM");

        System.out.println("LIB name=" + campus.nameOf("LIB"));

        List<String> path = campus.shortestPath("LIB", "DORM");
        System.out.println("path=" + path + " edges=" + (path.isEmpty() ? -1 : path.size() - 1));

        System.out.println("no path=" + campus.shortestPath("LIB", "LAB"));
        System.out.println("missing location=" + campus.shortestPath("LIB", "X"));
    }
}
