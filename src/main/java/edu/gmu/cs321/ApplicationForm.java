package edu.gmu.cs321;
import java.util.ArrayList;
import java.util.List;

public class ApplicationForm {
    private String applicationID;
    private Immigrant immigrant;  // Replaces name, dob, ssn, status, etc.
    private List<Document> documents;
    private Double paymentAmount;

    public ApplicationForm(String applicationID, Immigrant immigrant, List<Document> documents, Double paymentAmount) {
        this.applicationID = applicationID;
        this.immigrant = immigrant;
        this.documents = documents != null ? documents : new ArrayList<>();
        this.paymentAmount = paymentAmount;
    }

    public Immigrant getImmigrant() {
        return immigrant;
    }

    public void addDocument(Document doc) {
        this.documents.add(doc);
    }

    public boolean isComplete() {
        return immigrant != null &&
               immigrant.getFullName() != null &&
               immigrant.getSsn() != null &&
               immigrant.getAddress() != null &&
               paymentAmount != null;
    }

    @Override
    public String toString() {
        return "ApplicationForm{" +
                "applicationID='" + applicationID + '\'' +
                ", immigrant=" + immigrant +
                ", documents=" + documents.size() +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
