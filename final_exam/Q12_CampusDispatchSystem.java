import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Q12_CampusDispatchSystem {
    public record Request(String id, String location, int priority, long sequence) {}

    private final Map<String, Set<String>> roads = new LinkedHashMap<>();
    private final Map<String, Request> requestsById = new HashMap<>();
    private final PriorityQueue<Request> pending = new PriorityQueue<>(
            Comparator.comparingInt(Request::priority).thenComparingLong(Request::sequence));

    public boolean addLocation(String location) {
        if (location == null || location.isBlank()) {
            return false;
        }
        return roads.putIfAbsent(location, new LinkedHashSet<>()) == null;
    }

    public boolean addRoad(String first, String second) {
        if (!roads.containsKey(first) || !roads.containsKey(second) || first.equals(second)) {
            return false;
        }
        boolean changed = roads.get(first).add(second);
        roads.get(second).add(first);
        return changed;
    }

    public boolean submit(Request request) {
        if (request == null || request.id() == null || request.id().isBlank()
                || !roads.containsKey(request.location()) || requestsById.containsKey(request.id())) {
            return false;
        }
        requestsById.put(request.id(), request);
        pending.offer(request);
        return true;
    }

    public Request nextReachable(String serviceCenter) {
        if (serviceCenter == null || !roads.containsKey(serviceCenter)) {
            return null;
        }
        Set<String> reachable = reachableFrom(serviceCenter);

        List<Request> skipped = new ArrayList<>();
        Request chosen = null;
        while (!pending.isEmpty()) {
            Request candidate = pending.poll();
            if (!requestsById.containsKey(candidate.id())) {
                continue;
            }
            if (reachable.contains(candidate.location())) {
                chosen = candidate;
                break;
            }
            skipped.add(candidate);
        }
        for (Request request : skipped) {
            pending.offer(request);
        }
        if (chosen != null) {
            requestsById.remove(chosen.id());
        }
        return chosen;
    }

    private Set<String> reachableFrom(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (String next : roads.getOrDefault(current, Set.of())) {
                if (visited.add(next)) {
                    queue.offer(next);
                }
            }
        }
        return visited;
    }

    public List<String> route(String start, String target) {
        if (!roads.containsKey(start) || !roads.containsKey(target)) {
            return List.of();
        }
        if (start.equals(target)) {
            return List.of(start);
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
            for (String next : roads.getOrDefault(current, Set.of())) {
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

    public int pendingCount() {
        return requestsById.size();
    }

    public static void main(String[] args) {
        Q12_CampusDispatchSystem system = new Q12_CampusDispatchSystem();
        for (String location : List.of("Gate", "Library", "Gym", "Dorm", "Isolated")) {
            system.addLocation(location);
        }
        system.addRoad("Gate", "Library");
        system.addRoad("Library", "Gym");
        system.addRoad("Gate", "Dorm");

        System.out.println("submit=" + system.submit(new Request("R1", "Gym", 3, 1)));
        System.out.println("submit=" + system.submit(new Request("R2", "Dorm", 1, 2)));
        System.out.println("submit dup id=" + system.submit(new Request("R1", "Gym", 5, 3)));
        System.out.println("submit at disconnected location="
                + system.submit(new Request("R3", "Isolated", 1, 4)));
        System.out.println("submit=" + system.submit(new Request("R4", "Isolated", 0, 5)));
        System.out.println("submit at unregistered location="
                + system.submit(new Request("R5", "Nowhere", 0, 6)));

        System.out.println("pendingCount=" + system.pendingCount());

        Request next = system.nextReachable("Gate");
        System.out.println("nextReachable=" + next);
        System.out.println("pendingCount after dispatch=" + system.pendingCount());

        System.out.println("route Gate->Gym=" + system.route("Gate", "Gym"));
        System.out.println("route Gate->Isolated=" + system.route("Gate", "Isolated"));
        System.out.println("route same=" + system.route("Gate", "Gate"));

        System.out.println("nextReachable missing center=" + system.nextReachable("X"));
    }
}
