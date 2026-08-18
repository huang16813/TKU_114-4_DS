class Book {
    private String title;
    private String isbn;
    private int price;
    private int stock;

    Book(String title, String isbn, int price, int stock) {
        this.title = (title == null || title.isBlank()) ? "Unknown" : title;
        this.isbn = (isbn == null || isbn.isBlank()) ? "Unknown" : isbn;
        this.price = Math.max(0, price);
        this.stock = Math.max(0, stock);
    }

    int inventoryValue() {
        return price * stock;
    }

    int getPrice() {
        return price;
    }

    int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return isbn + " " + title + " price=" + price + " stock=" + stock;
    }
}

public class BookArrayReport {
    public static void main(String[] args) {
        Book[] books = {
            new Book("Java Basics", "B001", 350, 10),
            new Book("Data Structures", "B002", 480, 3),
            new Book("Algorithms", "B003", 520, 1),
            new Book("Design Patterns", "B004", 600, 5),
            new Book("Operating Systems", "B005", 450, 2)
        };

        System.out.println("所有書籍：");
        int totalValue = 0;
        Book mostExpensive = books[0];
        for (Book book : books) {
            System.out.println(book);
            totalValue += book.inventoryValue();
            if (book.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = book;
            }
        }

        System.out.println("庫存總價值：" + totalValue);
        System.out.println("價格最高的書：" + mostExpensive);

        System.out.println("庫存小於或等於 3 的書：");
        for (Book book : books) {
            if (book.getStock() <= 3) {
                System.out.println(book);
            }
        }
    }
}
