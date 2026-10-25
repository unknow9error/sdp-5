package edu.aitu.strategy;

import java.math.BigDecimal;

public final class AddStrategy implements Operation {
    @Override
    public BigDecimal apply(BigDecimal left, BigDecimal right) {
        return left.add(right);
    }
}
