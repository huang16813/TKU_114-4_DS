import java.util.ArrayList;
import java.util.List;

public class Q12_StudentBstSystem {
    public static class Student {
        private final int id;
        private final String name;
        private int score;

        public Student(int id, String name, int score) {
            if (id <= 0) {
                throw new IllegalArgumentException("id must be greater than 0");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("name must not be null or blank");
            }
            this.id = id;
            this.name = name;
            this.score = clampScore(score);
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        private static int clampScore(int score) {
            return Math.max(0, Math.min(100, score));
        }

        @Override
        public String toString() {
            return id + "|" + name + "|" + score;
        }
    }

    private static class Node {
        Student data;
        Node left;
        Node right;

        Node(Student data) {
            this.data = data;
        }
    }

    private Node root;

    public boolean add(Student student) {
        if (student == null) {
            return false;
        }
        if (root == null) {
            root = new Node(student);
            return true;
        }
        Node current = root;
        while (true) {
            if (student.getId() == current.data.getId()) {
                return false;
            }
            if (student.getId() < current.data.getId()) {
                if (current.left == null) {
                    current.left = new Node(student);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(student);
                    return true;
                }
                current = current.right;
            }
        }
    }

    public Student find(int id) {
        Node current = root;
        while (current != null) {
            if (id == current.data.getId()) {
                return current.data;
            }
            current = id < current.data.getId() ? current.left : current.right;
        }
        return null;
    }

    public boolean updateScore(int id, int score) {
        Student student = find(id);
        if (student == null) {
            return false;
        }
        student.score = Student.clampScore(score);
        return true;
    }

    public boolean remove(int id) {
        if (find(id) == null) {
            return false;
        }
        root = remove(root, id);
        return true;
    }

    private Node remove(Node node, int id) {
        if (node == null) {
            return null;
        }
        if (id < node.data.getId()) {
            node.left = remove(node.left, id);
        } else if (id > node.data.getId()) {
            node.right = remove(node.right, id);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node successor = minimumNode(node.right);
            node.data = successor.data;
            node.right = remove(node.right, successor.data.getId());
        }
        return node;
    }

    private Node minimumNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public List<Student> studentsBetween(int lowId, int highId) {
        List<Student> result = new ArrayList<>();
        if (lowId <= highId) {
            studentsBetween(root, lowId, highId, result);
        }
        return result;
    }

    private void studentsBetween(Node node, int lowId, int highId, List<Student> result) {
        if (node == null) {
            return;
        }
        if (lowId < node.data.getId()) {
            studentsBetween(node.left, lowId, highId, result);
        }
        if (lowId <= node.data.getId() && node.data.getId() <= highId) {
            result.add(node.data);
        }
        if (node.data.getId() < highId) {
            studentsBetween(node.right, lowId, highId, result);
        }
    }

    public List<Student> inorder() {
        List<Student> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<Student> result) {
        if (node == null) {
            return;
        }
        inorder(node.left, result);
        result.add(node.data);
        inorder(node.right, result);
    }
}
