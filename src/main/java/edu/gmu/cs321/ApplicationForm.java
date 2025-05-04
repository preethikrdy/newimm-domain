package edu.gmu.cs321;

import java.util.ArrayList;
import java.util.List;

public class ApplicationForm {
    private String applicationId;
    private Immigrant immigrant;
    private List<Document> documents;
    private Double processingFee;
    private boolean isComplete;

    public ApplicationForm(String applicationId, Immigrant immigrant, List<Document> documents, Double processingFee) {
        this.applicationId = applicationId;
        this.immigrant = immigrant;
        this.documents = documents != null ? new ArrayList<>(documents) : new ArrayList<>();
        this.processingFee = processingFee;
        this.isComplete = false;
    }

    public String getApplicationId() { return applicationId; }
    public Immigrant getImmigrant() { return immigrant; }
    public List<Document> getDocuments() { return new ArrayList<>(documents); }
    public Double getProcessingFee() { return processingFee; }
    public boolean isComplete() { return isComplete; }

    public void setComplete(boolean complete) { this.isComplete = complete; }

    public void addDocument(Document document) {
        documents.add(document);
    }
}