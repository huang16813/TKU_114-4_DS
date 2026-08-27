class Student {
    String studentId;
    String name;

    Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    @Override
    public String toString() {
        return studentId + " " + name;
    }
}

class StudentNode {
    Student data;
    StudentNode left;
    StudentNode right;

    StudentNode(Student data) {
        this.data = data;
    }
}

class StudentBst {
    private StudentNode root;

    boolean add(Student student) {
        if (student == null) return false;
        if (root == null) {
            root = new StudentNode(student);
            return true;
        }
        StudentNode current = root;
        while (true) {
            int compare = student.studentId.compareTo(current.data.studentId);
            if (compare == 0) return false;
            if (compare < 0) {
                if (current.left == null) {
                    current.left = new StudentNode(student);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new StudentNode(student);
                    return true;
                }
                current = current.right;
            }
        }
    }

    Student find(String studentId) {
        StudentNode current = root;
        while (current != null) {
            int compare = studentId.compareTo(current.data.studentId);
            if (compare == 0) return current.data;
            current = compare < 0 ? current.left : current.right;
        }
        return null;
    }

    boolean remove(String studentId) {
        if (find(studentId) == null) return false;
        root = remove(root, studentId);
        return true;
    }

    private StudentNode remove(StudentNode node, String studentId) {
        if (node == null) return null;
        int compare = studentId.compareTo(node.data.studentId);
        if (compare < 0) {
            node.left = remove(node.left, studentId);
        } else if (compare > 0) {
            node.right = remove(node.right, studentId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            StudentNode successor = minimumNode(node.right);
            node.data = successor.data;
            node.right = remove(node.right, successor.data.studentId);
        }
        return node;
    }

    private StudentNode minimumNode(StudentNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(StudentNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " | ");
        inorder(node.right);
    }
}

public class StudentBstIndex {
    public static void main(String[] args) {
        StudentBst tree = new StudentBst();
        System.out.println(tree.add(new Student("S300", "Amy")));
        System.out.println(tree.add(new Student("S100", "Ben")));
        System.out.println(tree.add(new Student("S500", "Cara")));
        System.out.println("duplicate=" + tree.add(new Student("S100", "Duplicate")));

        tree.inorder();
        System.out.println("find S100=" + tree.find("S100"));

        System.out.println("remove S300=" + tree.remove("S300"));
        tree.inorder();

        System.out.println("remove missing S999=" + tree.remove("S999"));
    }
}
