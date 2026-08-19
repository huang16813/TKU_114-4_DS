class ThresholdDiscountPricing implements PricingPolicy {
    @Override
    public int finalPrice(int originalPrice) {
        int price = Math.max(0, originalPrice);
        return price >= 2000 ? price - 300 : price;
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public boolean send(String receiver, String message) {
        if (receiver == null || receiver.isBlank()) {
            return false;
        }
        System.out.println("SMS " + receiver + " -> " + message);
        return true;
    }
}

class CheckoutResult {
    private final String orderId;
    private final int originalPrice;
    private final int finalPrice;
    private final boolean notified;

    CheckoutResult(String orderId, int originalPrice, int finalPrice, boolean notified) {
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.notified = notified;
    }

    @Override
    public String toString() {
        return orderId + " original=" + originalPrice
                + " final=" + finalPrice + " notified=" + notified;
    }
}

class FlexibleCheckoutService {
    private final PricingPolicy pricing;
    private final NotificationChannel channel;

    FlexibleCheckoutService(PricingPolicy pricing, NotificationChannel channel) {
        this.pricing = pricing;
        this.channel = channel;
    }

    CheckoutResult checkout(String orderId, int originalPrice, String receiver) {
        int finalPrice = pricing.finalPrice(originalPrice);
        boolean notified = channel.send(receiver,
                "order=" + orderId + ", amount=" + finalPrice);
        return new CheckoutResult(orderId, originalPrice, finalPrice, notified);
    }
}

public class FlexibleCheckoutSystem {
    public static void main(String[] args) {
        FlexibleCheckoutService vipEmail = new FlexibleCheckoutService(
                new VipPricing(), new EmailChannel());
        FlexibleCheckoutService standardConsole = new FlexibleCheckoutService(
                new StandardPricing(), new ConsoleChannel());
        FlexibleCheckoutService thresholdSms = new FlexibleCheckoutService(
                new ThresholdDiscountPricing(), new SmsChannel());

        System.out.println(vipEmail.checkout("O200", 2000, "amy@example.com"));
        System.out.println(standardConsole.checkout("O201", 800, "counter"));
        System.out.println(thresholdSms.checkout("O202", 2500, "0912345678"));
    }
}
