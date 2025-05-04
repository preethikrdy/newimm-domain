package edu.gmu.cs321;

import com.cs321.Workflow;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.*;

public class GreenCardUpdateController {

    @FXML private StackPane rootPane;
    @FXML private VBox formView;
    @FXML private VBox confirmationView;
    @FXML private VBox saveDraftView;
    @FXML private VBox helpView;

    @FXML private Label loggedInLabel;
    @FXML private Label errorLabel;
    @FXML private Label fileNameLabel;
    @FXML private Label uploadStatusLabel;

    @FXML private ComboBox<String> languageComboBox;

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField birthdateField;
    @FXML private TextField ssNumberField;
    @FXML private TextField streetField;
    @FXML private TextField cityField;
    @FXML private TextField zipField;
    @FXML private TextField stateField;

    @FXML private Button saveDraftButton;
    @FXML private Button submitButton;
    @FXML private Button helpButton;
    @FXML private Button returnToFormButton;
    @FXML private Button closeHelpButton;
    @FXML private Button uploadButton;

    private List<Document> uploadedDocuments = new ArrayList<>();

    @FXML
    public void initialize() {
        languageComboBox.getItems().addAll("ENGLISH", "ITALIAN");
        languageComboBox.setValue("ENGLISH");
        loggedInLabel.setText("Logged in: Luigi");
        errorLabel.setVisible(false);
    }

    @FXML
    private void saveDraft() {
        errorLabel.setVisible(false);
        showView(saveDraftView);
    }

    @FXML
    private void returnToForm() {
        showView(formView);
    }

    @FXML
    private void closeHelp() {
        showView(formView);
    }

    @FXML
    private void showHelp() {
        showView(helpView);
    }

    private void showView(VBox target) {
        formView.setVisible(false);
        confirmationView.setVisible(false);
        saveDraftView.setVisible(false);
        helpView.setVisible(false);
        target.setVisible(true);
    }

    @FXML
    private void uploadDocument() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Document");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Files", "*.*"),
            new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(rootPane.getScene().getWindow());
        if (selectedFile != null) {
            Document doc = new Document(
                "DOC-" + System.currentTimeMillis(),
                selectedFile.getName(),
                selectedFile.getName().substring(selectedFile.getName().lastIndexOf('.') + 1).toUpperCase(),
                ssNumberField.getText(),
                "Uploaded"
            );
            uploadedDocuments.add(doc);
            fileNameLabel.setText("Selected File: " + selectedFile.getName());
            fileNameLabel.setVisible(true);
            uploadStatusLabel.setText("File uploaded");
            uploadStatusLabel.setVisible(true);
        }
    }

    @FXML
    private void submit() {
        // Validate fields
        List<String> missing = new ArrayList<>();
        if (firstNameField.getText().isEmpty()) missing.add("First Name");
        if (lastNameField.getText().isEmpty()) missing.add("Last Name");
        if (birthdateField.getText().isEmpty()) missing.add("Birthdate");
        if (ssNumberField.getText().isEmpty()) missing.add("SS Number");
        if (streetField.getText().isEmpty()) missing.add("Street");
        if (cityField.getText().isEmpty()) missing.add("City");
        if (zipField.getText().isEmpty()) missing.add("Zip");
        if (stateField.getText().isEmpty()) missing.add("State");

        if (!missing.isEmpty()) {
            errorLabel.setText("Missing fields: " + String.join(", ", missing));
            errorLabel.setVisible(true);
            return;
        }

        // Confirmation
        String fullName = firstNameField.getText() + " " + lastNameField.getText();
        String addressStr = streetField.getText() + ", " + cityField.getText() + ", " + stateField.getText() + " " + zipField.getText();
        String uploaded = uploadedDocuments.isEmpty() ? "None" : uploadedDocuments.get(0).getFileName();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirm Submission");
        dialog.setContentText("Confirm your details:\n\nName: " + fullName +
                "\nDOB: " + birthdateField.getText() +
                "\nSSN: " + ssNumberField.getText() +
                "\nAddress: " + addressStr +
                "\nDocument: " + uploaded);

        dialog.getDialogPane().getButtonTypes().addAll(
            new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE),
            new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE)
        );

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            try {
                Address address = new Address(
                    streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText()
                );

                Immigrant immigrant = new Immigrant(
                    fullName,
                    birthdateField.getText(),
                    ssNumberField.getText(),
                    "USA",
                    "Pending",
                    address
                );

                int immigrantId = FormDAO.saveImmigrant(immigrant);
                Workflow wf = new Workflow();
                int wfResult = wf.AddWFItem(immigrantId, "Review");
                wf.closeConnection();

                System.out.println("Immigrant ID: " + immigrantId + ", Workflow result: " + wfResult);

                uploadedDocuments.clear();
                fileNameLabel.setVisible(false);
                uploadStatusLabel.setVisible(false);
                errorLabel.setVisible(false);
                showView(confirmationView);
            } catch (Exception e) {
                e.printStackTrace();
                errorLabel.setText("Error during submission.");
                errorLabel.setVisible(true);
            }
        }
    }
}
