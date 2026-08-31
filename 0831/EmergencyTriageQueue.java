import java.util.Comparator;
import java.util.PriorityQueue;

public class EmergencyTriageQueue {
    record Patient(String id, int severity, long registeredOrder) {
        Patient {
            if (id == null || id.isBlank()) {
                throw new IllegalArgumentException("id");
            }
        }

        @Override
        public String toString() {
            return id + "|severity=" + severity + "|order=" + registeredOrder;
        }
    }

    private final PriorityQueue<Patient> queue = new PriorityQueue<>(
            Comparator.comparingInt(Patient::severity).reversed()
                    .thenComparingLong(Patient::registeredOrder));
    private long nextOrder = 1;

    public void register(String id, int severity) {
        queue.offer(new Patient(id, severity, nextOrder++));
    }

    public Patient peekNext() {
        return queue.peek();
    }

    public Patient serveNext() {
        return queue.poll();
    }

    public int waitingCount() {
        return queue.size();
    }

    public static void main(String[] args) {
        EmergencyTriageQueue triage = new EmergencyTriageQueue();
        triage.register("P1", 2);
        triage.register("P2", 5);
        triage.register("P3", 5);
        triage.register("P4", 3);

        System.out.println("waiting=" + triage.waitingCount());
        System.out.println("next=" + triage.peekNext());

        while (triage.waitingCount() > 0) {
            System.out.println("serve=" + triage.serveNext()
                    + " remaining=" + triage.waitingCount());
        }

        Patient empty = triage.serveNext();
        System.out.println("serve empty=" + (empty == null ? "EMPTY" : empty));
    }
}
