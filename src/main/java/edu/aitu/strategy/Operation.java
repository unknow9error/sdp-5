package edu.aitu.strategy;

import java.math.BigDecimal;

/** The shared contract for interchangeable calculator algorithms. */
public interface Operation {
    BigDecimal apply(BigDecimal left, BigDecimal right);
}
