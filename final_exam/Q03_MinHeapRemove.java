import java.util.ArrayList;
import java.util.List;

public class Q03_MinHeapRemove {
    private final List<Integer> data = new ArrayList<>();

    public Q03_MinHeapRemove(List<Integer> values) {
        if (values != null) {
            for (Integer value : values) {
                if (value != null) {
                    data.add(value);
                }
            }
        }
        for (int i = data.size() / 2 - 1; i >= 0; i--) {
            bubbleDown(i);
        }
    }

    public Integer removeMin() {
        if (data.isEmpty()) {
            return null;
        }
        int result = data.get(0);
        int last = data.remove(data.size() - 1);
        if (!data.isEmpty()) {
            data.set(0, last);
            bubbleDown(0);
        }
        return result;
    }

    public Integer peek() {
        return data.isEmpty() ? null : data.get(0);
    }

    public int size() {
        return data.size();
    }

    public List<Integer> snapshot() {
        return List.copyOf(data);
    }

    private void bubbleDown(int index) {
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = index;
            if (left < data.size() && data.get(left) < data.get(smallest)) {
                smallest = left;
            }
            if (right < data.size() && data.get(right) < data.get(smallest)) {
                smallest = right;
            }
            if (smallest == index) {
                return;
            }
            int temp = data.get(index);
            data.set(index, data.get(smallest));
            data.set(smallest, temp);
            index = smallest;
        }
    }

    public static void main(String[] args) {
        Q03_MinHeapRemove empty = new Q03_MinHeapRemove(List.of());
        System.out.println("empty removeMin=" + empty.removeMin());
        System.out.println("empty peek=" + empty.peek());

        List<Integer> values = new ArrayList<>(List.of(45, 12, 30, 8, 20, 18));
        values.add(2, null);
        Q03_MinHeapRemove heap = new Q03_MinHeapRemove(values);
        System.out.println("size=" + heap.size());
        System.out.println("snapshot=" + heap.snapshot());
        System.out.println("peek after heapify=" + heap.peek());

        StringBuilder order = new StringBuilder();
        while (heap.size() > 0) {
            order.append(heap.removeMin()).append(" ");
        }
        System.out.println("remove order=" + order.toString().trim());

        Q03_MinHeapRemove single = new Q03_MinHeapRemove(List.of(7));
        System.out.println("single removeMin=" + single.removeMin());
        System.out.println("single now empty peek=" + single.peek());
    }
}
