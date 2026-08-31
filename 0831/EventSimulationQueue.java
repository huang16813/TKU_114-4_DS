import java.util.Comparator;
import java.util.PriorityQueue;

public class EventSimulationQueue {
    record Event(int id, long time, String type, long sequence) {
        Event {
            if (type == null || type.isBlank()) {
                throw new IllegalArgumentException("type");
            }
        }
    }

    private final PriorityQueue<Event> queue = new PriorityQueue<>(
            Comparator.comparingLong(Event::time).thenComparingLong(Event::sequence));
    private long nextSequence = 1;
    private int nextId = 1;

    public int schedule(long time, String type) {
        int id = nextId++;
        queue.offer(new Event(id, time, type, nextSequence++));
        return id;
    }

    public boolean cancel(int id) {
        return queue.removeIf(event -> event.id() == id);
    }

    public void run() {
        while (!queue.isEmpty()) {
            Event event = queue.poll();
            System.out.println("time=" + event.time() + " type=" + event.type()
                    + " id=" + event.id());
        }
    }

    public static void main(String[] args) {
        EventSimulationQueue simulator = new EventSimulationQueue();
        simulator.schedule(10, "Arrive");
        int toCancel = simulator.schedule(5, "Setup");
        simulator.schedule(10, "Depart");
        simulator.schedule(1, "Init");

        System.out.println("cancel=" + simulator.cancel(toCancel));
        System.out.println("cancel missing=" + simulator.cancel(999));

        simulator.run();
    }
}
