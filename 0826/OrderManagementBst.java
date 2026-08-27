import java.util.ArrayList;
import java.util.List;

class Order {
    int orderId;
    String customer;
    double amount;
    String status;

    Order(int orderId, String customer, double amount) {
        this.orderId = orderId;
        this.customer = customer;
        this.amount = Math.max(0, amount);
        this.status = "PENDING";
    }

    @Override
    public String toString() {
        return orderId + " " + customer + " amount=" + amount + " status=" + status;
    }
}

class OrderNode {
    Order data;
    OrderNode left;
    OrderNode right;

    OrderNode(Order data) {
        this.data = data;
    }
}

class OrderBst {
    private OrderNode root;

    boolean add(Order order) {
        if (order == null) return false;
        if (root == null) {
            root = new OrderNode(order);
            return true;
        }
        OrderNode current = root;
        while (true) {
            if (order.orderId == current.data.orderId) return false;
            if (order.orderId < current.data.orderId) {
                if (current.left == null) {
                    current.left = new OrderNode(order);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new OrderNode(order);
                    return true;
                }
                current = current.right;
            }
        }
    }

    Order find(int orderId) {
        OrderNode current = root;
        while (current != null) {
            if (orderId == current.data.orderId) return current.data;
            current = orderId < current.data.orderId ? current.left : current.right;
        }
        return null;
    }

    boolean updateStatus(int orderId, String status) {
        Order order = find(orderId);
        if (order == null || status == null) return false;
        order.status = status;
        return true;
    }

    boolean cancel(int orderId) {
        return updateStatus(orderId, "CANCELLED");
    }

    boolean remove(int orderId) {
        Order order = find(orderId);
        if (order == null || !"CANCELLED".equals(order.status)) return false;
        root = remove(root, orderId);
        return true;
    }

    private OrderNode remove(OrderNode node, int orderId) {
        if (node == null) return null;
        if (orderId < node.data.orderId) {
            node.left = remove(node.left, orderId);
        } else if (orderId > node.data.orderId) {
            node.right = remove(node.right, orderId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            OrderNode successor = minimumNode(node.right);
            node.data = successor.data;
            node.right = remove(node.right, successor.data.orderId);
        }
        return node;
    }

    private OrderNode minimumNode(OrderNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    List<Order> range(int lowId, int highId) {
        List<Order> result = new ArrayList<>();
        if (lowId <= highId) range(root, lowId, highId, result);
        return result;
    }

    private void range(OrderNode node, int lowId, int highId, List<Order> result) {
        if (node == null) return;
        if (lowId < node.data.orderId) range(node.left, lowId, highId, result);
        if (lowId <= node.data.orderId && node.data.orderId <= highId) result.add(node.data);
        if (node.data.orderId < highId) range(node.right, lowId, highId, result);
    }

    double totalAmount() {
        return totalAmount(root);
    }

    private double totalAmount(OrderNode node) {
        if (node == null) return 0;
        return node.data.amount + totalAmount(node.left) + totalAmount(node.right);
    }
}

public class OrderManagementBst {
    public static void main(String[] args) {
        OrderBst system = new OrderBst();
        system.add(new Order(3001, "Amy", 1200));
        system.add(new Order(1001, "Ben", -500));
        system.add(new Order(5001, "Cara", 2200));

        System.out.println("find 1001=" + system.find(1001));

        System.out.println("remove pending 3001=" + system.remove(3001));

        System.out.println("cancel 3001=" + system.cancel(3001));
        System.out.println("remove cancelled 3001=" + system.remove(3001));

        System.out.println("range=" + system.range(1001, 5001));
        System.out.println("totalAmount=" + system.totalAmount());
    }
}
