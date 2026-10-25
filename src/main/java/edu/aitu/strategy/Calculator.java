package edu.aitu.strategy;

import java.math.BigDecimal;
import java.util.Objects;

/** Context: delegates arithmetic and supports replacing the algorithm at runtime. */
public final class Calculator {
    private Operation operation;

    public Calculator(Operation operation) {
        setOperation(operation);
    }

    public void setOperation(Operation operation) {
        this.operation = Objects.requireNonNull(operation, "operation must not be null");
    }

    public BigDecimal calculate(BigDecimal left, BigDecimal right) {
        Objects.requireNonNull(left, "left operand must not be null");
        Objects.requireNonNull(right, "right operand must not be null");
        return operation.apply(left, right);
    }
}
