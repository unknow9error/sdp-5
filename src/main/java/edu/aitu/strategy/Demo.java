package edu.aitu.strategy;

import java.math.BigDecimal;

public final class Demo {
    private Demo() { }

    public static void main(String[] args) {
        BigDecimal left = new BigDecimal("12.5");
        BigDecimal right = new BigDecimal("2");
        Calculator calculator = new Calculator(new AddStrategy());

        System.out.println("Same Calculator, operands 12.5 and 2:");
        printResult("Addition", calculator, left, right);
        calculator.setOperation(new SubtractStrategy());
        printResult("Subtraction", calculator, left, right);
        calculator.setOperation(new MultiplyStrategy());
        printResult("Multiplication", calculator, left, right);
    }

    private static void printResult(String label, Calculator calculator,
                                    BigDecimal left, BigDecimal right) {
        System.out.println(label + ": " + calculator.calculate(left, right).toPlainString());
    }
}
