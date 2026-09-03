import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q07_AdjacencyListGraph {
    private final Map<String, Set<String>> outgoingEdges = new LinkedHashMap<>();

    public boolean addVertex(String vertex) {
        if (vertex == null || vertex.isBlank()) {
            return false;
        }
        return outgoingEdges.putIfAbsent(vertex, new LinkedHashSet<>()) == null;
    }

    public boolean addEdge(String from, String to) {
        if (!outgoingEdges.containsKey(from) || !outgoingEdges.containsKey(to) || from.equals(to)) {
            return false;
        }
        return outgoingEdges.get(from).add(to);
    }

    public boolean removeEdge(String from, String to) {
        if (!outgoingEdges.containsKey(from)) {
            return false;
        }
        return outgoingEdges.get(from).remove(to);
    }

    public List<String> outgoing(String vertex) {
        Set<String> edges = outgoingEdges.get(vertex);
        return edges == null ? List.of() : new ArrayList<>(edges);
    }

    public int inDegree(String vertex) {
        if (!outgoingEdges.containsKey(vertex)) {
            return 0;
        }
        int count = 0;
        for (Set<String> edges : outgoingEdges.values()) {
            if (edges.contains(vertex)) {
                count++;
            }
        }
        return count;
    }

    public int edgeCount() {
        int total = 0;
        for (Set<String> edges : outgoingEdges.values()) {
            total += edges.size();
        }
        return total;
    }

    public static void main(String[] args) {
        Q07_AdjacencyListGraph graph = new Q07_AdjacencyListGraph();
        for (String vertex : List.of("A", "B", "C")) {
            graph.addVertex(vertex);
        }

        System.out.println("addEdge=" + graph.addEdge("A", "B"));
        System.out.println("addEdge dup=" + graph.addEdge("A", "B"));
        graph.addEdge("A", "C");
        System.out.println("self-loop rejected=" + graph.addEdge("C", "C"));

        System.out.println("A outgoing=" + graph.outgoing("A"));
        System.out.println("B inDegree=" + graph.inDegree("B"));
        System.out.println("edgeCount=" + graph.edgeCount());

        System.out.println("missing vertex addEdge=" + graph.addEdge("A", "X"));
        System.out.println("missing vertex outgoing=" + graph.outgoing("X"));
        System.out.println("missing vertex inDegree=" + graph.inDegree("X"));

        System.out.println("removeEdge=" + graph.removeEdge("A", "B"));
        System.out.println("A outgoing after remove=" + graph.outgoing("A"));
    }
}
