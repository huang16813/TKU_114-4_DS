import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CourseDependencyGraph {
    private final Map<String, List<String>> prerequisiteOf = new LinkedHashMap<>();

    public void addCourse(String course) {
        if (course == null || course.isBlank()) {
            throw new IllegalArgumentException("course");
        }
        prerequisiteOf.putIfAbsent(course, new ArrayList<>());
    }

    public boolean addPrerequisite(String course, String prerequisite) {
        if (!prerequisiteOf.containsKey(course) || !prerequisiteOf.containsKey(prerequisite)) {
            return false;
        }
        List<String> prereqs = prerequisiteOf.get(course);
        if (prereqs.contains(prerequisite)) {
            return false;
        }
        prereqs.add(prerequisite);
        return true;
    }

    public List<String> prerequisitesOf(String course) {
        return List.copyOf(prerequisiteOf.getOrDefault(course, List.of()));
    }

    public List<String> coursesThatNeed(String prerequisite) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : prerequisiteOf.entrySet()) {
            if (entry.getValue().contains(prerequisite)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public int outDegree(String course) {
        return prerequisiteOf.getOrDefault(course, List.of()).size();
    }

    public int inDegree(String course) {
        return coursesThatNeed(course).size();
    }

    public static void main(String[] args) {
        CourseDependencyGraph graph = new CourseDependencyGraph();
        for (String course : List.of("CS101", "CS201", "CS301", "CS401")) {
            graph.addCourse(course);
        }

        graph.addPrerequisite("CS201", "CS101");
        graph.addPrerequisite("CS301", "CS201");
        graph.addPrerequisite("CS401", "CS201");
        graph.addPrerequisite("CS401", "CS301");

        System.out.println("CS401 prerequisites=" + graph.prerequisitesOf("CS401"));
        System.out.println("courses needing CS201=" + graph.coursesThatNeed("CS201"));
        System.out.println("CS201 outDegree=" + graph.outDegree("CS201"));
        System.out.println("CS201 inDegree=" + graph.inDegree("CS201"));
    }
}
