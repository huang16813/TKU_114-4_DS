import java.util.ArrayDeque;
import java.util.Deque;

class Customer {
    private final String id;
    private final String name;

    Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}

public class CounterWaitingQueue {
    private final Deque<Customer> waiting = new ArrayDeque<>();

    void add(Customer customer) {
        waiting.offerLast(customer);
    }

    Customer peekNext() {
        return waiting.peekFirst();
    }

    Customer serveNext() {
        return waiting.pollFirst();
    }

    int waitingCount() {
        return waiting.size();
    }

    public static void main(String[] args) {
        CounterWaitingQueue counter = new CounterWaitingQueue();

        counter.add(new Customer("C001", "Amy"));
        counter.add(new Customer("C002", "Ben"));
        counter.add(new Customer("C003", "Cara"));

        System.out.println("等待人數：" + counter.waitingCount());
        System.out.println("下一位：" + counter.peekNext());

        System.out.println("服務：" + counter.serveNext());
        System.out.println("服務：" + counter.serveNext());
        System.out.println("等待人數：" + counter.waitingCount());

        System.out.println("服務：" + counter.serveNext());
        Customer empty = counter.serveNext();
        System.out.println("空佇列服務：" + (empty == null ? "EMPTY" : empty));
    }
}
