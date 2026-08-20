import java.util.ArrayList;
import java.util.List;

class Repository<T> {
    private final List<T> items = new ArrayList<>();

    boolean add(T item) {
        if (item == null) {
            return false;
        }
        return items.add(item);
    }

    T get(int index) {
        if (index < 0 || index >= items.size()) {
            return null;
        }
        return items.get(index);
    }

    boolean remove(T item) {
        return items.remove(item);
    }

    int size() {
        return items.size();
    }

    @Override
    public String toString() {
        return items.toString();
    }
}

class Product {
    private final String id;
    private final String name;

    Product(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}

public class GenericRepositorySystem {
    public static void main(String[] args) {
        Repository<String> names = new Repository<>();
        names.add("Amy");
        names.add("Ben");
        System.out.println("names=" + names + " size=" + names.size());
        System.out.println("get(0)=" + names.get(0));
        names.remove("Amy");
        System.out.println("after remove=" + names);

        Repository<Product> products = new Repository<>();
        products.add(new Product("P101", "Keyboard"));
        products.add(new Product("P102", "Mouse"));
        System.out.println("products=" + products + " size=" + products.size());
    }
}
