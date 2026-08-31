import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class EnrollmentConflictSet {
    record Enrollment(String studentId, String courseCode) {}
    private record Key(String studentId, String courseCode) {}

    static List<Enrollment> duplicates(List<Enrollment> enrollments) {
        Set<Key> seen = new HashSet<>();
        Set<Key> reported = new HashSet<>();
        List<Enrollment> result = new ArrayList<>();
        if (enrollments == null) {
            return result;
        }
        for (Enrollment enrollment : enrollments) {
            if (enrollment == null) {
                continue;
            }
            Key key = new Key(enrollment.studentId(), enrollment.courseCode());
            if (!seen.add(key) && reported.add(key)) {
                result.add(enrollment);
            }
        }
        return result;
    }

    static Map<String, Integer> courseCountPerStudent(List<Enrollment> enrollments) {
        Map<String, Set<String>> coursesByStudent = new HashMap<>();
        if (enrollments != null) {
            for (Enrollment enrollment : enrollments) {
                if (enrollment == null) continue;
                coursesByStudent.computeIfAbsent(enrollment.studentId(), key -> new HashSet<>())
                        .add(enrollment.courseCode());
            }
        }
        Map<String, Integer> result = new TreeMap<>();
        for (Map.Entry<String, Set<String>> entry : coursesByStudent.entrySet()) {
            result.put(entry.getKey(), entry.getValue().size());
        }
        return result;
    }

    static Map<String, Integer> studentCountPerCourse(List<Enrollment> enrollments) {
        Map<String, Set<String>> studentsByCourse = new HashMap<>();
        if (enrollments != null) {
            for (Enrollment enrollment : enrollments) {
                if (enrollment == null) continue;
                studentsByCourse.computeIfAbsent(enrollment.courseCode(), key -> new HashSet<>())
                        .add(enrollment.studentId());
            }
        }
        Map<String, Integer> result = new TreeMap<>();
        for (Map.Entry<String, Set<String>> entry : studentsByCourse.entrySet()) {
            result.put(entry.getKey(), entry.getValue().size());
        }
        return result;
    }

    public static void main(String[] args) {
        List<Enrollment> enrollments = List.of(
                new Enrollment("S01", "DS"),
                new Enrollment("S01", "DS"),
                new Enrollment("S01", "Java"),
                new Enrollment("S02", "DS"),
                new Enrollment("S02", "DS"),
                new Enrollment("S02", "DS")
        );

        System.out.println("duplicates=" + duplicates(enrollments));
        System.out.println("courseCountPerStudent=" + courseCountPerStudent(enrollments));
        System.out.println("studentCountPerCourse=" + studentCountPerCourse(enrollments));
    }
}
