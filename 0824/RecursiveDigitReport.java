public class RecursiveDigitReport {
    static int digitSum(int number) {
        int n = Math.abs(number);
        if (n < 10) {
            return n;
        }
        return n % 10 + digitSum(n / 10);
    }

    static int digitCount(int number) {
        int n = Math.abs(number);
        if (n < 10) {
            return 1;
        }
        return 1 + digitCount(n / 10);
    }

    static int countDigit(int number, int target) {
        int n = Math.abs(number);
        if (n < 10) {
            return n == target ? 1 : 0;
        }
        int current = n % 10 == target ? 1 : 0;
        return current + countDigit(n / 10, target);
    }

    public static void main(String[] args) {
        int[] samples = {50205, 0, -731};
        for (int sample : samples) {
            System.out.println(sample + " digitSum=" + digitSum(sample)
                    + " digitCount=" + digitCount(sample)
                    + " countDigit(0)=" + countDigit(sample, 0));
        }
    }
}
