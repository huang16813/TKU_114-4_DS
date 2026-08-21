import java.util.Arrays;

class CircularQueue<T> {
    private final Object[] data;
    private int front;
    private int rear;
    private int size;

    CircularQueue(int capacity) {
        data = new Object[Math.max(1, capacity)];
    }

    boolean enqueue(T value) {
        if (isFull()) {
            return false;
        }
        data[rear] = value;
        rear = (rear + 1) % data.length;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T value = (T) data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return value;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == data.length;
    }

    void printState() {
        System.out.println(Arrays.toString(data)
                + " front=" + front + " rear=" + rear + " size=" + size);
    }

    void printRemaining() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % data.length;
            result.append(data[index]);
            if (i < size - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        System.out.println("剩餘（FIFO 順序）：" + result);
    }
}

public class CircularQueuePractice {
    public static void main(String[] args) {
        CircularQueue<String> queue = new CircularQueue<>(4);

        System.out.println("enqueue A=" + queue.enqueue("A"));
        queue.printState();
        System.out.println("enqueue B=" + queue.enqueue("B"));
        queue.printState();
        System.out.println("enqueue C=" + queue.enqueue("C"));
        queue.printState();

        System.out.println("dequeue=" + queue.dequeue());
        queue.printState();
        System.out.println("dequeue=" + queue.dequeue());
        queue.printState();

        System.out.println("enqueue D=" + queue.enqueue("D"));
        queue.printState();
        System.out.println("enqueue E=" + queue.enqueue("E"));
        queue.printState();
        System.out.println("enqueue F=" + queue.enqueue("F"));
        queue.printState();

        System.out.println("dequeue=" + queue.dequeue());
        queue.printState();

        System.out.println("enqueue G=" + queue.enqueue("G"));
        queue.printState();

        queue.printRemaining();
    }
}
