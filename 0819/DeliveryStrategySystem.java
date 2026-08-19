interface DeliveryMethod {
    int calculateFee(int weight, int distance);
    String estimate();
}

class StandardDelivery implements DeliveryMethod {
    @Override
    public int calculateFee(int weight, int distance) {
        return 60 + weight * 5;
    }

    @Override
    public String estimate() {
        return "3-5 天送達";
    }
}

class ExpressDelivery implements DeliveryMethod {
    @Override
    public int calculateFee(int weight, int distance) {
        return 150 + weight * 10 + distance * 2;
    }

    @Override
    public String estimate() {
        return "1 天送達";
    }
}

class CashOnDeliveryMethod implements DeliveryMethod {
    @Override
    public int calculateFee(int weight, int distance) {
        return 60 + weight * 5 + 30;
    }

    @Override
    public String estimate() {
        return "3-5 天送達，貨到付款";
    }
}

class OrderService {
    private DeliveryMethod deliveryMethod;

    OrderService(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    void printShippingInfo(int weight, int distance) {
        int fee = deliveryMethod.calculateFee(weight, distance);
        System.out.println("運費：" + fee + "，" + deliveryMethod.estimate());
    }
}

public class DeliveryStrategySystem {
    public static void main(String[] args) {
        OrderService standard = new OrderService(new StandardDelivery());
        OrderService express = new OrderService(new ExpressDelivery());
        OrderService cod = new OrderService(new CashOnDeliveryMethod());

        standard.printShippingInfo(3, 10);
        express.printShippingInfo(3, 10);
        cod.printShippingInfo(3, 10);
    }
}
