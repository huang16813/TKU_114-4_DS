abstract class PayrollEmployee {
    private String name;

    PayrollEmployee(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    abstract int calculatePay();
}

class SalariedStaff extends PayrollEmployee {
    private int salary;

    SalariedStaff(String name, int salary) {
        super(name);
        this.salary = Math.max(0, salary);
    }

    @Override
    int calculatePay() {
        return salary;
    }
}

class HourlyStaff extends PayrollEmployee {
    private int hours;
    private int hourlyRate;

    HourlyStaff(String name, int hours, int hourlyRate) {
        super(name);
        this.hours = Math.max(0, hours);
        this.hourlyRate = Math.max(0, hourlyRate);
    }

    @Override
    int calculatePay() {
        return hours * hourlyRate;
    }
}

class CommissionStaff extends PayrollEmployee {
    private int baseSalary;
    private int salesAmount;
    private int commissionRate;

    CommissionStaff(String name, int baseSalary, int salesAmount, int commissionRate) {
        super(name);
        this.baseSalary = Math.max(0, baseSalary);
        this.salesAmount = Math.max(0, salesAmount);
        this.commissionRate = Math.max(0, commissionRate);
    }

    @Override
    int calculatePay() {
        return baseSalary + salesAmount * commissionRate / 100;
    }
}

public class PayrollPolymorphismSystem {
    public static void main(String[] args) {
        PayrollEmployee[] employees = {
            new SalariedStaff("Amy", 50000),
            new HourlyStaff("Ben", 80, 220),
            new CommissionStaff("Cara", 20000, 100000, 10)
        };

        int total = 0;
        PayrollEmployee highest = employees[0];

        for (PayrollEmployee employee : employees) {
            int pay = employee.calculatePay();
            System.out.println(employee.getName() + " pay=" + pay);
            total += pay;
            if (pay > highest.calculatePay()) {
                highest = employee;
            }
        }

        System.out.println("薪資總額：" + total);
        System.out.println("最高薪資：" + highest.getName()
                + " (" + highest.calculatePay() + ")");
    }
}
