import java.util.ArrayList;
import java.util.List;

class Member {
    int memberId;
    String name;
    String email;

    Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return memberId + " " + name + " " + email;
    }
}

class MemberNode {
    Member data;
    MemberNode left;
    MemberNode right;

    MemberNode(Member data) {
        this.data = data;
    }
}

class MemberBst {
    private MemberNode root;

    boolean add(Member member) {
        if (member == null) return false;
        if (root == null) {
            root = new MemberNode(member);
            return true;
        }
        MemberNode current = root;
        while (true) {
            if (member.memberId == current.data.memberId) return false;
            if (member.memberId < current.data.memberId) {
                if (current.left == null) {
                    current.left = new MemberNode(member);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new MemberNode(member);
                    return true;
                }
                current = current.right;
            }
        }
    }

    Member find(int memberId) {
        MemberNode current = root;
        while (current != null) {
            if (memberId == current.data.memberId) return current.data;
            current = memberId < current.data.memberId ? current.left : current.right;
        }
        return null;
    }

    boolean updateEmail(int memberId, String newEmail) {
        if (newEmail == null || newEmail.isBlank()) return false;
        Member member = find(memberId);
        if (member == null) return false;
        member.email = newEmail;
        return true;
    }

    boolean remove(int memberId) {
        if (find(memberId) == null) return false;
        root = remove(root, memberId);
        return true;
    }

    private MemberNode remove(MemberNode node, int memberId) {
        if (node == null) return null;
        if (memberId < node.data.memberId) {
            node.left = remove(node.left, memberId);
        } else if (memberId > node.data.memberId) {
            node.right = remove(node.right, memberId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            MemberNode successor = minimumNode(node.right);
            node.data = successor.data;
            node.right = remove(node.right, successor.data.memberId);
        }
        return node;
    }

    private MemberNode minimumNode(MemberNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    List<Member> inorder() {
        List<Member> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(MemberNode node, List<Member> result) {
        if (node == null) return;
        inorder(node.left, result);
        result.add(node.data);
        inorder(node.right, result);
    }
}

public class MemberBstIndex {
    public static void main(String[] args) {
        MemberBst index = new MemberBst();
        System.out.println("add=" + index.add(new Member(300, "Amy", "amy@mail.com")));
        System.out.println("add=" + index.add(new Member(100, "Ben", "ben@mail.com")));
        System.out.println("add=" + index.add(new Member(500, "Cara", "cara@mail.com")));
        System.out.println("duplicate=" + index.add(new Member(100, "Dup", "dup@mail.com")));

        System.out.println("find 100=" + index.find(100));

        System.out.println("updateEmail blank=" + index.updateEmail(100, "  "));
        System.out.println("updateEmail valid=" + index.updateEmail(100, "ben.new@mail.com"));

        System.out.println("remove 300=" + index.remove(300));

        for (Member member : index.inorder()) {
            System.out.println(member);
        }
    }
}
