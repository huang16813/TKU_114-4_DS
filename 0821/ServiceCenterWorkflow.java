import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ServiceTicket {
    private final String id;
    private final String description;
    private boolean completed;

    ServiceTicket(String id, String description) {
        this.id = id;
        this.description = description;
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
        return id + " " + description + " completed=" + completed;
    }
}

public class ServiceCenterWorkflow {
    private final Map<String, ServiceTicket> ticketsById = new HashMap<>();
    private final Deque<ServiceTicket> waiting = new ArrayDeque<>();
    private final Deque<ServiceTicket> completedHistory = new ArrayDeque<>();
    private final Set<String> usedIds = new HashSet<>();

    boolean createTicket(String id, String description) {
        if (usedIds.contains(id)) {
            return false;
        }
        ServiceTicket ticket = new ServiceTicket(id, description);
        usedIds.add(id);
        ticketsById.put(id, ticket);
        waiting.offerLast(ticket);
        return true;
    }

    ServiceTicket processNext() {
        ServiceTicket ticket = waiting.pollFirst();
        if (ticket == null) {
            return null;
        }
        ticket.complete();
        completedHistory.push(ticket);
        return ticket;
    }

    boolean cancelWaiting(String id) {
        ServiceTicket ticket = ticketsById.get(id);
        if (ticket == null) {
            return false;
        }
        boolean removed = waiting.remove(ticket);
        if (removed) {
            ticketsById.remove(id);
            usedIds.remove(id);
        }
        return removed;
    }

    ServiceTicket undoLastCompletion() {
        ServiceTicket ticket = completedHistory.poll();
        if (ticket == null) {
            return null;
        }
        ticket.reopen();
        waiting.offerFirst(ticket);
        return ticket;
    }

    ServiceTicket findById(String id) {
        return ticketsById.get(id);
    }

    void printSummary() {
        System.out.println("總件數：" + ticketsById.size()
                + " 等待：" + waiting.size()
                + " 已完成：" + completedHistory.size());
    }

    public static void main(String[] args) {
        ServiceCenterWorkflow center = new ServiceCenterWorkflow();

        System.out.println("建立 K001：" + center.createTicket("K001", "螢幕故障"));
        System.out.println("建立 K002：" + center.createTicket("K002", "電池膨脹"));
        System.out.println("重複建立 K001：" + center.createTicket("K001", "重複"));
        center.printSummary();

        System.out.println("處理下一件：" + center.processNext());
        center.printSummary();

        System.out.println("取消不存在 K999：" + center.cancelWaiting("K999"));
        System.out.println("建立 K003：" + center.createTicket("K003", "無法開機"));
        System.out.println("取消等待中 K003：" + center.cancelWaiting("K003"));
        System.out.println("查詢 K003：" + center.findById("K003"));
        center.printSummary();

        System.out.println("處理下一件：" + center.processNext());
        center.printSummary();

        System.out.println("復原：" + center.undoLastCompletion());
        center.printSummary();
        System.out.println("再次復原：" + center.undoLastCompletion());
        center.printSummary();

        ServiceCenterWorkflow emptyCenter = new ServiceCenterWorkflow();
        ServiceTicket result = emptyCenter.processNext();
        System.out.println("空佇列處理：" + (result == null ? "EMPTY" : result));
    }
}
