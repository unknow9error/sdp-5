package edu.aitu.strategy;

import java.math.BigDecimal;

/** Extension added without changing Calculator or the existing strategies. */
public final class MaximumStrategy implements Operation {
    @Override
    public BigDecimal apply(BigDecimal left, BigDecimal right) {
        return left.max(right);
    }
}
