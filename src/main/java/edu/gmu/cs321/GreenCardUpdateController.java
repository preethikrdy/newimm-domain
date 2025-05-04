package edu.gmu.cs321;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreenCardUpdateController {

    @FXML
    private StackPane rootPane;

    @FXML
    private VBox formView;

    @FXML
    private VBox confirmationView;

    @FXML
    private VBox saveDraftView;

    @FXML
    private VBox helpView;

    @FXML
    private Label loggedInLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField birthdateField;

    @FXML
    private TextField ssNumberField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField zipField;

    @FXML
    private TextField stateField;

    @FXML
    private Button saveDraftButton;

    @FXML
    private Button submitButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button returnToFormButton;

    @FXML
    private Button closeHelpButton;

    @FXML
    private Button uploadButton;

    @FXML
    private Label fileNameLabel;

    @FXML
    private Label uploadStatusLabel;

    private List<Document> uploadedDocuments = new ArrayList<>();

    @FXML
    public void initialize() {
        languageComboBox.getItems().addAll("ENGLISH", "ITALIAN");
        languageComboBox.setValue("ENGLISH");
        loggedInLabel.setText("Logged in: Data Entry User");
    }

    @FXML
    private void saveDraft() {
        errorLabel.setVisible(false);
        formView.setVisible(false);
        confirmationView.setVisible(false);
        helpView.setVisible(false);
        saveDraftView.setVisible(true);
    }

    @FXML
    private void returnToForm() {
        saveDraftView.setVisible(false);
        formView.setVisible(true);
    }

    @FXML
    private void uploadDocument() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Document to Upload");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Files", "*.*"),
            new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(rootPane.getScene().getWindow());
        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            String docId = "DOC-" + System.currentTimeMillis();
            String applicantId = "ID-" + (ssNumberField.getText().isEmpty() ? "UNKNOWN" : ssNumberField.getText());
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
            Document doc = new Document(docId, fileName, fileType, applicantId, "Uploaded");

            uploadedDocuments.add(doc);

            fileNameLabel.setText("Selected File: " + fileName);
            fileNameLabel.setVisible(true);

            uploadStatusLabel.setText("File uploaded");
            uploadStatusLabel.setVisible(true);
        }
    }

    @FXML
    private void submit() {
        // Collect form data
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String birthdate = birthdateField.getText();
        String ssNumber = ssNumberField.getText();
        String street = streetField.getText();
        String city = cityField.getText();
        String zip = zipField.getText();
        String state = stateField.getText();

        // Validate all fields
        ArrayList<String> missingFields = new ArrayList<>();
        if (firstName == null || firstName.trim().isEmpty()) {
            missingFields.add("First Name");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            missingFields.add("Last Name");
        }
        if (birthdate == null || birthdate.trim().isEmpty()) {
            missingFields.add("Birthdate");
        }
        if (ssNumber == null || ssNumber.trim().isEmpty()) {
            missingFields.add("SS Number");
        }
        if (street == null || street.trim().isEmpty()) {
            missingFields.add("Street");
        }
        if (city == null || city.trim().isEmpty()) {
            missingFields.add("City");
        }
        if (zip == null || zip.trim().isEmpty()) {
            missingFields.add("Zip");
        }
        if (state == null || state.trim().isEmpty()) {
            missingFields.add("State");
        }

        // If there are missing fields, show error message
        if (!missingFields.isEmpty()) {
            String errorMessage = "Please fill in the following required fields: " + String.join(", ", missingFields);
            errorLabel.setText(errorMessage);
            errorLabel.setVisible(true);
            return;
        }

        // If all fields are filled, show confirmation dialog
        String fullName = firstName + " " + lastName;
        String address = street + ", " + city + ", " + state + " " + zip;
        String uploadedDoc = uploadedDocuments.isEmpty() ? "None" : uploadedDocuments.get(0).getFileName();

        // Create the confirmation message
        String confirmationMessage = "Please confirm the following information is correct:\n\n" +
                "Name: " + fullName + "\n" +
                "Birthdate: " + birthdate + "\n" +
                "SS Number: " + ssNumber + "\n" +
                "Address: " + address + "\n" +
                "Uploaded Document: " + uploadedDoc + "\n\n" +
                "Is this information correct?";

        // Show confirmation dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirm Submission");
        dialog.setHeaderText("Confirm Your Information");
        dialog.setContentText(confirmationMessage);

        ButtonType confirmButton = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButton, cancelButton);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == confirmButton) {
            // Proceed with submission if user confirms
            errorLabel.setVisible(false);

            String loggedInUser = loggedInLabel.getText().replace("Logged in: ", "");

            // Create an Immigrant and ApplicationForm
            Address addressObj = new Address(street, city, state, zip);
            Immigrant immigrant = new Immigrant("ID-" + ssNumber, fullName, birthdate, ssNumber, "Unknown", "Pending", addressObj);
            ApplicationForm form = new ApplicationForm("APP-" + System.currentTimeMillis(), immigrant, uploadedDocuments, 100.0);

            // Store the application in ApplicationDataStore
            ApplicationDataStore.getInstance().addApplication(form);

            // Show the confirmation view
            formView.setVisible(false);
            saveDraftView.setVisible(false);
            helpView.setVisible(false);
            confirmationView.setVisible(true);

            // Reset the upload labels and document list
            fileNameLabel.setVisible(false);
            uploadStatusLabel.setVisible(false);
            uploadedDocuments.clear();
        } else {
            // If user cancels, return to the form (no action needed since formView is already visible)
            errorLabel.setVisible(false);
        }
    }

    @FXML
    private void showHelp() {
        errorLabel.setVisible(false);
        formView.setVisible(false);
        saveDraftView.setVisible(false);
        confirmationView.setVisible(false);
        helpView.setVisible(true);
    }

    @FXML
    private void closeHelp() {
        helpView.setVisible(false);
        formView.setVisible(true);
    }
}