import java.util.List;

public class HeapPropertyValidator {
    static boolean isMinHeap(List<Integer> heap) {
        if (heap == null) {
            return false;
        }
        for (int parent = 0; parent < heap.size(); parent++) {
            int left = parent * 2 + 1;
            int right = parent * 2 + 2;
            if (left < heap.size() && heap.get(parent) > heap.get(left)) {
                return false;
            }
            if (right < heap.size() && heap.get(parent) > heap.get(right)) {
                return false;
            }
        }
        return true;
    }

    static boolean isMaxHeap(List<Integer> heap) {
        if (heap == null) {
            return false;
        }
        for (int parent = 0; parent < heap.size(); parent++) {
            int left = parent * 2 + 1;
            int right = parent * 2 + 2;
            if (left < heap.size() && heap.get(parent) < heap.get(left)) {
                return false;
            }
            if (right < heap.size() && heap.get(parent) < heap.get(right)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("null minHeap=" + isMinHeap(null));
        System.out.println("empty minHeap=" + isMinHeap(List.of()));
        System.out.println("single minHeap=" + isMinHeap(List.of(5)));
        System.out.println("valid minHeap=" + isMinHeap(List.of(8, 12, 18, 45, 20, 30)));
        System.out.println("invalid minHeap=" + isMinHeap(List.of(10, 5, 20)));

        System.out.println("null maxHeap=" + isMaxHeap(null));
        System.out.println("empty maxHeap=" + isMaxHeap(List.of()));
        System.out.println("single maxHeap=" + isMaxHeap(List.of(5)));
        System.out.println("valid maxHeap=" + isMaxHeap(List.of(50, 40, 50, 25, 30, 10)));
        System.out.println("invalid maxHeap=" + isMaxHeap(List.of(10, 20, 5)));
    }
}
