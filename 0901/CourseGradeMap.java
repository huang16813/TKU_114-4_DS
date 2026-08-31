import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CourseGradeMap {
    private final Map<String, List<Integer>> grades = new TreeMap<>();

    public void addGrade(String course, int grade) {
        if (course == null || course.isBlank()) {
            return;
        }
        grades.computeIfAbsent(course, key -> new ArrayList<>()).add(grade);
    }

    public Double average(String course) {
        List<Integer> scores = grades.get(course);
        if (scores == null || scores.isEmpty()) {
            return null;
        }
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return (double) total / scores.size();
    }

    public Integer highest(String course) {
        List<Integer> scores = grades.get(course);
        if (scores == null || scores.isEmpty()) {
            return null;
        }
        int max = scores.get(0);
        for (int score : scores) {
            max = Math.max(max, score);
        }
        return max;
    }

    public List<String> report() {
        List<String> lines = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : grades.entrySet()) {
            lines.add(entry.getKey() + " avg=" + String.format("%.2f", average(entry.getKey()))
                    + " max=" + highest(entry.getKey()));
        }
        return lines;
    }

    public static void main(String[] args) {
        CourseGradeMap tracker = new CourseGradeMap();
        tracker.addGrade("DS", 88);
        tracker.addGrade("DS", 72);
        tracker.addGrade("Java", 90);
        tracker.addGrade("DS", 95);
        tracker.addGrade("Java", 60);

        System.out.println("DS average=" + tracker.average("DS"));
        System.out.println("DS highest=" + tracker.highest("DS"));
        System.out.println("missing average=" + tracker.average("Math"));

        for (String line : tracker.report()) {
            System.out.println(line);
        }
    }
}
