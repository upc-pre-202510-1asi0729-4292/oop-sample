package com.acme.oop.sales.domain.model.aggregates;

import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.Money;
import lombok.Getter;

/**
 * Represents an item in a sales order aggregate for the Sales bounded context.
 * Each item has a product ID, quantity, and unit price.
 */
@Getter
public class SalesOrderItem {
    private final ProductId productId;
    private final int quantity;
    private final Money unitPrice;

    /**
     * Constructs a SalesOrderItem with the specified product ID, quantity, and unit price.
     *
     * @param productId the ID of the product. Must not be null.
     * @param quantity  the quantity of the product. Must be greater than zero.
     * @param unitPrice the unit price of the product. Must be a positive amount.
     * @throws IllegalArgumentException if quantity is less than or equal to zero,
     *                                  unit price is null or negative,
     *                                  or product ID is null
     */
    public SalesOrderItem(ProductId productId, int quantity, Money unitPrice) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (unitPrice == null || unitPrice.amount() == null || unitPrice.amount().scale() < 0) {
            throw new IllegalArgumentException("Unit price must be a positive amount");
        }
        if (productId == null || productId.value() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    /**
     * Calculates the subtotal for this sales order item.
     *
     * @return the subtotal as a Money object.
     */
    public Money calculateSubtotal() {
        return unitPrice.multiply(quantity);
    }
}
