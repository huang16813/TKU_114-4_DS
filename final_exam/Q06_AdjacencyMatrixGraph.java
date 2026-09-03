import java.util.ArrayList;
import java.util.List;

public class Q06_AdjacencyMatrixGraph {
    private final List<String> vertices;
    private final boolean[][] edges;

    public Q06_AdjacencyMatrixGraph(List<String> vertices) {
        if (vertices == null || vertices.isEmpty()) {
            throw new IllegalArgumentException("vertices");
        }
        this.vertices = List.copyOf(vertices);
        this.edges = new boolean[vertices.size()][vertices.size()];
    }

    private int indexOf(String vertex) {
        return vertex == null ? -1 : vertices.indexOf(vertex);
    }

    public boolean addEdge(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);
        if (a < 0 || b < 0 || a == b) {
            return false;
        }
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
        if (a < 0 || b < 0 || !edges[a][b]) {
            return false;
        }
        edges[a][b] = false;
        edges[b][a] = false;
        return true;
    }

    public boolean hasEdge(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);
        if (a < 0 || b < 0) {
            return false;
        }
        return edges[a][b];
    }

    public int degree(String vertex) {
        int row = indexOf(vertex);
        if (row < 0) {
            return 0;
        }
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
        if (row < 0) {
            return result;
        }
        for (int column = 0; column < vertices.size(); column++) {
            if (edges[row][column]) {
                result.add(vertices.get(column));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q06_AdjacencyMatrixGraph graph = new Q06_AdjacencyMatrixGraph(List.of("A", "B", "C", "D"));

        System.out.println("addEdge=" + graph.addEdge("A", "B"));
        System.out.println("addEdge dup=" + graph.addEdge("A", "B"));
        graph.addEdge("A", "C");
        graph.addEdge("C", "D");
        System.out.println("self-loop rejected=" + graph.addEdge("D", "D"));

        System.out.println("A neighbors=" + graph.neighbors("A"));
        System.out.println("D degree=" + graph.degree("D"));
        System.out.println("hasEdge A-C=" + graph.hasEdge("A", "C"));

        System.out.println("missing vertex addEdge=" + graph.addEdge("A", "X"));
        System.out.println("missing vertex degree=" + graph.degree("X"));
        System.out.println("missing vertex neighbors=" + graph.neighbors("X"));
        System.out.println("missing vertex hasEdge=" + graph.hasEdge("X", "A"));

        System.out.println("removeEdge=" + graph.removeEdge("A", "B"));
        System.out.println("A neighbors after remove=" + graph.neighbors("A"));
    }
}
