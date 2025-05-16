package ru.usov.followme.util;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class Money {
    private long units;
    private int nanos;

    public Money() {}

    public Money(long units, int nanos) {
        this.units = units;
        this.nanos = nanos;
    }

    public BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(units).add(BigDecimal.valueOf(nanos, 9));
    }

    public static Money from(BigDecimal value) {
        long units = value.longValue();
        int nanos = value.subtract(BigDecimal.valueOf(units)).movePointRight(9).intValue();
        return new Money(units, nanos);
    }
}
