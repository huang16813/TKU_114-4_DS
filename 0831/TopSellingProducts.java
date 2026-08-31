import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopSellingProducts {
    record Product(String id, int sales) {}

    static List<Product> topK(List<Product> products, int k) {
        if (products == null || k <= 0) {
            return List.of();
        }

        Map<String, Integer> merged = new LinkedHashMap<>();
        for (Product product : products) {
            if (product == null || product.id() == null) {
                continue;
            }
            merged.merge(product.id(), product.sales(), Integer::sum);
        }

        PriorityQueue<Product> minHeap = new PriorityQueue<>(Comparator.comparingInt(Product::sales));

        for (Map.Entry<String, Integer> entry : merged.entrySet()) {
            minHeap.offer(new Product(entry.getKey(), entry.getValue()));
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<Product> result = new ArrayList<>(minHeap);
        result.sort(Comparator.comparingInt(Product::sales).reversed()
                .thenComparing(Product::id));
        return result;
    }

    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("P1", 50),
                new Product("P2", 80),
                new Product("P3", 30),
                new Product("P1", 20),
                new Product("P4", 80),
                new Product("P5", 10)
        );

        System.out.println("top3=" + topK(products, 3));
        System.out.println("top0=" + topK(products, 0));
        System.out.println("null=" + topK(null, 3));
    }
}
