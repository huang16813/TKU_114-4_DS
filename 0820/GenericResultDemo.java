class Result<T> {
    private final boolean success;
    private final String message;
    private final T data;

    private Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    static <T> Result<T> ok(T data) {
        return new Result<>(true, "OK", data);
    }

    static <T> Result<T> fail(String message) {
        return new Result<>(false, message, null);
    }

    boolean isSuccess() {
        return success;
    }

    String getMessage() {
        return message;
    }

    T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{success=" + success + ", message=" + message
                + ", data=" + data + "}";
    }
}

public class GenericResultDemo {
    static Result<Integer> parsePositive(String text) {
        try {
            int value = Integer.parseInt(text);
            if (value <= 0) {
                return Result.fail("value must be positive");
            }
            return Result.ok(value);
        } catch (NumberFormatException exception) {
            return Result.fail("not a number");
        }
    }

    public static void main(String[] args) {
        Result<String> stringResult = Result.ok("Java");
        Result<Integer> intResult = parsePositive("42");
        Result<Integer> failResult = parsePositive("-5");

        System.out.println(stringResult);
        System.out.println(intResult);
        System.out.println(failResult);
        System.out.println("failResult data == null: "
                + (failResult.getData() == null));
    }
}
