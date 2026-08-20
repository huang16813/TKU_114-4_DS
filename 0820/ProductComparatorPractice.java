import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class StoreProduct implements Comparable<StoreProduct> {
    private final String id;
    private final String name;
    private final int price;
    private final int stock;

    StoreProduct(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    int getPrice() {
        return price;
    }

    int getStock() {
        return stock;
    }

    @Override
    public int compareTo(StoreProduct other) {
        return id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return id + " " + name + " price=" + price + " stock=" + stock;
    }
}

public class ProductComparatorPractice {
    public static void main(String[] args) {
        List<StoreProduct> products = new ArrayList<>();
        products.add(new StoreProduct("P103", "Mouse", 300, 20));
        products.add(new StoreProduct("P101", "Keyboard", 300, 15));
        products.add(new StoreProduct("P102", "Monitor", 5000, 8));

        List<StoreProduct> byId = new ArrayList<>(products);
        byId.sort(null);
        System.out.println("by id=" + byId);

        List<StoreProduct> byPrice = new ArrayList<>(products);
        byPrice.sort(Comparator.comparingInt(StoreProduct::getPrice)
                .thenComparing(StoreProduct::getName));
        System.out.println("by price=" + byPrice);

        List<StoreProduct> byStock = new ArrayList<>(products);
        byStock.sort(Comparator.comparingInt(StoreProduct::getStock)
                .reversed()
                .thenComparing(StoreProduct::getId));
        System.out.println("by stock=" + byStock);

        System.out.println("original=" + products);
    }
}
