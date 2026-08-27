class ScoreRecord {
    int score;
    String studentId;

    ScoreRecord(int score, String studentId) {
        this.score = score;
        this.studentId = studentId;
    }

    int compareKey(ScoreRecord other) {
        if (this.score != other.score) {
            return Integer.compare(this.score, other.score);
        }
        return this.studentId.compareTo(other.studentId);
    }

    @Override
    public String toString() {
        return studentId + ":" + score;
    }
}

class ScoreNode {
    ScoreRecord data;
    ScoreNode left;
    ScoreNode right;

    ScoreNode(ScoreRecord data) {
        this.data = data;
    }
}

class ScoreBst {
    private ScoreNode root;

    boolean add(ScoreRecord record) {
        if (record == null) return false;
        if (root == null) {
            root = new ScoreNode(record);
            return true;
        }
        ScoreNode current = root;
        while (true) {
            int compare = record.compareKey(current.data);
            if (compare == 0) return false;
            if (compare < 0) {
                if (current.left == null) {
                    current.left = new ScoreNode(record);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ScoreNode(record);
                    return true;
                }
                current = current.right;
            }
        }
    }

    void printRange(int low, int high) {
        StringBuilder result = new StringBuilder();
        printRange(root, low, high, result);
        System.out.println("score range [" + low + "," + high + "]: " + result.toString().trim());
    }

    private void printRange(ScoreNode node, int low, int high, StringBuilder result) {
        if (node == null) return;
        if (node.data.score > low) {
            printRange(node.left, low, high, result);
        }
        if (node.data.score >= low && node.data.score <= high) {
            result.append(node.data).append(" ");
        }
        if (node.data.score < high) {
            printRange(node.right, low, high, result);
        }
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(ScoreNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }
}

public class ScoreRangeBst {
    public static void main(String[] args) {
        ScoreBst tree = new ScoreBst();
        tree.add(new ScoreRecord(85, "S001"));
        tree.add(new ScoreRecord(70, "S002"));
        tree.add(new ScoreRecord(85, "S003"));
        tree.add(new ScoreRecord(90, "S004"));
        tree.add(new ScoreRecord(60, "S005"));

        tree.inorder();
        tree.printRange(70, 90);
        tree.printRange(0, 65);
    }
}
