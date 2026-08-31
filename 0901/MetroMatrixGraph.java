import java.util.ArrayList;
import java.util.List;

public class MetroMatrixGraph {
    private final List<String> stations;
    private final boolean[][] edges;

    public MetroMatrixGraph(List<String> stations) {
        if (stations == null || stations.isEmpty()) {
            throw new IllegalArgumentException("stations");
        }
        this.stations = List.copyOf(stations);
        this.edges = new boolean[stations.size()][stations.size()];
    }

    private int indexOf(String station) {
        int index = stations.indexOf(station);
        if (index < 0) {
            throw new IllegalArgumentException("unknown station: " + station);
        }
        return index;
    }

    public void connect(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);
        edges[a][b] = true;
        edges[b][a] = true;
    }

    public List<String> adjacentStations(String station) {
        int row = indexOf(station);
        List<String> result = new ArrayList<>();
        for (int column = 0; column < stations.size(); column++) {
            if (edges[row][column]) {
                result.add(stations.get(column));
            }
        }
        return result;
    }

    public int degree(String station) {
        return adjacentStations(station).size();
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

    public void matrixReport() {
        StringBuilder header = new StringBuilder("        ");
        for (String station : stations) {
            header.append(String.format("%-10s", station));
        }
        System.out.println(header);

        for (int row = 0; row < stations.size(); row++) {
            StringBuilder line = new StringBuilder(String.format("%-8s", stations.get(row)));
            for (int column = 0; column < stations.size(); column++) {
                line.append(String.format("%-10s", edges[row][column] ? "1" : "0"));
            }
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        MetroMatrixGraph metro = new MetroMatrixGraph(List.of("Central", "North", "East", "South"));
        metro.connect("Central", "North");
        metro.connect("Central", "East");
        metro.connect("East", "South");

        System.out.println("Central adjacent=" + metro.adjacentStations("Central"));
        System.out.println("Central degree=" + metro.degree("Central"));
        System.out.println("edgeCount=" + metro.edgeCount());
        metro.matrixReport();
    }
}
