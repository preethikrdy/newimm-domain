package edu.gmu.cs321;

public class Immigrant {
<<<<<<< HEAD
    private String immigrantId;
=======
    private int id;  // <-- new field
>>>>>>> objects-javafx
    private String fullName;
    private String dateOfBirth;
    private String ssn;
    private String countryOfOrigin;
    private String immigrationStatus;
    private Address address;

<<<<<<< HEAD
    public Immigrant(String immigrantId, String fullName, String dateOfBirth, String ssn, String countryOfOrigin, String immigrationStatus, Address address) {
        this.immigrantId = immigrantId;
=======
    public Immigrant(int id, String fullName, String dob, String ssn, String countryOfOrigin, String immigrationStatus, Address address) {
        this.id = id;
>>>>>>> objects-javafx
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.ssn = ssn;
        this.countryOfOrigin = countryOfOrigin;
        this.immigrationStatus = immigrationStatus;
        this.address = address;
    }

<<<<<<< HEAD
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
=======
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
>>>>>>> objects-javafx
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "Immigrant [ID: " + immigrantId + ", Name: " + fullName + ", Status: " + immigrationStatus + "]";
    }
}
=======
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

>>>>>>> objects-javafx
