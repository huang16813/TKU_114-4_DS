import java.util.ArrayDeque;
import java.util.Queue;

class OrgReportNode {
    String name;
    OrgReportNode left;
    OrgReportNode right;

    OrgReportNode(String name) {
        this.name = name;
    }
}

public class OrganizationTreeReport {
    static OrgReportNode findParent(OrgReportNode root, String childName) {
        if (root == null || childName == null) {
            return null;
        }
        if ((root.left != null && root.left.name.equals(childName))
                || (root.right != null && root.right.name.equals(childName))) {
            return root;
        }
        OrgReportNode fromLeft = findParent(root.left, childName);
        if (fromLeft != null) {
            return fromLeft;
        }
        return findParent(root.right, childName);
    }

    static int findDepth(OrgReportNode node, String target) {
        return findDepth(node, target, 0);
    }

    private static int findDepth(OrgReportNode node, String target, int depth) {
        if (node == null) {
            return -1;
        }
        if (node.name.equals(target)) {
            return depth;
        }
        int leftResult = findDepth(node.left, target, depth + 1);
        if (leftResult != -1) {
            return leftResult;
        }
        return findDepth(node.right, target, depth + 1);
    }

    static String pathFromRoot(OrgReportNode root, String target) {
        StringBuilder path = new StringBuilder();
        boolean found = buildPath(root, target, path);
        return found ? path.toString().trim() : "";
    }

    private static boolean buildPath(OrgReportNode node, String target, StringBuilder path) {
        if (node == null) {
            return false;
        }
        path.append(node.name).append(" ");
        if (node.name.equals(target)) {
            return true;
        }
        if (buildPath(node.left, target, path) || buildPath(node.right, target, path)) {
            return true;
        }
        path.setLength(path.length() - node.name.length() - 1);
        return false;
    }

    static void printByLevel(OrgReportNode root) {
        if (root == null) {
            System.out.println("empty");
            return;
        }
        Queue<OrgReportNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < levelSize; i++) {
                OrgReportNode current = queue.poll();
                line.append(current.name).append(" ");
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            System.out.println("level " + level + ": " + line.toString().trim());
            level++;
        }
    }

    public static void main(String[] args) {
        OrgReportNode root = new OrgReportNode("HeadOffice");
        root.left = new OrgReportNode("Sales");
        root.right = new OrgReportNode("Technology");
        root.left.left = new OrgReportNode("Domestic");
        root.left.right = new OrgReportNode("Export");
        root.right.left = new OrgReportNode("Platform");
        root.right.right = new OrgReportNode("Support");

        OrgReportNode parent = findParent(root, "Export");
        System.out.println("Export parent=" + (parent == null ? "NONE" : parent.name));

        OrgReportNode noParent = findParent(root, "HR");
        System.out.println("HR parent=" + (noParent == null ? "NONE" : noParent.name));

        System.out.println("findDepth Support=" + findDepth(root, "Support"));
        System.out.println("findDepth HR=" + findDepth(root, "HR"));

        System.out.println("pathFromRoot Export=" + pathFromRoot(root, "Export"));
        System.out.println("pathFromRoot HR=\"" + pathFromRoot(root, "HR") + "\"");

        printByLevel(root);
    }
}
