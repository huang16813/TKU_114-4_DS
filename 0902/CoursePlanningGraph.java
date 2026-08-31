import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoursePlanningGraph {
    private final Map<String, List<String>> unlocks = new LinkedHashMap<>();

    public void addCourse(String course) {
        if (course == null || course.isBlank()) {
            throw new IllegalArgumentException("course");
        }
        unlocks.putIfAbsent(course, new ArrayList<>());
    }

    public boolean addPrerequisite(String prerequisite, String course) {
        if (!unlocks.containsKey(prerequisite) || !unlocks.containsKey(course)) {
            return false;
        }
        List<String> nextCourses = unlocks.get(prerequisite);
        if (nextCourses.contains(course)) {
            return false;
        }
        nextCourses.add(course);
        return true;
    }

    public boolean reachable(String from, String to) {
        if (!unlocks.containsKey(from) || !unlocks.containsKey(to)) {
            return false;
        }
        Deque<String> stack = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        stack.push(from);
        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (current.equals(to)) {
                return true;
            }
            if (!visited.add(current)) {
                continue;
            }
            for (String next : unlocks.getOrDefault(current, List.of())) {
                if (!visited.contains(next)) {
                    stack.push(next);
                }
            }
        }
        return false;
    }

    public List<String> directlyAffectedBy(String course) {
        return List.copyOf(unlocks.getOrDefault(course, List.of()));
    }

    public List<String> allAffectedBy(String course) {
        List<String> result = new ArrayList<>();
        if (!unlocks.containsKey(course)) {
            return result;
        }
        Deque<String> stack = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        stack.push(course);
        visited.add(course);
        while (!stack.isEmpty()) {
            String current = stack.pop();
            for (String next : unlocks.getOrDefault(current, List.of())) {
                if (visited.add(next)) {
                    result.add(next);
                    stack.push(next);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CoursePlanningGraph graph = new CoursePlanningGraph();
        for (String course : List.of("CS101", "CS201", "CS301", "CS302", "CS401")) {
            graph.addCourse(course);
        }

        graph.addPrerequisite("CS101", "CS201");
        graph.addPrerequisite("CS201", "CS301");
        graph.addPrerequisite("CS201", "CS302");
        graph.addPrerequisite("CS301", "CS401");

        System.out.println("CS101 directly unlocks=" + graph.directlyAffectedBy("CS101"));
        System.out.println("CS101 all affected=" + graph.allAffectedBy("CS101"));
        System.out.println("CS101 -> CS401 reachable=" + graph.reachable("CS101", "CS401"));
        System.out.println("CS401 -> CS101 reachable=" + graph.reachable("CS401", "CS101"));
        System.out.println("missing course reachable=" + graph.reachable("CS101", "X"));
    }
}
