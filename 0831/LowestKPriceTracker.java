import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LowestKPriceTracker {
    static List<Integer> lowestK(List<Integer> prices, int k) {
        if (prices == null || k <= 0) {
            return List.of();
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (Integer price : prices) {
            if (price == null || price < 0) {
                continue;
            }
            maxHeap.offer(price);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        List<Integer> result = new ArrayList<>(maxHeap);
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> prices = new ArrayList<>(List.of(70, 30, 90, 20, 60));
        prices.add(1, null);
        prices.add(3, -5);

        System.out.println("lowest3=" + lowestK(prices, 3));
        System.out.println("lowest0=" + lowestK(prices, 0));
        System.out.println("null=" + lowestK(null, 3));
    }
}
