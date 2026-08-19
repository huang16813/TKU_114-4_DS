class OrderCustomer {
    private String id;
    private String name;

    OrderCustomer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    String label() {
        return id + " " + name;
    }
}

class DemoOrder {
    private String orderId;
    private OrderCustomer customer;
    private int total;

    DemoOrder(String orderId, OrderCustomer customer, int total) {
        this.orderId = orderId;
        this.customer = customer;
        this.total = Math.max(0, total);
    }

    String summary() {
        return orderId + " | " + customer.label() + " | $" + total;
    }
}

public class OrderCompositionDemo {
    public static void main(String[] args) {
        OrderCustomer customer = new OrderCustomer("C101", "Amy");
        DemoOrder order = new DemoOrder("O9001", customer, 2500);

        System.out.println(order.summary());
    }
}
