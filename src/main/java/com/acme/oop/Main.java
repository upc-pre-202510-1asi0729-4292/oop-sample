package com.acme.oop;

import com.acme.oop.crm.domain.model.aggregates.Customer;
import com.acme.oop.sales.domain.model.aggregates.SalesOrder;
import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.Address;
import com.acme.oop.shared.domain.model.valueobjects.Money;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Main class to demonstrate the usage of the CRM and Sales modules.
 * This class serves as an entry point for the application.
 */
public class Main {
    public static void main(String[] args) {
        // CRM context
        Address address = new Address("Main St", " 123", "Springfield", "IL", "62704");
        Customer customer = new Customer("John Doe", " john.doe@gmail.com", address);
        Address anotherAddress = new Address("Main St", " 123", "Springfield", "IL", "62704");
        customer.updateContactInfo("john.doe@outlook.com", anotherAddress);

        // Sales context
        SalesOrder order = new SalesOrder(customer.getId());
        Money price = new Money(new BigDecimal("29.99"), Currency.getInstance("USD"));
        ProductId productId = new ProductId();
        order.addItem(productId, 2, price);
        // Display order
        System.out.println("Customer ID: " + order.getCustomerId());
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Order Total: " +
                order.getTotalAmount().amount() + " " +
                order.getTotalAmount().currency());
    }
}
