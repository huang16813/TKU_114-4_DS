import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Enrollment {
    private final String studentId;
    private final String courseCode;

    Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Enrollment enrollment)) {
            return false;
        }
        return Objects.equals(studentId, enrollment.studentId)
                && Objects.equals(courseCode, enrollment.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() {
        return studentId + "-" + courseCode;
    }
}

public class EnrollmentSetSystem {
    public static void main(String[] args) {
        Set<Enrollment> enrollments = new HashSet<>();

        System.out.println("add S101-CS101=" + enrollments.add(new Enrollment("S101", "CS101")));
        System.out.println("add S101-CS102=" + enrollments.add(new Enrollment("S101", "CS102")));
        System.out.println("add S101-CS101 dup=" + enrollments.add(new Enrollment("S101", "CS101")));

        System.out.println("size=" + enrollments.size());
        System.out.println("contains new S101-CS101="
                + enrollments.contains(new Enrollment("S101", "CS101")));
        System.out.println("remove new S101-CS102="
                + enrollments.remove(new Enrollment("S101", "CS102")));
        System.out.println("size after remove=" + enrollments.size());
    }
}
