import java.util.ArrayList;
import java.util.List;

class Book {
    String isbn;
    String title;
    String author;
    boolean available;

    Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    @Override
    public String toString() {
        return isbn + " " + title + " by " + author + " available=" + available;
    }
}

class BookNode {
    Book data;
    BookNode left;
    BookNode right;

    BookNode(Book data) {
        this.data = data;
    }
}

class BookBst {
    private BookNode root;

    boolean add(Book book) {
        if (book == null) return false;
        if (root == null) {
            root = new BookNode(book);
            return true;
        }
        BookNode current = root;
        while (true) {
            int compare = book.isbn.compareTo(current.data.isbn);
            if (compare == 0) return false;
            if (compare < 0) {
                if (current.left == null) {
                    current.left = new BookNode(book);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new BookNode(book);
                    return true;
                }
                current = current.right;
            }
        }
    }

    Book find(String isbn) {
        BookNode current = root;
        while (current != null) {
            int compare = isbn.compareTo(current.data.isbn);
            if (compare == 0) return current.data;
            current = compare < 0 ? current.left : current.right;
        }
        return null;
    }

    boolean borrow(String isbn) {
        Book book = find(isbn);
        if (book == null || !book.available) return false;
        book.available = false;
        return true;
    }

    boolean returnBook(String isbn) {
        Book book = find(isbn);
        if (book == null || book.available) return false;
        book.available = true;
        return true;
    }

    boolean remove(String isbn) {
        Book book = find(isbn);
        if (book == null || !book.available) return false;
        root = remove(root, isbn);
        return true;
    }

    private BookNode remove(BookNode node, String isbn) {
        if (node == null) return null;
        int compare = isbn.compareTo(node.data.isbn);
        if (compare < 0) {
            node.left = remove(node.left, isbn);
        } else if (compare > 0) {
            node.right = remove(node.right, isbn);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            BookNode successor = minimumNode(node.right);
            node.data = successor.data;
            node.right = remove(node.right, successor.data.isbn);
        }
        return node;
    }

    private BookNode minimumNode(BookNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    List<Book> range(String low, String high) {
        List<Book> result = new ArrayList<>();
        if (low.compareTo(high) <= 0) range(root, low, high, result);
        return result;
    }

    private void range(BookNode node, String low, String high, List<Book> result) {
        if (node == null) return;
        if (low.compareTo(node.data.isbn) < 0) range(node.left, low, high, result);
        if (low.compareTo(node.data.isbn) <= 0 && node.data.isbn.compareTo(high) <= 0) {
            result.add(node.data);
        }
        if (node.data.isbn.compareTo(high) < 0) range(node.right, low, high, result);
    }

    List<Book> inorder() {
        List<Book> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(BookNode node, List<Book> result) {
        if (node == null) return;
        inorder(node.left, result);
        result.add(node.data);
        inorder(node.right, result);
    }
}

public class LibraryBookBst {
    public static void main(String[] args) {
        BookBst library = new BookBst();
        library.add(new Book("ISBN300", "Data Structures", "Author C"));
        library.add(new Book("ISBN100", "Algorithms", "Author A"));
        library.add(new Book("ISBN500", "Operating Systems", "Author E"));

        System.out.println("borrow ISBN100=" + library.borrow("ISBN100"));
        System.out.println("borrow again ISBN100=" + library.borrow("ISBN100"));

        System.out.println("remove borrowed ISBN100=" + library.remove("ISBN100"));

        System.out.println("returnBook ISBN100=" + library.returnBook("ISBN100"));
        System.out.println("remove after return ISBN100=" + library.remove("ISBN100"));

        System.out.println("range=" + library.range("ISBN200", "ISBN500"));
        System.out.println("inorder=" + library.inorder());
    }
}
