package edu.gmu.cs321;

public class Address {
    private String street;
    private String city;
    private String zipcode;

    public Address(String street, String city, String zipcode) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }

    public String createAddress() {
        // Simulate failure return
        return "ERROR";
    }

    public boolean updateAddress(String newStreet, String newZip) {
        // failure return
        return false;
    }

    public Address getAddress(String id) {
        // failed lookup
        return null;
    }

    // Getters (for test verifications)
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }
}
