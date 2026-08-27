class OneChildNode {
    int value;
    OneChildNode left;
    OneChildNode right;

    OneChildNode(int value) {
        this.value = value;
    }
}

class OneChildBst {
    private OneChildNode root;

    boolean add(int value) {
        if (root == null) {
            root = new OneChildNode(value);
            return true;
        }
        OneChildNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new OneChildNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new OneChildNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        OneChildNode current = root;
        while (current != null) {
            if (value == current.value) return true;
            current = value < current.value ? current.left : current.right;
        }
        return false;
    }

    Integer minimum() {
        if (root == null) return null;
        OneChildNode current = root;
        while (current.left != null) current = current.left;
        return current.value;
    }

    Integer maximum() {
        if (root == null) return null;
        OneChildNode current = root;
        while (current.right != null) current = current.right;
        return current.value;
    }

    int size() {
        return size(root);
    }

    private int size(OneChildNode node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    int height() {
        return height(root);
    }

    private int height(OneChildNode node) {
        return node == null
                ? -1
                : 1 + Math.max(height(node.left), height(node.right));
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(OneChildNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    boolean remove(int value) {
        if (!contains(value)) return false;
        root = remove(root, value);
        return true;
    }

    private OneChildNode remove(OneChildNode node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = remove(node.left, value);
        } else if (value > node.value) {
            node.right = remove(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            OneChildNode successor = minimumNode(node.right);
            node.value = successor.value;
            node.right = remove(node.right, successor.value);
        }
        return node;
    }

    private OneChildNode minimumNode(OneChildNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
}

public class BstDeleteOneChildDemo {
    public static void main(String[] args) {
        OneChildBst tree = new OneChildBst();
        for (int value : new int[]{50, 30, 70, 40}) tree.add(value);
        System.out.println("remove30=" + tree.remove(30));
        tree.inorder();
    }
}
