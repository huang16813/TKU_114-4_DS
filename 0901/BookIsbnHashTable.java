import java.util.ArrayList;
import java.util.List;

public class BookIsbnHashTable {
    private record Book(String isbn, String title) {}

    private final List<List<Book>> buckets;
    private int size;

    public BookIsbnHashTable(int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("bucketCount");
        }
        buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private int index(String isbn) {
        if (isbn == null) {
            throw new IllegalArgumentException("isbn");
        }
        return Math.floorMod(isbn.hashCode(), buckets.size());
    }

    public boolean add(String isbn, String title) {
        List<Book> chain = buckets.get(index(isbn));
        for (Book book : chain) {
            if (book.isbn().equals(isbn)) {
                return false;
            }
        }
        chain.add(new Book(isbn, title));
        size++;
        return true;
    }

    public boolean update(String isbn, String title) {
        List<Book> chain = buckets.get(index(isbn));
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).isbn().equals(isbn)) {
                chain.set(i, new Book(isbn, title));
                return true;
            }
        }
        return false;
    }

    public String find(String isbn) {
        for (Book book : buckets.get(index(isbn))) {
            if (book.isbn().equals(isbn)) {
                return book.title();
            }
        }
        return null;
    }

    public boolean remove(String isbn) {
        List<Book> chain = buckets.get(index(isbn));
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).isbn().equals(isbn)) {
                chain.remove(i);
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public double loadFactor() {
        return (double) size / buckets.size();
    }

    public void bucketReport() {
        for (int i = 0; i < buckets.size(); i++) {
            System.out.println(i + " -> " + buckets.get(i));
        }
    }

    public static void main(String[] args) {
        BookIsbnHashTable table = new BookIsbnHashTable(5);
        System.out.println("add=" + table.add("ISBN001", "Data Structures"));
        System.out.println("add=" + table.add("ISBN002", "Algorithms"));
        System.out.println("duplicate add=" + table.add("ISBN001", "Duplicate"));

        System.out.println("update=" + table.update("ISBN001", "Data Structures 2nd Ed"));
        System.out.println("find=" + table.find("ISBN001"));

        System.out.println("size=" + table.size());
        System.out.printf("load=%.2f%n", table.loadFactor());

        System.out.println("remove=" + table.remove("ISBN002"));
        System.out.println("remove missing=" + table.remove("ISBN999"));

        table.bucketReport();
    }
}
