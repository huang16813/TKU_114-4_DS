import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;

class Delivery {
    private final String id;
    private final String destination;
    private boolean completed;

    Delivery(String id, String destination) {
        this.id = id;
        this.destination = destination;
    }

    String getId() {
        return id;
    }

    void complete() {
        completed = true;
    }

    void reopen() {
        completed = false;
    }

    @Override
    public String toString() {
        return id + " -> " + destination + " completed=" + completed;
    }
}

public class DeliveryWorkflowSystem {
    private final Map<String, Delivery> deliveriesById = new LinkedHashMap<>();
    private final Deque<Delivery> waiting = new ArrayDeque<>();
    private final Deque<Delivery> completedHistory = new ArrayDeque<>();

    boolean addDelivery(Delivery delivery) {
        if (deliveriesById.containsKey(delivery.getId())) {
            return false;
        }
        deliveriesById.put(delivery.getId(), delivery);
        waiting.offerLast(delivery);
        return true;
    }

    Delivery completeNext() {
        Delivery delivery = waiting.pollFirst();
        if (delivery == null) {
            return null;
        }
        delivery.complete();
        completedHistory.push(delivery);
        return delivery;
    }

    Delivery undoLastCompletion() {
        Delivery delivery = completedHistory.poll();
        if (delivery == null) {
            return null;
        }
        delivery.reopen();
        waiting.offerFirst(delivery);
        return delivery;
    }

    Delivery findById(String id) {
        return deliveriesById.get(id);
    }

    void printSummary() {
        System.out.println("總件數：" + deliveriesById.size()
                + " 等待：" + waiting.size()
                + " 已完成：" + completedHistory.size());
    }

    public static void main(String[] args) {
        DeliveryWorkflowSystem system = new DeliveryWorkflowSystem();

        System.out.println("新增 D001：" + system.addDelivery(new Delivery("D001", "台北")));
        System.out.println("新增 D002：" + system.addDelivery(new Delivery("D002", "台中")));
        System.out.println("重複新增 D001：" + system.addDelivery(new Delivery("D001", "高雄")));

        system.printSummary();

        System.out.println("完成：" + system.completeNext());
        system.printSummary();

        System.out.println("查詢 D001：" + system.findById("D001"));

        System.out.println("復原：" + system.undoLastCompletion());
        system.printSummary();

        System.out.println("查詢 D001：" + system.findById("D001"));
    }
}
