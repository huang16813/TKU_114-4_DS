import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ServiceRequestSystem {
    record Request(String id, int priority, long order, boolean cancelled) {
        Request withCancelled() {
            return new Request(id, priority, order, true);
        }
    }

    private final Map<String, Request> requestsById = new HashMap<>();
    private final PriorityQueue<Request> queue = new PriorityQueue<>(
            Comparator.comparingInt(Request::priority).reversed()
                    .thenComparingLong(Request::order));
    private long nextOrder = 1;

    public boolean submit(String id, int priority) {
        if (id == null || id.isBlank() || requestsById.containsKey(id)) {
            return false;
        }
        Request request = new Request(id, priority, nextOrder++, false);
        requestsById.put(id, request);
        queue.offer(request);
        return true;
    }

    public boolean cancel(String id) {
        Request request = requestsById.get(id);
        if (request == null || request.cancelled()) {
            return false;
        }
        Request cancelled = request.withCancelled();
        requestsById.put(id, cancelled);
        queue.remove(request);
        return true;
    }

    public Request find(String id) {
        return requestsById.get(id);
    }

    public Request serveNext() {
        while (!queue.isEmpty()) {
            Request request = queue.poll();
            Request current = requestsById.get(request.id());
            if (current != null && !current.cancelled()) {
                requestsById.remove(request.id());
                return current;
            }
        }
        return null;
    }

    public int waitingCount() {
        return queue.size();
    }

    public static void main(String[] args) {
        ServiceRequestSystem system = new ServiceRequestSystem();
        System.out.println("submit=" + system.submit("R1", 2));
        System.out.println("submit=" + system.submit("R2", 5));
        System.out.println("submit dup=" + system.submit("R1", 1));
        System.out.println("submit=" + system.submit("R3", 5));

        System.out.println("cancel R2=" + system.cancel("R2"));
        System.out.println("cancel missing=" + system.cancel("R99"));

        System.out.println("serve=" + system.serveNext());
        System.out.println("serve=" + system.serveNext());
        System.out.println("serve empty=" + system.serveNext());

        System.out.println("waitingCount=" + system.waitingCount());
    }
}
