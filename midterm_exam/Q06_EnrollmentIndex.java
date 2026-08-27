import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Q06_EnrollmentIndex {
    private final Map<String, Set<String>> courseToStudents = new TreeMap<>();

    public boolean enroll(String courseCode, String studentId) {
        if (isBlank(courseCode) || isBlank(studentId)) {
            return false;
        }
        Set<String> students = courseToStudents.computeIfAbsent(courseCode, key -> new LinkedHashSet<>());
        if (students.contains(studentId)) {
            return false;
        }
        students.add(studentId);
        return true;
    }

    public boolean drop(String courseCode, String studentId) {
        if (isBlank(courseCode) || isBlank(studentId)) {
            return false;
        }
        Set<String> students = courseToStudents.get(courseCode);
        if (students == null || !students.remove(studentId)) {
            return false;
        }
        if (students.isEmpty()) {
            courseToStudents.remove(courseCode);
        }
        return true;
    }

    public int courseSize(String courseCode) {
        Set<String> students = courseToStudents.get(courseCode);
        return students == null ? 0 : students.size();
    }

    public List<String> studentsOf(String courseCode) {
        Set<String> students = courseToStudents.get(courseCode);
        List<String> result = new ArrayList<>();
        if (students != null) {
            result.addAll(students);
        }
        Collections.sort(result);
        return result;
    }

    public List<String> coursesOf(String studentId) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : courseToStudents.entrySet()) {
            if (entry.getValue().contains(studentId)) {
                result.add(entry.getKey());
            }
        }
        Collections.sort(result);
        return result;
    }

    public Map<String, Integer> summary() {
        Map<String, Integer> result = new TreeMap<>();
        for (Map.Entry<String, Set<String>> entry : courseToStudents.entrySet()) {
            result.put(entry.getKey(), entry.getValue().size());
        }
        return result;
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
