import java.util.Arrays;

public class ArrayMinHeap {
    private int[] data;
    private int size;

    public ArrayMinHeap(int initialCapacity) {
        data = new int[Math.max(1, initialCapacity)];
    }

    public void add(int value) {
        ensureCapacity();
        data[size] = value;
        int index = size;
        size++;

        while (index > 0) {
            int parent = (index - 1) / 2;
            if (data[parent] <= data[index]) break;
            swap(parent, index);
            index = parent;
        }
    }

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("heap is empty");
        }
        return data[0];
    }

    public int remove() {
        if (size == 0) {
            throw new IllegalStateException("heap is empty");
        }
        int result = data[0];
        size--;
        data[0] = data[size];
        data[size] = 0;
        bubbleDown(0);
        return result;
    }

    public int size() {
        return size;
    }

    public int[] snapshot() {
        return Arrays.copyOf(data, size);
    }

    private void bubbleDown(int index) {
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            if (left >= size) return;

            int smaller = left;
            if (right < size && data[right] < data[left]) {
                smaller = right;
            }
            if (data[index] <= data[smaller]) return;
            swap(index, smaller);
            index = smaller;
        }
    }

    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    private void swap(int first, int second) {
        int temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {
        ArrayMinHeap heap = new ArrayMinHeap(4);
        int[] values = {50, 30, 70, 10, 90, 20, 60, 40, 80, 5,
                45, 35, 25, 15, 65, 55, 75, 85, 95, 100};
        for (int value : values) {
            heap.add(value);
        }
        System.out.println("size=" + heap.size());
        System.out.println("peek=" + heap.peek());
        System.out.println("snapshot=" + Arrays.toString(heap.snapshot()));

        StringBuilder order = new StringBuilder();
        while (heap.size() > 0) {
            order.append(heap.remove()).append(" ");
        }
        System.out.println("remove order=" + order.toString().trim());
    }
}
