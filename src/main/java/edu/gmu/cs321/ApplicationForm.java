package edu.gmu.cs321;

import java.util.ArrayList;
import java.util.List;

public class ApplicationForm {
    private String applicationId;
    private Immigrant immigrant;
    private List<Document> documents;
    private Double paymentAmount;
    private String comments;

    public ApplicationForm(String applicationId, Immigrant immigrant, List<Document> documents, Double processingFee) {
        this.applicationId = applicationId;
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

    public String getFormId(){
        return this.applicationID;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
