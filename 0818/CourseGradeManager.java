class CourseGrade {
    private String studentId;
    private String name;
    private int regularScore;
    private int midtermScore;
    private int finalScore;
    private int attendanceScore;

    CourseGrade(String studentId, String name, int regularScore,
                int midtermScore, int finalScore, int attendanceScore) {
        this.studentId = (studentId == null || studentId.isBlank()) ? "UNKNOWN" : studentId;
        this.name = (name == null || name.isBlank()) ? "Unknown" : name;
        this.regularScore = clamp(regularScore);
        this.midtermScore = clamp(midtermScore);
        this.finalScore = clamp(finalScore);
        this.attendanceScore = clamp(attendanceScore);
    }

    private static int clamp(int score) {
        return Math.max(0, Math.min(100, score));
    }

    double calculateFinalScore() {
        return regularScore * 0.5 + midtermScore * 0.2
                + finalScore * 0.2 + attendanceScore * 0.1;
    }

    char getLevel() {
        double total = calculateFinalScore();
        if (total >= 90) {
            return 'A';
        }
        if (total >= 80) {
            return 'B';
        }
        if (total >= 70) {
            return 'C';
        }
        if (total >= 60) {
            return 'D';
        }
        return 'F';
    }

    @Override
    public String toString() {
        return studentId + " " + name
                + " final=" + String.format("%.1f", calculateFinalScore())
                + " level=" + getLevel();
    }
}

public class CourseGradeManager {
    public static void main(String[] args) {
        CourseGrade[] grades = {
            new CourseGrade("S201", "Amy", 90, 85, 88, 100),
            new CourseGrade("S202", "Ben", 60, 50, 55, 80),
            new CourseGrade("S203", "Cara", 75, 70, 72, 90)
        };

        double totalScore = 0;
        double highest = Double.MIN_VALUE;
        int failCount = 0;

        for (CourseGrade grade : grades) {
            System.out.println(grade);
            double score = grade.calculateFinalScore();
            totalScore += score;
            if (score > highest) {
                highest = score;
            }
            if (grade.getLevel() == 'F') {
                failCount++;
            }
        }

        System.out.printf("平均：%.1f%n", totalScore / grades.length);
        System.out.printf("最高：%.1f%n", highest);
        System.out.println("不及格人數：" + failCount);
    }
}
