import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q11_BstHashDirectory {
    private static class Node {
        int id;
        Node left;
        Node right;

        Node(int id) {
            this.id = id;
        }
    }

    private Node root;
    private final Map<Integer, String> namesById = new HashMap<>();

    public boolean add(int id, String name) {
        if (id <= 0 || name == null) {
            return false;
        }
        String trimmed = name.trim();
        if (trimmed.isEmpty() || namesById.containsKey(id)) {
            return false;
        }
        root = insert(root, id);
        namesById.put(id, trimmed);
        return true;
    }

    private Node insert(Node node, int id) {
        if (node == null) {
            return new Node(id);
        }
        if (id < node.id) {
            node.left = insert(node.left, id);
        } else if (id > node.id) {
            node.right = insert(node.right, id);
        }
        return node;
    }

    public String findName(int id) {
        return namesById.get(id);
    }

    public boolean remove(int id) {
        if (!namesById.containsKey(id)) {
            return false;
        }
        root = remove(root, id);
        namesById.remove(id);
        return true;
    }

    private Node remove(Node node, int id) {
        if (node == null) {
            return null;
        }
        if (id < node.id) {
            node.left = remove(node.left, id);
        } else if (id > node.id) {
            node.right = remove(node.right, id);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            node.id = successor.id;
            node.right = remove(node.right, successor.id);
        }
        return node;
    }

    public List<Integer> idsBetween(int low, int high) {
        List<Integer> result = new ArrayList<>();
        if (low <= high) {
            collect(root, low, high, result);
        }
        return result;
    }

    private void collect(Node node, int low, int high, List<Integer> result) {
        if (node == null) {
            return;
        }
        if (low < node.id) {
            collect(node.left, low, high, result);
        }
        if (low <= node.id && node.id <= high) {
            result.add(node.id);
        }
        if (node.id < high) {
            collect(node.right, low, high, result);
        }
    }

    public int size() {
        return namesById.size();
    }

    public static void main(String[] args) {
        Q11_BstHashDirectory directory = new Q11_BstHashDirectory();
        System.out.println("add=" + directory.add(300, "Mina"));
        System.out.println("add=" + directory.add(100, "Leo"));
        System.out.println("add=" + directory.add(500, "Nora"));
        System.out.println("add=" + directory.add(200, "Ivy"));
        System.out.println("duplicate=" + directory.add(100, "Other"));
        System.out.println("invalid id=" + directory.add(-1, "Bad"));
        System.out.println("blank name=" + directory.add(400, "   "));

        System.out.println("findName 200=" + directory.findName(200));
        System.out.println("findName missing=" + directory.findName(999));

        System.out.println("idsBetween(150,500)=" + directory.idsBetween(150, 500));
        System.out.println("idsBetween invalid(500,150)=" + directory.idsBetween(500, 150));

        System.out.println("remove=" + directory.remove(300));
        System.out.println("findName 300 after remove=" + directory.findName(300));
        System.out.println("size=" + directory.size());
        System.out.println("idsBetween after remove=" + directory.idsBetween(0, 1000));
    }
}
