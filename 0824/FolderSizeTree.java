class FolderNode {
    String name;
    int ownSize;
    FolderNode left;
    FolderNode right;

    FolderNode(String name, int ownSize) {
        this.name = name;
        this.ownSize = ownSize;
    }
}

public class FolderSizeTree {
    static int subtreeSize(FolderNode node) {
        if (node == null) {
            return 0;
        }
        int leftSize = subtreeSize(node.left);
        int rightSize = subtreeSize(node.right);
        return node.ownSize + leftSize + rightSize;
    }

    static String largestSubtree(FolderNode node) {
        if (node == null) {
            return "NONE";
        }
        String best = node.name;
        int bestSize = subtreeSize(node);

        if (node.left != null) {
            int leftSize = subtreeSize(node.left);
            if (leftSize > bestSize) {
                best = node.left.name;
                bestSize = leftSize;
            }
        }
        if (node.right != null) {
            int rightSize = subtreeSize(node.right);
            if (rightSize > bestSize) {
                best = node.right.name;
                bestSize = rightSize;
            }
        }
        return best + "(" + bestSize + ")";
    }

    static void collectLeaves(FolderNode node, StringBuilder result) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            result.append(node.name).append(" ");
            return;
        }
        collectLeaves(node.left, result);
        collectLeaves(node.right, result);
    }

    public static void main(String[] args) {
        FolderNode root = new FolderNode("root", 10);
        root.left = new FolderNode("docs", 20);
        root.right = new FolderNode("media", 15);
        root.left.left = new FolderNode("reports", 30);
        root.left.right = new FolderNode("notes", 5);
        root.right.left = new FolderNode("photos", 50);
        root.right.right = new FolderNode("videos", 80);

        System.out.println("total size=" + subtreeSize(root));
        System.out.println("largest subtree=" + largestSubtree(root));

        StringBuilder leaves = new StringBuilder();
        collectLeaves(root, leaves);
        System.out.println("leaf folders=" + leaves.toString().trim());
    }
}
