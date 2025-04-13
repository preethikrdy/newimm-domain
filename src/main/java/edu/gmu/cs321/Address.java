package edu.gmu.cs321;

public class Address {
    private String street;
    private String city;
    private String zipcode;
    private String state;

    public Address(String street, String city, String zipcode, String state) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.state = state;

    }

    public String createAddress() {
        //  failure return
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

    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + zipcode;
    }
}
