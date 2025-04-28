package edu.gmu.cs321;

public class Immigrant {
    private String fullName;
    private String dob;
    private String ssn;
    private String countryOfOrigin;
    private String immigrationStatus;
    private Address address;

    public Immigrant(String fullName, String dob, String ssn, String countryOfOrigin, String immigrationStatus, Address address) {
        this.fullName = fullName;
        this.dob = dob;
        this.ssn = ssn;
        this.countryOfOrigin = countryOfOrigin;
        this.immigrationStatus = immigrationStatus;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDateOfBirth() {
        return dob;
    }

    public String getSsn() {
        return ssn;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public String getImmigrationStatus() {
        return immigrationStatus;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isValid() {
        return this.ssn != null && !this.ssn.isEmpty();
    }

    public static Immigrant getImmigrantBySSN(String ssn) {
        return null; // for future DB implementation
    }

    public boolean updateStatus(String newStatus) {
        return false; // for future DB update
    }

    @Override
    public String toString() {
        return "Immigrant{" +
                ", fullName='" + fullName + '\'' +
                ", dob='" + dob + '\'' +
                ", ssn='" + ssn + '\'' +
                ", country='" + countryOfOrigin + '\'' +
                ", status='" + immigrationStatus + '\'' +
                '}';
    }
}
