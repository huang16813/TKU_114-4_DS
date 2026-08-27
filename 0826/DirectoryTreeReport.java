class FileSystemNode {
    String name;
    boolean isDirectory;
    int ownSize;
    FileSystemNode left;
    FileSystemNode right;

    FileSystemNode(String name, boolean isDirectory, int ownSize) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.ownSize = ownSize;
    }
}

public class DirectoryTreeReport {
    static int totalSize(FileSystemNode node) {
        if (node == null) return 0;
        return node.ownSize + totalSize(node.left) + totalSize(node.right);
    }

    static void printDirectorySizes(FileSystemNode node) {
        if (node == null) return;
        printDirectorySizes(node.left);
        printDirectorySizes(node.right);
        if (node.isDirectory) {
            System.out.println("directory " + node.name + " total size=" + totalSize(node));
        }
    }

    static int totalNodes(FileSystemNode node) {
        return node == null ? 0 : 1 + totalNodes(node.left) + totalNodes(node.right);
    }

    static int fileCount(FileSystemNode node) {
        if (node == null) return 0;
        int current = node.isDirectory ? 0 : 1;
        return current + fileCount(node.left) + fileCount(node.right);
    }

    static int directoryCount(FileSystemNode node) {
        if (node == null) return 0;
        int current = node.isDirectory ? 1 : 0;
        return current + directoryCount(node.left) + directoryCount(node.right);
    }

    static int height(FileSystemNode node) {
        return node == null ? -1 : 1 + Math.max(height(node.left), height(node.right));
    }

    static FileSystemNode largestFile(FileSystemNode node, FileSystemNode best) {
        if (node == null) return best;
        if (!node.isDirectory && (best == null || node.ownSize > best.ownSize)) {
            best = node;
        }
        best = largestFile(node.left, best);
        return largestFile(node.right, best);
    }

    public static void main(String[] args) {
        FileSystemNode root = new FileSystemNode("root", true, 0);
        root.left = new FileSystemNode("docs", true, 0);
        root.right = new FileSystemNode("media", true, 0);
        root.left.left = new FileSystemNode("report.pdf", false, 30);
        root.left.right = new FileSystemNode("notes.txt", false, 5);
        root.right.left = new FileSystemNode("photo.png", false, 50);
        root.right.right = new FileSystemNode("video.mp4", false, 120);

        printDirectorySizes(root);

        System.out.println("totalNodes=" + totalNodes(root));
        System.out.println("fileCount=" + fileCount(root));
        System.out.println("directoryCount=" + directoryCount(root));
        System.out.println("height=" + height(root));

        FileSystemNode largest = largestFile(root, null);
        System.out.println("largestFile=" + (largest == null ? "NONE" : largest.name + "(" + largest.ownSize + ")"));
    }
}
