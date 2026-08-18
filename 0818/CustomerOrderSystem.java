class Customer {
    private String id;
    private String name;

    Customer(String id, String name) {
        this.id = (id == null || id.isBlank()) ? "UNKNOWN" : id;
        this.name = (name == null || name.isBlank()) ? "Unknown" : name;
    }

    String label() {
        return id + " " + name;
    }
}

class OrderItem {
    private String productName;
    private int quantity;
    private int unitPrice;

    OrderItem(String productName, int quantity, int unitPrice) {
        this.productName = (productName == null || productName.isBlank())
                ? "Unknown" : productName;
        this.quantity = Math.max(0, quantity);
        this.unitPrice = Math.max(0, unitPrice);
    }

    int subtotal() {
        return quantity * unitPrice;
    }

    @Override
    public String toString() {
        return productName + " x" + quantity + " @" + unitPrice + " = " + subtotal();
    }
}

class CustomerOrder {
    private String orderId;
    private Customer customer;
    private OrderItem[] items;
    private int itemCount;

    CustomerOrder(String orderId, Customer customer, int capacity) {
        this.orderId = (orderId == null || orderId.isBlank()) ? "UNKNOWN" : orderId;
        this.customer = customer;
        this.items = new OrderItem[Math.max(1, capacity)];
        this.itemCount = 0;
    }

    boolean addItem(OrderItem item) {
        if (item == null || itemCount >= items.length) {
            return false;
        }
        items[itemCount] = item;
        itemCount++;
        return true;
    }

    int totalAmount() {
        int total = 0;
        for (int i = 0; i < itemCount; i++) {
            total += items[i].subtotal();
        }
        return total;
    }

    int remainingSlots() {
        return items.length - itemCount;
    }

    void printSummary() {
        System.out.println(orderId + " | customer=" + customer.label());
        for (int i = 0; i < itemCount; i++) {
            System.out.println("  " + items[i]);
        }
        System.out.println("  total=" + totalAmount() + " remainingSlots=" + remainingSlots());
    }
}

public class CustomerOrderSystem {
    public static void main(String[] args) {
        Customer customer = new Customer("C201", "Cara");
        CustomerOrder order = new CustomerOrder("O3001", customer, 3);

        System.out.println("加入商品 Mouse：" + order.addItem(new OrderItem("Mouse", 2, 300)));
        System.out.println("加入商品 Keyboard：" + order.addItem(new OrderItem("Keyboard", 1, 800)));
        System.out.println("加入商品 Monitor：" + order.addItem(new OrderItem("Monitor", 1, 4500)));
        System.out.println("加入商品 Cable（已滿）：" + order.addItem(new OrderItem("Cable", 3, 100)));

        order.printSummary();
    }
}
