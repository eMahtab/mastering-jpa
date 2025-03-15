package net.mahtabalam;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String addressLine1;

    private String addressLine2;

    private String country;

    private String state;

    private String city;

    private String zipCode;

    public Address setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public Address setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public Address setCountry(String country) {
        this.country = country;
        return this;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public Address setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
