import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q05_StudentHashIndex {
    private final Map<String, Set<String>> coursesByStudent = new HashMap<>();
    private final Map<String, Set<String>> studentsByCourse = new HashMap<>();
    private int enrollmentCount;

    private static String normalize(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed.toUpperCase();
    }

    public boolean enroll(String studentId, String courseId) {
        String student = normalize(studentId);
        String course = normalize(courseId);
        if (student == null || course == null) {
            return false;
        }
        Set<String> courses = coursesByStudent.computeIfAbsent(student, key -> new HashSet<>());
        if (!courses.add(course)) {
            return false;
        }
        studentsByCourse.computeIfAbsent(course, key -> new HashSet<>()).add(student);
        enrollmentCount++;
        return true;
    }

    public boolean drop(String studentId, String courseId) {
        String student = normalize(studentId);
        String course = normalize(courseId);
        if (student == null || course == null) {
            return false;
        }
        Set<String> courses = coursesByStudent.get(student);
        if (courses == null || !courses.remove(course)) {
            return false;
        }
        if (courses.isEmpty()) {
            coursesByStudent.remove(student);
        }
        Set<String> students = studentsByCourse.get(course);
        if (students != null) {
            students.remove(student);
            if (students.isEmpty()) {
                studentsByCourse.remove(course);
            }
        }
        enrollmentCount--;
        return true;
    }

    public Set<String> coursesOf(String studentId) {
        String student = normalize(studentId);
        Set<String> courses = student == null ? null : coursesByStudent.get(student);
        return courses == null ? Set.of() : Set.copyOf(courses);
    }

    public Set<String> studentsIn(String courseId) {
        String course = normalize(courseId);
        Set<String> students = course == null ? null : studentsByCourse.get(course);
        return students == null ? Set.of() : Set.copyOf(students);
    }

    public int enrollmentCount() {
        return enrollmentCount;
    }

    public static void main(String[] args) {
        Q05_StudentHashIndex index = new Q05_StudentHashIndex();

        System.out.println("enroll=" + index.enroll(" s01 ", " ds "));
        System.out.println("duplicate=" + index.enroll("S01", "DS"));
        System.out.println("enroll=" + index.enroll("S02", "DS"));
        System.out.println("blank=" + index.enroll("", "DS"));
        System.out.println("null=" + index.enroll("S03", null));

        System.out.println("coursesOf S01=" + index.coursesOf(" s01"));
        System.out.println("studentsIn DS=" + index.studentsIn("ds "));
        System.out.println("enrollmentCount=" + index.enrollmentCount());

        System.out.println("drop=" + index.drop("S01", "DS"));
        System.out.println("coursesOf S01 after drop=" + index.coursesOf("S01"));
        System.out.println("drop missing=" + index.drop("S01", "DS"));
        System.out.println("enrollmentCount after drop=" + index.enrollmentCount());
    }
}
