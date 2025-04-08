package com.acme.oop.sales.domain.model.aggregates;

import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.CustomerId;
import com.acme.oop.shared.domain.model.valueobjects.Money;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a sales order in the Sales bounded context.
 * This aggregate root is responsible for managing the lifecycle of a sales order.
 * It contains a list of items and calculates the total amount of the order.
 *
 * @author Open Source Application Development Team
 */
@Getter
public class SalesOrder {
    private final UUID id;
    private final CustomerId customerId;
    private final LocalDateTime orderDate;
    private final List<SalesOrderItem> items;
    private Money totalAmount;

    /**
     * Constructs a SalesOrder object with the specified customer ID.
     * <p>
     *     * The order date is set to the current date and time.
     *     * The order ID is generated randomly.
     *     * The items list is initialized as an empty list.
     *     * The total amount is initialized to zero.
     * </p>
     *
     * @param customerId The ID of the customer placing the order.
     * @throws IllegalArgumentException if the customerId is null.
     */
    public SalesOrder(CustomerId customerId) {
        if (customerId == null) throw new IllegalArgumentException("CustomerId cannot be null");
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.orderDate = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.totalAmount = Money.zero();
    }

    /**
     * Adds an item to the sales order.
     * @param productId The ID of the product to be added. Must not be null.
     * @param quantity The quantity of the product to be added. Must be greater than zero.
     * @param unitPrice The unit price of the product. Must be greater than zero.
     */
    public void addItem(ProductId productId, int quantity, Money unitPrice) {
        if (productId == null) throw new IllegalArgumentException("ProductId cannot be null");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than zero");
        if (unitPrice == null || unitPrice.amount().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("UnitPrice must be greater than zero");
        SalesOrderItem item = new SalesOrderItem(productId, quantity, unitPrice);
        items.add(item);
        totalAmount = calculateTotalAmount();
    }

    /**
     * Calculates the total amount of the sales order.
     * @return The total amount of the sales order as a Money object.
     */
    private Money calculateTotalAmount() {
        return items.stream().map(SalesOrderItem::calculateSubtotal).reduce(Money.zero(), Money::add);
    }
}
