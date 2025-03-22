package edu.gmu.cs321;

public class Document {

    private String filePath;
    private String fileType;

    public String createDocument(String filePath, String fileType) {
        // failure return
        return "ERROR";
    }

    public boolean updateDocument(String docId, String newFilePath) {
        // failure return
        return false;
    }

    public Document getDocumentById(String docId) {
        // failed lookup
        return null;
    }

    // Getters and setters (if needed later)
    public String getFilePath() {
        return filePath;
    }

    public String getFileType() {
        return fileType;
    }
}
