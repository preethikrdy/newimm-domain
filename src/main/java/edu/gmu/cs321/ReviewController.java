package edu.gmu.cs321;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReviewController {

    @FXML
    private Label userLabel;

    @FXML
    private TextField ssnField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextArea addressField;

    @FXML
    private TextField dobField;

    @FXML
    private TextArea commentsBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button editButton;

    @FXML
    private Button markButton;

    @FXML
    private Button viewButton;

    @FXML
    private Button verifyButton;

    @FXML
    public void initialize() {
        // Get the most recent application from ApplicationDataStore
        ApplicationDataStore dataStore = ApplicationDataStore.getInstance();
        var applications = dataStore.getApplications();
        if (applications.isEmpty()) {
            userLabel.setText("UserID: Unknown logged in");
            ssnField.setText("N/A");
            firstNameField.setText("N/A");
            lastNameField.setText("N/A");
            dobField.setText("N/A");
            addressField.setText("N/A");
            return;
        }

        // Use the most recent application
        ApplicationForm form = applications.get(applications.size() - 1);
        Immigrant immigrant = form.getImmigrant();
        Address address = immigrant.getAddress();

        // Split full name into first and last name
        String[] nameParts = immigrant.getFullName().split(" ", 2);
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        userLabel.setText("UserID: Reviewer logged in");
        ssnField.setText(immigrant.getSsn().isEmpty() ? "N/A" : immigrant.getSsn());
        firstNameField.setText(firstName.isEmpty() ? "N/A" : firstName);
        lastNameField.setText(lastName.isEmpty() ? "N/A" : lastName);
        dobField.setText(immigrant.getDateOfBirth().isEmpty() ? "N/A" : immigrant.getDateOfBirth());

        String addressText = (address.getStreet().isEmpty() ? "" : address.getStreet() + "\n") +
                            (address.getCity().isEmpty() ? "" : address.getCity() + ", ") +
                            (address.getState().isEmpty() ? "" : address.getState() + " ") +
                            (address.getZip().isEmpty() ? "" : address.getZip());
        addressField.setText(addressText.isEmpty() ? "N/A" : addressText);

        // Update the "View Documents" button to show documents
        viewButton.setOnAction(e -> {
            var docs = form.getDocuments();
            if (docs.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "No documents attached.").showAndWait();
            } else {
                String docList = docs.stream()
                                    .map(Document::getName)
                                    .reduce((a, b) -> a + "\n" + b)
                                    .orElse("");
                new Alert(Alert.AlertType.INFORMATION, "Documents:\n" + docList).showAndWait();
            }
        });
    }

    @FXML
    private void save() {
        System.out.println("Save button clicked");
    }

    @FXML
    private void edit() {
        System.out.println("Edit button clicked");
        ssnField.setEditable(true);
        firstNameField.setEditable(true);
        lastNameField.setEditable(true);
        addressField.setEditable(true);
        dobField.setEditable(true);
    }

    @FXML
    private void markForApproval() {
        // Update the status of the most recent application
        ApplicationDataStore dataStore = ApplicationDataStore.getInstance();
        var applications = dataStore.getApplications();
        if (!applications.isEmpty()) {
            ApplicationForm form = applications.get(applications.size() - 1);
            form.getImmigrant().updateStatus("Marked for Approval");
            System.out.println("Marked for Approval: " + form.getImmigrant().getFullName());
        }
    }

    @FXML
    private void viewDocuments() {
        // Handled in initialize via viewButton.setOnAction
    }

    @FXML
    private void verifyInformation() {
        System.out.println("Verify Information button clicked");
    }
}