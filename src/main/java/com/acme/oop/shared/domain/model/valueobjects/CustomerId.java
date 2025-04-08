package com.acme.oop.shared.domain.model.valueobjects;

import java.util.UUID;

/**
 * Represents a unique identifier for a Customer across bounded contexts.
 * This class is immutable and thread-safe.
 *
 * @author Open Source Application Development Team
 */
public record CustomerId(UUID value) {

    /**
     * Constructs a CustomerId with the specified UUID.
     *
     * @param value the UUID representing the customer ID
     * @throws IllegalArgumentException if the value is null
     */
    public CustomerId {
        if (value == null) {
            throw new IllegalArgumentException("CustomerId cannot be null");
        }
    }

    /**
     * Constructs a CustomerId with a randomly generated UUID.
     */
    public CustomerId() {
        this(UUID.randomUUID());
    }
}
