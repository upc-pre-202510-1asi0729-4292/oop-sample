package com.acme.oop.crm.domain.model.aggregates;

import com.acme.oop.shared.domain.model.valueobjects.Address;
import com.acme.oop.shared.domain.model.valueobjects.CustomerId;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Customer {
    private final CustomerId id;
    @Setter private String name;
    @Setter private String email;
    @Setter private Address address;

    public Customer(String name, String email, Address address) {
        validateName(name);
        validateEmail(email);
        validateAddress(address);
        this.id = new CustomerId();
        this.name = name;
        this.email = email;
        this.address = address;
    }

    private static void validateName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
    }

    public void updateContactInfo(String email, Address address) {
        validateEmail(email);
        validateAddress(address);
        this.email = email;
        this.address = address;
    }

    private static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("Email cannot be null or empty");
    }

    private static void validateAddress(Address address) {
        if (address == null)
            throw new IllegalArgumentException("Address cannot be null");
    }
}
