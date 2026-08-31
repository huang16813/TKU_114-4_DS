import java.util.ArrayList;
import java.util.List;

public class CampusMatrixGraph {
    private final List<String> vertices;
    private final boolean[][] edges;

    public CampusMatrixGraph(List<String> vertices) {
        if (vertices == null || vertices.isEmpty()) {
            throw new IllegalArgumentException("vertices");
        }
        this.vertices = List.copyOf(vertices);
        this.edges = new boolean[vertices.size()][vertices.size()];
    }

    private int indexOf(String vertex) {
        int index = vertices.indexOf(vertex);
        if (index < 0) {
            throw new IllegalArgumentException("unknown vertex: " + vertex);
        }
        return index;
    }

    public boolean addEdge(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);
        if (edges[a][b]) {
            return false;
        }
        edges[a][b] = true;
        edges[b][a] = true;
        return true;
    }

    public boolean removeEdge(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);
        if (!edges[a][b]) {
            return false;
        }
        edges[a][b] = false;
        edges[b][a] = false;
        return true;
    }

    public int degree(String vertex) {
        int row = indexOf(vertex);
        int degree = 0;
        for (boolean connected : edges[row]) {
            if (connected) {
                degree++;
            }
        }
        return degree;
    }

    public List<String> neighbors(String vertex) {
        int row = indexOf(vertex);
        List<String> result = new ArrayList<>();
        for (int column = 0; column < vertices.size(); column++) {
            if (edges[row][column]) {
                result.add(vertices.get(column));
            }
        }
        return result;
    }

    public int edgeCount() {
        int total = 0;
        for (boolean[] row : edges) {
            for (boolean connected : row) {
                if (connected) {
                    total++;
                }
            }
        }
        return total / 2;
    }

    public static void main(String[] args) {
        CampusMatrixGraph graph = new CampusMatrixGraph(List.of("Library", "Gym", "Cafeteria", "Dorm"));
        System.out.println("addEdge=" + graph.addEdge("Library", "Gym"));
        System.out.println("addEdge dup=" + graph.addEdge("Library", "Gym"));
        graph.addEdge("Library", "Cafeteria");
        graph.addEdge("Cafeteria", "Dorm");

        System.out.println("Library neighbors=" + graph.neighbors("Library"));
        System.out.println("Cafeteria degree=" + graph.degree("Cafeteria"));
        System.out.println("edgeCount=" + graph.edgeCount());

        System.out.println("removeEdge=" + graph.removeEdge("Library", "Gym"));
        System.out.println("edgeCount after remove=" + graph.edgeCount());
    }
}
