import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class CourseRecord {
    private final String studentId;
    private final String name;
    private int score;
    private final Set<String> tags = new HashSet<>();

    CourseRecord(String studentId, String name, int score) {
        this.studentId = studentId;
        this.name = name;
        this.score = clamp(score);
    }

    private static int clamp(int score) {
        return Math.max(0, Math.min(100, score));
    }

    String getStudentId() {
        return studentId;
    }

    String getName() {
        return name;
    }

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = clamp(score);
    }

    void addTag(String tag) {
        if (tag != null && !tag.isBlank()) {
            tags.add(tag.toLowerCase());
        }
    }

    boolean hasTag(String tag) {
        return tag != null && tags.contains(tag.toLowerCase());
    }

    String getGrade() {
        if (score >= 90) {
            return "A";
        }
        if (score >= 80) {
            return "B";
        }
        if (score >= 70) {
            return "C";
        }
        if (score >= 60) {
            return "D";
        }
        return "F";
    }

    @Override
    public String toString() {
        return studentId + " " + name + " score=" + score + " tags=" + tags;
    }
}

class EnrollmentManager {
    private final List<CourseRecord> order = new ArrayList<>();
    private final Set<String> registeredIds = new HashSet<>();
    private final Map<String, CourseRecord> byId = new HashMap<>();

    boolean enroll(CourseRecord record) {
        if (record == null || !registeredIds.add(record.getStudentId())) {
            return false;
        }
        order.add(record);
        byId.put(record.getStudentId(), record);
        return true;
    }

    CourseRecord find(String studentId) {
        return byId.get(studentId);
    }

    boolean updateScore(String studentId, int score) {
        CourseRecord record = byId.get(studentId);
        if (record == null) {
            return false;
        }
        record.setScore(score);
        return true;
    }

    List<CourseRecord> findByTag(String tag) {
        List<CourseRecord> result = new ArrayList<>();
        for (CourseRecord record : order) {
            if (record.hasTag(tag)) {
                result.add(record);
            }
        }
        return result;
    }

    Map<String, Integer> scoreDistribution() {
        Map<String, Integer> distribution = new LinkedHashMap<>();
        distribution.put("A", 0);
        distribution.put("B", 0);
        distribution.put("C", 0);
        distribution.put("D", 0);
        distribution.put("F", 0);
        for (CourseRecord record : order) {
            distribution.merge(record.getGrade(), 1, Integer::sum);
        }
        return distribution;
    }

    List<CourseRecord> top(int count) {
        List<CourseRecord> sorted = new ArrayList<>(order);
        sorted.sort(Comparator.comparingInt(CourseRecord::getScore)
                .reversed()
                .thenComparing(CourseRecord::getStudentId));
        if (count >= sorted.size()) {
            return sorted;
        }
        return new ArrayList<>(sorted.subList(0, Math.max(0, count)));
    }

    void removeBelow(int minimum) {
        order.removeIf(record -> record.getScore() < minimum);
        registeredIds.clear();
        byId.clear();
        for (CourseRecord record : order) {
            registeredIds.add(record.getStudentId());
            byId.put(record.getStudentId(), record);
        }
    }
}

public class CourseCollectionManager {
    public static void main(String[] args) {
        EnrollmentManager manager = new EnrollmentManager();

        CourseRecord amy = new CourseRecord("S101", "Amy", 88);
        CourseRecord ben = new CourseRecord("S102", "Ben", 55);
        CourseRecord cara = new CourseRecord("S103", "Cara", 92);
        CourseRecord dan = new CourseRecord("S104", "Dan", 70);

        amy.addTag("Java");
        cara.addTag("Java");
        dan.addTag("  ");

        manager.enroll(amy);
        manager.enroll(ben);
        manager.enroll(cara);
        manager.enroll(dan);

        System.out.println("duplicate enroll="
                + manager.enroll(new CourseRecord("S101", "Amy2", 100)));
        System.out.println("updateScore=" + manager.updateScore("S102", 65));
        System.out.println("find S102=" + manager.find("S102"));
        System.out.println("findByTag(java)=" + manager.findByTag("java"));
        System.out.println("distribution=" + manager.scoreDistribution());
        System.out.println("top(2)=" + manager.top(2));
        System.out.println("top(10)=" + manager.top(10));

        manager.removeBelow(60);
        System.out.println("after cleanup=" + manager.top(10));
    }
}
