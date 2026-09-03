import java.util.ArrayList;
import java.util.List;

public class Q02_MinHeapInsert {
    private final List<Integer> data = new ArrayList<>();

    public void add(int value) {
        data.add(value);
        int index = data.size() - 1;
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (data.get(parent) <= data.get(index)) {
                break;
            }
            swap(parent, index);
            index = parent;
        }
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

    public boolean isValidMinHeap() {
        for (int parent = 0; parent < data.size(); parent++) {
            int left = parent * 2 + 1;
            int right = parent * 2 + 2;
            if (left < data.size() && data.get(parent) > data.get(left)) {
                return false;
            }
            if (right < data.size() && data.get(parent) > data.get(right)) {
                return false;
            }
        }
        return true;
    }

    private void swap(int first, int second) {
        int temp = data.get(first);
        data.set(first, data.get(second));
        data.set(second, temp);
    }

    public static void main(String[] args) {
        Q02_MinHeapInsert heap = new Q02_MinHeapInsert();
        System.out.println("empty peek=" + heap.peek());

        for (int value : new int[]{30, 10, 20, 10, 50}) {
            heap.add(value);
        }
        System.out.println("size=" + heap.size());
        System.out.println("peek=" + heap.peek());
        System.out.println("snapshot=" + heap.snapshot());
        System.out.println("isValidMinHeap=" + heap.isValidMinHeap());
    }
}
