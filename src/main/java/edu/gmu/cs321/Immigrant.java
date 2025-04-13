package edu.gmu.cs321;

public class Immigrant {
    private String id;
    private String fullName;
    private String dob;
    private String ssn;
    private String countryOfOrigin;
    private String immigrationStatus;
    private Address address;

    public Immigrant(String id, String fullName, String dob, String ssn, String country, String status, Address address) {
        this.id = id;
        this.fullName = fullName;
        this.dob = dob;
        this.ssn = ssn;
        this.countryOfOrigin = country;
        this.immigrationStatus = status;
        this.address = address;
    }
      // hardcoded to fail
      public static Immigrant getImmigrantBySSN(String ssn) {
        return null; // will connect to MySQL later
    }

    // Simulated update method
    public boolean updateStatus(String newStatus) {
        return false; // hardcoded to fail
    }

    // change this later
    public boolean isValid() {
        return this.ssn != null && !this.ssn.isEmpty();
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

    @Override
    public String toString() {
        return "Immigrant{" +
                "Id=" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dob='" + dob + '\'' +
                ", ssn='" + ssn + '\'' +
                ", country='" + countryOfOrigin + '\'' +
                ", status='" + immigrationStatus + '\'' +
                '}';
    }
}
