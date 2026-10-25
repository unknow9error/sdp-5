package edu.aitu.strategy;

import java.math.BigDecimal;

/** Behavior checks that run with the JDK alone, without external dependencies. */
public final class CalculatorTest {
    private static int checks;

    private CalculatorTest() { }

    public static void main(String[] args) {
        Calculator calculator = new Calculator(new AddStrategy());
        assertValue("exact decimal addition", "0.3", calculator, "0.1", "0.2");
        calculator.setOperation(new SubtractStrategy());
        assertValue("runtime switch to subtraction", "-5", calculator, "2", "7");
        calculator.setOperation(new MultiplyStrategy());
        assertValue("runtime switch to multiplication", "-10", calculator, "-2.5", "4");
        assertValue("zero multiplication", "0", calculator, "99", "0");
        calculator.setOperation(new AddStrategy());
        assertValue("switch back to addition", "9", calculator, "2", "7");

        expectNull("null initial strategy", () -> new Calculator(null));
        expectNull("null replacement", () -> calculator.setOperation(null));
        assertValue("failed replacement preserves strategy", "9", calculator, "2", "7");
        expectNull("null left operand", () -> calculator.calculate(null, BigDecimal.ONE));
        expectNull("null right operand", () -> calculator.calculate(BigDecimal.ONE, null));

        Calculator custom = new Calculator((left, right) -> left.max(right));
        assertValue("independent strategy via contract", "8", custom, "3", "8");
        System.out.println("PASS: " + checks + " behavior checks");
    }

    private static void assertValue(String label, String expected, Calculator calculator,
                                    String left, String right) {
        BigDecimal actual = calculator.calculate(new BigDecimal(left), new BigDecimal(right));
        if (actual.compareTo(new BigDecimal(expected)) != 0) {
            throw new AssertionError(label + ": expected " + expected + ", got " + actual);
        }
        checks++;
        System.out.println("PASS " + label);
    }

    private static void expectNull(String label, Runnable action) {
        try {
            action.run();
        } catch (NullPointerException expected) {
            checks++;
            System.out.println("PASS " + label);
            return;
        }
        throw new AssertionError(label + ": expected NullPointerException");
    }
}
