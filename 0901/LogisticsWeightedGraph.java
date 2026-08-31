import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LogisticsWeightedGraph {
    public record Route(String to, int cost) {
        public Route {
            if (to == null || to.isBlank()) {
                throw new IllegalArgumentException("to");
            }
            if (cost < 0) {
                throw new IllegalArgumentException("cost");
            }
        }
    }

    private final Map<String, List<Route>> routes = new LinkedHashMap<>();

    public void addHub(String hub) {
        if (hub == null || hub.isBlank()) {
            throw new IllegalArgumentException("hub");
        }
        routes.putIfAbsent(hub, new ArrayList<>());
    }

    public boolean addRoute(String from, String to, int cost) {
        if (!routes.containsKey(from) || !routes.containsKey(to)) {
            return false;
        }
        List<Route> list = routes.get(from);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).to().equals(to)) {
                list.set(i, new Route(to, cost));
                return true;
            }
        }
        list.add(new Route(to, cost));
        return true;
    }

    public boolean removeRoute(String from, String to) {
        if (!routes.containsKey(from)) {
            return false;
        }
        return routes.get(from).removeIf(route -> route.to().equals(to));
    }

    public Integer routeCost(String from, String to) {
        if (!routes.containsKey(from)) {
            return null;
        }
        for (Route route : routes.get(from)) {
            if (route.to().equals(to)) {
                return route.cost();
            }
        }
        return null;
    }

    public List<Route> routesFrom(String hub) {
        return List.copyOf(routes.getOrDefault(hub, List.of()));
    }

    public int totalOutgoingCost(String hub) {
        int total = 0;
        for (Route route : routes.getOrDefault(hub, List.of())) {
            total += route.cost();
        }
        return total;
    }

    public static void main(String[] args) {
        LogisticsWeightedGraph network = new LogisticsWeightedGraph();
        for (String hub : List.of("Taipei", "Taichung", "Tainan")) {
            network.addHub(hub);
        }

        network.addRoute("Taipei", "Taichung", 100);
        network.addRoute("Taipei", "Tainan", 250);
        network.addRoute("Taichung", "Tainan", 80);

        System.out.println("Taipei routes=" + network.routesFrom("Taipei"));
        System.out.println("Taipei total cost=" + network.totalOutgoingCost("Taipei"));

        System.out.println("update=" + network.addRoute("Taipei", "Taichung", 90));
        System.out.println("Taipei->Taichung cost=" + network.routeCost("Taipei", "Taichung"));

        System.out.println("removeRoute=" + network.removeRoute("Taipei", "Tainan"));
        System.out.println("missing vertex cost=" + network.routeCost("Taipei", "Kaohsiung"));
        System.out.println("nonexistent hub routes=" + network.routesFrom("Kaohsiung"));
    }
}
