class InventoryProduct {
    int id;
    String name;
    int stock;

    InventoryProduct(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = Math.max(0, stock);
    }

    @Override
    public String toString() {
        return id + " " + name + " stock=" + stock;
    }
}

class InventoryNode {
    InventoryProduct data;
    InventoryNode left;
    InventoryNode right;

    InventoryNode(InventoryProduct data) {
        this.data = data;
    }
}

class InventoryBst {
    private InventoryNode root;

    boolean add(InventoryProduct product) {
        if (product == null) return false;
        if (root == null) {
            root = new InventoryNode(product);
            return true;
        }
        InventoryNode current = root;
        while (true) {
            if (product.id == current.data.id) return false;
            if (product.id < current.data.id) {
                if (current.left == null) {
                    current.left = new InventoryNode(product);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new InventoryNode(product);
                    return true;
                }
                current = current.right;
            }
        }
    }

    InventoryProduct find(int id) {
        InventoryNode current = root;
        while (current != null) {
            if (id == current.data.id) return current.data;
            current = id < current.data.id ? current.left : current.right;
        }
        return null;
    }

    boolean restock(int id, int amount) {
        InventoryProduct product = find(id);
        if (product == null || amount <= 0) return false;
        product.stock += amount;
        return true;
    }

    boolean reduceStock(int id, int amount) {
        InventoryProduct product = find(id);
        if (product == null || amount <= 0 || product.stock < amount) return false;
        product.stock -= amount;
        return true;
    }

    boolean remove(int id) {
        if (find(id) == null) return false;
        root = remove(root, id);
        return true;
    }

    private InventoryNode remove(InventoryNode node, int id) {
        if (node == null) return null;
        if (id < node.data.id) {
            node.left = remove(node.left, id);
        } else if (id > node.data.id) {
            node.right = remove(node.right, id);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            InventoryNode successor = minimumNode(node.right);
            node.data = successor.data;
            node.right = remove(node.right, successor.data.id);
        }
        return node;
    }

    private InventoryNode minimumNode(InventoryNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    void inorderReport() {
        inorderReport(root);
        System.out.println();
    }

    private void inorderReport(InventoryNode node) {
        if (node == null) return;
        inorderReport(node.left);
        System.out.print(node.data + " | ");
        inorderReport(node.right);
    }
}

public class ProductInventoryBst {
    public static void main(String[] args) {
        InventoryBst store = new InventoryBst();
        store.add(new InventoryProduct(300, "Keyboard", 5));
        store.add(new InventoryProduct(100, "Mouse", 8));
        store.add(new InventoryProduct(500, "Monitor", 2));

        store.inorderReport();

        System.out.println("find 100=" + store.find(100));

        System.out.println("restock 500 by 3=" + store.restock(500, 3));
        System.out.println("reduceStock 100 by 10=" + store.reduceStock(100, 10));
        System.out.println("reduceStock 100 by 5=" + store.reduceStock(100, 5));

        store.inorderReport();

        System.out.println("remove 300=" + store.remove(300));
        store.inorderReport();
    }
}
