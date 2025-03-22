package edu.gmu.cs321;
import java.time.LocalDate;

public class ApplcationForm {
    // Fields 
    String applicationID;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String ssn;
    Address address;
    String immigrationStatus;
    List documents;
    Double paymentAmount;
    String flag = "UNKNOWN"; // Tracks where in workflow form is in

    // Constructor
    private ApplcationForm(String applicationID, String firstName, String lastName, LocalDate dob, String ssn, Address address, String immigratiionStatus, List documents, Double paymentAmount){
        this.applicationID = applicationID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dob;
        this.ssn = ssn;
        this.address = address;
        this.immigrationStatus = immigratiionStatus;
        this.documents = documents;
        this.paymentAmount = paymentAmount;
    }

    // Adding a new instance
    public static ApplcationForm createNewAppForm(String applicationID, String firstName, String lastName, LocalDate dob, String ssn, Address address, String immigratiionStatus, List documents, Double paymentAmount){
        // Check firstname
        if(firstName == null || firstName.isEmpty()){return null;}
        // Check lastname
        if(lastName = null || lastName.isEmpty()){return null;}
        // Check ssn
        if(ssn = null || ssn.isEmpty()){return null;}
        // Check for valid date -- error checking could be improved
        //if(!dob.isValid()){return null;}
        // Address checking depends on how the class is implemented
        if(immigrationStatus = null || immigratiionStatus.isEmpty()){return null;}

        return new ApplcationForm(applicationID, firstName, lastName, null, ssn, null, immigratiionStatus, null, paymentAmount);
    }

    // Updating an instance
    public boolean updateFirstName(String firstName){
        if(firstName == null || firstName.isEmpty()){return false;}
        return true;
    }

    // Getting from a specific instance
    public String getFirstName(){
        return this.firstName;
    }

    // Class diagram methods -- to be implemented
    public void addDocument(Document doc){
        return null;
    }

    // Should be changed to work with flag.
    public boolean isComplete(){

    }

}

