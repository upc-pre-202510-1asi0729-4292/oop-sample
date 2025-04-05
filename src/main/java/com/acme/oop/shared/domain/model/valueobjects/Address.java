package com.acme.oop.shared.domain.model.valueobjects;

/**
 * Represents a physical address.
 * This value object is immutable and shared across bounded contexts.
 *
 * @author Open Source Application Development Team
 */
public record Address(String street,
                      String number,
                      String city,
                      String stateOrRegion,
                      String postalCode,
                      String country) {
    /**
     * Constructs an Address object with the specified parameters.
     * @param street            the street of the address, cannot be null or empty
     * @param number            the number of the address, cannot be null or empty
     * @param city              the city of the address, cannot be null or empty
     * @param stateOrRegion     the state or region of the address, can be null
     * @param postalCode        the postal code of the address, cannot be null or empty
     * @param country           the country of the address, cannot be null or empty
     */
    public Address {
        if (street == null || street.trim().isEmpty())
            throw new IllegalArgumentException("Street cannot be null or empty");
        if (number == null || number.trim().isEmpty())
            throw new IllegalArgumentException("Number cannot be null or empty");
        if (city == null || city.trim().isEmpty())
            throw new IllegalArgumentException("City cannot be null or empty");
        if (postalCode == null || postalCode.trim().isEmpty())
            throw new IllegalArgumentException("Postal code cannot be null or empty");
        if (country == null || country.trim().isEmpty())
            throw new IllegalArgumentException("Country cannot be null or empty");
    }

    /**
     * Constructs an Address object with the specified parameters.
     * This constructor does not include the state or region, which can be null.
     * @param street            the street of the address, cannot be null or empty
     * @param number            the number of the address, cannot be null or empty
     * @param city              the city of the address, cannot be null or empty
     * @param postalCode        the postal code of the address, cannot be null or empty
     * @param country           the country of the address, cannot be null or empty
     */
    public Address(String street,
                   String number,
                   String city,
                   String postalCode,
                   String country) {
        this(street, number, city, null, postalCode, country);
    }
}
