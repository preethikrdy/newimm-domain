package edu.gmu.cs321;

public class Document {

    private String documentId;
    private String fileName;
    private String associatedApplicantId;  // key reference
    private String uploadStatus;

    // Constructor
    public Document(String documentId, String fileName, String fileType, String associatedApplicantId, String uploadStatus) {
        this.documentId = documentId;
        this.fileName = fileName;
        this.associatedApplicantId = associatedApplicantId;
        this.uploadStatus = uploadStatus;
    }

    // returns null for now
    public static Document createDocument(String fileName, String fileType, String applicantId) {
        return null; // hardcoded to fail
    }

    // returns false
    public boolean updateFileName(String newFileName) {
        return false; // hardcoded to fail
    }

    // lookup method
    public static Document getDocumentById(String docId) {
        return null; // hardcoded to fail
    }

    // Getters
    public String getDocumentId() {
        return documentId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getAssociatedApplicantId() {
        return associatedApplicantId;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    // Add getName() to satisfy ReviewController
    public String getName() {
        return fileName;
    }

    // Optional setter 
    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentId='" + documentId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", applicantId='" + associatedApplicantId + '\'' +
                ", uploadStatus='" + uploadStatus + '\'' +
                '}';
    }
}