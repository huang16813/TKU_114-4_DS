import java.util.Comparator;
import java.util.PriorityQueue;

public class SupportTicketQueue {
    record Ticket(String id, int severity, long createdOrder) {
        Ticket {
            if (id == null || id.isBlank()) throw new IllegalArgumentException("id");
        }
    }

    public static void main(String[] args) {
        Comparator<Ticket> order = Comparator
                .comparingInt(Ticket::severity).reversed()
                .thenComparingLong(Ticket::createdOrder);

        PriorityQueue<Ticket> tickets = new PriorityQueue<>(order);
        tickets.offer(new Ticket("T1", 2, 1));
        tickets.offer(new Ticket("T2", 5, 2));
        tickets.offer(new Ticket("T3", 5, 3));
        tickets.offer(new Ticket("T4", 3, 4));

        while (!tickets.isEmpty()) {
            Ticket ticket = tickets.poll();
            System.out.println(ticket.id() + "|" + ticket.severity()
                    + "|" + ticket.createdOrder());
        }
    }
}
