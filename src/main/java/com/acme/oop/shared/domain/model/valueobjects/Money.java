package com.acme.oop.shared.domain.model.valueobjects;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Represents a monetary amount with a currency.
 * This value object is immutable and shared across bounded contexts.
 *
 * @author Open Source Application Development Team
 */
public record Money(BigDecimal amount, Currency currency) {
    /**
     * Constructs a Money object with the specified amount and currency.
     *
     * @param amount   The monetary amount.
     * @param currency The currency of the amount.
     * @throws IllegalArgumentException if the amount is null, the currency is null,
     *                                  or the amount has more decimal places than allowed by the currency.
     */
    public Money {
        if (amount == null)
            throw new IllegalArgumentException("Amount cannot be null");
        if (currency == null)
            throw new IllegalArgumentException("Currency cannot be null");
        if (amount.scale() > currency.getDefaultFractionDigits())
            throw new IllegalArgumentException("Too many decimal places for currency " + currency.getCurrencyCode());
    }

    /**
     * Creates a Money object representing zero amount in the specified currency.
     *
     * @return A Money object representing zero amount in the specified currency.
     */
    public static Money zero() {
        return new Money(BigDecimal.ZERO, Currency.getInstance("USD"));
    }

    /**
     * Adds the specified Money object to this Money object.
     * @param other The Money object to add. Must have the same currency as this Money object.
     * @return A new Money object representing the sum of this Money object and the specified Money object.
     * @throws IllegalArgumentException if the specified Money object has a different currency.
     */
    public Money add(Money other) {
        if (!currency.equals(other.currency))
            throw new IllegalArgumentException("Cannot add Money with different currencies");
        return new Money(amount.add(other.amount), currency);
    }

    public Money multiply(int multiplier) {
        return new Money(amount.multiply(BigDecimal.valueOf(multiplier)), currency);
    }
}
