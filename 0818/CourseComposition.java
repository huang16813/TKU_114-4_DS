class Instructor {
    private String id;
    private String name;

    Instructor(String id, String name) {
        this.id = (id == null || id.isBlank()) ? "Unknown" : id;
        this.name = (name == null || name.isBlank()) ? "Unknown" : name;
    }

    String label() {
        return id + " " + name;
    }
}

class Course {
    private String courseCode;
    private String title;
    private Instructor instructor;

    Course(String courseCode, String title, Instructor instructor) {
        this.courseCode = (courseCode == null || courseCode.isBlank()) ? "Unknown" : courseCode;
        this.title = (title == null || title.isBlank()) ? "Untitled" : title;
        this.instructor = instructor;
    }

    String summary() {
        return courseCode + " " + title + " | instructor=" + instructor.label();
    }
}

public class CourseComposition {
    public static void main(String[] args) {
        Instructor instructor = new Instructor("I001", "Dr. Lin");

        Course dataStructure = new Course("CS201", "Data Structures", instructor);
        Course algorithms = new Course("CS202", "Algorithms", instructor);

        System.out.println(dataStructure.summary());
        System.out.println(algorithms.summary());
    }
}
