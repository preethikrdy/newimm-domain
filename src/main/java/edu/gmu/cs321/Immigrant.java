package edu.gmu.cs321;

public class Immigrant {
    private String immigrantId;
    private String fullName;
    private String dateOfBirth;
    private String ssn;
    private String countryOfOrigin;
    private String immigrationStatus;
    private Address address;

    public Immigrant(String immigrantId, String fullName, String dateOfBirth, String ssn, String countryOfOrigin, String immigrationStatus, Address address) {
        this.immigrantId = immigrantId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.ssn = ssn;
        this.countryOfOrigin = countryOfOrigin;
        this.immigrationStatus = immigrationStatus;
        this.address = address;
    }

    public String getImmigrantId() { return immigrantId; }
    public String getFullName() { return fullName; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getSsn() { return ssn; }
    public String getCountryOfOrigin() { return countryOfOrigin; }
    public String getImmigrationStatus() { return immigrationStatus; }
    public Address getAddress() { return address; }

    public void updateStatus(String newStatus) {
        this.immigrationStatus = newStatus;
    }

    public String getID(){
        return id;
    }

    @Override
    public String toString() {
        return "Immigrant [ID: " + immigrantId + ", Name: " + fullName + ", Status: " + immigrationStatus + "]";
    }
}