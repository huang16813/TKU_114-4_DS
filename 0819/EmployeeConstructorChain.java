abstract class EmployeeBase {
    private final String id;
    private final String name;

    EmployeeBase(String id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("EmployeeBase constructor: " + id);
    }

    String label() {
        return id + " " + name;
    }

    abstract int calculatePay();
}

class FullTimeStaff extends EmployeeBase {
    private final int monthlySalary;

    FullTimeStaff(String id, String name, int monthlySalary) {
        super(id, name);
        this.monthlySalary = Math.max(0, monthlySalary);
        System.out.println("FullTimeStaff constructor: " + this.monthlySalary);
    }

    @Override
    int calculatePay() {
        return monthlySalary;
    }
}

class PartTimeStaff extends EmployeeBase {
    private final int hours;
    private final int hourlyRate;

    PartTimeStaff(String id, String name, int hours, int hourlyRate) {
        super(id, name);
        this.hours = Math.max(0, hours);
        this.hourlyRate = Math.max(0, hourlyRate);
        System.out.println("PartTimeStaff constructor: "
                + this.hours + "x" + this.hourlyRate);
    }

    @Override
    int calculatePay() {
        return hours * hourlyRate;
    }
}

public class EmployeeConstructorChain {
    public static void main(String[] args) {
        EmployeeBase fullTime = new FullTimeStaff("E01", "Amy", 50000);
        EmployeeBase partTime = new PartTimeStaff("E02", "Ben", -5, -200);

        System.out.println(fullTime.label() + " pay=" + fullTime.calculatePay());
        System.out.println(partTime.label() + " pay=" + partTime.calculatePay());
    }
}
