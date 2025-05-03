package edu.gmu.cs321;

public class Immigrant {
    private int id;  // <-- new field
    private String fullName;
    private String dob;
    private String ssn;
    private String countryOfOrigin;
    private String immigrationStatus;
    private Address address;

    public Immigrant(int id, String fullName, String dob, String ssn, String countryOfOrigin, String immigrationStatus, Address address) {
        this.id = id;
        this.fullName = fullName;
        this.dob = dob;
        this.ssn = ssn;
        this.countryOfOrigin = countryOfOrigin;
        this.immigrationStatus = immigrationStatus;
        this.address = address;
    }

    // Alternate constructor for when ID is unknown at time of creation
    public Immigrant(String fullName, String dob, String ssn, String countryOfOrigin, String immigrationStatus, Address address) {
        this(-1, fullName, dob, ssn, countryOfOrigin, immigrationStatus, address);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {  // used after saving to DB
        this.id = id;
    }

    public String getFullName() { return fullName; }
    public String getDateOfBirth() { return dob; }
    public String getSsn() { return ssn; }
    public String getCountryOfOrigin() { return countryOfOrigin; }
    public String getImmigrationStatus() { return immigrationStatus; }
    public Address getAddress() { return address; }

    public boolean isValid() {
        return this.ssn != null && !this.ssn.isEmpty();
    }

    public boolean updateStatus(String newStatus) {
        this.immigrationStatus = newStatus;
        // optional: update DB here if needed
        return true;
    }

    @Override
    public String toString() {
        return "Immigrant{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dob='" + dob + '\'' +
                ", ssn='" + ssn + '\'' +
                ", country='" + countryOfOrigin + '\'' +
                ", status='" + immigrationStatus + '\'' +
                '}';
    }
}

