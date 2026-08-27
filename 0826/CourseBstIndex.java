import java.util.ArrayList;
import java.util.List;

class Course {
    String courseCode;
    String title;
    int credit;

    Course(String courseCode, String title, int credit) {
        this.courseCode = courseCode;
        this.title = title;
        this.credit = Math.max(1, Math.min(6, credit));
    }

    @Override
    public String toString() {
        return courseCode + " " + title + " credit=" + credit;
    }
}

class CourseNode {
    Course data;
    CourseNode left;
    CourseNode right;

    CourseNode(Course data) {
        this.data = data;
    }
}

class CourseBst {
    private CourseNode root;

    boolean add(Course course) {
        if (course == null) return false;
        if (root == null) {
            root = new CourseNode(course);
            return true;
        }
        CourseNode current = root;
        while (true) {
            int compare = course.courseCode.compareTo(current.data.courseCode);
            if (compare == 0) return false;
            if (compare < 0) {
                if (current.left == null) {
                    current.left = new CourseNode(course);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new CourseNode(course);
                    return true;
                }
                current = current.right;
            }
        }
    }

    Course find(String courseCode) {
        CourseNode current = root;
        while (current != null) {
            int compare = courseCode.compareTo(current.data.courseCode);
            if (compare == 0) return current.data;
            current = compare < 0 ? current.left : current.right;
        }
        return null;
    }

    boolean updateCredit(String courseCode, int newCredit) {
        Course course = find(courseCode);
        if (course == null) return false;
        course.credit = Math.max(1, Math.min(6, newCredit));
        return true;
    }

    boolean remove(String courseCode) {
        if (find(courseCode) == null) return false;
        root = remove(root, courseCode);
        return true;
    }

    private CourseNode remove(CourseNode node, String courseCode) {
        if (node == null) return null;
        int compare = courseCode.compareTo(node.data.courseCode);
        if (compare < 0) {
            node.left = remove(node.left, courseCode);
        } else if (compare > 0) {
            node.right = remove(node.right, courseCode);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            CourseNode successor = minimumNode(node.right);
            node.data = successor.data;
            node.right = remove(node.right, successor.data.courseCode);
        }
        return node;
    }

    private CourseNode minimumNode(CourseNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    List<Course> range(String low, String high) {
        List<Course> result = new ArrayList<>();
        if (low.compareTo(high) <= 0) range(root, low, high, result);
        return result;
    }

    private void range(CourseNode node, String low, String high, List<Course> result) {
        if (node == null) return;
        if (low.compareTo(node.data.courseCode) < 0) range(node.left, low, high, result);
        if (low.compareTo(node.data.courseCode) <= 0 && node.data.courseCode.compareTo(high) <= 0) {
            result.add(node.data);
        }
        if (node.data.courseCode.compareTo(high) < 0) range(node.right, low, high, result);
    }

    List<Course> inorder() {
        List<Course> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(CourseNode node, List<Course> result) {
        if (node == null) return;
        inorder(node.left, result);
        result.add(node.data);
        inorder(node.right, result);
    }
}

public class CourseBstIndex {
    public static void main(String[] args) {
        CourseBst index = new CourseBst();
        System.out.println("add=" + index.add(new Course("CS301", "Data Structures", 3)));
        System.out.println("add=" + index.add(new Course("CS101", "Intro to CS", 4)));
        System.out.println("add=" + index.add(new Course("CS501", "Algorithms", 9)));
        System.out.println("duplicate=" + index.add(new Course("CS101", "Dup", 3)));

        System.out.println("find CS501=" + index.find("CS501"));

        System.out.println("updateCredit CS101 to 0=" + index.updateCredit("CS101", 0));
        System.out.println("find CS101=" + index.find("CS101"));

        System.out.println("range=" + index.range("CS101", "CS400"));

        System.out.println("remove CS301=" + index.remove("CS301"));

        System.out.println("inorder=" + index.inorder());
    }
}
