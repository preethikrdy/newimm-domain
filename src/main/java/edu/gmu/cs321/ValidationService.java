package edu.gmu.cs321;

public class ValidationService {
    boolean validationStatus = false;
    String id;
      
    public boolean createValService() {
        return false; // Error code
    }

    public boolean updateValService(boolean validationStatus) {
        return this.validationStatus;
    }

    public ValidationService getValidationServceByID(String id) {
        return null; // Error code
    }
}
