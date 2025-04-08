package com.acme.oop.sales.domain.model.valueobjects;

import java.util.UUID;

/**
 * ProductId is a value object that represents the unique identifier for a product in the Sales bounded context.
 * It is immutable and should be created using the constructor or factory methods.
 * <p>
 * This class is part of the domain model and should not be exposed to the application layer.
 * It is used to ensure that the product ID is always valid and consistent throughout the application.
 */
public record ProductId(UUID value) {
    /**
     * Creates a new ProductId with the given UUID.
     *
     * @param value the UUID of the product
     * @throws IllegalArgumentException if the value is null
     */
    public ProductId {
        if (value == null) throw new IllegalArgumentException("ProductId cannot be null");
    }

    /**
     * Creates a new ProductId with a random UUID.
     */
    public ProductId() {
        this(UUID.randomUUID());
    }
}
