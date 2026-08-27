class Order {
    int orderId;
    String customer;
    double amount;
    boolean cancelled;

    Order(int orderId, String customer, double amount) {
        this.orderId = orderId;
        this.customer = customer;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return orderId + " " + customer + " amount=" + amount + " cancelled=" + cancelled;
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

    boolean cancel(int orderId) {
        Order order = find(orderId);
        if (order == null || order.cancelled) return false;
        order.cancelled = true;
        return true;
    }

    boolean updateAmount(int orderId, double newAmount) {
        Order order = find(orderId);
        if (order == null || newAmount < 0) return false;
        order.amount = newAmount;
        return true;
    }

    void printRange(int lowId, int highId) {
        StringBuilder result = new StringBuilder();
        printRange(root, lowId, highId, result);
        System.out.println("order range [" + lowId + "," + highId + "]: " + result.toString().trim());
    }

    private void printRange(OrderNode node, int lowId, int highId, StringBuilder result) {
        if (node == null) return;
        if (node.data.orderId > lowId) {
            printRange(node.left, lowId, highId, result);
        }
        if (node.data.orderId >= lowId && node.data.orderId <= highId) {
            result.append(node.data).append(" | ");
        }
        if (node.data.orderId < highId) {
            printRange(node.right, lowId, highId, result);
        }
    }

    void summary() {
        int[] activeCount = {0};
        int[] cancelledCount = {0};
        double[] activeTotal = {0};
        summary(root, activeCount, cancelledCount, activeTotal);
        System.out.println("active=" + activeCount[0] + " cancelled=" + cancelledCount[0]
                + " activeTotal=" + activeTotal[0]);
    }

    private void summary(OrderNode node, int[] activeCount, int[] cancelledCount, double[] activeTotal) {
        if (node == null) return;
        summary(node.left, activeCount, cancelledCount, activeTotal);
        if (node.data.cancelled) {
            cancelledCount[0]++;
        } else {
            activeCount[0]++;
            activeTotal[0] += node.data.amount;
        }
        summary(node.right, activeCount, cancelledCount, activeTotal);
    }
}

public class OrderBstSystem {
    public static void main(String[] args) {
        OrderBst system = new OrderBst();
        system.add(new Order(3001, "Amy", 1200));
        system.add(new Order(1001, "Ben", 500));
        system.add(new Order(5001, "Cara", 2200));
        system.add(new Order(2001, "Dan", 800));

        System.out.println("find 1001=" + system.find(1001));

        System.out.println("updateAmount 1001=" + system.updateAmount(1001, 650));
        System.out.println("cancel 2001=" + system.cancel(2001));
        System.out.println("cancel missing 9999=" + system.cancel(9999));

        system.printRange(1001, 3001);
        system.summary();
    }
}
