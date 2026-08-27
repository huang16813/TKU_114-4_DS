public class RecursiveCallReport {
    static int sum(int[] data, int index) {
        if (data == null || index >= data.length) {
            System.out.println("index=" + index + " out of range, return 0");
            return 0;
        }
        System.out.println("enter index=" + index + " value=" + data[index]);
        int recursiveResult = sum(data, index + 1);
        int returnValue = data[index] + recursiveResult;
        System.out.println("index=" + index + " value=" + data[index]
                + " recursiveResult=" + recursiveResult + " return=" + returnValue);
        return returnValue;
    }

    public static void main(String[] args) {
        int[] values = {4, 7, 2, 9};
        System.out.println("sum=" + sum(values, 0));

        System.out.println("single element:");
        System.out.println("sum=" + sum(new int[]{5}, 0));

        System.out.println("empty array:");
        System.out.println("sum=" + sum(new int[]{}, 0));
    }
}
