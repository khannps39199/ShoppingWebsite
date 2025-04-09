package poly.edu.Entity;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Không thể chia cho 0!");
        }
        return (double) a / b;
    }

    public int parseNumber(String string) {
        if (string == null) {
            throw new NullPointerException("Giá trị nhập vào không được null");
        }
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Giá trị nhập vào không hợp lệ, phải là số");
        }
    }
}