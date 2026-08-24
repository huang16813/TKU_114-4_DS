import java.util.ArrayDeque;
import java.util.Queue;

class LineNode {
    String value;
    LineNode left;
    LineNode right;

    LineNode(String value) {
        this.value = value;
    }
}

public class LevelOrderByLine {
    static void printByLine(LineNode root) {
        if (root == null) {
            System.out.println("empty tree");
            return;
        }

        Queue<LineNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            StringBuilder line = new StringBuilder();

            for (int i = 0; i < levelSize; i++) {
                LineNode current = queue.poll();
                line.append(current.value).append(" ");
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            System.out.println("level " + level + " (count=" + levelSize + "): " + line.toString().trim());
            level++;
        }
    }

    public static void main(String[] args) {
        LineNode root = new LineNode("A");
        root.left = new LineNode("B");
        root.right = new LineNode("C");
        root.left.left = new LineNode("D");
        root.left.right = new LineNode("E");
        root.right.right = new LineNode("F");

        printByLine(root);
        System.out.println();
        printByLine(null);
    }
}
